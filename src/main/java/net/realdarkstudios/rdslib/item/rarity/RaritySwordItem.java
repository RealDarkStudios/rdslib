package net.realdarkstudios.rdslib.item.rarity;

import net.minecraft.world.item.*;
import net.realdarkstudios.rdslib.rarity.RDSRarity;

public class RaritySwordItem extends SwordItem {
    private final RDSRarity rarity;

    public RaritySwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Item.Properties pProperties, RDSRarity rarity) {
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
