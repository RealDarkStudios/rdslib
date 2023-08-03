package net.realdarkstudios.rdslib.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public abstract class AbstractFurnaceLikeRecipe implements Recipe<Container> {
    public final ResourceLocation id;
    public final String group;
    public final Ingredient ingredient;
    public final ItemStack result;
    public final float experience;
    public final int cookingTime;
    public final RecipeType<? extends AbstractFurnaceLikeRecipe> type;

    public AbstractFurnaceLikeRecipe(ResourceLocation pId, RecipeType<? extends AbstractFurnaceLikeRecipe> pType, String pGroup, Ingredient pIngredient, ItemStack pResult, float pExperience, int pCookingTime) {
        this.type = pType;
        this.id = pId;
        this.group = pGroup;
        this.ingredient = pIngredient;
        this.result = pResult;
        this.experience = pExperience;
        this.cookingTime = pCookingTime;
    }

    /**
     * Returns true if the ingredient is the same in both
     */
    public boolean matches(Container pInv, Level pLevel) {
        return this.ingredient.test(pInv.getItem(0));
    }

    /**
     * Returns the result
     */
    public ItemStack assemble(Container p_43746_, RegistryAccess p_267063_) {
        return this.result.copy();
    }

    /**
     * Used to determine if this recipe can fit in a grid of the given width/height
     */
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    /**
     * Gets the ingredients
     */
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }

    /**
     * Gets the experience of this recipe
     */
    public float getExperience() {
        return this.experience;
    }

    /**
     * Gets the result item
     */
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result;
    }

    /**
     * Recipes with equal group are combined into one button in the recipe book
     */
    public String getGroup() {
        return this.group;
    }

    /**
     * Gets the cook time in ticks
     */
    public int getCookingTime() {
        return this.cookingTime;
    }

    /**
     * Gets the ID of the recipe
     */
    public ResourceLocation getId() {
        return this.id;
    }

    /**
     * Gets the recipeType
     */
    public RecipeType<?> getType() {
        return this.type;
    }

    /**
     * Mark this as cannot be used in the recipe book
     */
    @Override
    public boolean isSpecial() {
        return true;
    }
}
