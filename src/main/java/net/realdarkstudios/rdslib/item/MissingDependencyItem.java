package net.realdarkstudios.rdslib.item;

import net.realdarkstudios.rdslib.item.rarity.CustomRarityItem;
import net.realdarkstudios.rdslib.rarity.DefaultRarities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MissingDependencyItem extends CustomRarityItem {
    private final String missingDepID;
    public MissingDependencyItem(Properties pProperties, String pMissingDep) {
        super(pProperties, DefaultRarities.DEPENDENCY_MISSING);
        this.missingDepID = pMissingDep;
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return Rarity.COMMON;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltips.rdslib.missing_dep_item").append(this.missingDepID));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
