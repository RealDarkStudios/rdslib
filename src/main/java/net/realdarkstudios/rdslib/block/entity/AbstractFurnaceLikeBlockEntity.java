package net.realdarkstudios.rdslib.block.entity;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.realdarkstudios.rdslib.RDSLib;
import net.realdarkstudios.rdslib.block.custom.AbstractFurnaceLikeBlock;
import net.realdarkstudios.rdslib.recipe.AbstractFurnaceLikeRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

@Deprecated(since = "4.0.3.0")
public abstract class AbstractFurnaceLikeBlockEntity extends BlockEntity implements WorldlyContainer, MenuProvider, StackedContentsCompatible {
    /**
     * This class is now deprecated.
     * Please use {@link AbstractFurnaceLikeBlockEntityV3} instead
     * @deprecated Since: 4.0.3.0
     */
    protected static final int SLOT_INPUT = 0;
    protected static final int SLOT_FUEL = 1;
    protected static final int SLOT_RESULT = 2;
    private static final int[] SLOTS_FOR_UP = new int[]{SLOT_INPUT};
    private static final int[] SLOTS_FOR_DOWN = new int[]{SLOT_RESULT, SLOT_FUEL};
    private static final int[] SLOTS_FOR_SIDES = new int[]{SLOT_FUEL};
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);

    protected final ContainerData data;
    private int cookingProgress = 0;
    private int maxCookingProgress = 22;
    private int litProgress = 0;
    private int maxLitProgress = 14;
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    private final RecipeType<? extends AbstractFurnaceLikeRecipe> recipeType;
    protected final RecipeManager.CachedCheck<Container, ? extends AbstractFurnaceLikeRecipe> quickCheck;

    public AbstractFurnaceLikeBlockEntity(BlockEntityType<?> pType, BlockPos pos, BlockState state, RecipeType<? extends AbstractFurnaceLikeRecipe> pRecipeType) {
        super(pType, pos, state);
        this.recipeType = pRecipeType;
        this.quickCheck = RecipeManager.createCheck((RecipeType)pRecipeType);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> AbstractFurnaceLikeBlockEntity.this.cookingProgress;
                    case 1 -> AbstractFurnaceLikeBlockEntity.this.maxCookingProgress;
                    case 2 -> AbstractFurnaceLikeBlockEntity.this.litProgress;
                    case 3 -> AbstractFurnaceLikeBlockEntity.this.maxLitProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> AbstractFurnaceLikeBlockEntity.this.cookingProgress = value;
                    case 1 -> AbstractFurnaceLikeBlockEntity.this.maxCookingProgress = value;
                    case 2 -> AbstractFurnaceLikeBlockEntity.this.litProgress = value;
                    case 3 -> AbstractFurnaceLikeBlockEntity.this.maxLitProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    /**
     * Check if the item is never a furnace fuel (by definition, {@code ItemTags.NON_FLAMMABLE_WOOD} is never a fuel
     */
    private static boolean isNeverAFurnaceFuel(Item pItem) {
        return pItem.builtInRegistryHolder().is(ItemTags.NON_FLAMMABLE_WOOD);
    }

    LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    /**
     * Gets the capability for this block
     */
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (!this.remove && side != null && cap == ForgeCapabilities.ITEM_HANDLER) {
            if (side == Direction.UP)
                return handlers[0].cast();
            else if (side == Direction.DOWN)
                return handlers[1].cast();
            else
                return handlers[2].cast();
        }

        return super.getCapability(cap, side);
    }

    /**
     * Pre-sets the lazyItemHandler
     */
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    /**
     * Invalidates the capabilities
     */
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        for (int x = 0; x < handlers.length; x++)
            handlers[x].invalidate();
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
    }

    /**
     * Saves NBT data to this block
     */
    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("cooking_progress", this.cookingProgress);
        nbt.putInt("lit_progress", this.litProgress);

        super.saveAdditional(nbt);
    }

    /**
     * Loads NBT data from this block
     */
    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            items.set(i, itemHandler.getStackInSlot(i));
        }
        cookingProgress = nbt.getInt("cooking_progress");
        litProgress = nbt.getInt("lit_progress");
    }

    /**
     * Drops the contents of this block
     */
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    /**
     * Is the block fueled
     */
    private boolean isLit() {
        return this.litProgress > 0;
    }

    /**
     * Runs every tick
     */
    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, AbstractFurnaceLikeBlockEntity pBlockEntity) {
        boolean isLit = pBlockEntity.isLit();
        boolean finishCooking = false;

        if (pBlockEntity.isLit()) {
            pBlockEntity.litProgress--;
        }

        SimpleContainer inventory = new SimpleContainer(pBlockEntity.itemHandler.getSlots());
        for (int i = 0; i < pBlockEntity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, pBlockEntity.itemHandler.getStackInSlot(i));
        }

        ItemStack ingredient = pBlockEntity.itemHandler.getStackInSlot(1);
        boolean fuelNotEmpty = !pBlockEntity.itemHandler.getStackInSlot(0).isEmpty();
        boolean ingredientNotEmpty = !ingredient.isEmpty();
        if (pBlockEntity.isLit() || ingredientNotEmpty && fuelNotEmpty) {
            Optional<? extends AbstractFurnaceLikeRecipe> recipe;
            if (fuelNotEmpty) {
                recipe = pLevel.getRecipeManager().getRecipeFor(pBlockEntity.recipeType, inventory, pLevel);
            } else {
                recipe = Optional.empty();
            }

            int i = 64;
            if (!pBlockEntity.isLit() && recipe.isPresent() && pBlockEntity.canBurn(pLevel.registryAccess(), recipe.get(), pBlockEntity.itemHandler, i)) {
                pBlockEntity.litProgress = pBlockEntity.getBurnDuration(ingredient);
                if (pBlockEntity.isLit()) {
                    finishCooking = true;
                    setChanged(pLevel, pPos, pState);
                    if (ingredient.hasCraftingRemainingItem())
                        pBlockEntity.itemHandler.setStackInSlot(1, ingredient.getCraftingRemainingItem());
                    else
                    if (ingredientNotEmpty) {
                        Item item = ingredient.getItem();
                        ingredient.shrink(1);
                        if (ingredient.isEmpty()) {
                            pBlockEntity.itemHandler.setStackInSlot(1, ingredient.getCraftingRemainingItem());
                        }
                    }
                }
            }

            if (pBlockEntity.isLit() && recipe.isPresent() && pBlockEntity.canBurn(pLevel.registryAccess(), recipe.get(), pBlockEntity.itemHandler, i)) {
                ++pBlockEntity.cookingProgress;
                setChanged(pLevel, pPos, pState);
                if (pBlockEntity.cookingProgress == pBlockEntity.maxCookingProgress) {
                    pBlockEntity.cookingProgress = 0;
                    pBlockEntity.maxCookingProgress = getTotalCookTime(recipe.get());
                    if (pBlockEntity.burn(pLevel.registryAccess(), recipe.get(), pBlockEntity.itemHandler, i)) {
                        pBlockEntity.setRecipeUsed(recipe.get());
                    }

                    finishCooking = true;
                }
            } else {
                pBlockEntity.cookingProgress = 0;
            }
        } else if (!pBlockEntity.isLit() && pBlockEntity.cookingProgress > 0) {
            pBlockEntity.cookingProgress = Mth.clamp(pBlockEntity.cookingProgress - 2, 0, pBlockEntity.maxCookingProgress);
            setChanged(pLevel, pPos, pState);
        }

        if (isLit != pBlockEntity.isLit()) {
            finishCooking = true;
            setChanged(pLevel, pPos, pState);
            pState = pState.setValue(AbstractFurnaceLikeBlock.LIT, pBlockEntity.isLit());
            pLevel.setBlock(pPos, pState, 3);
        }
    }

    /**
     * Determines if the furnace can smelt the next item
     */
    private boolean canBurn(RegistryAccess registryAccess, AbstractFurnaceLikeRecipe recipe, ItemStackHandler items, int currentItems) {
        if (!items.getStackInSlot(0).isEmpty() && recipe != null) {
            ItemStack recipeResult = recipe.assemble(new SimpleContainer(3), registryAccess);
            if (recipeResult.isEmpty()) {
                return false;
            } else {
                ItemStack resultSlot = items.getStackInSlot(2);
                if (resultSlot.isEmpty()) {
                    return true;
                } else if (!ItemStack.isSameItem(resultSlot, recipeResult)) {
                    return false;
                } else if (resultSlot.getCount() + recipeResult.getCount() <= currentItems && resultSlot.getCount() + recipeResult.getCount() <= resultSlot.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
                    return true;
                } else {
                    return resultSlot.getCount() + recipeResult.getCount() <= recipeResult.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Burns the next item
     */
    private boolean burn(RegistryAccess registryAccess, AbstractFurnaceLikeRecipe recipe, ItemStackHandler items, int currentItems) {
        if (recipe != null && this.canBurn(registryAccess, recipe, items, currentItems)) {
            ItemStack ingredientSlot = items.getStackInSlot(0);
            ItemStack recipeResult = recipe.assemble(new SimpleContainer(3), registryAccess);
            ItemStack resultSlot = items.getStackInSlot(2);
            if (resultSlot.isEmpty()) {
                items.setStackInSlot(2, recipeResult.copy());
            } else if (resultSlot.is(recipeResult.getItem())) {
                resultSlot.grow(recipeResult.getCount());
            }

            if (ingredientSlot.is(Blocks.WET_SPONGE.asItem()) && !items.getStackInSlot(1).isEmpty() && items.getStackInSlot(1).is(Items.BUCKET)) {
                items.setStackInSlot(1, new ItemStack(Items.WATER_BUCKET));
            }

            ingredientSlot.shrink(1);
            return true;
        } else {
            return false;
        }
    }

    protected int getBurnDuration(ItemStack pFuel) {
        if (pFuel.isEmpty()) {
            return 0;
        } else {
            Item item = pFuel.getItem();
            return net.minecraftforge.common.ForgeHooks.getBurnTime(pFuel, recipeType);
        }
    }

    /**
     * Gets the cooking time based off the recipe
     */
    private static int getTotalCookTime(AbstractFurnaceLikeRecipe recipe) {
        return recipe.getCookingTime();
    }

    /**
     * Gets the cooking time based off the level and blockEntity
     */
    private static int getTotalCookTime(Level pLevel, AbstractFurnaceLikeBlockEntity pBlockEntity) {
        return pBlockEntity.quickCheck.getRecipeFor(pBlockEntity, pLevel).map(AbstractFurnaceLikeRecipe::getCookingTime).orElse(50);
    }

    /**
     * Marks the recipe as used (this is used for awarding experience)
     */
    public void setRecipeUsed(@Nullable Recipe<?> pRecipe) {
        if (pRecipe != null) {
            ResourceLocation resourcelocation = pRecipe.getId();
            this.recipesUsed.addTo(resourcelocation, 1);
        }

    }

    /**
     * Returns null
     */
    @Nullable
    public Recipe<?> getRecipeUsed() {
        return null;
    }

    public void awardUsedRecipes(Player p_58396_, List<ItemStack> p_282202_) {
    }

    /**
     * Gets slots for hopper (seemingly broken, though)
     */
    public int[] getSlotsForFace(Direction pSide) {
        if (pSide == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        } else {
            return pSide == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
        }
    }

    /**
     * Returns {@code true} if automation can insert the given item in the given slot from the given side.
     */
    public boolean canPlaceItemThroughFace(int pIndex, ItemStack pItemStack, @Nullable Direction pDirection) {
        RDSLib.LOGGER.info(String.valueOf(pIndex));
        return this.canPlaceItem(pIndex, pItemStack);
    }

    /**
     * Returns {@code true} if automation can extract the given item in the given slot from the given side.
     */
    public boolean canTakeItemThroughFace(int pIndex, ItemStack pStack, Direction pDirection) {
        if (pDirection == Direction.DOWN && pIndex == SLOT_FUEL) {
            RDSLib.LOGGER.info("Slot tested: " + pIndex);
            return pStack.is(Items.WATER_BUCKET) || pStack.is(Items.BUCKET);
        } else if (pDirection == Direction.DOWN && pIndex == SLOT_INPUT) {
            RDSLib.LOGGER.info("Slot tested: " + pIndex);
            return false;
        } else {
            RDSLib.LOGGER.info("Slot tested: " + pIndex);
            return true;
        }
    }

    /**
     * Gets the inventory size
     */
    public int getContainerSize() {
        return this.itemHandler.getSlots();
    }

    /**
     * Returns true if the blockEntity is empty
     */
    public boolean isEmpty() {
        for(int i = 0; i < getContainerSize(); i++) {
            if (!itemHandler.getStackInSlot(i).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the stack in the given slot.
     */
    public ItemStack getItem(int pIndex) {
        return this.itemHandler.getStackInSlot(pIndex);
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    public ItemStack removeItem(int pIndex, int pCount) {
        return this.itemHandler.extractItem(pIndex, pCount, false);
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    public ItemStack removeItemNoUpdate(int pIndex) {
        return this.itemHandler.extractItem(pIndex, 1, true);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setItem(int pIndex, ItemStack pStack) {
        ItemStack itemstack = this.itemHandler.getStackInSlot(pIndex);
        boolean flag = !pStack.isEmpty() && ItemStack.isSameItemSameTags(itemstack, pStack);
        this.itemHandler.setStackInSlot(pIndex, pStack);
        if (pStack.getCount() > 64) {
            pStack.setCount(64);
        }

        if (pIndex == SLOT_INPUT && !flag) {
            this.maxCookingProgress = getTotalCookTime(this.level, this);
            this.cookingProgress = 0;
            this.setChanged();
        }

    }

    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     */
    public boolean stillValid(Player pPlayer) {
        return Container.stillValidBlockEntity(this, pPlayer);
    }

    /**
     * Returns {@code true} if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     * For guis use Slot.isItemValid
     */
    public boolean canPlaceItem(int pIndex, ItemStack pStack) {
        if (pIndex == SLOT_RESULT) {
            return false;
        } else if (pIndex != SLOT_FUEL) {
            return true;
        } else {
            ItemStack itemstack = this.itemHandler.getStackInSlot(1);
            return net.minecraftforge.common.ForgeHooks.getBurnTime(pStack, this.recipeType) > 0 || pStack.is(Items.BUCKET) && !itemstack.is(Items.BUCKET);
        }
    }

    /**
     * Clears the block entity
     */
    public void clearContent() {
        for (int i = 0; i < this.itemHandler.getSlots(); i++) {
            this.itemHandler.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    /**
     * Awards recipes
     */
    public void awardUsedRecipesAndPopExperience(ServerPlayer pPlayer) {
        List<Recipe<?>> list = this.getRecipesToAwardAndPopExperience(pPlayer.serverLevel(), pPlayer.position());
        pPlayer.awardRecipes(list);
    }

    /**
     * Gets a list of smelted recipes and pops experience
     */
    public List<Recipe<?>> getRecipesToAwardAndPopExperience(ServerLevel pLevel, Vec3 pPopVec) {
        List<Recipe<?>> list = Lists.newArrayList();

        for(Object2IntMap.Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
            pLevel.getRecipeManager().byKey(entry.getKey()).ifPresent((recipe) -> {
                list.add(recipe);
                createExperience(pLevel, pPopVec, entry.getIntValue(), ((AbstractFurnaceLikeRecipe) recipe).getExperience());
            });
        }

        return list;
    }

    /**
     * Creates experience orbs for player to pick up
     */
    private static void createExperience(ServerLevel pLevel, Vec3 pPopVec, int pRecipeIndex, float pExperience) {
        int i = Mth.floor((float)pRecipeIndex * pExperience);
        float f = Mth.frac((float)pRecipeIndex * pExperience);
        if (f != 0.0F && Math.random() < (double)f) {
            ++i;
        }

        ExperienceOrb.award(pLevel, pPopVec, i);
    }

    public void fillStackedContents(StackedContents pHelper) {
        for(ItemStack pStack : this.items) {
            pHelper.accountStack(pStack);
        }
    }
}
