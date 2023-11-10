package net.realdarkstudios.rdslib.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.realdarkstudios.rdslib.RDSLib;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class TieredItem extends Item {
    private final ItemTier[] tiers;
    private final int numTiers;
    private int curTier;
    private Rarity rarity;

    public TieredItem(ItemTier[] tiers, Properties pProperties) {
        this(tiers, 0, pProperties);
    }

    public TieredItem(ItemTier[] pTiers, int startingTier, Properties pProperties) {
        super(pProperties);
        this.numTiers = pTiers.length;
        if (this.numTiers == 0) throw new IndexOutOfBoundsException("Number of tiers can't be 0!");
        if (startingTier < 0 || startingTier > this.numTiers - 1) throw new IndexOutOfBoundsException(String.format("Starting tier out of bounds. Expected 0 - %d, got %d",  this.numTiers - 1, startingTier));
        this.curTier = startingTier;
        this.tiers = pTiers;
        this.rarity = pTiers[startingTier].getRarity();
    }

    public void upgradeTier() {
        upgradeTier(1);
    }

    public void upgradeTier(int numToIncrease) {
        if (this.curTier != this.numTiers - 1) {
            this.curTier += numToIncrease;

            RDSLib.LOGGER.info(String.format("Tier increased to %d", this.curTier));
        }
    }

    public int getCurrentTierIndex() {
        return curTier;
    }

    public ItemTier getCurrentTier() {
        return this.tiers[curTier];
    }

    public ItemTier getItemTier(int index) {
        return this.tiers[index];
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return tiers[pStack.getTag().getInt("currentTier") - 1].getRarity();
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal(String.format("Rarity Level %s/%s", this.curTier + 1, this.numTiers)));
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (!pStack.hasTag() || !pStack.getTag().contains("currentTier")) {
            CompoundTag compoundTag = new CompoundTag();

            compoundTag.putInt("currentTier", this.curTier + 1);

            pStack.setTag(compoundTag);
        } else {
            if (this.curTier != pStack.getTag().getInt("currentTier") - 1 && this.curTier != 0) {
                CompoundTag compoundTag = pStack.getTag();

                compoundTag.putInt("currentTier", this.curTier + 1);

                pStack.setTag(compoundTag);
            } else {
                this.curTier = pStack.getTag().getInt("currentTier") - 1;
            }
        }

    }
}
