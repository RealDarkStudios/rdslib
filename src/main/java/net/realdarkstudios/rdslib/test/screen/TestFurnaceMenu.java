package net.realdarkstudios.rdslib.test.screen;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.realdarkstudios.rdslib.screen.AbstractFurnaceLikeMenuV2;
import net.realdarkstudios.rdslib.test.block.TestBlocks;
import net.realdarkstudios.rdslib.test.block.entity.TestFurnaceBlockEntity;

public class TestFurnaceMenu extends AbstractFurnaceLikeMenuV2 {
    public TestFurnaceMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(TestMenuTypes.NETHER_BRICK_FURNACE_MENU.get(), id, inv, extraData);
    }

    public TestFurnaceMenu(int id, Inventory inv, TestFurnaceBlockEntity entity, ContainerData data) {
        super(TestMenuTypes.NETHER_BRICK_FURNACE_MENU.get(), id, inv, entity, data);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(this.blockEntity.getLevel(), blockEntity.getBlockPos()), pPlayer, TestBlocks.NETHER_BRICK_FURNACE.get());
    }
}
