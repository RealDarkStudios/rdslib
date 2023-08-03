package net.realdarkstudios.rdslib.test;

import net.realdarkstudios.rdslib.RDSLib;
import net.realdarkstudios.rdslib.test.datagen.TestRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RDSLib.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TestDataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(RDSLib.isTestMode, new TestRecipeProvider(packOutput));
    }
}
