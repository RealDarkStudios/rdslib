package net.realdarkstudios.rdslib.item.rarity;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.realdarkstudios.rdslib.rarity.RDSRarity;

public class RarityAxeItem extends AxeItem {
    private final RDSRarity rarity;

    public RarityAxeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, RDSRarity rarity) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties.rarity(Rarity.COMMON));
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
