package net.realdarkstudios.rdslib.test.block.entity;

import net.realdarkstudios.rdslib.RDSLib;
import net.realdarkstudios.rdslib.test.block.TestBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TestBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RDSLib.MODID);

    public static final RegistryObject<BlockEntityType<TestFurnaceBlockEntity>> NETHER_BRICK_FURNACE = BLOCK_ENTITIES.register("nether_brick_furnace",
            () -> BlockEntityType.Builder.of(TestFurnaceBlockEntity::new, TestBlocks.NETHER_BRICK_FURNACE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
