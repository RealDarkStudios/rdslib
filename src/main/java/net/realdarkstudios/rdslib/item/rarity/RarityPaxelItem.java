package net.realdarkstudios.rdslib.item.rarity;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.realdarkstudios.rdslib.item.PaxelItem;
import net.realdarkstudios.rdslib.rarity.RDSRarity;

public class RarityPaxelItem extends PaxelItem {
    private final RDSRarity rarity;

    public RarityPaxelItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, RDSRarity rarity) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
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
