package net.realdarkstudios.rdslib.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nullable;

public class FuelItem extends Item {
    private final int burnTime;
    private final boolean isBucket;

    public FuelItem(Properties pProperties, int burnTime) {
        this(pProperties, burnTime, false);
    }

    public FuelItem(Properties pProperties, int burnTime, boolean isBucket) {
        super(pProperties);
        this.burnTime = burnTime;
        this.isBucket = isBucket;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.burnTime;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return isBucket ? new ItemStack(Items.BUCKET) : super.getCraftingRemainingItem(itemStack);
    }
}