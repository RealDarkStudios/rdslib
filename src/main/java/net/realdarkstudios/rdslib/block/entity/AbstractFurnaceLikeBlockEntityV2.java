package net.realdarkstudios.rdslib.block.entity;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerData;
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
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.realdarkstudios.rdslib.block.AbstractFurnaceLikeBlock;
import net.realdarkstudios.rdslib.block.AbstractFurnaceLikeBlockV2;
import net.realdarkstudios.rdslib.recipe.AbstractFurnaceLikeRecipe;
import net.realdarkstudios.rdslib.util.inventory.InventoryDirectionWrapper;
import net.realdarkstudios.rdslib.util.inventory.InventoryDirectonEntry;
import net.realdarkstudios.rdslib.util.inventory.WrappedHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractFurnaceLikeBlockEntityV2 extends BlockEntity implements WorldlyContainer, MenuProvider {
    //protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> level.getRecipeManager().getRecipeFor(recipeType, new SimpleContainer(stack), level).isPresent();
                case 1 -> isFuel(stack);
                case 2 -> false;
                default -> isItemValid(slot, stack);
            };
        }
    };;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;
    private final int defaultCookingTime;
    private int cookingProgress = 0;
    private int maxCookingProgress;
    private int litProgress = 0;
    private int maxLitProgress = 14;
    private static final int INPUT_SLOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            new InventoryDirectionWrapper(itemHandler,
                    new InventoryDirectonEntry(Direction.DOWN, OUTPUT_SLOT, false),
                    new InventoryDirectonEntry(Direction.DOWN, FUEL_SLOT, false),
                    new InventoryDirectonEntry(Direction.SOUTH, FUEL_SLOT, true),
                    new InventoryDirectonEntry(Direction.NORTH, FUEL_SLOT, true),
                    new InventoryDirectonEntry(Direction.WEST, FUEL_SLOT, true),
                    new InventoryDirectonEntry(Direction.EAST, FUEL_SLOT, true),
                    new InventoryDirectonEntry(Direction.UP, INPUT_SLOT, true)).directionsMap;
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    protected final RecipeType<? extends AbstractFurnaceLikeRecipe> recipeType;
    private final RecipeManager.CachedCheck<Container, ? extends AbstractFurnaceLikeRecipe> quickCheck;

    public AbstractFurnaceLikeBlockEntityV2(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, RecipeType<? extends AbstractFurnaceLikeRecipe> pRecipeType, int defaultCookingTime) {
        super(pType, pPos, pBlockState);
        this.recipeType = pRecipeType;
        this.quickCheck = RecipeManager.createCheck((RecipeType)pRecipeType);
        this.defaultCookingTime = defaultCookingTime;
        this.maxCookingProgress = defaultCookingTime;
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch(pIndex) {
                    case 0 -> AbstractFurnaceLikeBlockEntityV2.this.cookingProgress;
                    case 1 -> AbstractFurnaceLikeBlockEntityV2.this.maxCookingProgress;
                    case 2 -> AbstractFurnaceLikeBlockEntityV2.this.litProgress;
                    case 3 -> AbstractFurnaceLikeBlockEntityV2.this.maxLitProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch(pIndex) {
                    case 0 -> AbstractFurnaceLikeBlockEntityV2.this.cookingProgress = pValue;
                    case 1 -> AbstractFurnaceLikeBlockEntityV2.this.maxCookingProgress = pValue;
                    case 2 -> AbstractFurnaceLikeBlockEntityV2.this.litProgress = pValue;
                    case 3 -> AbstractFurnaceLikeBlockEntityV2.this.maxLitProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if (side == null) {
                return lazyItemHandler.cast();
            }

            if (directionWrappedHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(AbstractFurnaceLikeBlockV2.FACING);

                if (side == Direction.DOWN || side == Direction.UP) {
                    return directionWrappedHandlerMap.get(side).cast();
                }

                return switch (localDir) {
                  default -> directionWrappedHandlerMap.get(side.getOpposite()).cast();
                  case EAST -> directionWrappedHandlerMap.get(side.getClockWise()).cast();
                  case SOUTH -> directionWrappedHandlerMap.get(side).cast();
                  case WEST -> directionWrappedHandlerMap.get(side.getCounterClockWise()).cast();
                };
            }
        }

        return super.getCapability(cap);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("furnaceEntity.cookingProgress", this.cookingProgress);
        pTag.putInt("furnaceEntity.litProgress", this.litProgress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        this.cookingProgress = pTag.getInt("furnaceEntity.cookingProgress");
        this.litProgress = pTag.getInt("furnaceEntity.litProgress");
    }


    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    private boolean isLit() {
        return this.litProgress > 0;
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, AbstractFurnaceLikeBlockEntityV2 pEntity) {
        boolean isLit = pEntity.isLit();
        boolean finishCooking = false;

        if (pEntity.isLit()) {
            pEntity.litProgress--;
        }

        SimpleContainer inventory = new SimpleContainer(pEntity.itemHandler.getSlots());
        for (int i = 0; i < pEntity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, pEntity.itemHandler.getStackInSlot(i));
        }

        ItemStack ingredient = pEntity.itemHandler.getStackInSlot(1);
        boolean fuelNotEmpty = !pEntity.itemHandler.getStackInSlot(0).isEmpty();
        boolean ingredientNotEmpty = !ingredient.isEmpty();
        if (pEntity.isLit() || ingredientNotEmpty && fuelNotEmpty) {
            Optional<? extends AbstractFurnaceLikeRecipe> recipe;
            if (fuelNotEmpty) {
                recipe = pLevel.getRecipeManager().getRecipeFor(pEntity.recipeType, inventory, pLevel);
            } else {
                recipe = Optional.empty();
            }

            recipe.ifPresent(abstractFurnaceLikeRecipe -> pEntity.maxCookingProgress = getTotalCookTime(abstractFurnaceLikeRecipe));

            int i = 64;
            if (!pEntity.isLit() && recipe.isPresent() && pEntity.canBurn(pLevel.registryAccess(), recipe.get(), pEntity.itemHandler, i)) {
                pEntity.litProgress = pEntity.getBurnDuration(ingredient);
                if (pEntity.isLit()) {
                    finishCooking = true;
                    setChanged(pLevel, pPos, pState);
                    if (ingredient.hasCraftingRemainingItem())
                        pEntity.itemHandler.setStackInSlot(1, ingredient.getCraftingRemainingItem());
                    else
                    if (ingredientNotEmpty) {
                        Item item = ingredient.getItem();
                        ingredient.shrink(1);
                        if (ingredient.isEmpty()) {
                            pEntity.itemHandler.setStackInSlot(1, ingredient.getCraftingRemainingItem());
                        }
                    }
                }
            }

            if (pEntity.isLit() && recipe.isPresent() && pEntity.canBurn(pLevel.registryAccess(), recipe.get(), pEntity.itemHandler, i)) {
                ++pEntity.cookingProgress;
                setChanged(pLevel, pPos, pState);
                if (pEntity.cookingProgress == pEntity.maxCookingProgress) {
                    pEntity.cookingProgress = 0;
                    if (pEntity.burn(pLevel.registryAccess(), recipe.get(), pEntity.itemHandler, i)) {
                        pEntity.setRecipeUsed(recipe.get());
                    }

                    finishCooking = true;
                }
            } else {
                pEntity.cookingProgress = 0;
            }
        } else if (!pEntity.isLit() && pEntity.cookingProgress > 0) {
            pEntity.cookingProgress = Mth.clamp(pEntity.cookingProgress - 2, 0, pEntity.maxCookingProgress);
            setChanged(pLevel, pPos, pState);
        }

        if (isLit != pEntity.isLit()) {
            finishCooking = true;
            setChanged(pLevel, pPos, pState);
            pState = pState.setValue(AbstractFurnaceLikeBlock.LIT, pEntity.isLit());
            pLevel.setBlock(pPos, pState, 3);
        }
    }

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

    public boolean isFuel(ItemStack pStack) {
        return ForgeHooks.getBurnTime(pStack, null) > 0;
    }

    private int getBurnDuration(ItemStack pFuel) {
        if (pFuel.isEmpty() || pFuel.is(Items.BUCKET)) {
            return 0;
        } else {
            return ForgeHooks.getBurnTime(pFuel, recipeType);
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
    private static int getTotalCookTime(Level pLevel, AbstractFurnaceLikeBlockEntityV2 pBlockEntity) {
        return pBlockEntity.quickCheck.getRecipeFor(pBlockEntity, pLevel).map(AbstractFurnaceLikeRecipe::getCookingTime).orElse(pBlockEntity.defaultCookingTime);
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

    @Override
    public int[] getSlotsForFace(Direction pSide) {
        return switch (pSide) {
            case UP -> new int[]{INPUT_SLOT};
            case DOWN -> new int[]{OUTPUT_SLOT, FUEL_SLOT};
            default -> new int[]{FUEL_SLOT};
        };
    }

    @Override
    public boolean canTakeItemThroughFace(int pIndex, ItemStack pStack, Direction pDirection) {
        if (pDirection == Direction.DOWN && pIndex == FUEL_SLOT) {
            return pStack.is(Items.WATER_BUCKET) || pStack.is(Items.BUCKET);
        } else {
            return pIndex == OUTPUT_SLOT;
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int pIndex, ItemStack pItemStack, @Nullable Direction pDirection) {
        return switch(pDirection) {
            case UP -> canInsertIntoSlot(pIndex, INPUT_SLOT, pItemStack);
            case DOWN -> false;
            default -> canPlaceItem(pIndex, pItemStack);
        };
    }

    @Override
    public boolean canPlaceItem(int pIndex, ItemStack pStack) {
        return canInsertIntoSlot(pIndex, INPUT_SLOT, pStack) || canInsertIntoSlot(pIndex, FUEL_SLOT, pStack);
    }

    protected boolean canInsertIntoSlot(int slotIndex, int targetIndex, ItemStack stack) {
        return slotIndex == targetIndex && itemHandler.getStackInSlot(targetIndex).getCount() < itemHandler.getSlotLimit(targetIndex) && itemHandler.isItemValid(targetIndex, stack);
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

        if (pIndex == INPUT_SLOT && !flag) {
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
        for(int i = 0; i < this.itemHandler.getSlots(); i++) {
            pHelper.accountStack(this.itemHandler.getStackInSlot(i));
        }
    }
}
