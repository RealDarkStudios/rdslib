package net.realdarkstudios.rdslib.item.rarity;

import net.realdarkstudios.rdslib.rarity.RDSRarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

import java.util.function.Supplier;

public class CustomRarityItem extends Item {

    private final Supplier<RDSRarity> rarity;

    public CustomRarityItem(Item.Properties pProperties, Supplier<RDSRarity> rarity) {
        super(pProperties.rarity(Rarity.COMMON));
        this.rarity = rarity;
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return rarity.get().convert();
    }

    public RDSRarity getCustomRarity() {
        return rarity.get();
    }
}
