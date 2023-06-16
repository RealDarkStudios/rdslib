package net.darkstudios.rdslib.util.item;

import net.darkstudios.rdslib.util.rarity.Rarities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MissingDependencyItem extends Item {
    private final String missingDepID;
    public MissingDependencyItem(Properties pProperties, String pMissingDep) {
        super(pProperties);
        this.missingDepID = pMissingDep;
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return Rarities.DEPENDENCY_MISSING;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltips.masterfulmines.missing_dep_item").append(this.missingDepID));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
