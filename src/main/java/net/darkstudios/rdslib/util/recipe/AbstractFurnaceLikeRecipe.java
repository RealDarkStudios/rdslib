package net.darkstudios.rdslib.util.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public abstract class AbstractFurnaceLikeRecipe implements Recipe<SimpleContainer> {
    protected final ResourceLocation id;
    protected final String group;
    protected final Ingredient ingredient;
    protected final ItemStack result;
    protected final float experience;
    protected final int cookingTime;
    private final RecipeType<? extends AbstractFurnaceLikeRecipe> type;

    public AbstractFurnaceLikeRecipe(ResourceLocation pId, RecipeType<? extends AbstractFurnaceLikeRecipe> pType, String pGroup, Ingredient pIngredient, ItemStack pResult, float pExperience, int pCookingTime) {
        this.type = pType;
        this.id = pId;
        this.group = pGroup;
        this.ingredient = pIngredient;
        this.result = pResult;
        this.experience = pExperience;
        this.cookingTime = pCookingTime;
    }

    public boolean matches(SimpleContainer pInv, Level pLevel) {
        return this.ingredient.test(pInv.getItem(0));
    }

    public ItemStack assemble(SimpleContainer p_43746_) {
        return this.result.copy();
    }

    /**
     * Used to determine if this recipe can fit in a grid of the given width/height
     */
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

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

    public ItemStack getResultItem() {
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

    public ResourceLocation getId() {
        return this.id;
    }

    public RecipeType<?> getType() {
        return this.type;
    }
}
