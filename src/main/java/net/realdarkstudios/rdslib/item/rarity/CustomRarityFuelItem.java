package net.realdarkstudios.rdslib.item.rarity;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.realdarkstudios.rdslib.item.FuelItem;
import net.realdarkstudios.rdslib.rarity.RDSRarity;

public class CustomRarityFuelItem extends FuelItem {
    private final RDSRarity rarity;

    public CustomRarityFuelItem(Properties pProperties, RDSRarity pRarity, int burnDuration) {
        super(pProperties, burnDuration);
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
