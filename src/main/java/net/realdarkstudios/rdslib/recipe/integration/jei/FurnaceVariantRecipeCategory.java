package net.realdarkstudios.rdslib.recipe.integration.jei;

import mezz.jei.api.constants.ModIds;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.resources.ResourceLocation;

public abstract class FurnaceVariantRecipeCategory<T> implements IRecipeCategory<T> {
    protected final IDrawableStatic staticFlame;
    protected final IDrawableAnimated animatedFlame;

    public static final ResourceLocation VANILLA_GUI = new ResourceLocation(ModIds.JEI_ID, "textures/jei/gui/gui_vanilla.png");

    public FurnaceVariantRecipeCategory(IGuiHelper guiHelper) {
        staticFlame = guiHelper.createDrawable(VANILLA_GUI, 82, 114, 14, 14);
        animatedFlame = guiHelper.createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);
    }
}
