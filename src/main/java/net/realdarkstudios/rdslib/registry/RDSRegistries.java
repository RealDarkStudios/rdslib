package net.realdarkstudios.rdslib.registry;

import net.realdarkstudios.rdslib.RDSLib;
import net.realdarkstudios.rdslib.rarity.RDSRarity;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public class RDSRegistries {
    public static final ResourceKey<Registry<RDSRarity>> RARITIES = ResourceKey.createRegistryKey(new ResourceLocation(RDSLib.MODID, "rarities"));
    private static final DeferredRegister<RDSRarity> RARITIES_DR = DeferredRegister.create(RARITIES, RDSLib.MODID);
    private static final Supplier<IForgeRegistry<RDSRarity>> RARITIES_REG = RARITIES_DR.makeRegistry(RegistryBuilder::new);

    public static void registerRegistries(IEventBus eventBus) {
        RARITIES_DR.register(eventBus);
    }
}
