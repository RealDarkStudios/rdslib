package net.realdarkstudios.rdslib.screen.slot;

import net.realdarkstudios.rdslib.screen.AbstractFurnaceLikeMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class CustomFurnaceFuelSlot extends SlotItemHandler {
    private final AbstractFurnaceLikeMenu menu;

    public CustomFurnaceFuelSlot(IItemHandler pHandler, AbstractFurnaceLikeMenu pFurnaceMenu, int pSlot, int pXPosition, int pYPosition) {
        super(pHandler, pSlot, pXPosition, pYPosition);
        this.menu = pFurnaceMenu;
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean mayPlace(ItemStack pStack) {
        return this.menu.isFuel(pStack) || isBucket(pStack);
    }

    public int getMaxStackSize(ItemStack pStack) {
        return isBucket(pStack) ? 1 : super.getMaxStackSize(pStack);
    }

    public static boolean isBucket(ItemStack pStack) {
        return pStack.is(Items.BUCKET);
    }
}
