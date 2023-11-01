package net.realdarkstudios.rdslib.screen;

import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.AbstractFurnaceRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public abstract class AbstractFurnaceLikeScreenV3<M extends AbstractFurnaceLikeMenuV3> extends AbstractFurnaceScreen<M> {
    public AbstractFurnaceLikeScreenV3(M pMenu, AbstractFurnaceRecipeBookComponent pRecipeBookComponent, Inventory pPlayerInventory, Component pTitle, ResourceLocation pTexture) {
        super(pMenu, pRecipeBookComponent, pPlayerInventory, pTitle, pTexture);
    }
}
