package net.realdarkstudios.rdslib;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import net.realdarkstudios.rdslib.rarity.DefaultRarities;
import net.realdarkstudios.rdslib.registry.RegistryHelper;
import net.realdarkstudios.rdslib.registry.RegistryManager;
import net.realdarkstudios.rdslib.registry.RegistryManagers;
import net.realdarkstudios.rdslib.test.TestRegistries;
import net.realdarkstudios.rdslib.util.Version;
import org.slf4j.Logger;

@Mod(RDSLib.MODID)
public class RDSLib {
    public static final String MODID = "rdslib";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Version VERSION = Version.fromModId(MODID, 0);
    public static final String VERSION_STRING = ModList.get().getModFileById(MODID).versionString();
    public static final RegistryManager REGISTRY_MANAGER = RegistryManagers.get().newManager(MODID);

    public RDSLib() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        DefaultRarities.registerDefaultRarities(modEventBus);

        if (!FMLLoader.isProduction()) {
            TestRegistries.register(modEventBus);

            // creates a list of the registries registered to each modid
            // Example:
            // Registries:
            //   example:
            //     minecraft:item
            //   example2:
            //     minecraft:block
            //     minecraft:enchantment

            StringBuilder reg_list = new StringBuilder("Checking registries: \nRegistries: \n");

            for (RegistryManager registryManager: RegistryManagers.get().getRegistryManagers()) {
                reg_list.append("  ").append(registryManager.getModId()).append(":\n");

                if (registryManager.getRegistryHelpers().isEmpty()) {
                    reg_list.append("    NO REGISTRIES\n");
                } else {
                    for (RegistryHelper<?> registryHelper : registryManager.getRegistryHelpers()) {
                        reg_list.append("    ").append(registryHelper.getRegistry().getRegistryKey().location()).append("\n");
                    }
                }
            }

            String registries = reg_list.substring(0, reg_list.length() - 1);
            LOGGER.info(registries);
        }

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("RDSLib Loading (v{})", VERSION_STRING);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("RDSLib Server Starting (v{})", VERSION_STRING);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("RDSLib Client Loading (v{})", VERSION_STRING);
        }
    }

    public static ResourceLocation resource(String path) {
        return new ResourceLocation(MODID, path);
    }
}