package net.darkstudios.rdslib.util.item;

import net.darkstudios.rdslib.RDSLib;
import net.darkstudios.rdslib.util.rarity.ItemTier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class TieredItem extends Item {
    private final int numTiers;
    private int curTier;
    private final ItemTier[] tiers;
    private final Rarity rarity;

    public TieredItem(Item.Properties pProperties, ItemTier[] tiers) {
        this(pProperties, tiers, 0);
    }

    public TieredItem(Item.Properties pProperties, ItemTier[] pTiers, int startingTier) {
        super(pProperties);
        this.numTiers = pTiers.length;
        if (this.numTiers == 0) throw new IndexOutOfBoundsException("Number of tiers can't be 0!");
        if (startingTier < 0 || startingTier > this.numTiers - 1) throw new IndexOutOfBoundsException(String.format("Starting tier out of bounds. Expected 0 - %d, got %d",  this.numTiers - 1, startingTier));
        this.curTier = startingTier;
        this.tiers = pTiers;
        this.rarity = pTiers[startingTier].getRarity();

        RDSLib.LOGGER.warn("TieredItem is BROKEN! Please refrain from using it in this version!");
    }

    public void upgradeTier() {
        if (this.curTier != this.numTiers - 1) {
            this.curTier++;
            RDSLib.LOGGER.info(String.format("Tier increased to %d", this.curTier));
        }
    }

    public void upgradeTier(int numToIncrease) {
        if (this.curTier != this.numTiers - 1) {
            this.curTier += numToIncrease;
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
        Rarity nextRarity;
        if (this.curTier + 1 < this.numTiers - 1) {
            nextRarity = this.tiers[this.curTier + 1].getRarity();
            RDSLib.LOGGER.info(String.format("Current Tier: %d, Number of Tiers: %d, New Rarity: %s", this.curTier, this.numTiers - 1, nextRarity.toString()));
        } else {
            nextRarity = this.tiers[this.curTier].getRarity();
            RDSLib.LOGGER.info(String.format("Current Tier: %d, Number of Tiers: %d, Rarity: %s", this.curTier, this.numTiers - 1, nextRarity.toString()));
        }

        return pStack.isEnchanted() ? nextRarity : this.rarity;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal(String.format("Rarity Level %s/%s", this.curTier + 1, this.numTiers)));
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        TieredItem that = (TieredItem) o;
//        return numTiers == that.numTiers && curTier == that.curTier && tiers.equals(that.tiers) && rarity == that.rarity;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(numTiers, curTier, tiers, rarity);
//    }
}
