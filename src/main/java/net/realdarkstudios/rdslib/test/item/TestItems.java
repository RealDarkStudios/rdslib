package net.realdarkstudios.rdslib.test.item;

import net.realdarkstudios.rdslib.RDSLib;
import net.realdarkstudios.rdslib.item.PaxelItem;
import net.realdarkstudios.rdslib.item.rarity.CustomRarityItem;
import net.realdarkstudios.rdslib.registry.RegistryHelper;
import net.realdarkstudios.rdslib.test.TestRarities;
import net.realdarkstudios.rdslib.util.RDSTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TestItems {
    public static final RegistryHelper<Item> ITEMS = RegistryHelper.create(ForgeRegistries.ITEMS, RDSLib.MODID);

    public static final RegistryObject<CustomRarityItem> RARITY_TEST = ITEMS.register("rarity_test",
            () -> new CustomRarityItem(new Item.Properties(), TestRarities.TEST));

    public static final RegistryObject<Item> PAXEL_TEST = ITEMS.register("paxel_test",
            () -> new PaxelItem(new ForgeTier(5, 3000, 10, 10, 10, RDSTags.Blocks.PAXEL_MINEABLE,
                    () -> Ingredient.of(TestItems.RARITY_TEST.get())), 0, 0, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}