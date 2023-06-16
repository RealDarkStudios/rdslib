package net.darkstudios.rdslib;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(RDSLib.MODID)
public class RDSLib {
    public static final String MODID = "rdslib";
    public static final Logger LOGGER = LogUtils.getLogger();

//    public static final ItemTier[] tiers = new ItemTier[]{
//            new ItemTier("red", Rarities.RED),
//            new ItemTier("yellow", Rarities.YELLOW),
//            new ItemTier("green", Rarities.GREEN),
//            new ItemTier("blue", Rarities.BLUE),
//            new ItemTier("purple", Rarities.PURPLE)
//    };
//
//    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//    public static final RegistryObject<Item> TEST = ITEMS.register("test", () -> new CooldownTieredItem(tiers, 0, 20, new Item.Properties()));
//    public static final RegistryObject<Item> TEST2 = ITEMS.register("test2", () -> new Item(new Item.Properties().rarity(new Rarities.Builder().color(TextColor.fromLegacyFormat(ChatFormatting.RED)).bold().underline().strikethrough().build())));
//    public static final RegistryObject<Item> TIER_TEST = ITEMS.register("tier_test", () -> new TieredTestItem(new Item.Properties(), tiers));

    public RDSLib() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        // ITEMS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("RDSLib Loading");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("RDSLib Server Starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("RDSLib Client Loading");
        }
    }
}
