package net.realdarkstudios.rdslib.screen;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import net.realdarkstudios.rdslib.block.entity.AbstractFurnaceLikeBlockEntityV2;
import net.realdarkstudios.rdslib.screen.slot.CustomFurnaceFuelSlotV2;
import net.realdarkstudios.rdslib.screen.slot.CustomFurnaceOutputSlot;

@Deprecated(since = "4.0.3.0")
public abstract class AbstractFurnaceLikeMenuV2 extends AbstractContainerMenu {
    /**
     * This class is now deprecated.
     * Please use {@link } instead
     * @deprecated Since: 4.0.3.0
     */
    public final AbstractFurnaceLikeBlockEntityV2 blockEntity;
    private final Level level;
    private final ContainerData data;

    public AbstractFurnaceLikeMenuV2(MenuType<?> menuType, int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(menuType, pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(4));
    }

    public AbstractFurnaceLikeMenuV2(MenuType<?> menuType, int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(menuType, pContainerId);
        checkContainerSize(inv, 3);
        blockEntity = ((AbstractFurnaceLikeBlockEntityV2) entity);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            // Corresponds to the slot ABOVE the fuel indicator ( the input slot )
            this.addSlot(new SlotItemHandler(iItemHandler, 0, 56, 17));
            // Corresponds to the slot BELOW the fuel indicator ( the fuel slot )
            this.addSlot(new CustomFurnaceFuelSlotV2(iItemHandler, this, 1, 56, 53));
            // Corresponds to the slot RIGHT of the arrow ( the result slot )
            this.addSlot(new CustomFurnaceOutputSlot(iItemHandler, inv.player, 2, 116, 35));
        });

        addDataSlots(data);
    }

    public boolean isCooking() {
        return getCookingProgress() > 0;
    }
    public boolean isLit() {
        return getLitProgress() > 0;
    }

    public int getCookingProgress() {
        return data.get(0);
    }

    public int getLitProgress() {
        return data.get(2);
    }

    /**
     * Get the cooking progress scaled to the progress arrow
     */
    public int getScaledCookingProgress() {
        int progress = getCookingProgress();
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 22; // This is the height in pixels of your arrow

        return (maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0) % maxProgress;
    }

    /**
     * Get the lit progress scaled to the fuel indicator
     */
    public int getScaledLitProgress() {
        int progress = getLitProgress();
        int maxProgress = this.data.get(3);  // Max Progress
        int fuelIndicatorSize = 14; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * fuelIndicatorSize / maxProgress : 0;
    }

    public boolean canSmelt(ItemStack pStack) {
        return this.level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(pStack), this.level).isPresent();
    }
    public boolean isFuel(ItemStack pStack) {
        return ForgeHooks.getBurnTime(pStack, RecipeType.SMELTING) > 0;
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the BE's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int BE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int BE_INVENTORY_SLOT_COUNT = 3;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (canSmelt(sourceStack)) {
                if (!moveItemStackTo(sourceStack, BE_INVENTORY_FIRST_SLOT_INDEX, BE_INVENTORY_FIRST_SLOT_INDEX + 1, false)) {
                    return ItemStack.EMPTY;  // EMPTY_ITEM
                }
            } else if (isFuel(sourceStack)) {
                if (!moveItemStackTo(sourceStack, BE_INVENTORY_FIRST_SLOT_INDEX + 1, BE_INVENTORY_FIRST_SLOT_INDEX + 2, false)) {
                    return ItemStack.EMPTY;  // EMPTY_ITEM
                }
            } else if (pIndex >= VANILLA_FIRST_SLOT_INDEX + HOTBAR_SLOT_COUNT) {
                if (!this.moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + HOTBAR_SLOT_COUNT, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (pIndex >= VANILLA_FIRST_SLOT_INDEX && !this.moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX + HOTBAR_SLOT_COUNT, VANILLA_SLOT_COUNT + BE_INVENTORY_SLOT_COUNT - 1, false)) {
                return ItemStack.EMPTY;
            }
        } else if (pIndex < BE_INVENTORY_FIRST_SLOT_INDEX + BE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(pPlayer, sourceStack);
        return copyOfSourceStack;
    }

    /**
     * Adds the slots for the inventory
     */
    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    /**
     * Adds the slots for the hotbar
     */
    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
