package net.realdarkstudios.rdslib.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public abstract class AbstractFurnaceLikeScreen<T extends AbstractFurnaceLikeMenu> extends AbstractContainerScreen<T> {
    private final ResourceLocation TEXTURE;

    public AbstractFurnaceLikeScreen(T pMenu, Inventory pPlayerInventory, Component pTitle, ResourceLocation pTexture) {
        super(pMenu, pPlayerInventory, pTitle);
        TEXTURE = pTexture;
    }

    @Override
    protected void init() {
        super.init();
    }

    // blit(TEXTURE, draw @ x, draw @ y, copy @ x, copy @ y, x size, y size)

    /**
     * Renders texture, progress arrow, and fuel indicator
     */
    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        if (this.menu.isLit()) {
            int k = this.menu.getScaledLitProgress();
            pGuiGraphics.blit(TEXTURE, x + 57, y + 36 + 13 - k, 176, 12 - k, 14, k);
        }

        if (this.menu.isCooking()) {
            int l = this.menu.getFixedScaledCookingProgress();
            pGuiGraphics.blit(TEXTURE, x + 79, y + 34, 176, 14, menu.getFixedScaledCookingProgress(), 17);
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, mouseX, mouseY, delta);
        renderTooltip(pGuiGraphics, mouseX, mouseY);
    }
}
