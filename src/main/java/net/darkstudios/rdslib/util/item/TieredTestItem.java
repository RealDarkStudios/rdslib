package net.darkstudios.rdslib.util.item;

import net.darkstudios.rdslib.util.rarity.ItemTier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class TieredTestItem extends TieredItem{
    public TieredTestItem(Properties pProperties, ItemTier[] tiers) {
        super(pProperties, tiers);
    }

    public TieredTestItem(Properties pProperties, ItemTier[] tiers, int startingTier) {
        super(pProperties, tiers, startingTier);
    }

    private boolean debounce = false;

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!debounce) {
            upgradeTier();
            debounce = true;
        }
        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        debounce = false;
        super.releaseUsing(pStack, pLevel, pLivingEntity, pTimeCharged);
    }
}
