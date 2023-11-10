package net.realdarkstudios.rdslib.test.recipe;

import net.realdarkstudios.rdslib.RDSLib;
import net.realdarkstudios.rdslib.recipe.FurnaceLikeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TestRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, RDSLib.MODID);

    public static final RegistryObject<FurnaceLikeSerializer<TestFurnaceRecipe>> NETHER_BRICK_FURNACE = SERIALIZERS.register("nether_brick_furnace",
            () -> new FurnaceLikeSerializer<>(TestFurnaceRecipe::new, 100));

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
