package net.realdarkstudios.rdslib.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.realdarkstudios.rdslib.RDSLib;
import net.realdarkstudios.rdslib.rarity.RDSRarity;

import java.util.function.Supplier;

@Deprecated(since = "4.0.3.0")
public class RDSRegistries {
    /**
     * This class has now been deprecated.
     * @deprecated Since: 4.0.3.0
     */
    private static final DeferredRegister<RDSRarity> RARITIES_DR = DeferredRegister.create(new ResourceLocation(RDSLib.MODID, "rarities"), RDSLib.MODID);
    public static final Supplier<IForgeRegistry<RDSRarity>> RARITIES = RARITIES_DR.makeRegistry(RegistryBuilder::new);

    public static void registerRegistries(IEventBus eventBus) {
        RARITIES_DR.register(eventBus);
    }
}
