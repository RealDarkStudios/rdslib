package net.realdarkstudios.rdslib.test.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.realdarkstudios.rdslib.block.entity.AbstractFurnaceLikeBlockEntityV2;
import net.realdarkstudios.rdslib.test.recipe.TestFurnaceRecipe;
import net.realdarkstudios.rdslib.test.screen.TestFurnaceMenu;
import org.jetbrains.annotations.Nullable;

public class TestFurnaceBlockEntity extends AbstractFurnaceLikeBlockEntityV2 implements MenuProvider {
    public TestFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(TestBlockEntities.NETHER_BRICK_FURNACE.get(), pos, state, TestFurnaceRecipe.Type.INSTANCE, 80);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Nether Brick Furnace");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player pPlayer) {
        return new TestFurnaceMenu(id, inventory, this, this.data);
    }

    @Override
    public boolean isFuel(ItemStack stack) {
        if (stack.isEmpty() || !stack.is(Items.LAVA_BUCKET)) {
            return false;
        } else return super.isFuel(stack);
    }
}
