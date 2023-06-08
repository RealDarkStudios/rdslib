package net.darkstudios.rdslib.util.item;

import net.darkstudios.rdslib.util.RDSUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CooldownTieredItem extends TieredItem {
    public int ticks = 0;

    public CooldownTieredItem(ItemTier[] tiers, Properties pProperties) {
        this(tiers, 0, RDSUtils.SECOND * 5, pProperties);
    }

    public CooldownTieredItem(ItemTier[] pTiers, int startingTier, int pTickAmount, Properties pProperties) {
        super(pTiers, startingTier, pProperties);
        this.ticks = pTickAmount != 0 ? pTickAmount : RDSUtils.SECOND * 5;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide()) {
            pPlayer.getCooldowns().addCooldown(this, this.ticks);
            upgradeTier();
        }

        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }
}
