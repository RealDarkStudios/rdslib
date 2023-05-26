package net.darkstudios.rdslib.util.item;

import net.darkstudios.rdslib.util.rarity.Rarities;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class MissingDependencyItem extends Item {
    public MissingDependencyItem(Item.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return Rarities.DEPENDENCY_MISSING;
    }
}
