package net.realdarkstudios.rdslib.item.rarity;

import net.realdarkstudios.rdslib.rarity.RDSRarity;
import net.minecraft.world.item.*;

import java.util.function.Supplier;

public class RarityArmorItem extends ArmorItem {
    private final Supplier<RDSRarity> rarity;

    public RarityArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties, Supplier<RDSRarity> rarity) {
        super(pMaterial, pType, pProperties.rarity(Rarity.COMMON));
        this.rarity = rarity;
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return rarity.get().convert();
    }

    public RDSRarity getCustomRarity() {
        return rarity.get();
    }
}
