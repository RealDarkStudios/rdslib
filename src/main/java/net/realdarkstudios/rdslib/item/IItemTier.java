package net.realdarkstudios.rdslib.item;

import net.minecraft.world.item.Rarity;

public interface IItemTier {
    String getName();

    Rarity getRarity();

    void setRarity(Rarity pRarity);
}
