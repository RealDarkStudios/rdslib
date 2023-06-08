package net.darkstudios.rdslib.util.item;

import net.darkstudios.rdslib.util.DataClass;
import net.minecraft.world.item.Rarity;

import java.util.List;


public class ItemTier extends DataClass<ItemTier> implements IItemTier {
    private final String name;
    private Rarity rarity;

    public ItemTier(String pName, Rarity pRarity) {
        super(List.of(pName, pRarity));
        this.name = pName;
        this.rarity = pRarity;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Rarity getRarity() {
        return this.rarity;
    }

    public void setRarity(Rarity pRarity) {
        this.rarity = pRarity;
    }
}
