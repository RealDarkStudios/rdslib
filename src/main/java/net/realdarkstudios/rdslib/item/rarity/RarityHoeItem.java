package net.realdarkstudios.rdslib.item.rarity;

import net.realdarkstudios.rdslib.rarity.RDSRarity;
import net.minecraft.world.item.*;

import java.util.function.Supplier;

public class RarityHoeItem extends HoeItem {
    private final Supplier<RDSRarity> rarity;

    public RarityHoeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, Supplier<RDSRarity> rarity) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties.rarity(Rarity.COMMON));
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
