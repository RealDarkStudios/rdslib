package net.realdarkstudios.rdslib.rarity;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;

public class RDSRarity {
    public final ResourceLocation name;
    public final ChatFormatting color;
    private final Style style;


    public RDSRarity(ResourceLocation name, Style style) {
        this.name = name;
        this.color = ChatFormatting.BLACK;
        this.style = style;
    }

    public Style getStyle() {
        return this.style;
    }

    public Rarity convert() {
        return Rarity.create(this.name.toString(), sty -> this.style);
    }
}
