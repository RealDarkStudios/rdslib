package net.realdarkstudios.rdslib.test;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.realdarkstudios.rdslib.rarity.DefaultRarities;
import net.realdarkstudios.rdslib.registry.RegistryHelper;

public class TestRegistries {
    private static final String MODID = "rdslib_test";
    public static final RegistryHelper<Block> BLOCKS = RegistryHelper.create(ForgeRegistries.BLOCKS, MODID);
    public static final RegistryHelper<Item> ITEMS = RegistryHelper.create(ForgeRegistries.ITEMS, MODID);
    static {
        ITEMS.register("test", () -> new Item(new Item.Properties().rarity(DefaultRarities.RED_BOLD_ITALIC_UNDERLINE.convert())));
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
        BLOCKS.register(bus);
    }
}