package net.realdarkstudios.rdslib.test.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.realdarkstudios.rdslib.block.custom.AbstractFurnaceLikeBlockV2;
import net.realdarkstudios.rdslib.test.block.entity.TestBlockEntities;
import net.realdarkstudios.rdslib.test.block.entity.TestFurnaceBlockEntity;
import org.jetbrains.annotations.Nullable;

public class TestFurnaceBlock extends AbstractFurnaceLikeBlockV2 {
    public TestFurnaceBlock(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TestFurnaceBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide()) {
            return null;
        }

        return createTickerHelper(pBlockEntityType, TestBlockEntities.NETHER_BRICK_FURNACE.get(), TestFurnaceBlockEntity::tick);
    }
}
