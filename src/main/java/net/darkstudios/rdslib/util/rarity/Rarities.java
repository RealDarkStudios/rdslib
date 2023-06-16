package net.darkstudios.rdslib.util.rarity;

import net.darkstudios.rdslib.RDSLib;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;
import org.apache.commons.lang3.RandomStringUtils;


public class Rarities {
    /* NON-COLOR SPECIFIC */
    public static final Rarity DEPENDENCY_MISSING = new Builder().name("dep_missing").color(TextColor.fromRgb(0xFF0000)).bold().underline().build();

    /* NO COLORS */
    public static final Rarity NORMAL = createRarity("normal", null);
    public static final Rarity BOLD = createRarity("b", null, true, false, false, false, false);
    public static final Rarity ITALIC = createRarity("i", null, false, true, false, false, false);
    public static final Rarity UNDERLINE = createRarity("u", null, false, false, true, false, false);
    public static final Rarity STRIKETHROUGH = createRarity("s", null, false, false, false, true, false);
    public static final Rarity OBFUSCATED = createRarity("o", null, false, false, false, false, true);
    public static final Rarity BOLD_ITALIC = createRarity("bi", null, true, true, false, false, false);
    public static final Rarity BOLD_UNDERLINE = createRarity("bu", null, true, false, true, false, false);
    public static final Rarity BOLD_STRIKETHROUGH = createRarity("bs", null, true, false, false, true, false);
    public static final Rarity BOLD_OBFUSCATED = createRarity("bo", null, true, false, false, false, true);
    public static final Rarity BOLD_ITALIC_UNDERLINE = createRarity("biu", null, true, true, true, false, false);
    public static final Rarity BOLD_ITALIC_STRIKETHROUGH = createRarity("bis", null, true, true, false, true, false);
    public static final Rarity BOLD_ITALIC_OBFUSCATE = createRarity("bio", null, true, true, false, false, true);
    public static final Rarity BOLD_UNDERLINE_STRIKETHROUGH = createRarity("bus", null, true, false, true, true, false);
    public static final Rarity BOLD_UNDERLINE_OBFUSCATE = createRarity("buo", null, true, false, true, false, true);
    public static final Rarity BOLD_STRIKETHROUGH_OBFUSCATE = createRarity("bso", null, true, false, false, true, true);
    public static final Rarity BOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("bius", null, true, true, true, true, false);
    public static final Rarity BOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("biuo", null, true, true, true, false, true);
    public static final Rarity BOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("biso", null, true, true, false, true, true);
    public static final Rarity BOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("buso", null, true, false, true, true, true);
    public static final Rarity ITALIC_UNDERLINE = createRarity("iu", null, false, true, true, false, false);
    public static final Rarity ITALIC_STRIKETHROUGH = createRarity("is", null, false, true, false, true, false);
    public static final Rarity ITALIC_OBFUSCATE = createRarity("io", null, false, true, false, false, true);
    public static final Rarity ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("ius", null, false, true, true, true, false);
    public static final Rarity ITALIC_UNDERLINE_OBFUSCATE = createRarity("iuo", null, false, true, true, false, true);
    public static final Rarity ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("iso", null, false, true, false, true, true);
    public static final Rarity ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("iuso", null, false, true, true, true, true);
    public static final Rarity UNDERLINE_STRIKETHROUGH = createRarity("us", null, false, false, true, true, false);
    public static final Rarity UNDERLINE_OBFUSCATE = createRarity("uo", null, false, false, true, false, true);
    public static final Rarity UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("uso", null, false, false, true, true, true);
    public static final Rarity STRIKETHROUGH_OBFUSCATE = createRarity("so", null, false, false, false, true, true);
    public static final Rarity BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("biuso", null, true, true, true, true, true);
    public static final Rarity ALL = BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE;
    public static final Rarity RESET = NORMAL;

    /* WHITE */
    public static final Rarity WHITE = createRarity("white", TextColor.fromRgb(16777215));
    public static final Rarity WHITE_BOLD = createRarity("white_b", TextColor.fromRgb(16777215), true, false, false, false, false);
    public static final Rarity WHITE_ITALIC = createRarity("white_i", TextColor.fromRgb(16777215), false, true, false, false, false);
    public static final Rarity WHITE_UNDERLINE = createRarity("white_u", TextColor.fromRgb(16777215), false, false, true, false, false);
    public static final Rarity WHITE_STRIKETHROUGH = createRarity("white_s", TextColor.fromRgb(16777215), false, false, false, true, false);
    public static final Rarity WHITE_OBFUSCATED = createRarity("white_o", TextColor.fromRgb(16777215), false, false, false, false, true);
    public static final Rarity WHITE_BOLD_ITALIC = createRarity("white_bi", TextColor.fromRgb(16777215), true, true, false, false, false);
    public static final Rarity WHITE_BOLD_UNDERLINE = createRarity("white_bu", TextColor.fromRgb(16777215), true, false, true, false, false);
    public static final Rarity WHITE_BOLD_STRIKETHROUGH = createRarity("white_bs", TextColor.fromRgb(16777215), true, false, false, true, false);
    public static final Rarity WHITE_BOLD_OBFUSCATED = createRarity("white_bo", TextColor.fromRgb(16777215), true, false, false, false, true);
    public static final Rarity WHITE_BOLD_ITALIC_UNDERLINE = createRarity("white_biu", TextColor.fromRgb(16777215), true, true, true, false, false);
    public static final Rarity WHITE_BOLD_ITALIC_STRIKETHROUGH = createRarity("white_bis", TextColor.fromRgb(16777215), true, true, false, true, false);
    public static final Rarity WHITE_BOLD_ITALIC_OBFUSCATE = createRarity("white_bio", TextColor.fromRgb(16777215), true, true, false, false, true);
    public static final Rarity WHITE_BOLD_UNDERLINE_STRIKETHROUGH = createRarity("white_bus", TextColor.fromRgb(16777215), true, false, true, true, false);
    public static final Rarity WHITE_BOLD_UNDERLINE_OBFUSCATE = createRarity("white_buo", TextColor.fromRgb(16777215), true, false, true, false, true);
    public static final Rarity WHITE_BOLD_STRIKETHROUGH_OBFUSCATE = createRarity("white_bso", TextColor.fromRgb(16777215), true, false, false, true, true);
    public static final Rarity WHITE_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("white_bius", TextColor.fromRgb(16777215), true, true, true, true, false);
    public static final Rarity WHITE_BOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("white_biuo", TextColor.fromRgb(16777215), true, true, true, false, true);
    public static final Rarity WHITE_BOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("white_biso", TextColor.fromRgb(16777215), true, true, false, true, true);
    public static final Rarity WHITE_BOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("white_buso", TextColor.fromRgb(16777215), true, false, true, true, true);
    public static final Rarity WHITE_ITALIC_UNDERLINE = createRarity("white_iu", TextColor.fromRgb(16777215), false, true, true, false, false);
    public static final Rarity WHITE_ITALIC_STRIKETHROUGH = createRarity("white_is", TextColor.fromRgb(16777215), false, true, false, true, false);
    public static final Rarity WHITE_ITALIC_OBFUSCATE = createRarity("white_io", TextColor.fromRgb(16777215), false, true, false, false, true);
    public static final Rarity WHITE_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("white_ius", TextColor.fromRgb(16777215), false, true, true, true, false);
    public static final Rarity WHITE_ITALIC_UNDERLINE_OBFUSCATE = createRarity("white_iuo", TextColor.fromRgb(16777215), false, true, true, false, true);
    public static final Rarity WHITE_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("white_iso", TextColor.fromRgb(16777215), false, true, false, true, true);
    public static final Rarity WHITE_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("white_iuso", TextColor.fromRgb(16777215), false, true, true, true, true);
    public static final Rarity WHITE_UNDERLINE_STRIKETHROUGH = createRarity("white_us", TextColor.fromRgb(16777215), false, false, true, true, false);
    public static final Rarity WHITE_UNDERLINE_OBFUSCATE = createRarity("white_uo", TextColor.fromRgb(16777215), false, false, true, false, true);
    public static final Rarity WHITE_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("white_uso", TextColor.fromRgb(16777215), false, false, true, true, true);
    public static final Rarity WHITE_STRIKETHROUGH_OBFUSCATE = createRarity("white_so", TextColor.fromRgb(16777215), false, false, false, true, true);
    public static final Rarity WHITE_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("white_biuso", TextColor.fromRgb(16777215), true, true, true, true, true);
    public static final Rarity WHITE_ALL = WHITE_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE;

    /* GRAY */
    public static final Rarity GRAY = createRarity("gray", TextColor.fromRgb(11184810));
    public static final Rarity GRAY_BOLD = createRarity("gray_b", TextColor.fromRgb(11184810), true, false, false, false, false);
    public static final Rarity GRAY_ITALIC = createRarity("gray_i", TextColor.fromRgb(11184810), false, true, false, false, false);
    public static final Rarity GRAY_UNDERLINE = createRarity("gray_u", TextColor.fromRgb(11184810), false, false, true, false, false);
    public static final Rarity GRAY_STRIKETHROUGH = createRarity("gray_s", TextColor.fromRgb(11184810), false, false, false, true, false);
    public static final Rarity GRAY_OBFUSCATED = createRarity("gray_o", TextColor.fromRgb(11184810), false, false, false, false, true);
    public static final Rarity GRAY_BOLD_ITALIC = createRarity("gray_bi", TextColor.fromRgb(11184810), true, true, false, false, false);
    public static final Rarity GRAY_BOLD_UNDERLINE = createRarity("gray_bu", TextColor.fromRgb(11184810), true, false, true, false, false);
    public static final Rarity GRAY_BOLD_STRIKETHROUGH = createRarity("gray_bs", TextColor.fromRgb(11184810), true, false, false, true, false);
    public static final Rarity GRAY_BOLD_OBFUSCATED = createRarity("gray_bo", TextColor.fromRgb(11184810), true, false, false, false, true);
    public static final Rarity GRAY_BOLD_ITALIC_UNDERLINE = createRarity("gray_biu", TextColor.fromRgb(11184810), true, true, true, false, false);
    public static final Rarity GRAY_BOLD_ITALIC_STRIKETHROUGH = createRarity("gray_bis", TextColor.fromRgb(11184810), true, true, false, true, false);
    public static final Rarity GRAY_BOLD_ITALIC_OBFUSCATE = createRarity("gray_bio", TextColor.fromRgb(11184810), true, true, false, false, true);
    public static final Rarity GRAY_BOLD_UNDERLINE_STRIKETHROUGH = createRarity("gray_bus", TextColor.fromRgb(11184810), true, false, true, true, false);
    public static final Rarity GRAY_BOLD_UNDERLINE_OBFUSCATE = createRarity("gray_buo", TextColor.fromRgb(11184810), true, false, true, false, true);
    public static final Rarity GRAY_BOLD_STRIKETHROUGH_OBFUSCATE = createRarity("gray_bso", TextColor.fromRgb(11184810), true, false, false, true, true);
    public static final Rarity GRAY_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("gray_bius", TextColor.fromRgb(11184810), true, true, true, true, false);
    public static final Rarity GRAY_BOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("gray_biuo", TextColor.fromRgb(11184810), true, true, true, false, true);
    public static final Rarity GRAY_BOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("gray_biso", TextColor.fromRgb(11184810), true, true, false, true, true);
    public static final Rarity GRAY_BOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("gray_buso", TextColor.fromRgb(11184810), true, false, true, true, true);
    public static final Rarity GRAY_ITALIC_UNDERLINE = createRarity("gray_iu", TextColor.fromRgb(11184810), false, true, true, false, false);
    public static final Rarity GRAY_ITALIC_STRIKETHROUGH = createRarity("gray_is", TextColor.fromRgb(11184810), false, true, false, true, false);
    public static final Rarity GRAY_ITALIC_OBFUSCATE = createRarity("gray_io", TextColor.fromRgb(11184810), false, true, false, false, true);
    public static final Rarity GRAY_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("gray_ius", TextColor.fromRgb(11184810), false, true, true, true, false);
    public static final Rarity GRAY_ITALIC_UNDERLINE_OBFUSCATE = createRarity("gray_iuo", TextColor.fromRgb(11184810), false, true, true, false, true);
    public static final Rarity GRAY_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("gray_iso", TextColor.fromRgb(11184810), false, true, false, true, true);
    public static final Rarity GRAY_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("gray_iuso", TextColor.fromRgb(11184810), false, true, true, true, true);
    public static final Rarity GRAY_UNDERLINE_STRIKETHROUGH = createRarity("gray_us", TextColor.fromRgb(11184810), false, false, true, true, false);
    public static final Rarity GRAY_UNDERLINE_OBFUSCATE = createRarity("gray_uo", TextColor.fromRgb(11184810), false, false, true, false, true);
    public static final Rarity GRAY_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("gray_uso", TextColor.fromRgb(11184810), false, false, true, true, true);
    public static final Rarity GRAY_STRIKETHROUGH_OBFUSCATE = createRarity("gray_so", TextColor.fromRgb(11184810), false, false, false, true, true);
    public static final Rarity GRAY_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("gray_biuso", TextColor.fromRgb(11184810), true, true, true, true, true);
    public static final Rarity GRAY_ALL = GRAY_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE;

    /* DARK GRAY */
    public static final Rarity DARK_GRAY = createRarity("dark_gray", TextColor.fromRgb(5592405));
    public static final Rarity DARK_GRAY_BOLD = createRarity("dark_gray_b", TextColor.fromRgb(5592405), true, false, false, false, false);
    public static final Rarity DARK_GRAY_ITALIC = createRarity("dark_gray_i", TextColor.fromRgb(5592405), false, true, false, false, false);
    public static final Rarity DARK_GRAY_UNDERLINE = createRarity("dark_gray_u", TextColor.fromRgb(5592405), false, false, true, false, false);
    public static final Rarity DARK_GRAY_STRIKETHROUGH = createRarity("dark_gray_s", TextColor.fromRgb(5592405), false, false, false, true, false);
    public static final Rarity DARK_GRAY_OBFUSCATED = createRarity("dark_gray_o", TextColor.fromRgb(5592405), false, false, false, false, true);
    public static final Rarity DARK_GRAY_BOLD_ITALIC = createRarity("dark_gray_bi", TextColor.fromRgb(5592405), true, true, false, false, false);
    public static final Rarity DARK_GRAY_BOLD_UNDERLINE = createRarity("dark_gray_bu", TextColor.fromRgb(5592405), true, false, true, false, false);
    public static final Rarity DARK_GRAY_BOLD_STRIKETHROUGH = createRarity("dark_gray_bs", TextColor.fromRgb(5592405), true, false, false, true, false);
    public static final Rarity DARK_GRAY_BOLD_OBFUSCATED = createRarity("dark_gray_bo", TextColor.fromRgb(5592405), true, false, false, false, true);
    public static final Rarity DARK_GRAY_BOLD_ITALIC_UNDERLINE = createRarity("dark_gray_biu", TextColor.fromRgb(5592405), true, true, true, false, false);
    public static final Rarity DARK_GRAY_BOLD_ITALIC_STRIKETHROUGH = createRarity("dark_gray_bis", TextColor.fromRgb(5592405), true, true, false, true, false);
    public static final Rarity DARK_GRAY_BOLD_ITALIC_OBFUSCATE = createRarity("dark_gray_bio", TextColor.fromRgb(5592405), true, true, false, false, true);
    public static final Rarity DARK_GRAY_BOLD_UNDERLINE_STRIKETHROUGH = createRarity("dark_gray_bus", TextColor.fromRgb(5592405), true, false, true, true, false);
    public static final Rarity DARK_GRAY_BOLD_UNDERLINE_OBFUSCATE = createRarity("dark_gray_buo", TextColor.fromRgb(5592405), true, false, true, false, true);
    public static final Rarity DARK_GRAY_BOLD_STRIKETHROUGH_OBFUSCATE = createRarity("dark_gray_bso", TextColor.fromRgb(5592405), true, false, false, true, true);
    public static final Rarity DARK_GRAY_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("dark_gray_bius", TextColor.fromRgb(5592405), true, true, true, true, false);
    public static final Rarity DARK_GRAY_BOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("dark_gray_biuo", TextColor.fromRgb(5592405), true, true, true, false, true);
    public static final Rarity DARK_GRAY_BOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("dark_gray_biso", TextColor.fromRgb(5592405), true, true, false, true, true);
    public static final Rarity DARK_GRAY_BOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_gray_buso", TextColor.fromRgb(5592405), true, false, true, true, true);
    public static final Rarity DARK_GRAY_ITALIC_UNDERLINE = createRarity("dark_gray_iu", TextColor.fromRgb(5592405), false, true, true, false, false);
    public static final Rarity DARK_GRAY_ITALIC_STRIKETHROUGH = createRarity("dark_gray_is", TextColor.fromRgb(5592405), false, true, false, true, false);
    public static final Rarity DARK_GRAY_ITALIC_OBFUSCATE = createRarity("dark_gray_io", TextColor.fromRgb(5592405), false, true, false, false, true);
    public static final Rarity DARK_GRAY_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("dark_gray_ius", TextColor.fromRgb(5592405), false, true, true, true, false);
    public static final Rarity DARK_GRAY_ITALIC_UNDERLINE_OBFUSCATE = createRarity("dark_gray_iuo", TextColor.fromRgb(5592405), false, true, true, false, true);
    public static final Rarity DARK_GRAY_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("dark_gray_iso", TextColor.fromRgb(5592405), false, true, false, true, true);
    public static final Rarity DARK_GRAY_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_gray_iuso", TextColor.fromRgb(5592405), false, true, true, true, true);
    public static final Rarity DARK_GRAY_UNDERLINE_STRIKETHROUGH = createRarity("dark_gray_us", TextColor.fromRgb(5592405), false, false, true, true, false);
    public static final Rarity DARK_GRAY_UNDERLINE_OBFUSCATE = createRarity("dark_gray_uo", TextColor.fromRgb(5592405), false, false, true, false, true);
    public static final Rarity DARK_GRAY_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_gray_uso", TextColor.fromRgb(5592405), false, false, true, true, true);
    public static final Rarity DARK_GRAY_STRIKETHROUGH_OBFUSCATE = createRarity("dark_gray_so", TextColor.fromRgb(5592405), false, false, false, true, true);
    public static final Rarity DARK_GRAY_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_gray_biuso", TextColor.fromRgb(5592405), true, true, true, true, true);
    public static final Rarity DARK_GRAY_ALL = DARK_GRAY_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE;

    /* BLACK */
    public static final Rarity BLACK = createRarity("black", TextColor.fromRgb(0));
    public static final Rarity BLACK_BOLD = createRarity("black_b", TextColor.fromRgb(0), true, false, false, false, false);
    public static final Rarity BLACK_ITALIC = createRarity("black_i", TextColor.fromRgb(0), false, true, false, false, false);
    public static final Rarity BLACK_UNDERLINE = createRarity("black_u", TextColor.fromRgb(0), false, false, true, false, false);
    public static final Rarity BLACK_STRIKETHROUGH = createRarity("black_s", TextColor.fromRgb(0), false, false, false, true, false);
    public static final Rarity BLACK_OBFUSCATED = createRarity("black_o", TextColor.fromRgb(0), false, false, false, false, true);
    public static final Rarity BLACK_BOLD_ITALIC = createRarity("black_bi", TextColor.fromRgb(0), true, true, false, false, false);
    public static final Rarity BLACK_BOLD_UNDERLINE = createRarity("black_bu", TextColor.fromRgb(0), true, false, true, false, false);
    public static final Rarity BLACK_BOLD_STRIKETHROUGH = createRarity("black_bs", TextColor.fromRgb(0), true, false, false, true, false);
    public static final Rarity BLACK_BOLD_OBFUSCATED = createRarity("black_bo", TextColor.fromRgb(0), true, false, false, false, true);
    public static final Rarity BLACK_BOLD_ITALIC_UNDERLINE = createRarity("black_biu", TextColor.fromRgb(0), true, true, true, false, false);
    public static final Rarity BLACK_BOLD_ITALIC_STRIKETHROUGH = createRarity("black_bis", TextColor.fromRgb(0), true, true, false, true, false);
    public static final Rarity BLACK_BOLD_ITALIC_OBFUSCATE = createRarity("black_bio", TextColor.fromRgb(0), true, true, false, false, true);
    public static final Rarity BLACK_BOLD_UNDERLINE_STRIKETHROUGH = createRarity("black_bus", TextColor.fromRgb(0), true, false, true, true, false);
    public static final Rarity BLACK_BOLD_UNDERLINE_OBFUSCATE = createRarity("black_buo", TextColor.fromRgb(0), true, false, true, false, true);
    public static final Rarity BLACK_BOLD_STRIKETHROUGH_OBFUSCATE = createRarity("black_bso", TextColor.fromRgb(0), true, false, false, true, true);
    public static final Rarity BLACK_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("black_bius", TextColor.fromRgb(0), true, true, true, true, false);
    public static final Rarity BLACK_BOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("black_biuo", TextColor.fromRgb(0), true, true, true, false, true);
    public static final Rarity BLACK_BOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("black_biso", TextColor.fromRgb(0), true, true, false, true, true);
    public static final Rarity BLACK_BOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("black_buso", TextColor.fromRgb(0), true, false, true, true, true);
    public static final Rarity BLACK_ITALIC_UNDERLINE = createRarity("black_iu", TextColor.fromRgb(0), false, true, true, false, false);
    public static final Rarity BLACK_ITALIC_STRIKETHROUGH = createRarity("black_is", TextColor.fromRgb(0), false, true, false, true, false);
    public static final Rarity BLACK_ITALIC_OBFUSCATE = createRarity("black_io", TextColor.fromRgb(0), false, true, false, false, true);
    public static final Rarity BLACK_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("black_ius", TextColor.fromRgb(0), false, true, true, true, false);
    public static final Rarity BLACK_ITALIC_UNDERLINE_OBFUSCATE = createRarity("black_iuo", TextColor.fromRgb(0), false, true, true, false, true);
    public static final Rarity BLACK_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("black_iso", TextColor.fromRgb(0), false, true, false, true, true);
    public static final Rarity BLACK_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("black_iuso", TextColor.fromRgb(0), false, true, true, true, true);
    public static final Rarity BLACK_UNDERLINE_STRIKETHROUGH = createRarity("black_us", TextColor.fromRgb(0), false, false, true, true, false);
    public static final Rarity BLACK_UNDERLINE_OBFUSCATE = createRarity("black_uo", TextColor.fromRgb(0), false, false, true, false, true);
    public static final Rarity BLACK_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("black_uso", TextColor.fromRgb(0), false, false, true, true, true);
    public static final Rarity BLACK_STRIKETHROUGH_OBFUSCATE = createRarity("black_so", TextColor.fromRgb(0), false, false, false, true, true);
    public static final Rarity BLACK_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("black_biuso", TextColor.fromRgb(0), true, true, true, true, true);
    public static final Rarity BLACK_ALL = BLACK_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE;

    /* RED */
    public static final Rarity RED = createRarity("red", TextColor.fromRgb(16733525));
    public static final Rarity RED_BOLD = createRarity("red_b", TextColor.fromRgb(16733525), true, false, false, false, false);
    public static final Rarity RED_ITALIC = createRarity("red_i", TextColor.fromRgb(16733525), false, true, false, false, false);
    public static final Rarity RED_UNDERLINE = createRarity("red_u", TextColor.fromRgb(16733525), false, false, true, false, false);
    public static final Rarity RED_STRIKETHROUGH = createRarity("red_s", TextColor.fromRgb(16733525), false, false, false, true, false);
    public static final Rarity RED_OBFUSCATED = createRarity("red_o", TextColor.fromRgb(16733525), false, false, false, false, true);
    public static final Rarity RED_BOLD_ITALIC = createRarity("red_bi", TextColor.fromRgb(16733525), true, true, false, false, false);
    public static final Rarity RED_BOLD_UNDERLINE = createRarity("red_bu", TextColor.fromRgb(16733525), true, false, true, false, false);
    public static final Rarity RED_BOLD_STRIKETHROUGH = createRarity("red_bs", TextColor.fromRgb(16733525), true, false, false, true, false);
    public static final Rarity RED_BOLD_OBFUSCATED = createRarity("red_bo", TextColor.fromRgb(16733525), true, false, false, false, true);
    public static final Rarity RED_BOLD_ITALIC_UNDERLINE = createRarity("red_biu", TextColor.fromRgb(16733525), true, true, true, false, false);
    public static final Rarity RED_BOLD_ITALIC_STRIKETHROUGH = createRarity("red_bis", TextColor.fromRgb(16733525), true, true, false, true, false);
    public static final Rarity RED_BOLD_ITALIC_OBFUSCATE = createRarity("red_bio", TextColor.fromRgb(16733525), true, true, false, false, true);
    public static final Rarity RED_BOLD_UNDERLINE_STRIKETHROUGH = createRarity("red_bus", TextColor.fromRgb(16733525), true, false, true, true, false);
    public static final Rarity RED_BOLD_UNDERLINE_OBFUSCATE = createRarity("red_buo", TextColor.fromRgb(16733525), true, false, true, false, true);
    public static final Rarity RED_BOLD_STRIKETHROUGH_OBFUSCATE = createRarity("red_bso", TextColor.fromRgb(16733525), true, false, false, true, true);
    public static final Rarity RED_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("red_bius", TextColor.fromRgb(16733525), true, true, true, true, false);
    public static final Rarity RED_BOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("red_biuo", TextColor.fromRgb(16733525), true, true, true, false, true);
    public static final Rarity RED_BOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("red_biso", TextColor.fromRgb(16733525), true, true, false, true, true);
    public static final Rarity RED_BOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("red_buso", TextColor.fromRgb(16733525), true, false, true, true, true);
    public static final Rarity RED_ITALIC_UNDERLINE = createRarity("red_iu", TextColor.fromRgb(16733525), false, true, true, false, false);
    public static final Rarity RED_ITALIC_STRIKETHROUGH = createRarity("red_is", TextColor.fromRgb(16733525), false, true, false, true, false);
    public static final Rarity RED_ITALIC_OBFUSCATE = createRarity("red_io", TextColor.fromRgb(16733525), false, true, false, false, true);
    public static final Rarity RED_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("red_ius", TextColor.fromRgb(16733525), false, true, true, true, false);
    public static final Rarity RED_ITALIC_UNDERLINE_OBFUSCATE = createRarity("red_iuo", TextColor.fromRgb(16733525), false, true, true, false, true);
    public static final Rarity RED_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("red_iso", TextColor.fromRgb(16733525), false, true, false, true, true);
    public static final Rarity RED_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("red_iuso", TextColor.fromRgb(16733525), false, true, true, true, true);
    public static final Rarity RED_UNDERLINE_STRIKETHROUGH = createRarity("red_us", TextColor.fromRgb(16733525), false, false, true, true, false);
    public static final Rarity RED_UNDERLINE_OBFUSCATE = createRarity("red_uo", TextColor.fromRgb(16733525), false, false, true, false, true);
    public static final Rarity RED_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("red_uso", TextColor.fromRgb(16733525), false, false, true, true, true);
    public static final Rarity RED_STRIKETHROUGH_OBFUSCATE = createRarity("red_so", TextColor.fromRgb(16733525), false, false, false, true, true);
    public static final Rarity RED_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("red_biuso", TextColor.fromRgb(16733525), true, true, true, true, true);
    public static final Rarity RED_ALL = RED_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE;

    /* DARK RED */
    public static final Rarity DARK_RED = createRarity("dark_red", TextColor.fromRgb(11141120));
    public static final Rarity DARK_RED_BOLD = createRarity("dark_red_b", TextColor.fromRgb(11141120), true, false, false, false, false);
    public static final Rarity DARK_RED_ITALIC = createRarity("dark_red_i", TextColor.fromRgb(11141120), false, true, false, false, false);
    public static final Rarity DARK_RED_UNDERLINE = createRarity("dark_red_u", TextColor.fromRgb(11141120), false, false, true, false, false);
    public static final Rarity DARK_RED_STRIKETHROUGH = createRarity("dark_red_s", TextColor.fromRgb(11141120), false, false, false, true, false);
    public static final Rarity DARK_RED_OBFUSCATED = createRarity("dark_red_o", TextColor.fromRgb(11141120), false, false, false, false, true);
    public static final Rarity DARK_RED_BOLD_ITALIC = createRarity("dark_red_bi", TextColor.fromRgb(11141120), true, true, false, false, false);
    public static final Rarity DARK_RED_BOLD_UNDERLINE = createRarity("dark_red_bu", TextColor.fromRgb(11141120), true, false, true, false, false);
    public static final Rarity DARK_RED_BOLD_STRIKETHROUGH = createRarity("dark_red_bs", TextColor.fromRgb(11141120), true, false, false, true, false);
    public static final Rarity DARK_RED_BOLD_OBFUSCATED = createRarity("dark_red_bo", TextColor.fromRgb(11141120), true, false, false, false, true);
    public static final Rarity DARK_RED_BOLD_ITALIC_UNDERLINE = createRarity("dark_red_biu", TextColor.fromRgb(11141120), true, true, true, false, false);
    public static final Rarity DARK_RED_BOLD_ITALIC_STRIKETHROUGH = createRarity("dark_red_bis", TextColor.fromRgb(11141120), true, true, false, true, false);
    public static final Rarity DARK_RED_BOLD_ITALIC_OBFUSCATE = createRarity("dark_red_bio", TextColor.fromRgb(11141120), true, true, false, false, true);
    public static final Rarity DARK_RED_BOLD_UNDERLINE_STRIKETHROUGH = createRarity("dark_red_bus", TextColor.fromRgb(11141120), true, false, true, true, false);
    public static final Rarity DARK_RED_BOLD_UNDERLINE_OBFUSCATE = createRarity("dark_red_buo", TextColor.fromRgb(11141120), true, false, true, false, true);
    public static final Rarity DARK_RED_BOLD_STRIKETHROUGH_OBFUSCATE = createRarity("dark_red_bso", TextColor.fromRgb(11141120), true, false, false, true, true);
    public static final Rarity DARK_RED_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("dark_red_bius", TextColor.fromRgb(11141120), true, true, true, true, false);
    public static final Rarity DARK_RED_BOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("dark_red_biuo", TextColor.fromRgb(11141120), true, true, true, false, true);
    public static final Rarity DARK_RED_BOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("dark_red_biso", TextColor.fromRgb(11141120), true, true, false, true, true);
    public static final Rarity DARK_RED_BOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_red_buso", TextColor.fromRgb(11141120), true, false, true, true, true);
    public static final Rarity DARK_RED_ITALIC_UNDERLINE = createRarity("dark_red_iu", TextColor.fromRgb(11141120), false, true, true, false, false);
    public static final Rarity DARK_RED_ITALIC_STRIKETHROUGH = createRarity("dark_red_is", TextColor.fromRgb(11141120), false, true, false, true, false);
    public static final Rarity DARK_RED_ITALIC_OBFUSCATE = createRarity("dark_red_io", TextColor.fromRgb(11141120), false, true, false, false, true);
    public static final Rarity DARK_RED_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("dark_red_ius", TextColor.fromRgb(11141120), false, true, true, true, false);
    public static final Rarity DARK_RED_ITALIC_UNDERLINE_OBFUSCATE = createRarity("dark_red_iuo", TextColor.fromRgb(11141120), false, true, true, false, true);
    public static final Rarity DARK_RED_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("dark_red_iso", TextColor.fromRgb(11141120), false, true, false, true, true);
    public static final Rarity DARK_RED_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_red_iuso", TextColor.fromRgb(11141120), false, true, true, true, true);
    public static final Rarity DARK_RED_UNDERLINE_STRIKETHROUGH = createRarity("dark_red_us", TextColor.fromRgb(11141120), false, false, true, true, false);
    public static final Rarity DARK_RED_UNDERLINE_OBFUSCATE = createRarity("dark_red_uo", TextColor.fromRgb(11141120), false, false, true, false, true);
    public static final Rarity DARK_RED_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_red_uso", TextColor.fromRgb(11141120), false, false, true, true, true);
    public static final Rarity DARK_RED_STRIKETHROUGH_OBFUSCATE = createRarity("dark_red_so", TextColor.fromRgb(11141120), false, false, false, true, true);
    public static final Rarity DARK_RED_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_red_biuso", TextColor.fromRgb(11141120), true, true, true, true, true);
    public static final Rarity DARK_RED_ALL = DARK_RED_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE;

    /* YELLOW */
    public static final Rarity YELLOW = createRarity("yellow", TextColor.fromRgb(16777045));
    public static final Rarity YELLOW_BOLD = createRarity("yellow_b", TextColor.fromRgb(16777045), true, false, false, false, false);
    public static final Rarity YELLOW_ITALIC = createRarity("yellow_i", TextColor.fromRgb(16777045), false, true, false, false, false);
    public static final Rarity YELLOW_UNDERLINE = createRarity("yellow_u", TextColor.fromRgb(16777045), false, false, true, false, false);
    public static final Rarity YELLOW_STRIKETHROUGH = createRarity("yellow_s", TextColor.fromRgb(16777045), false, false, false, true, false);
    public static final Rarity YELLOW_OBFUSCATED = createRarity("yellow_o", TextColor.fromRgb(16777045), false, false, false, false, true);
    public static final Rarity YELLOW_BOLD_ITALIC = createRarity("yellow_bi", TextColor.fromRgb(16777045), true, true, false, false, false);
    public static final Rarity YELLOW_BOLD_UNDERLINE = createRarity("yellow_bu", TextColor.fromRgb(16777045), true, false, true, false, false);
    public static final Rarity YELLOW_BOLD_STRIKETHROUGH = createRarity("yellow_bs", TextColor.fromRgb(16777045), true, false, false, true, false);
    public static final Rarity YELLOW_BOLD_OBFUSCATED = createRarity("yellow_bo", TextColor.fromRgb(16777045), true, false, false, false, true);
    public static final Rarity YELLOW_BOLD_ITALIC_UNDERLINE = createRarity("yellow_biu", TextColor.fromRgb(16777045), true, true, true, false, false);
    public static final Rarity YELLOW_BOLD_ITALIC_STRIKETHROUGH = createRarity("yellow_bis", TextColor.fromRgb(16777045), true, true, false, true, false);
    public static final Rarity YELLOW_BOLD_ITALIC_OBFUSCATE = createRarity("yellow_bio", TextColor.fromRgb(16777045), true, true, false, false, true);
    public static final Rarity YELLOW_BOLD_UNDERLINE_STRIKETHROUGH = createRarity("yellow_bus", TextColor.fromRgb(16777045), true, false, true, true, false);
    public static final Rarity YELLOW_BOLD_UNDERLINE_OBFUSCATE = createRarity("yellow_buo", TextColor.fromRgb(16777045), true, false, true, false, true);
    public static final Rarity YELLOW_BOLD_STRIKETHROUGH_OBFUSCATE = createRarity("yellow_bso", TextColor.fromRgb(16777045), true, false, false, true, true);
    public static final Rarity YELLOW_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("yellow_bius", TextColor.fromRgb(16777045), true, true, true, true, false);
    public static final Rarity YELLOW_BOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("yellow_biuo", TextColor.fromRgb(16777045), true, true, true, false, true);
    public static final Rarity YELLOW_BOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("yellow_biso", TextColor.fromRgb(16777045), true, true, false, true, true);
    public static final Rarity YELLOW_BOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("yellow_buso", TextColor.fromRgb(16777045), true, false, true, true, true);
    public static final Rarity YELLOW_ITALIC_UNDERLINE = createRarity("yellow_iu", TextColor.fromRgb(16777045), false, true, true, false, false);
    public static final Rarity YELLOW_ITALIC_STRIKETHROUGH = createRarity("yellow_is", TextColor.fromRgb(16777045), false, true, false, true, false);
    public static final Rarity YELLOW_ITALIC_OBFUSCATE = createRarity("yellow_io", TextColor.fromRgb(16777045), false, true, false, false, true);
    public static final Rarity YELLOW_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("yellow_ius", TextColor.fromRgb(16777045), false, true, true, true, false);
    public static final Rarity YELLOW_ITALIC_UNDERLINE_OBFUSCATE = createRarity("yellow_iuo", TextColor.fromRgb(16777045), false, true, true, false, true);
    public static final Rarity YELLOW_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("yellow_iso", TextColor.fromRgb(16777045), false, true, false, true, true);
    public static final Rarity YELLOW_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("yellow_iuso", TextColor.fromRgb(16777045), false, true, true, true, true);
    public static final Rarity YELLOW_UNDERLINE_STRIKETHROUGH = createRarity("yellow_us", TextColor.fromRgb(16777045), false, false, true, true, false);
    public static final Rarity YELLOW_UNDERLINE_OBFUSCATE = createRarity("yellow_uo", TextColor.fromRgb(16777045), false, false, true, false, true);
    public static final Rarity YELLOW_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("yellow_uso", TextColor.fromRgb(16777045), false, false, true, true, true);
    public static final Rarity YELLOW_STRIKETHROUGH_OBFUSCATE = createRarity("yellow_so", TextColor.fromRgb(16777045), false, false, false, true, true);
    public static final Rarity YELLOW_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("yellow_biuso", TextColor.fromRgb(16777045), true, true, true, true, true);
    public static final Rarity YELLOW_ALL = YELLOW_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE;

    /* GOLD */
    public static final Rarity GOLD = createRarity("gold", TextColor.fromRgb(16755200));
    public static final Rarity GOLD_BOLD = createRarity("gold_b", TextColor.fromRgb(16755200), true, false, false, false, false);
    public static final Rarity GOLD_ITALIC = createRarity("gold_i", TextColor.fromRgb(16755200), false, true, false, false, false);
    public static final Rarity GOLD_UNDERLINE = createRarity("gold_u", TextColor.fromRgb(16755200), false, false, true, false, false);
    public static final Rarity GOLD_STRIKETHROUGH = createRarity("gold_s", TextColor.fromRgb(16755200), false, false, false, true, false);
    public static final Rarity GOLD_OBFUSCATED = createRarity("gold_o", TextColor.fromRgb(16755200), false, false, false, false, true);
    public static final Rarity GOLD_BOLD_ITALIC = createRarity("gold_bi", TextColor.fromRgb(16755200), true, true, false, false, false);
    public static final Rarity GOLD_BOLD_UNDERLINE = createRarity("gold_bu", TextColor.fromRgb(16755200), true, false, true, false, false);
    public static final Rarity GOLD_BOLD_STRIKETHROUGH = createRarity("gold_bs", TextColor.fromRgb(16755200), true, false, false, true, false);
    public static final Rarity GOLD_BOLD_OBFUSCATED = createRarity("gold_bo", TextColor.fromRgb(16755200), true, false, false, false, true);
    public static final Rarity GOLD_BOLD_ITALIC_UNDERLINE = createRarity("gold_biu", TextColor.fromRgb(16755200), true, true, true, false, false);
    public static final Rarity GOLD_BOLD_ITALIC_STRIKETHROUGH = createRarity("gold_bis", TextColor.fromRgb(16755200), true, true, false, true, false);
    public static final Rarity GOLD_BOLD_ITALIC_OBFUSCATE = createRarity("gold_bio", TextColor.fromRgb(16755200), true, true, false, false, true);
    public static final Rarity GOLD_BOLD_UNDERLINE_STRIKETHROUGH = createRarity("gold_bus", TextColor.fromRgb(16755200), true, false, true, true, false);
    public static final Rarity GOLD_BOLD_UNDERLINE_OBFUSCATE = createRarity("gold_buo", TextColor.fromRgb(16755200), true, false, true, false, true);
    public static final Rarity GOLD_BOLD_STRIKETHROUGH_OBFUSCATE = createRarity("gold_bso", TextColor.fromRgb(16755200), true, false, false, true, true);
    public static final Rarity GOLD_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("gold_bius", TextColor.fromRgb(16755200), true, true, true, true, false);
    public static final Rarity GOLD_BOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("gold_biuo", TextColor.fromRgb(16755200), true, true, true, false, true);
    public static final Rarity GOLD_BOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("gold_biso", TextColor.fromRgb(16755200), true, true, false, true, true);
    public static final Rarity GOLD_BOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("gold_buso", TextColor.fromRgb(16755200), true, false, true, true, true);
    public static final Rarity GOLD_ITALIC_UNDERLINE = createRarity("gold_iu", TextColor.fromRgb(16755200), false, true, true, false, false);
    public static final Rarity GOLD_ITALIC_STRIKETHROUGH = createRarity("gold_is", TextColor.fromRgb(16755200), false, true, false, true, false);
    public static final Rarity GOLD_ITALIC_OBFUSCATE = createRarity("gold_io", TextColor.fromRgb(16755200), false, true, false, false, true);
    public static final Rarity GOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("gold_ius", TextColor.fromRgb(16755200), false, true, true, true, false);
    public static final Rarity GOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("gold_iuo", TextColor.fromRgb(16755200), false, true, true, false, true);
    public static final Rarity GOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("gold_iso", TextColor.fromRgb(16755200), false, true, false, true, true);
    public static final Rarity GOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("gold_iuso", TextColor.fromRgb(16755200), false, true, true, true, true);
    public static final Rarity GOLD_UNDERLINE_STRIKETHROUGH = createRarity("gold_us", TextColor.fromRgb(16755200), false, false, true, true, false);
    public static final Rarity GOLD_UNDERLINE_OBFUSCATE = createRarity("gold_uo", TextColor.fromRgb(16755200), false, false, true, false, true);
    public static final Rarity GOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("gold_uso", TextColor.fromRgb(16755200), false, false, true, true, true);
    public static final Rarity GOLD_STRIKETHROUGH_OBFUSCATE = createRarity("gold_so", TextColor.fromRgb(16755200), false, false, false, true, true);
    public static final Rarity GOLD_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("gold_biuso", TextColor.fromRgb(16755200), true, true, true, true, true);
    public static final Rarity GOLD_ALL = GOLD_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE;

    /* GREEN */
    public static final Rarity GREEN = createRarity("green", TextColor.fromRgb(5635925));
    public static final Rarity GREEN_BOLD = createRarity("green_b", TextColor.fromRgb(5635925), true, false, false, false, false);
    public static final Rarity GREEN_ITALIC = createRarity("green_i", TextColor.fromRgb(5635925), false, true, false, false, false);
    public static final Rarity GREEN_UNDERLINE = createRarity("green_u", TextColor.fromRgb(5635925), false, false, true, false, false);
    public static final Rarity GREEN_STRIKETHROUGH = createRarity("green_s", TextColor.fromRgb(5635925), false, false, false, true, false);
    public static final Rarity GREEN_OBFUSCATED = createRarity("green_o", TextColor.fromRgb(5635925), false, false, false, false, true);
    public static final Rarity GREEN_BOLD_ITALIC = createRarity("green_bi", TextColor.fromRgb(5635925), true, true, false, false, false);
    public static final Rarity GREEN_BOLD_UNDERLINE = createRarity("green_bu", TextColor.fromRgb(5635925), true, false, true, false, false);
    public static final Rarity GREEN_BOLD_STRIKETHROUGH = createRarity("green_bs", TextColor.fromRgb(5635925), true, false, false, true, false);
    public static final Rarity GREEN_BOLD_OBFUSCATED = createRarity("green_bo", TextColor.fromRgb(5635925), true, false, false, false, true);
    public static final Rarity GREEN_BOLD_ITALIC_UNDERLINE = createRarity("green_biu", TextColor.fromRgb(5635925), true, true, true, false, false);
    public static final Rarity GREEN_BOLD_ITALIC_STRIKETHROUGH = createRarity("green_bis", TextColor.fromRgb(5635925), true, true, false, true, false);
    public static final Rarity GREEN_BOLD_ITALIC_OBFUSCATE = createRarity("green_bio", TextColor.fromRgb(5635925), true, true, false, false, true);
    public static final Rarity GREEN_BOLD_UNDERLINE_STRIKETHROUGH = createRarity("green_bus", TextColor.fromRgb(5635925), true, false, true, true, false);
    public static final Rarity GREEN_BOLD_UNDERLINE_OBFUSCATE = createRarity("green_buo", TextColor.fromRgb(5635925), true, false, true, false, true);
    public static final Rarity GREEN_BOLD_STRIKETHROUGH_OBFUSCATE = createRarity("green_bso", TextColor.fromRgb(5635925), true, false, false, true, true);
    public static final Rarity GREEN_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("green_bius", TextColor.fromRgb(5635925), true, true, true, true, false);
    public static final Rarity GREEN_BOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("green_biuo", TextColor.fromRgb(5635925), true, true, true, false, true);
    public static final Rarity GREEN_BOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("green_biso", TextColor.fromRgb(5635925), true, true, false, true, true);
    public static final Rarity GREEN_BOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("green_buso", TextColor.fromRgb(5635925), true, false, true, true, true);
    public static final Rarity GREEN_ITALIC_UNDERLINE = createRarity("green_iu", TextColor.fromRgb(5635925), false, true, true, false, false);
    public static final Rarity GREEN_ITALIC_STRIKETHROUGH = createRarity("green_is", TextColor.fromRgb(5635925), false, true, false, true, false);
    public static final Rarity GREEN_ITALIC_OBFUSCATE = createRarity("green_io", TextColor.fromRgb(5635925), false, true, false, false, true);
    public static final Rarity GREEN_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("green_ius", TextColor.fromRgb(5635925), false, true, true, true, false);
    public static final Rarity GREEN_ITALIC_UNDERLINE_OBFUSCATE = createRarity("green_iuo", TextColor.fromRgb(5635925), false, true, true, false, true);
    public static final Rarity GREEN_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("green_iso", TextColor.fromRgb(5635925), false, true, false, true, true);
    public static final Rarity GREEN_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("green_iuso", TextColor.fromRgb(5635925), false, true, true, true, true);
    public static final Rarity GREEN_UNDERLINE_STRIKETHROUGH = createRarity("green_us", TextColor.fromRgb(5635925), false, false, true, true, false);
    public static final Rarity GREEN_UNDERLINE_OBFUSCATE = createRarity("green_uo", TextColor.fromRgb(5635925), false, false, true, false, true);
    public static final Rarity GREEN_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("green_uso", TextColor.fromRgb(5635925), false, false, true, true, true);
    public static final Rarity GREEN_STRIKETHROUGH_OBFUSCATE = createRarity("green_so", TextColor.fromRgb(5635925), false, false, false, true, true);
    public static final Rarity GREEN_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("green_biuso", TextColor.fromRgb(5635925), true, true, true, true, true);
    public static final Rarity GREEN_ALL = GREEN_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE;

    /* DARK GREEN */
    public static final Rarity DARK_GREEN = createRarity("dark_green", TextColor.fromRgb(43520));
    public static final Rarity DARK_GREEN_BOLD = createRarity("dark_green_b", TextColor.fromRgb(43520), true, false, false, false, false);
    public static final Rarity DARK_GREEN_ITALIC = createRarity("dark_green_i", TextColor.fromRgb(43520), false, true, false, false, false);
    public static final Rarity DARK_GREEN_UNDERLINE = createRarity("dark_green_u", TextColor.fromRgb(43520), false, false, true, false, false);
    public static final Rarity DARK_GREEN_STRIKETHROUGH = createRarity("dark_green_s", TextColor.fromRgb(43520), false, false, false, true, false);
    public static final Rarity DARK_GREEN_OBFUSCATED = createRarity("dark_green_o", TextColor.fromRgb(43520), false, false, false, false, true);
    public static final Rarity DARK_GREEN_BOLD_ITALIC = createRarity("dark_green_bi", TextColor.fromRgb(43520), true, true, false, false, false);
    public static final Rarity DARK_GREEN_BOLD_UNDERLINE = createRarity("dark_green_bu", TextColor.fromRgb(43520), true, false, true, false, false);
    public static final Rarity DARK_GREEN_BOLD_STRIKETHROUGH = createRarity("dark_green_bs", TextColor.fromRgb(43520), true, false, false, true, false);
    public static final Rarity DARK_GREEN_BOLD_OBFUSCATED = createRarity("dark_green_bo", TextColor.fromRgb(43520), true, false, false, false, true);
    public static final Rarity DARK_GREEN_BOLD_ITALIC_UNDERLINE = createRarity("dark_green_biu", TextColor.fromRgb(43520), true, true, true, false, false);
    public static final Rarity DARK_GREEN_BOLD_ITALIC_STRIKETHROUGH = createRarity("dark_green_bis", TextColor.fromRgb(43520), true, true, false, true, false);
    public static final Rarity DARK_GREEN_BOLD_ITALIC_OBFUSCATE = createRarity("dark_green_bio", TextColor.fromRgb(43520), true, true, false, false, true);
    public static final Rarity DARK_GREEN_BOLD_UNDERLINE_STRIKETHROUGH = createRarity("dark_green_bus", TextColor.fromRgb(43520), true, false, true, true, false);
    public static final Rarity DARK_GREEN_BOLD_UNDERLINE_OBFUSCATE = createRarity("dark_green_buo", TextColor.fromRgb(43520), true, false, true, false, true);
    public static final Rarity DARK_GREEN_BOLD_STRIKETHROUGH_OBFUSCATE = createRarity("dark_green_bso", TextColor.fromRgb(43520), true, false, false, true, true);
    public static final Rarity DARK_GREEN_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("dark_green_bius", TextColor.fromRgb(43520), true, true, true, true, false);
    public static final Rarity DARK_GREEN_BOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("dark_green_biuo", TextColor.fromRgb(43520), true, true, true, false, true);
    public static final Rarity DARK_GREEN_BOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("dark_green_biso", TextColor.fromRgb(43520), true, true, false, true, true);
    public static final Rarity DARK_GREEN_BOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_green_buso", TextColor.fromRgb(43520), true, false, true, true, true);
    public static final Rarity DARK_GREEN_ITALIC_UNDERLINE = createRarity("dark_green_iu", TextColor.fromRgb(43520), false, true, true, false, false);
    public static final Rarity DARK_GREEN_ITALIC_STRIKETHROUGH = createRarity("dark_green_is", TextColor.fromRgb(43520), false, true, false, true, false);
    public static final Rarity DARK_GREEN_ITALIC_OBFUSCATE = createRarity("dark_green_io", TextColor.fromRgb(43520), false, true, false, false, true);
    public static final Rarity DARK_GREEN_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("dark_green_ius", TextColor.fromRgb(43520), false, true, true, true, false);
    public static final Rarity DARK_GREEN_ITALIC_UNDERLINE_OBFUSCATE = createRarity("dark_green_iuo", TextColor.fromRgb(43520), false, true, true, false, true);
    public static final Rarity DARK_GREEN_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("dark_green_iso", TextColor.fromRgb(43520), false, true, false, true, true);
    public static final Rarity DARK_GREEN_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_green_iuso", TextColor.fromRgb(43520), false, true, true, true, true);
    public static final Rarity DARK_GREEN_UNDERLINE_STRIKETHROUGH = createRarity("dark_green_us", TextColor.fromRgb(43520), false, false, true, true, false);
    public static final Rarity DARK_GREEN_UNDERLINE_OBFUSCATE = createRarity("dark_green_uo", TextColor.fromRgb(43520), false, false, true, false, true);
    public static final Rarity DARK_GREEN_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_green_uso", TextColor.fromRgb(43520), false, false, true, true, true);
    public static final Rarity DARK_GREEN_STRIKETHROUGH_OBFUSCATE = createRarity("dark_green_so", TextColor.fromRgb(43520), false, false, false, true, true);
    public static final Rarity DARK_GREEN_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_green_biuso", TextColor.fromRgb(43520), true, true, true, true, true);
    public static final Rarity DARK_GREEN_ALL = DARK_GREEN_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE;

    /* AQUA */
    public static final Rarity AQUA = createRarity("aqua", TextColor.fromRgb(5636095));
    public static final Rarity AQUA_BOLD = createRarity("aqua_b", TextColor.fromRgb(5636095), true, false, false, false, false);
    public static final Rarity AQUA_ITALIC = createRarity("aqua_i", TextColor.fromRgb(5636095), false, true, false, false, false);
    public static final Rarity AQUA_UNDERLINE = createRarity("aqua_u", TextColor.fromRgb(5636095), false, false, true, false, false);
    public static final Rarity AQUA_STRIKETHROUGH = createRarity("aqua_s", TextColor.fromRgb(5636095), false, false, false, true, false);
    public static final Rarity AQUA_OBFUSCATED = createRarity("aqua_o", TextColor.fromRgb(5636095), false, false, false, false, true);
    public static final Rarity AQUA_BOLD_ITALIC = createRarity("aqua_bi", TextColor.fromRgb(5636095), true, true, false, false, false);
    public static final Rarity AQUA_BOLD_UNDERLINE = createRarity("aqua_bu", TextColor.fromRgb(5636095), true, false, true, false, false);
    public static final Rarity AQUA_BOLD_STRIKETHROUGH = createRarity("aqua_bs", TextColor.fromRgb(5636095), true, false, false, true, false);
    public static final Rarity AQUA_BOLD_OBFUSCATED = createRarity("aqua_bo", TextColor.fromRgb(5636095), true, false, false, false, true);
    public static final Rarity AQUA_BOLD_ITALIC_UNDERLINE = createRarity("aqua_biu", TextColor.fromRgb(5636095), true, true, true, false, false);
    public static final Rarity AQUA_BOLD_ITALIC_STRIKETHROUGH = createRarity("aqua_bis", TextColor.fromRgb(5636095), true, true, false, true, false);
    public static final Rarity AQUA_BOLD_ITALIC_OBFUSCATE = createRarity("aqua_bio", TextColor.fromRgb(5636095), true, true, false, false, true);
    public static final Rarity AQUA_BOLD_UNDERLINE_STRIKETHROUGH = createRarity("aqua_bus", TextColor.fromRgb(5636095), true, false, true, true, false);
    public static final Rarity AQUA_BOLD_UNDERLINE_OBFUSCATE = createRarity("aqua_buo", TextColor.fromRgb(5636095), true, false, true, false, true);
    public static final Rarity AQUA_BOLD_STRIKETHROUGH_OBFUSCATE = createRarity("aqua_bso", TextColor.fromRgb(5636095), true, false, false, true, true);
    public static final Rarity AQUA_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("aqua_bius", TextColor.fromRgb(5636095), true, true, true, true, false);
    public static final Rarity AQUA_BOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("aqua_biuo", TextColor.fromRgb(5636095), true, true, true, false, true);
    public static final Rarity AQUA_BOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("aqua_biso", TextColor.fromRgb(5636095), true, true, false, true, true);
    public static final Rarity AQUA_BOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("aqua_buso", TextColor.fromRgb(5636095), true, false, true, true, true);
    public static final Rarity AQUA_ITALIC_UNDERLINE = createRarity("aqua_iu", TextColor.fromRgb(5636095), false, true, true, false, false);
    public static final Rarity AQUA_ITALIC_STRIKETHROUGH = createRarity("aqua_is", TextColor.fromRgb(5636095), false, true, false, true, false);
    public static final Rarity AQUA_ITALIC_OBFUSCATE = createRarity("aqua_io", TextColor.fromRgb(5636095), false, true, false, false, true);
    public static final Rarity AQUA_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("aqua_ius", TextColor.fromRgb(5636095), false, true, true, true, false);
    public static final Rarity AQUA_ITALIC_UNDERLINE_OBFUSCATE = createRarity("aqua_iuo", TextColor.fromRgb(5636095), false, true, true, false, true);
    public static final Rarity AQUA_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("aqua_iso", TextColor.fromRgb(5636095), false, true, false, true, true);
    public static final Rarity AQUA_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("aqua_iuso", TextColor.fromRgb(5636095), false, true, true, true, true);
    public static final Rarity AQUA_UNDERLINE_STRIKETHROUGH = createRarity("aqua_us", TextColor.fromRgb(5636095), false, false, true, true, false);
    public static final Rarity AQUA_UNDERLINE_OBFUSCATE = createRarity("aqua_uo", TextColor.fromRgb(5636095), false, false, true, false, true);
    public static final Rarity AQUA_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("aqua_uso", TextColor.fromRgb(5636095), false, false, true, true, true);
    public static final Rarity AQUA_STRIKETHROUGH_OBFUSCATE = createRarity("aqua_so", TextColor.fromRgb(5636095), false, false, false, true, true);
    public static final Rarity AQUA_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("aqua_biuso", TextColor.fromRgb(5636095), true, true, true, true, true);
    public static final Rarity AQUA_ALL = AQUA_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE;

    /* DARK AQUA */
    public static final Rarity DARK_AQUA = createRarity("dark_aqua", TextColor.fromRgb(43690));
    public static final Rarity DARK_AQUA_BOLD = createRarity("dark_aqua_b", TextColor.fromRgb(43690), true, false, false, false, false);
    public static final Rarity DARK_AQUA_ITALIC = createRarity("dark_aqua_i", TextColor.fromRgb(43690), false, true, false, false, false);
    public static final Rarity DARK_AQUA_UNDERLINE = createRarity("dark_aqua_u", TextColor.fromRgb(43690), false, false, true, false, false);
    public static final Rarity DARK_AQUA_STRIKETHROUGH = createRarity("dark_aqua_s", TextColor.fromRgb(43690), false, false, false, true, false);
    public static final Rarity DARK_AQUA_OBFUSCATED = createRarity("dark_aqua_o", TextColor.fromRgb(43690), false, false, false, false, true);
    public static final Rarity DARK_AQUA_BOLD_ITALIC = createRarity("dark_aqua_bi", TextColor.fromRgb(43690), true, true, false, false, false);
    public static final Rarity DARK_AQUA_BOLD_UNDERLINE = createRarity("dark_aqua_bu", TextColor.fromRgb(43690), true, false, true, false, false);
    public static final Rarity DARK_AQUA_BOLD_STRIKETHROUGH = createRarity("dark_aqua_bs", TextColor.fromRgb(43690), true, false, false, true, false);
    public static final Rarity DARK_AQUA_BOLD_OBFUSCATED = createRarity("dark_aqua_bo", TextColor.fromRgb(43690), true, false, false, false, true);
    public static final Rarity DARK_AQUA_BOLD_ITALIC_UNDERLINE = createRarity("dark_aqua_biu", TextColor.fromRgb(43690), true, true, true, false, false);
    public static final Rarity DARK_AQUA_BOLD_ITALIC_STRIKETHROUGH = createRarity("dark_aqua_bis", TextColor.fromRgb(43690), true, true, false, true, false);
    public static final Rarity DARK_AQUA_BOLD_ITALIC_OBFUSCATE = createRarity("dark_aqua_bio", TextColor.fromRgb(43690), true, true, false, false, true);
    public static final Rarity DARK_AQUA_BOLD_UNDERLINE_STRIKETHROUGH = createRarity("dark_aqua_bus", TextColor.fromRgb(43690), true, false, true, true, false);
    public static final Rarity DARK_AQUA_BOLD_UNDERLINE_OBFUSCATE = createRarity("dark_aqua_buo", TextColor.fromRgb(43690), true, false, true, false, true);
    public static final Rarity DARK_AQUA_BOLD_STRIKETHROUGH_OBFUSCATE = createRarity("dark_aqua_bso", TextColor.fromRgb(43690), true, false, false, true, true);
    public static final Rarity DARK_AQUA_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("dark_aqua_bius", TextColor.fromRgb(43690), true, true, true, true, false);
    public static final Rarity DARK_AQUA_BOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("dark_aqua_biuo", TextColor.fromRgb(43690), true, true, true, false, true);
    public static final Rarity DARK_AQUA_BOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("dark_aqua_biso", TextColor.fromRgb(43690), true, true, false, true, true);
    public static final Rarity DARK_AQUA_BOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_aqua_buso", TextColor.fromRgb(43690), true, false, true, true, true);
    public static final Rarity DARK_AQUA_ITALIC_UNDERLINE = createRarity("dark_aqua_iu", TextColor.fromRgb(43690), false, true, true, false, false);
    public static final Rarity DARK_AQUA_ITALIC_STRIKETHROUGH = createRarity("dark_aqua_is", TextColor.fromRgb(43690), false, true, false, true, false);
    public static final Rarity DARK_AQUA_ITALIC_OBFUSCATE = createRarity("dark_aqua_io", TextColor.fromRgb(43690), false, true, false, false, true);
    public static final Rarity DARK_AQUA_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("dark_aqua_ius", TextColor.fromRgb(43690), false, true, true, true, false);
    public static final Rarity DARK_AQUA_ITALIC_UNDERLINE_OBFUSCATE = createRarity("dark_aqua_iuo", TextColor.fromRgb(43690), false, true, true, false, true);
    public static final Rarity DARK_AQUA_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("dark_aqua_iso", TextColor.fromRgb(43690), false, true, false, true, true);
    public static final Rarity DARK_AQUA_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_aqua_iuso", TextColor.fromRgb(43690), false, true, true, true, true);
    public static final Rarity DARK_AQUA_UNDERLINE_STRIKETHROUGH = createRarity("dark_aqua_us", TextColor.fromRgb(43690), false, false, true, true, false);
    public static final Rarity DARK_AQUA_UNDERLINE_OBFUSCATE = createRarity("dark_aqua_uo", TextColor.fromRgb(43690), false, false, true, false, true);
    public static final Rarity DARK_AQUA_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_aqua_uso", TextColor.fromRgb(43690), false, false, true, true, true);
    public static final Rarity DARK_AQUA_STRIKETHROUGH_OBFUSCATE = createRarity("dark_aqua_so", TextColor.fromRgb(43690), false, false, false, true, true);
    public static final Rarity DARK_AQUA_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_aqua_biuso", TextColor.fromRgb(43690), true, true, true, true, true);
    public static final Rarity DARK_AQUA_ALL = DARK_AQUA_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE;

    /* BLUE */
    public static final Rarity BLUE = createRarity("blue", TextColor.fromRgb(5592575));
    public static final Rarity BLUE_BOLD = createRarity("blue_b", TextColor.fromRgb(5592575), true, false, false, false, false);
    public static final Rarity BLUE_ITALIC = createRarity("blue_i", TextColor.fromRgb(5592575), false, true, false, false, false);
    public static final Rarity BLUE_UNDERLINE = createRarity("blue_u", TextColor.fromRgb(5592575), false, false, true, false, false);
    public static final Rarity BLUE_STRIKETHROUGH = createRarity("blue_s", TextColor.fromRgb(5592575), false, false, false, true, false);
    public static final Rarity BLUE_OBFUSCATED = createRarity("blue_o", TextColor.fromRgb(5592575), false, false, false, false, true);
    public static final Rarity BLUE_BOLD_ITALIC = createRarity("blue_bi", TextColor.fromRgb(5592575), true, true, false, false, false);
    public static final Rarity BLUE_BOLD_UNDERLINE = createRarity("blue_bu", TextColor.fromRgb(5592575), true, false, true, false, false);
    public static final Rarity BLUE_BOLD_STRIKETHROUGH = createRarity("blue_bs", TextColor.fromRgb(5592575), true, false, false, true, false);
    public static final Rarity BLUE_BOLD_OBFUSCATED = createRarity("blue_bo", TextColor.fromRgb(5592575), true, false, false, false, true);
    public static final Rarity BLUE_BOLD_ITALIC_UNDERLINE = createRarity("blue_biu", TextColor.fromRgb(5592575), true, true, true, false, false);
    public static final Rarity BLUE_BOLD_ITALIC_STRIKETHROUGH = createRarity("blue_bis", TextColor.fromRgb(5592575), true, true, false, true, false);
    public static final Rarity BLUE_BOLD_ITALIC_OBFUSCATE = createRarity("blue_bio", TextColor.fromRgb(5592575), true, true, false, false, true);
    public static final Rarity BLUE_BOLD_UNDERLINE_STRIKETHROUGH = createRarity("blue_bus", TextColor.fromRgb(5592575), true, false, true, true, false);
    public static final Rarity BLUE_BOLD_UNDERLINE_OBFUSCATE = createRarity("blue_buo", TextColor.fromRgb(5592575), true, false, true, false, true);
    public static final Rarity BLUE_BOLD_STRIKETHROUGH_OBFUSCATE = createRarity("blue_bso", TextColor.fromRgb(5592575), true, false, false, true, true);
    public static final Rarity BLUE_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("blue_bius", TextColor.fromRgb(5592575), true, true, true, true, false);
    public static final Rarity BLUE_BOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("blue_biuo", TextColor.fromRgb(5592575), true, true, true, false, true);
    public static final Rarity BLUE_BOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("blue_biso", TextColor.fromRgb(5592575), true, true, false, true, true);
    public static final Rarity BLUE_BOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("blue_buso", TextColor.fromRgb(5592575), true, false, true, true, true);
    public static final Rarity BLUE_ITALIC_UNDERLINE = createRarity("blue_iu", TextColor.fromRgb(5592575), false, true, true, false, false);
    public static final Rarity BLUE_ITALIC_STRIKETHROUGH = createRarity("blue_is", TextColor.fromRgb(5592575), false, true, false, true, false);
    public static final Rarity BLUE_ITALIC_OBFUSCATE = createRarity("blue_io", TextColor.fromRgb(5592575), false, true, false, false, true);
    public static final Rarity BLUE_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("blue_ius", TextColor.fromRgb(5592575), false, true, true, true, false);
    public static final Rarity BLUE_ITALIC_UNDERLINE_OBFUSCATE = createRarity("blue_iuo", TextColor.fromRgb(5592575), false, true, true, false, true);
    public static final Rarity BLUE_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("blue_iso", TextColor.fromRgb(5592575), false, true, false, true, true);
    public static final Rarity BLUE_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("blue_iuso", TextColor.fromRgb(5592575), false, true, true, true, true);
    public static final Rarity BLUE_UNDERLINE_STRIKETHROUGH = createRarity("blue_us", TextColor.fromRgb(5592575), false, false, true, true, false);
    public static final Rarity BLUE_UNDERLINE_OBFUSCATE = createRarity("blue_uo", TextColor.fromRgb(5592575), false, false, true, false, true);
    public static final Rarity BLUE_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("blue_uso", TextColor.fromRgb(5592575), false, false, true, true, true);
    public static final Rarity BLUE_STRIKETHROUGH_OBFUSCATE = createRarity("blue_so", TextColor.fromRgb(5592575), false, false, false, true, true);
    public static final Rarity BLUE_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("blue_biuso", TextColor.fromRgb(5592575), true, true, true, true, true);
    public static final Rarity BLUE_ALL = BLUE_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE;

    /* DARK BLUE */
    public static final Rarity DARK_BLUE = createRarity("dark_blue", TextColor.fromRgb(170));
    public static final Rarity DARK_BLUE_BOLD = createRarity("dark_blue_b", TextColor.fromRgb(170), true, false, false, false, false);
    public static final Rarity DARK_BLUE_ITALIC = createRarity("dark_blue_i", TextColor.fromRgb(170), false, true, false, false, false);
    public static final Rarity DARK_BLUE_UNDERLINE = createRarity("dark_blue_u", TextColor.fromRgb(170), false, false, true, false, false);
    public static final Rarity DARK_BLUE_STRIKETHROUGH = createRarity("dark_blue_s", TextColor.fromRgb(170), false, false, false, true, false);
    public static final Rarity DARK_BLUE_OBFUSCATED = createRarity("dark_blue_o", TextColor.fromRgb(170), false, false, false, false, true);
    public static final Rarity DARK_BLUE_BOLD_ITALIC = createRarity("dark_blue_bi", TextColor.fromRgb(170), true, true, false, false, false);
    public static final Rarity DARK_BLUE_BOLD_UNDERLINE = createRarity("dark_blue_bu", TextColor.fromRgb(170), true, false, true, false, false);
    public static final Rarity DARK_BLUE_BOLD_STRIKETHROUGH = createRarity("dark_blue_bs", TextColor.fromRgb(170), true, false, false, true, false);
    public static final Rarity DARK_BLUE_BOLD_OBFUSCATED = createRarity("dark_blue_bo", TextColor.fromRgb(170), true, false, false, false, true);
    public static final Rarity DARK_BLUE_BOLD_ITALIC_UNDERLINE = createRarity("dark_blue_biu", TextColor.fromRgb(170), true, true, true, false, false);
    public static final Rarity DARK_BLUE_BOLD_ITALIC_STRIKETHROUGH = createRarity("dark_blue_bis", TextColor.fromRgb(170), true, true, false, true, false);
    public static final Rarity DARK_BLUE_BOLD_ITALIC_OBFUSCATE = createRarity("dark_blue_bio", TextColor.fromRgb(170), true, true, false, false, true);
    public static final Rarity DARK_BLUE_BOLD_UNDERLINE_STRIKETHROUGH = createRarity("dark_blue_bus", TextColor.fromRgb(170), true, false, true, true, false);
    public static final Rarity DARK_BLUE_BOLD_UNDERLINE_OBFUSCATE = createRarity("dark_blue_buo", TextColor.fromRgb(170), true, false, true, false, true);
    public static final Rarity DARK_BLUE_BOLD_STRIKETHROUGH_OBFUSCATE = createRarity("dark_blue_bso", TextColor.fromRgb(170), true, false, false, true, true);
    public static final Rarity DARK_BLUE_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("dark_blue_bius", TextColor.fromRgb(170), true, true, true, true, false);
    public static final Rarity DARK_BLUE_BOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("dark_blue_biuo", TextColor.fromRgb(170), true, true, true, false, true);
    public static final Rarity DARK_BLUE_BOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("dark_blue_biso", TextColor.fromRgb(170), true, true, false, true, true);
    public static final Rarity DARK_BLUE_BOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_blue_buso", TextColor.fromRgb(170), true, false, true, true, true);
    public static final Rarity DARK_BLUE_ITALIC_UNDERLINE = createRarity("dark_blue_iu", TextColor.fromRgb(170), false, true, true, false, false);
    public static final Rarity DARK_BLUE_ITALIC_STRIKETHROUGH = createRarity("dark_blue_is", TextColor.fromRgb(170), false, true, false, true, false);
    public static final Rarity DARK_BLUE_ITALIC_OBFUSCATE = createRarity("dark_blue_io", TextColor.fromRgb(170), false, true, false, false, true);
    public static final Rarity DARK_BLUE_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("dark_blue_ius", TextColor.fromRgb(170), false, true, true, true, false);
    public static final Rarity DARK_BLUE_ITALIC_UNDERLINE_OBFUSCATE = createRarity("dark_blue_iuo", TextColor.fromRgb(170), false, true, true, false, true);
    public static final Rarity DARK_BLUE_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("dark_blue_iso", TextColor.fromRgb(170), false, true, false, true, true);
    public static final Rarity DARK_BLUE_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_blue_iuso", TextColor.fromRgb(170), false, true, true, true, true);
    public static final Rarity DARK_BLUE_UNDERLINE_STRIKETHROUGH = createRarity("dark_blue_us", TextColor.fromRgb(170), false, false, true, true, false);
    public static final Rarity DARK_BLUE_UNDERLINE_OBFUSCATE = createRarity("dark_blue_uo", TextColor.fromRgb(170), false, false, true, false, true);
    public static final Rarity DARK_BLUE_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_blue_uso", TextColor.fromRgb(170), false, false, true, true, true);
    public static final Rarity DARK_BLUE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_blue_so", TextColor.fromRgb(170), false, false, false, true, true);
    public static final Rarity DARK_BLUE_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_blue_biuso", TextColor.fromRgb(170), true, true, true, true, true);
    public static final Rarity DARK_BLUE_ALL = DARK_BLUE_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE;

    /* PURPLE */
    public static final Rarity PURPLE = createRarity("purple", TextColor.fromRgb(16733695));
    public static final Rarity PURPLE_BOLD = createRarity("purple_b", TextColor.fromRgb(16733695), true, false, false, false, false);
    public static final Rarity PURPLE_ITALIC = createRarity("purple_i", TextColor.fromRgb(16733695), false, true, false, false, false);
    public static final Rarity PURPLE_UNDERLINE = createRarity("purple_u", TextColor.fromRgb(16733695), false, false, true, false, false);
    public static final Rarity PURPLE_STRIKETHROUGH = createRarity("purple_s", TextColor.fromRgb(16733695), false, false, false, true, false);
    public static final Rarity PURPLE_OBFUSCATED = createRarity("purple_o", TextColor.fromRgb(16733695), false, false, false, false, true);
    public static final Rarity PURPLE_BOLD_ITALIC = createRarity("purple_bi", TextColor.fromRgb(16733695), true, true, false, false, false);
    public static final Rarity PURPLE_BOLD_UNDERLINE = createRarity("purple_bu", TextColor.fromRgb(16733695), true, false, true, false, false);
    public static final Rarity PURPLE_BOLD_STRIKETHROUGH = createRarity("purple_bs", TextColor.fromRgb(16733695), true, false, false, true, false);
    public static final Rarity PURPLE_BOLD_OBFUSCATED = createRarity("purple_bo", TextColor.fromRgb(16733695), true, false, false, false, true);
    public static final Rarity PURPLE_BOLD_ITALIC_UNDERLINE = createRarity("purple_biu", TextColor.fromRgb(16733695), true, true, true, false, false);
    public static final Rarity PURPLE_BOLD_ITALIC_STRIKETHROUGH = createRarity("purple_bis", TextColor.fromRgb(16733695), true, true, false, true, false);
    public static final Rarity PURPLE_BOLD_ITALIC_OBFUSCATE = createRarity("purple_bio", TextColor.fromRgb(16733695), true, true, false, false, true);
    public static final Rarity PURPLE_BOLD_UNDERLINE_STRIKETHROUGH = createRarity("purple_bus", TextColor.fromRgb(16733695), true, false, true, true, false);
    public static final Rarity PURPLE_BOLD_UNDERLINE_OBFUSCATE = createRarity("purple_buo", TextColor.fromRgb(16733695), true, false, true, false, true);
    public static final Rarity PURPLE_BOLD_STRIKETHROUGH_OBFUSCATE = createRarity("purple_bso", TextColor.fromRgb(16733695), true, false, false, true, true);
    public static final Rarity PURPLE_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("purple_bius", TextColor.fromRgb(16733695), true, true, true, true, false);
    public static final Rarity PURPLE_BOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("purple_biuo", TextColor.fromRgb(16733695), true, true, true, false, true);
    public static final Rarity PURPLE_BOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("purple_biso", TextColor.fromRgb(16733695), true, true, false, true, true);
    public static final Rarity PURPLE_BOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("purple_buso", TextColor.fromRgb(16733695), true, false, true, true, true);
    public static final Rarity PURPLE_ITALIC_UNDERLINE = createRarity("purple_iu", TextColor.fromRgb(16733695), false, true, true, false, false);
    public static final Rarity PURPLE_ITALIC_STRIKETHROUGH = createRarity("purple_is", TextColor.fromRgb(16733695), false, true, false, true, false);
    public static final Rarity PURPLE_ITALIC_OBFUSCATE = createRarity("purple_io", TextColor.fromRgb(16733695), false, true, false, false, true);
    public static final Rarity PURPLE_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("purple_ius", TextColor.fromRgb(16733695), false, true, true, true, false);
    public static final Rarity PURPLE_ITALIC_UNDERLINE_OBFUSCATE = createRarity("purple_iuo", TextColor.fromRgb(16733695), false, true, true, false, true);
    public static final Rarity PURPLE_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("purple_iso", TextColor.fromRgb(16733695), false, true, false, true, true);
    public static final Rarity PURPLE_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("purple_iuso", TextColor.fromRgb(16733695), false, true, true, true, true);
    public static final Rarity PURPLE_UNDERLINE_STRIKETHROUGH = createRarity("purple_us", TextColor.fromRgb(16733695), false, false, true, true, false);
    public static final Rarity PURPLE_UNDERLINE_OBFUSCATE = createRarity("purple_uo", TextColor.fromRgb(16733695), false, false, true, false, true);
    public static final Rarity PURPLE_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("purple_uso", TextColor.fromRgb(16733695), false, false, true, true, true);
    public static final Rarity PURPLE_STRIKETHROUGH_OBFUSCATE = createRarity("purple_so", TextColor.fromRgb(16733695), false, false, false, true, true);
    public static final Rarity PURPLE_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("purple_biuso", TextColor.fromRgb(16733695), true, true, true, true, true);
    public static final Rarity PURPLE_ALL = PURPLE_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE;

    /* DARK PURPLE */
    public static final Rarity DARK_PURPLE = createRarity("dark_purple", TextColor.fromRgb(11141290));
    public static final Rarity DARK_PURPLE_BOLD = createRarity("dark_purple_b", TextColor.fromRgb(11141290), true, false, false, false, false);
    public static final Rarity DARK_PURPLE_ITALIC = createRarity("dark_purple_i", TextColor.fromRgb(11141290), false, true, false, false, false);
    public static final Rarity DARK_PURPLE_UNDERLINE = createRarity("dark_purple_u", TextColor.fromRgb(11141290), false, false, true, false, false);
    public static final Rarity DARK_PURPLE_STRIKETHROUGH = createRarity("dark_purple_s", TextColor.fromRgb(11141290), false, false, false, true, false);
    public static final Rarity DARK_PURPLE_OBFUSCATED = createRarity("dark_purple_o", TextColor.fromRgb(11141290), false, false, false, false, true);
    public static final Rarity DARK_PURPLE_BOLD_ITALIC = createRarity("dark_purple_bi", TextColor.fromRgb(11141290), true, true, false, false, false);
    public static final Rarity DARK_PURPLE_BOLD_UNDERLINE = createRarity("dark_purple_bu", TextColor.fromRgb(11141290), true, false, true, false, false);
    public static final Rarity DARK_PURPLE_BOLD_STRIKETHROUGH = createRarity("dark_purple_bs", TextColor.fromRgb(11141290), true, false, false, true, false);
    public static final Rarity DARK_PURPLE_BOLD_OBFUSCATED = createRarity("dark_purple_bo", TextColor.fromRgb(11141290), true, false, false, false, true);
    public static final Rarity DARK_PURPLE_BOLD_ITALIC_UNDERLINE = createRarity("dark_purple_biu", TextColor.fromRgb(11141290), true, true, true, false, false);
    public static final Rarity DARK_PURPLE_BOLD_ITALIC_STRIKETHROUGH = createRarity("dark_purple_bis", TextColor.fromRgb(11141290), true, true, false, true, false);
    public static final Rarity DARK_PURPLE_BOLD_ITALIC_OBFUSCATE = createRarity("dark_purple_bio", TextColor.fromRgb(11141290), true, true, false, false, true);
    public static final Rarity DARK_PURPLE_BOLD_UNDERLINE_STRIKETHROUGH = createRarity("dark_purple_bus", TextColor.fromRgb(11141290), true, false, true, true, false);
    public static final Rarity DARK_PURPLE_BOLD_UNDERLINE_OBFUSCATE = createRarity("dark_purple_buo", TextColor.fromRgb(11141290), true, false, true, false, true);
    public static final Rarity DARK_PURPLE_BOLD_STRIKETHROUGH_OBFUSCATE = createRarity("dark_purple_bso", TextColor.fromRgb(11141290), true, false, false, true, true);
    public static final Rarity DARK_PURPLE_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("dark_purple_bius", TextColor.fromRgb(11141290), true, true, true, true, false);
    public static final Rarity DARK_PURPLE_BOLD_ITALIC_UNDERLINE_OBFUSCATE = createRarity("dark_purple_biuo", TextColor.fromRgb(11141290), true, true, true, false, true);
    public static final Rarity DARK_PURPLE_BOLD_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("dark_purple_biso", TextColor.fromRgb(11141290), true, true, false, true, true);
    public static final Rarity DARK_PURPLE_BOLD_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_purple_buso", TextColor.fromRgb(11141290), true, false, true, true, true);
    public static final Rarity DARK_PURPLE_ITALIC_UNDERLINE = createRarity("dark_purple_iu", TextColor.fromRgb(11141290), false, true, true, false, false);
    public static final Rarity DARK_PURPLE_ITALIC_STRIKETHROUGH = createRarity("dark_purple_is", TextColor.fromRgb(11141290), false, true, false, true, false);
    public static final Rarity DARK_PURPLE_ITALIC_OBFUSCATE = createRarity("dark_purple_io", TextColor.fromRgb(11141290), false, true, false, false, true);
    public static final Rarity DARK_PURPLE_ITALIC_UNDERLINE_STRIKETHROUGH = createRarity("dark_purple_ius", TextColor.fromRgb(11141290), false, true, true, true, false);
    public static final Rarity DARK_PURPLE_ITALIC_UNDERLINE_OBFUSCATE = createRarity("dark_purple_iuo", TextColor.fromRgb(11141290), false, true, true, false, true);
    public static final Rarity DARK_PURPLE_ITALIC_STRIKETHROUGH_OBFUSCATE = createRarity("dark_purple_iso", TextColor.fromRgb(11141290), false, true, false, true, true);
    public static final Rarity DARK_PURPLE_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_purple_iuso", TextColor.fromRgb(11141290), false, true, true, true, true);
    public static final Rarity DARK_PURPLE_UNDERLINE_STRIKETHROUGH = createRarity("dark_purple_us", TextColor.fromRgb(11141290), false, false, true, true, false);
    public static final Rarity DARK_PURPLE_UNDERLINE_OBFUSCATE = createRarity("dark_purple_uo", TextColor.fromRgb(11141290), false, false, true, false, true);
    public static final Rarity DARK_PURPLE_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_purple_uso", TextColor.fromRgb(11141290), false, false, true, true, true);
    public static final Rarity DARK_PURPLE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_purple_so", TextColor.fromRgb(11141290), false, false, false, true, true);
    public static final Rarity DARK_PURPLE_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE = createRarity("dark_purple_biuso", TextColor.fromRgb(11141290), true, true, true, true, true);
    public static final Rarity DARK_PURPLE_ALL = DARK_PURPLE_BOLD_ITALIC_UNDERLINE_STRIKETHROUGH_OBFUSCATE;

    private static Rarity createRarity(String name, TextColor color) {
        Style useStyle;
        if (color != null) {
            useStyle = Style.EMPTY.withColor(color);
        } else {
            useStyle = Style.EMPTY;
        }

        return Rarity.create(name, style -> useStyle);
    }

    private static Rarity createRarity(String name, TextColor color, boolean bold, boolean italic, boolean underline, boolean strikethrough, boolean obfuscate) {
        Style useStyle;
        if (color != null) {
            useStyle = Style.EMPTY.withColor(color).withBold(bold).withItalic(italic).withUnderlined(underline).withStrikethrough(strikethrough).withObfuscated(obfuscate);
        } else {
            useStyle = Style.EMPTY.withBold(bold).withItalic(italic).withUnderlined(underline).withStrikethrough(strikethrough).withObfuscated(obfuscate);
        }

        return Rarity.create(name, style -> useStyle);
    }

    private static Rarity createRarity(String name, TextColor color, boolean bold, boolean italic, boolean underline, boolean strikethrough, boolean obfuscate, ClickEvent clickEvent, HoverEvent hoverEvent, ResourceLocation font) {
        Style useStyle;
        if (color != null) {
            useStyle = Style.EMPTY.withColor(color).withBold(bold).withItalic(italic).withUnderlined(underline).withStrikethrough(strikethrough).withObfuscated(obfuscate).withClickEvent(clickEvent).withHoverEvent(hoverEvent).withFont(font);
        } else {
            useStyle = Style.EMPTY.withBold(bold).withItalic(italic).withUnderlined(underline).withStrikethrough(strikethrough).withObfuscated(obfuscate).withClickEvent(clickEvent).withHoverEvent(hoverEvent).withFont(font);

        }

        return Rarity.create(name, style -> useStyle);
    }

    public static class Builder {
        private String name = null;
        private TextColor color = null;
        private boolean bold, italic, underline, strikethrough, obfuscate = false;
        private ClickEvent clickEvent = null;
        private HoverEvent hoverEvent = null;
        private ResourceLocation font = null;

        public Builder name(String pName) {
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

        public Rarity build() {
            if (this.name == null) {
                this.name = RandomStringUtils.randomAlphanumeric(12);
            }
            RDSLib.LOGGER.info(String.format("Creating Rarity \"%s\"", this.name));
            return Rarities.createRarity(this.name, this.color, this.bold, this.italic, this.underline, this.strikethrough, this.obfuscate, this.clickEvent, this.hoverEvent, this.font);
        }
    }

    public static Style getStyle(Rarity rarity) {
        return rarity.getStyleModifier().apply(Style.EMPTY);
    }
}
