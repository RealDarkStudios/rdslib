package net.realdarkstudios.rdslib.test.recipe;

import net.realdarkstudios.rdslib.recipe.AbstractFurnaceLikeRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class TestFurnaceRecipe extends AbstractFurnaceLikeRecipe {


    public TestFurnaceRecipe(ResourceLocation pId, String pGroup, Ingredient pIngredient, ItemStack pResult, float pExperience, int pCookingTime) {
        super(pId, Type.INSTANCE, pGroup, pIngredient, pResult, pExperience, pCookingTime);
    }

    public RecipeSerializer<?> getSerializer() {
        return TestRecipes.NETHER_BRICK_FURNACE.get();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Type implements RecipeType<TestFurnaceRecipe> {
        private Type() {
        }

        public static final Type INSTANCE = new Type();
        public static final String ID = "gem_infusing";
    }
}
