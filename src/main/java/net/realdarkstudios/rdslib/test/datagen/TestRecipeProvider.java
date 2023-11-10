package net.realdarkstudios.rdslib.test.datagen;

import net.realdarkstudios.rdslib.RDSLib;
import net.realdarkstudios.rdslib.recipe.RecipeGenHelper;
import net.realdarkstudios.rdslib.test.block.TestBlocks;
import net.realdarkstudios.rdslib.test.recipe.TestRecipes;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class TestRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private final RecipeGenHelper helper = new RecipeGenHelper(RDSLib.MODID);

    public TestRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        helper.addCustomFurnaceRecipes(pFinishedRecipeConsumer, TestRecipes.NETHER_BRICK_FURNACE.get(), "_from_test_smelting_");
        helper.customFurnaceRecipe(pFinishedRecipeConsumer, TestBlocks.NETHER_BRICK_FURNACE.get(), Blocks.NETHER_BRICKS, Blocks.FURNACE);
    }
}
