package net.realdarkstudios.rdslib.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;

public abstract class AbstractFurnaceLikeRecipeV2 extends AbstractCookingRecipe {
    public AbstractFurnaceLikeRecipeV2(RecipeType<?> pRecipeType, ResourceLocation pId, String pGroup, CookingBookCategory pCategory, Ingredient pIngredient, ItemStack pResult, float pExperience, int pCookingTime) {
        super(pRecipeType, pId, pGroup, pCategory, pIngredient, pResult, pExperience, pCookingTime);
    }
}
