package net.darkstudios.rdslib.util.item.tools;

import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

public class RDSShovelItem extends ShovelItem {
    public RDSShovelItem(Tier pTier, Properties pProperties) {
        this(pTier, 1.5F, -3.0F, pProperties);
    }
    public RDSShovelItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
}
