package net.realdarkstudios.rdslib.item.rarity;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.realdarkstudios.rdslib.rarity.RDSRarity;

public class CustomRarityItem extends Item {
    private final RDSRarity rarity;

    public CustomRarityItem(Item.Properties pProperties, RDSRarity rarity) {
        super(pProperties.rarity(Rarity.COMMON));
        this.rarity = rarity;
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return rarity.convert();
    }

    public RDSRarity getCustomRarity() {
        return rarity;
    }
}
