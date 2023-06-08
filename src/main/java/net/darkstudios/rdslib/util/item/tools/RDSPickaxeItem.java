package net.darkstudios.rdslib.util.item.tools;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class RDSPickaxeItem extends PickaxeItem {
    public RDSPickaxeItem(Tier pTier, Properties pProperties) {
        this(pTier, 1, -2.8F, pProperties);
    }
    public RDSPickaxeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
}
