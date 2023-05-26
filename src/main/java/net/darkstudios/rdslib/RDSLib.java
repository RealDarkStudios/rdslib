package net.darkstudios.rdslib;

import com.mojang.logging.LogUtils;
import net.darkstudios.rdslib.util.item.TieredItem;
import net.darkstudios.rdslib.util.item.TieredTestItem;
import net.darkstudios.rdslib.util.rarity.ItemTier;
import net.darkstudios.rdslib.util.rarity.Rarities;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(RDSLib.MODID)
public class RDSLib {
    public static final String MODID = "rdslib";
    public static final Logger LOGGER = LogUtils.getLogger();

//    public static final ItemTier[] tiers = new ItemTier[]{
//            new ItemTier(Rarities.RED),
//            new ItemTier(Rarities.YELLOW),
//            new ItemTier(Rarities.GREEN),
//            new ItemTier(Rarities.BLUE),
//            new ItemTier(Rarities.PURPLE)
//    };

//    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//    public static final RegistryObject<Item> TEST = ITEMS.register("test", () -> new Item(new Item.Properties().rarity(Rarities.PURPLE_ITALIC_UNDERLINE_OBFUSCATE)));
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
