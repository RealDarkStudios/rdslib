package net.realdarkstudios.rdslib.item.rarity;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.realdarkstudios.rdslib.rarity.RDSRarity;

public class RarityArmorItem extends ArmorItem {
    private final RDSRarity rarity;

    public RarityArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties, RDSRarity rarity) {
        super(pMaterial, pSlot, pProperties.rarity(Rarity.COMMON));
        this.rarity = rarity;
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return rarity.convert();
    }

    public RDSRarity getCustomRarity() {
        return rarity;
    }
}
