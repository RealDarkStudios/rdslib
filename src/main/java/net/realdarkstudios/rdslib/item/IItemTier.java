package net.realdarkstudios.rdslib.item;

import net.realdarkstudios.rdslib.rarity.RDSRarity;

public interface IItemTier {
    String getName();

    RDSRarity getRarity();

    void setRarity(RDSRarity pRarity);
}
