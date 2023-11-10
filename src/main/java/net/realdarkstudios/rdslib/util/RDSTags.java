package net.realdarkstudios.rdslib.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class RDSTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_TOOL_LEVEL_5 = minecraftTag("needs_tool_level_5");
        public static final TagKey<Block> NEEDS_TOOL_LEVEL_6 = minecraftTag("needs_tool_level_6");
        public static final TagKey<Block> NEEDS_TOOL_LEVEL_7 = minecraftTag("needs_tool_level_7");
        public static final TagKey<Block> NEEDS_TOOL_LEVEL_8 = minecraftTag("needs_tool_level_8");
        public static final TagKey<Block> NEEDS_TOOL_LEVEL_9 = minecraftTag("needs_tool_level_9");
        public static final TagKey<Block> NEEDS_TOOL_LEVEL_10 = minecraftTag("needs_tool_level_10");
        public static final TagKey<Block> PAXEL_MINEABLE = minecraftTag("mineable/paxel");

        private static TagKey<Block> minecraftTag(String name) {
            return BlockTags.create(new ResourceLocation(name));
        }
    }

    public static class Items {
        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
