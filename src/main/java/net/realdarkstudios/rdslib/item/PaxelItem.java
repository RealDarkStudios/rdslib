package net.realdarkstudios.rdslib.item;

import net.realdarkstudios.rdslib.util.RDSTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Vanishable;

public class PaxelItem extends DiggerItem implements Vanishable {
    public PaxelItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pAttackDamageModifier, pAttackSpeedModifier, pTier, RDSTags.Blocks.PAXEL_MINEABLE, pProperties);
    }
}
