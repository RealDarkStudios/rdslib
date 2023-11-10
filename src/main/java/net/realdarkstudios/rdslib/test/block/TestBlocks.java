package net.realdarkstudios.rdslib.test.block;

import net.realdarkstudios.rdslib.RDSLib;
import net.realdarkstudios.rdslib.registry.RegistryHelper;
import net.realdarkstudios.rdslib.test.block.custom.TestFurnaceBlock;
import net.realdarkstudios.rdslib.test.item.TestItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class TestBlocks {
    public static final RegistryHelper<Block> BLOCKS = RegistryHelper.create(ForgeRegistries.BLOCKS, RDSLib.MODID);

    /* FURNACES */
    public static final RegistryObject<TestFurnaceBlock> NETHER_BRICK_FURNACE = registerBlock("nether_brick_furnace",
            () -> new TestFurnaceBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.NETHER).strength(7, 30).lightLevel(litBlockEmission(13))),
            new Item.Properties());

    private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (block) -> block.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
    }


    public static <T extends Block> RegistryObject<T> registerBlock(String pName, Supplier<T> pBlock, Item.Properties pProperties) {
        RegistryObject<T> toReturn = BLOCKS.register(pName, pBlock);
        registerBlockItem(pName, toReturn, pProperties);
        return toReturn;
    }

    private static <B extends Block> RegistryObject<BlockItem> registerBlockItem(String pName, Supplier<B> pBlock, Item.Properties pProperties) {
        return TestItems.ITEMS.register(pName, () -> new BlockItem(pBlock.get(), pProperties));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
