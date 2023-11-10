package net.realdarkstudios.rdslib.recipe.integration.rei;

import me.shedaniel.rei.api.common.display.SimpleGridMenuDisplay;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.api.common.registry.RecipeManagerContext;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.crafting.Recipe;
import net.realdarkstudios.rdslib.recipe.AbstractFurnaceLikeRecipeV2;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class FurnaceLikeDisplay extends BasicDisplay implements SimpleGridMenuDisplay {
    private Recipe<?> recipe;
    private float xp;
    private double cookTime;

    public FurnaceLikeDisplay(AbstractFurnaceLikeRecipeV2 recipe) {
        this(EntryIngredients.ofIngredients(recipe.getIngredients()), Collections.singletonList(EntryIngredient.of(EntryStack.of(VanillaEntryTypes.ITEM, recipe.getResultItem(BasicDisplay.registryAccess())))),
                recipe, recipe.getExperience(), recipe.getCookingTime());
    }

    public FurnaceLikeDisplay(List<EntryIngredient> input, List<EntryIngredient> output, CompoundTag tag) {
        this(input, output, RecipeManagerContext.getInstance().byId(tag, "location"),
                tag.getFloat("xp"), tag.getDouble("cookTime"));
    }

    public FurnaceLikeDisplay(List<EntryIngredient> input, List<EntryIngredient> output, Recipe<?> recipe, float xp, double cookTime) {
        super(input, output, Optional.ofNullable(recipe).map(Recipe::getId));
        this.recipe = recipe;
        this.xp = xp;
        this.cookTime = cookTime;
    }

    public float getXp() {
        return xp;
    }

    public double getCookingTime() {
        return cookTime;
    }

    public Optional<Recipe<?>> getOptionalRecipe() {
        return Optional.ofNullable(recipe);
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    public static <R extends FurnaceLikeDisplay> Serializer<R> serializer(Serializer.RecipeLessConstructor<R> constructor) {
        return Serializer.ofRecipeLess(constructor, (display, tag) -> {
            tag.putFloat("xp", display.getXp());
            tag.putDouble("cookTime", display.getCookingTime());
        });
    }
}

