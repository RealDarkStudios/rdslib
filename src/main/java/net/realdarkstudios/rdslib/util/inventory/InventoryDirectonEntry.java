package net.realdarkstudios.rdslib.util.inventory;

import net.minecraft.core.Direction;

public class InventoryDirectonEntry {
    public Direction direction;
    public int slotIndex;
    public boolean canInsert;

    public InventoryDirectonEntry(Direction direction, int slotIndex, boolean canInsert) {
        this.direction = direction;
        this.slotIndex = slotIndex;
        this.canInsert = canInsert;
    }
}
