package net.realdarkstudios.rdslib.item.rarity;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.realdarkstudios.rdslib.rarity.RDSRarity;

public class CustomRarityBlockItem extends BlockItem {
    private final RDSRarity rarity;

    public CustomRarityBlockItem(Block pBlock, RDSRarity pRarity, Properties pProperties) {
        super(pBlock, pProperties);
        this.rarity = pRarity;
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return rarity.convert();
    }

    public RDSRarity getCustomRarity() {
        return rarity;
    }
}
