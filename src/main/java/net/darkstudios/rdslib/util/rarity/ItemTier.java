package net.darkstudios.rdslib.util.rarity;

import net.minecraft.world.item.Rarity;

public class ItemTier {
    private final Rarity rarity;

    public ItemTier(Rarity pRarity) {
        this.rarity = pRarity;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
