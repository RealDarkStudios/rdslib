package net.realdarkstudios.rdslib.item;

import net.realdarkstudios.rdslib.rarity.RDSRarity;
import net.realdarkstudios.rdslib.util.DataClass;

import java.util.List;


public class ItemTier extends DataClass<ItemTier> implements IItemTier {
    private final String name;
    private RDSRarity rarity;

    public ItemTier(String pName, RDSRarity pRarity) {
        super(List.of(pName, pRarity));
        this.name = pName;
        this.rarity = pRarity;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public RDSRarity getRarity() {
        return this.rarity;
    }

    public void setRarity(RDSRarity pRarity) {
        this.rarity = pRarity;
    }
}
