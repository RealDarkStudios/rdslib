package net.realdarkstudios.rdslib.test.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.realdarkstudios.rdslib.RDSLib;
import net.realdarkstudios.rdslib.screen.AbstractFurnaceLikeScreenV2;

public class TestFurnaceScreen extends AbstractFurnaceLikeScreenV2<TestFurnaceMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(RDSLib.MODID,"textures/gui/nether_brick_furnace_gui.png");

    public TestFurnaceScreen(TestFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle, TEXTURE);
    }
}
