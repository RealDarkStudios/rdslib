package net.realdarkstudios.rdslib.test;

import net.minecraft.network.chat.TextColor;
import net.realdarkstudios.rdslib.rarity.RDSRarity;
import net.realdarkstudios.rdslib.rarity.Rarities;

public class TestRarities {
    public static final RDSRarity TEST = new Rarities.Builder().name("test").color(TextColor.fromRgb(0xFF0000)).bold().build();
}
