package net.realdarkstudios.rdslib;

import com.mojang.logging.LogUtils;
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
import net.realdarkstudios.rdslib.registry.RegistryManager;
import net.realdarkstudios.rdslib.util.Version;
import org.slf4j.Logger;


@Mod(RDSLib.MODID)
public class RDSLib {
    public static final String MODID = "rdslib";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Version VERSION = Version.fromModId(MODID, 0);
    public static boolean isTestMode = false;
    public static final RegistryManager regManager = RegistryManager.getOrCreate(MODID);

    public RDSLib() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        DefaultRarities.registerDefaultRarities(modEventBus);

        if (!FMLLoader.isProduction()) {
            addTestItems();
        }

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("RDSLib Loading (v{})", ModList.get().getModFileById(MODID).versionString());
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("RDSLib Server Starting (v{})", ModList.get().getModFileById(MODID).versionString());
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("RDSLib Client Loading (v{})", ModList.get().getModFileById(MODID).versionString());

            if (isTestMode) {

            }
        }
    }

    private void addTestItems() {

        isTestMode = true;
        LOGGER.info("=============================");
        LOGGER.info("TEST MODE ENABLED FOR RDSLIB!");
        LOGGER.info("=============================");

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
    }
}
