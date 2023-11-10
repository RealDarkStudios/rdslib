package net.realdarkstudios.rdslib.screen.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.realdarkstudios.rdslib.screen.AbstractFurnaceLikeMenuV2;

@Deprecated(since = "4.0.3.0")
public class CustomFurnaceFuelSlotV2 extends SlotItemHandler {
    /**
     * This class is now deprecated.
     * @deprecated Since: 4.0.3.0
     */
    private final AbstractFurnaceLikeMenuV2 menu;

    public CustomFurnaceFuelSlotV2(IItemHandler pHandler, AbstractFurnaceLikeMenuV2 pFurnaceMenu, int pSlot, int pXPosition, int pYPosition) {
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
