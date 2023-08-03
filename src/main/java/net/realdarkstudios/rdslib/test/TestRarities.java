package net.realdarkstudios.rdslib.test;

import net.realdarkstudios.rdslib.RDSLib;
import net.realdarkstudios.rdslib.rarity.RDSRarity;
import net.realdarkstudios.rdslib.rarity.Rarities;
import net.realdarkstudios.rdslib.registry.RDSRegistries;
import net.realdarkstudios.rdslib.registry.RegistryHelper;
import net.minecraft.network.chat.TextColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

public class TestRarities {
    public static final RegistryHelper<RDSRarity> RARITIES = RegistryHelper.create(RDSRegistries.RARITIES, RDSLib.MODID);

    public static final RegistryObject<RDSRarity> TEST = (RegistryObject<RDSRarity>) RARITIES.register("test",
            () -> new Rarities.Builder().name("test").color(TextColor.fromRgb(0xFF0000)).bold().build());

    public static void register(IEventBus eventBus) {
        RARITIES.register(eventBus);
    }
}
