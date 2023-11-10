package net.realdarkstudios.rdslib.rarity;

import net.realdarkstudios.rdslib.RDSLib;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Locale;
import java.util.function.Supplier;


public class Rarities {

    private static RDSRarity createBuilderRarity(ResourceLocation name, TextColor color, boolean bold, boolean italic, boolean underline, boolean strikethrough, boolean obfuscate, ClickEvent clickEvent, HoverEvent hoverEvent, ResourceLocation font) {
        Style useStyle;
        if (color != null) {
            useStyle = Style.EMPTY.withColor(color).withBold(bold).withItalic(italic).withUnderlined(underline).withStrikethrough(strikethrough).withObfuscated(obfuscate).withClickEvent(clickEvent).withHoverEvent(hoverEvent).withFont(font);
        } else {
            useStyle = Style.EMPTY.withBold(bold).withItalic(italic).withUnderlined(underline).withStrikethrough(strikethrough).withObfuscated(obfuscate).withClickEvent(clickEvent).withHoverEvent(hoverEvent).withFont(font);

        }
        
        return register(name, useStyle);
    }

    public static class Builder {
        private ResourceLocation name = null;
        private TextColor color = null;
        private boolean bold, italic, underline, strikethrough, obfuscate = false;
        private ClickEvent clickEvent = null;
        private HoverEvent hoverEvent = null;
        private ResourceLocation font = null;

        public Builder name(String pName) {
            this.name = new ResourceLocation(RDSLib.MODID, pName);
            return this;
        }

        public Builder name(ResourceLocation pName) {
            this.name = pName;
            return this;
        }

        public Builder color(TextColor pColor) {
            this.color = pColor;
            return this;
        }

        public Builder bold() {
            this.bold = true;
            return this;
        }

        public Builder italic() {
            this.italic = true;
            return this;
        }

        public Builder underline() {
            this.underline = true;
            return this;
        }

        public Builder strikethrough() {
            this.strikethrough = true;
            return this;
        }

        public Builder obfuscate() {
            this.obfuscate = true;
            return this;
        }

        public Builder clickEvent(ClickEvent pClickEvent) {
            this.clickEvent = pClickEvent;
            return this;
        }

        public Builder hoverEvent(HoverEvent pHoverEvent) {
            this.hoverEvent = pHoverEvent;
            return this;
        }

        public Builder font(ResourceLocation pFont) {
            this.font = pFont;
            return this;
        }

        public RDSRarity build() {
            if (this.name == null) {
                this.name = new ResourceLocation(RDSLib.MODID, RandomStringUtils.randomAlphanumeric(12).toLowerCase(Locale.ROOT));
            }
            return createBuilderRarity(this.name, this.color, this.bold, this.italic, this.underline, this.strikethrough, this.obfuscate, this.clickEvent, this.hoverEvent, this.font);
        }
    }

    public static Style getStyle(Rarity rarity) {
        return rarity.getStyleModifier().apply(Style.EMPTY);
    }

    public static Style getStyle(RDSRarity rarity) {
        return rarity.getStyle();
    }

    public static Style getStyle(Supplier<RDSRarity> rarity) {
        return rarity.get().getStyle();
    }

    public static RDSRarity register(ResourceLocation name, Style style) {
        RDSLib.LOGGER.info(String.format("Creating rarity with name %s", name));
        return new RDSRarity(name, style);
    }
}
