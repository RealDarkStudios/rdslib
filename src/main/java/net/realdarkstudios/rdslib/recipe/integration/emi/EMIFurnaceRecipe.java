package net.realdarkstudios.rdslib.recipe.integration.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.realdarkstudios.rdslib.recipe.AbstractFurnaceLikeRecipe;
import net.realdarkstudios.rdslib.recipe.AbstractFurnaceLikeRecipeV2;
import net.realdarkstudios.rdslib.recipe.RecipeUtil;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

@Deprecated(since = "3.0.3.0")
public class EMIFurnaceRecipe implements EmiRecipe {
    /**
     * This class is now deprecated.
     * Please use {@link EMIFurnaceRecipeV2} instead
     * @deprecated Since: 3.0.3.0
     */
    private final ResourceLocation id;
    private final EmiRecipeCategory category;
    private final EmiIngredient input;
    private final EmiStack output;
    private final AbstractFurnaceLikeRecipe recipe;
    private final int fuelMultiplier;
    private final boolean infiniBurn;

    public EMIFurnaceRecipe(AbstractFurnaceLikeRecipe recipe, EmiRecipeCategory category, int fuelMultiplier, boolean infiniBurn) {
        this.id = recipe.getId();
        this.category = category;
        input = EmiIngredient.of(recipe.getIngredients().get(0));
        output = EmiStack.of(RecipeUtil.getResultItem(recipe));
        if (input.getEmiStacks().get(0).getItemStack().is(Items.WET_SPONGE)) {
            input.getEmiStacks().get(0).setRemainder(EmiStack.of(Fluids.WATER, FluidUnits.BUCKET));
        }
        this.recipe = recipe;
        this.fuelMultiplier = fuelMultiplier;
        this.infiniBurn = infiniBurn;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return category;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return List.of(input);
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(output);
    }

    @Override
    public int getDisplayWidth() {
        return 82;
    }

    @Override
    public int getDisplayHeight() {
        return 38;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addFillingArrow(24, 5, 50 * recipe.getCookingTime()).tooltip((mx, my) -> {
            return List.of(ClientTooltipComponent.create(Component.translatable("emi.cooking.time", recipe.getCookingTime() / 20f).getVisualOrderText()));
        });
        if (infiniBurn) {
            widgets.addTexture(EmiTexture.FULL_FLAME, 1, 24);
        } else {
            widgets.addTexture(EmiTexture.EMPTY_FLAME, 1, 24);
            widgets.addAnimatedTexture(EmiTexture.FULL_FLAME, 1, 24, 4000 / fuelMultiplier, false, true, true);
        }
        widgets.addText(Component.translatable("emi.cooking.experience", recipe.getExperience()).getVisualOrderText(), 26, 28, -1, true);
        widgets.addSlot(input, 0, 4);
        widgets.addSlot(output, 56, 0).large(true).recipeContext(this);
    }
}
