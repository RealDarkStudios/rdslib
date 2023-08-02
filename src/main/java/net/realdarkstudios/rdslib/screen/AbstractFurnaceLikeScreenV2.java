package net.realdarkstudios.rdslib.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public abstract class AbstractFurnaceLikeScreenV2<T extends AbstractFurnaceLikeMenuV2> extends AbstractContainerScreen<T> {
    private final ResourceLocation TEXTURE;
    public AbstractFurnaceLikeScreenV2(T pMenu, Inventory pPlayerInventory, Component pTitle, ResourceLocation pTexture) {
        super(pMenu, pPlayerInventory, pTitle);
        this.TEXTURE = pTexture;
    }

    @Override
    protected void init() {
        super.init();
    }

    // blit(TEXTURE, draw @ x, draw @ y, copy @ x, copy @ y, x size, y size)
    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        if (this.menu.isLit()) {
            int k = this.menu.getScaledLitProgress();
            pGuiGraphics.blit(TEXTURE, x + 57, y + 36 + 13 - k, 176, 12 - k, 14, k);
        }

        if (this.menu.isCooking()) {
            int l = this.menu.getScaledCookingProgress();
            pGuiGraphics.blit(TEXTURE, x + 79, y + 34, 176, 14, l, 17);
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
