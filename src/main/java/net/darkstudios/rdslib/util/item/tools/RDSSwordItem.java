package net.darkstudios.rdslib.util.item.tools;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class RDSSwordItem extends SwordItem {
    public RDSSwordItem(Tier pTier, Properties pProperties) {
        this(pTier, 3, -2.4F, pProperties);
    }
    public RDSSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
}
