package net.realdarkstudios.rdslib.recipe;

import com.google.common.collect.ImmutableList;
import net.realdarkstudios.rdslib.RDSLib;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Consumer;

import static net.minecraft.data.recipes.packs.VanillaRecipeProvider.*;

public class RecipeGenHelper {
    
    private final String modid;
    
    public RecipeGenHelper(String modid) {
        this.modid = modid;
    }

    public void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIn, ItemLike pOut, float pExperience, int pDuration) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(pIn), RecipeCategory.MISC, pOut, pExperience, pDuration)
                .group(getItemName(pOut))
                .unlockedBy(getHasName(pIn), inventoryTrigger(ItemPredicate.Builder.item().of(pIn).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(modid, String.format("%s_from_smelting_%s", getItemName(pIn), getItemName(pOut))));
    }

    public void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIn, ItemLike pOut, float pExperience, int pDuration) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(pIn), RecipeCategory.MISC, pOut, pExperience, pDuration)
                .group(getItemName(pOut))
                .unlockedBy(getHasName(pIn), inventoryTrigger(ItemPredicate.Builder.item().of(pIn).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(modid, String.format("%s_from_blasting_%s", getItemName(pIn), getItemName(pOut))));
    }

    public void customSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIn, ItemLike pOut, float pExperience, int pDuration, RecipeSerializer<? extends Recipe> pSerializer) {
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(pIn), RecipeCategory.MISC, pOut, pExperience, pDuration, pSerializer)
                .group(getItemName(pOut))
                .unlockedBy(getHasName(pIn), inventoryTrigger(ItemPredicate.Builder.item().of(pIn).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(modid, getCustomSmeltingRecipeName(pOut, pSerializer) + getItemName(pIn)));
    }

    public void fullSetNoPaxel(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pSword, ItemLike pPickaxe, ItemLike pAxe,
                        ItemLike pShovel, ItemLike pHoe, ItemLike pHelmet, ItemLike pChestplate, ItemLike pLeggings, ItemLike pBoots) {
        fullToolSetNoPaxel(pFinishedRecipeConsumer, pIngot, pSword, pPickaxe, pAxe, pShovel, pHoe);
        fullArmorSet(pFinishedRecipeConsumer, pIngot, pHelmet, pChestplate, pLeggings, pBoots);
    }

    public void fullToolSetNoPaxel(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pSword, ItemLike pPickaxe,
                            ItemLike pAxe, ItemLike pShovel, ItemLike pHoe) {
        sword(pFinishedRecipeConsumer, pIngot, pSword);
        pickaxe(pFinishedRecipeConsumer, pIngot, pPickaxe);
        axe(pFinishedRecipeConsumer, pIngot, pAxe);
        shovel(pFinishedRecipeConsumer, pIngot, pShovel);
        hoe(pFinishedRecipeConsumer, pIngot, pHoe);
    }

    public void fullSet(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pSword, ItemLike pPickaxe, ItemLike pAxe,
                               ItemLike pShovel, ItemLike pHoe, ItemLike pPaxel, ItemLike pHelmet, ItemLike pChestplate, ItemLike pLeggings,
                               ItemLike pBoots) {
        fullToolSet(pFinishedRecipeConsumer, pIngot, pSword, pPickaxe, pAxe, pShovel, pHoe, pPaxel);
        fullArmorSet(pFinishedRecipeConsumer, pIngot, pHelmet, pChestplate, pLeggings, pBoots);
    }

    public void fullToolSet(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pSword, ItemLike pPickaxe,
                                   ItemLike pAxe, ItemLike pShovel, ItemLike pHoe, ItemLike pPaxel) {
        sword(pFinishedRecipeConsumer, pIngot, pSword);
        pickaxe(pFinishedRecipeConsumer, pIngot, pPickaxe);
        axe(pFinishedRecipeConsumer, pIngot, pAxe);
        shovel(pFinishedRecipeConsumer, pIngot, pShovel);
        hoe(pFinishedRecipeConsumer, pIngot, pHoe);
        paxel(pFinishedRecipeConsumer, pPaxel, pPickaxe, pAxe, pShovel);
    }

    public void fullArmorSet(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pHelmet, ItemLike pChestplate,
                                    ItemLike pLeggings, ItemLike pBoots) {
        helmet(pFinishedRecipeConsumer, pIngot, pHelmet);
        chestplate(pFinishedRecipeConsumer, pIngot, pChestplate);
        leggings(pFinishedRecipeConsumer, pIngot, pLeggings);
        boots(pFinishedRecipeConsumer, pIngot, pBoots);
    }

    public void helmet(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pHelmet) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pHelmet)
                .define('i', pIngot)
                .pattern("iii")
                .pattern("i i")
                .group(getItemName(pHelmet))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(pHelmet)));
    }

    public void chestplate(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pChestplate) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pChestplate)
                .define('i', pIngot)
                .pattern("i i")
                .pattern("iii")
                .pattern("iii")
                .group(getItemName(pChestplate))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(pChestplate)));
    }

    public void leggings(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pLeggings) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pLeggings)
                .define('i', pIngot)
                .pattern("iii")
                .pattern("i i")
                .pattern("i i")
                .group(getItemName(pLeggings))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(pLeggings)));
    }

    public void boots(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pBoots) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pBoots)
                .define('i', pIngot)
                .pattern("i i")
                .pattern("i i")
                .group(getItemName(pBoots))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(pBoots)));
    }

    public void sword(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pSword) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pSword)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("i")
                .pattern("i")
                .pattern("s")
                .group(getItemName(pSword))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(pSword)));
    }

    public void pickaxe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pPickaxe) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pPickaxe)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("iii")
                .pattern(" s ")
                .pattern(" s ")
                .group(getItemName(pPickaxe))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(pPickaxe)));
    }

    public void axe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pAxe) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pAxe)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("ii")
                .pattern("is")
                .pattern(" s")
                .group(getItemName(pAxe))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(pAxe)));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pAxe)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("ii")
                .pattern("si")
                .pattern("s ")
                .group(getItemName(pAxe))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(pAxe) + "2"));
    }

    public void shovel(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pShovel) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pShovel)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("i")
                .pattern("s")
                .pattern("s")
                .group(getItemName(pShovel))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(pShovel)));
    }

    public void hoe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pHoe) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pHoe)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("ii")
                .pattern(" s")
                .pattern(" s")
                .group(getItemName(pHoe))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(pHoe)));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pHoe)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("ii")
                .pattern("s ")
                .pattern("s ")
                .group(getItemName(pHoe))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(pHoe) + "2"));
    }

    public void paxel(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pPaxel, ItemLike pPickaxe, ItemLike pAxe, ItemLike pShovel) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pPaxel)
                .define('p', pPickaxe)
                .define('a', pAxe)
                .define('s', pShovel)
                .define('#', Items.STICK)
                .pattern("aps")
                .pattern(" # ")
                .pattern(" # ")
                .group(getItemName(pPaxel))
                .unlockedBy(getHasName(pPickaxe), inventoryTrigger(ItemPredicate.Builder.item().of(pPickaxe).build()))
                .unlockedBy(getHasName(pAxe), inventoryTrigger(ItemPredicate.Builder.item().of(pAxe).build()))
                .unlockedBy(getHasName(pShovel), inventoryTrigger(ItemPredicate.Builder.item().of(pShovel).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(pPaxel)));
    }

    /**
     * This method is now deprecated.
     * Please use {@link RecipeGenHelper#terracottaRecipes(Consumer, FurnaceLikeSerializerV2, String)} instead.
     * @deprecated Since: 4.0.3.0
     */
    @Deprecated(since = "4.0.3.0")
    public void addCustomFurnaceRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, FurnaceLikeSerializer<? extends Recipe> pSerializer, String pFurnaceString) {
        int pCookingTime = pSerializer.defaultCookingTime;
        
        customSmeltingList(pFinishedRecipeConsumer, COAL_SMELTABLES, RecipeCategory.MISC, Items.COAL, 0.1F, pCookingTime, "coal", pSerializer, pFurnaceString);
        customSmeltingList(pFinishedRecipeConsumer, IRON_SMELTABLES, RecipeCategory.MISC, Items.IRON_INGOT, 0.7F, pCookingTime, "iron_ingot", pSerializer, pFurnaceString);
        customSmeltingList(pFinishedRecipeConsumer, COPPER_SMELTABLES, RecipeCategory.MISC, Items.COPPER_INGOT, 0.7F, pCookingTime, "copper_ingot", pSerializer, pFurnaceString);
        customSmeltingList(pFinishedRecipeConsumer, GOLD_SMELTABLES, RecipeCategory.MISC, Items.GOLD_INGOT, 1.0F, pCookingTime, "gold_ingot", pSerializer, pFurnaceString);
        customSmeltingList(pFinishedRecipeConsumer, DIAMOND_SMELTABLES, RecipeCategory.MISC, Items.DIAMOND, 1.0F, pCookingTime, "diamond", pSerializer, pFurnaceString);
        customSmeltingList(pFinishedRecipeConsumer, LAPIS_SMELTABLES, RecipeCategory.MISC, Items.LAPIS_LAZULI, 0.2F, pCookingTime, "lapis_lazuli", pSerializer, pFurnaceString);
        customSmeltingList(pFinishedRecipeConsumer, REDSTONE_SMELTABLES, RecipeCategory.REDSTONE, Items.REDSTONE, 0.7F, pCookingTime, "redstone", pSerializer, pFurnaceString);
        customSmeltingList(pFinishedRecipeConsumer, EMERALD_SMELTABLES, RecipeCategory.MISC, Items.EMERALD, 1.0F, pCookingTime, "emerald", pSerializer, pFurnaceString);
        terracottaRecipes(pFinishedRecipeConsumer, pSerializer, pFurnaceString);
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(ItemTags.SAND), RecipeCategory.BUILDING_BLOCKS, Blocks.GLASS.asItem(), 0.1F, pCookingTime, pSerializer).unlockedBy("has_sand", has(ItemTags.SAND)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.GLASS) + pFurnaceString + getItemName(Blocks.SAND)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.SEA_PICKLE), RecipeCategory.MISC, Items.LIME_DYE, 0.1F, pCookingTime, pSerializer).unlockedBy("has_sea_pickle", has(Blocks.SEA_PICKLE)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getCustomSmeltingRecipeName(Items.LIME_DYE, pSerializer)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.CACTUS.asItem()), RecipeCategory.MISC, Items.GREEN_DYE, 1.0F, pCookingTime, pSerializer).unlockedBy("has_cactus", has(Blocks.CACTUS)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Items.GREEN_DYE) + pFurnaceString + getItemName(Blocks.CACTUS)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Items.GOLDEN_PICKAXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_AXE, Items.GOLDEN_HOE, Items.GOLDEN_SWORD, Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS, Items.GOLDEN_HORSE_ARMOR), RecipeCategory.MISC, Items.GOLD_NUGGET, 0.1F, pCookingTime, pSerializer).unlockedBy("has_golden_pickaxe", has(Items.GOLDEN_PICKAXE)).unlockedBy("has_golden_shovel", has(Items.GOLDEN_SHOVEL)).unlockedBy("has_golden_axe", has(Items.GOLDEN_AXE)).unlockedBy("has_golden_hoe", has(Items.GOLDEN_HOE)).unlockedBy("has_golden_sword", has(Items.GOLDEN_SWORD)).unlockedBy("has_golden_helmet", has(Items.GOLDEN_HELMET)).unlockedBy("has_golden_chestplate", has(Items.GOLDEN_CHESTPLATE)).unlockedBy("has_golden_leggings", has(Items.GOLDEN_LEGGINGS)).unlockedBy("has_golden_boots", has(Items.GOLDEN_BOOTS)).unlockedBy("has_golden_horse_armor", has(Items.GOLDEN_HORSE_ARMOR)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getCustomSmeltingRecipeName(Items.GOLD_NUGGET, pSerializer)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_AXE, Items.IRON_HOE, Items.IRON_SWORD, Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS, Items.IRON_HORSE_ARMOR, Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.CHAINMAIL_BOOTS), RecipeCategory.MISC, Items.IRON_NUGGET, 0.1F, pCookingTime, pSerializer).unlockedBy("has_iron_pickaxe", has(Items.IRON_PICKAXE)).unlockedBy("has_iron_shovel", has(Items.IRON_SHOVEL)).unlockedBy("has_iron_axe", has(Items.IRON_AXE)).unlockedBy("has_iron_hoe", has(Items.IRON_HOE)).unlockedBy("has_iron_sword", has(Items.IRON_SWORD)).unlockedBy("has_iron_helmet", has(Items.IRON_HELMET)).unlockedBy("has_iron_chestplate", has(Items.IRON_CHESTPLATE)).unlockedBy("has_iron_leggings", has(Items.IRON_LEGGINGS)).unlockedBy("has_iron_boots", has(Items.IRON_BOOTS)).unlockedBy("has_iron_horse_armor", has(Items.IRON_HORSE_ARMOR)).unlockedBy("has_chainmail_helmet", has(Items.CHAINMAIL_HELMET)).unlockedBy("has_chainmail_chestplate", has(Items.CHAINMAIL_CHESTPLATE)).unlockedBy("has_chainmail_leggings", has(Items.CHAINMAIL_LEGGINGS)).unlockedBy("has_chainmail_boots", has(Items.CHAINMAIL_BOOTS)).save(pFinishedRecipeConsumer, new ResourceLocation(RDSLib.MODID, getCustomSmeltingRecipeName(Items.IRON_NUGGET, pSerializer)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.CLAY), RecipeCategory.BUILDING_BLOCKS, Blocks.TERRACOTTA.asItem(), 0.35F, pCookingTime, pSerializer).unlockedBy("has_clay_block", has(Blocks.CLAY)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.TERRACOTTA) + pFurnaceString + getItemName(Blocks.CLAY)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.NETHERRACK), RecipeCategory.MISC, Items.NETHER_BRICK, 0.1F, pCookingTime, pSerializer).unlockedBy("has_netherrack", has(Blocks.NETHERRACK)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.NETHER_BRICKS) + pFurnaceString + getItemName(Blocks.NETHERRACK)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.NETHER_QUARTZ_ORE), RecipeCategory.MISC, Items.QUARTZ, 0.2F, pCookingTime, pSerializer).unlockedBy("has_nether_quartz_ore", has(Blocks.NETHER_QUARTZ_ORE)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Items.QUARTZ) + pFurnaceString + getItemName(Blocks.NETHER_QUARTZ_ORE)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.WET_SPONGE), RecipeCategory.BUILDING_BLOCKS, Blocks.SPONGE.asItem(), 0.15F, pCookingTime, pSerializer).unlockedBy("has_wet_sponge", has(Blocks.WET_SPONGE)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.SPONGE) + pFurnaceString + getItemName(Blocks.WET_SPONGE)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.COBBLESTONE), RecipeCategory.BUILDING_BLOCKS, Blocks.STONE.asItem(), 0.1F, pCookingTime, pSerializer).unlockedBy("has_cobblestone", has(Blocks.COBBLESTONE)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.STONE) + pFurnaceString + getItemName(Blocks.COBBLESTONE)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.STONE), RecipeCategory.BUILDING_BLOCKS, Blocks.SMOOTH_STONE.asItem(), 0.1F, pCookingTime, pSerializer).unlockedBy("has_stone", has(Blocks.STONE)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.SMOOTH_STONE) + pFurnaceString + getItemName(Blocks.STONE)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.SANDSTONE), RecipeCategory.BUILDING_BLOCKS, Blocks.SMOOTH_SANDSTONE.asItem(), 0.1F, pCookingTime, pSerializer).unlockedBy("has_sandstone", has(Blocks.SANDSTONE)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.SMOOTH_SANDSTONE) + pFurnaceString + getItemName(Blocks.SANDSTONE)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.RED_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, Blocks.SMOOTH_RED_SANDSTONE.asItem(), 0.1F, pCookingTime, pSerializer).unlockedBy("has_red_sandstone", has(Blocks.RED_SANDSTONE)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.SMOOTH_RED_SANDSTONE) + pFurnaceString + getItemName(Blocks.RED_SANDSTONE)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.QUARTZ_BLOCK), RecipeCategory.BUILDING_BLOCKS, Blocks.SMOOTH_QUARTZ.asItem(), 0.1F, pCookingTime, pSerializer).unlockedBy("has_quartz_block", has(Blocks.QUARTZ_BLOCK)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.SMOOTH_QUARTZ) + pFurnaceString + getItemName(Blocks.QUARTZ_BLOCK)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, Blocks.CRACKED_STONE_BRICKS.asItem(), 0.1F, pCookingTime, pSerializer).unlockedBy("has_stone_bricks", has(Blocks.STONE_BRICKS)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.CRACKED_STONE_BRICKS) + pFurnaceString + getItemName(Blocks.STONE_BRICKS)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.ANCIENT_DEBRIS), RecipeCategory.MISC, Items.NETHERITE_SCRAP, 2.0F, pCookingTime, pSerializer).unlockedBy("has_ancient_debris", has(Blocks.ANCIENT_DEBRIS)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Items.NETHERITE_SCRAP) + pFurnaceString + getItemName(Blocks.ANCIENT_DEBRIS)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.BASALT), RecipeCategory.BUILDING_BLOCKS, Blocks.SMOOTH_BASALT, 0.1F, pCookingTime, pSerializer).unlockedBy("has_basalt", has(Blocks.BASALT)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.SMOOTH_BASALT) + pFurnaceString + getItemName(Blocks.BASALT)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.COBBLED_DEEPSLATE), RecipeCategory.BUILDING_BLOCKS, Blocks.DEEPSLATE, 0.1F, pCookingTime, pSerializer).unlockedBy("has_cobbled_deepslate", has(Blocks.COBBLED_DEEPSLATE)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.DEEPSLATE) + pFurnaceString + getItemName(Blocks.COBBLED_DEEPSLATE)));
    }

    public void addCustomFurnaceRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, FurnaceLikeSerializerV2<? extends Recipe> pSerializer, String pFurnaceString) {
        int pCookingTime = pSerializer.defaultCookingTime;

        customSmeltingList(pFinishedRecipeConsumer, COAL_SMELTABLES, RecipeCategory.MISC, Items.COAL, 0.1F, pCookingTime, "coal", pSerializer, pFurnaceString);
        customSmeltingList(pFinishedRecipeConsumer, IRON_SMELTABLES, RecipeCategory.MISC, Items.IRON_INGOT, 0.7F, pCookingTime, "iron_ingot", pSerializer, pFurnaceString);
        customSmeltingList(pFinishedRecipeConsumer, COPPER_SMELTABLES, RecipeCategory.MISC, Items.COPPER_INGOT, 0.7F, pCookingTime, "copper_ingot", pSerializer, pFurnaceString);
        customSmeltingList(pFinishedRecipeConsumer, GOLD_SMELTABLES, RecipeCategory.MISC, Items.GOLD_INGOT, 1.0F, pCookingTime, "gold_ingot", pSerializer, pFurnaceString);
        customSmeltingList(pFinishedRecipeConsumer, DIAMOND_SMELTABLES, RecipeCategory.MISC, Items.DIAMOND, 1.0F, pCookingTime, "diamond", pSerializer, pFurnaceString);
        customSmeltingList(pFinishedRecipeConsumer, LAPIS_SMELTABLES, RecipeCategory.MISC, Items.LAPIS_LAZULI, 0.2F, pCookingTime, "lapis_lazuli", pSerializer, pFurnaceString);
        customSmeltingList(pFinishedRecipeConsumer, REDSTONE_SMELTABLES, RecipeCategory.REDSTONE, Items.REDSTONE, 0.7F, pCookingTime, "redstone", pSerializer, pFurnaceString);
        customSmeltingList(pFinishedRecipeConsumer, EMERALD_SMELTABLES, RecipeCategory.MISC, Items.EMERALD, 1.0F, pCookingTime, "emerald", pSerializer, pFurnaceString);
        terracottaRecipes(pFinishedRecipeConsumer, pSerializer, pFurnaceString);
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(ItemTags.SAND), RecipeCategory.BUILDING_BLOCKS, Blocks.GLASS.asItem(), 0.1F, pCookingTime, pSerializer).unlockedBy("has_sand", has(ItemTags.SAND)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.GLASS) + pFurnaceString + getItemName(Blocks.SAND)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.SEA_PICKLE), RecipeCategory.MISC, Items.LIME_DYE, 0.1F, pCookingTime, pSerializer).unlockedBy("has_sea_pickle", has(Blocks.SEA_PICKLE)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getCustomSmeltingRecipeName(Items.LIME_DYE, pSerializer)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.CACTUS.asItem()), RecipeCategory.MISC, Items.GREEN_DYE, 1.0F, pCookingTime, pSerializer).unlockedBy("has_cactus", has(Blocks.CACTUS)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Items.GREEN_DYE) + pFurnaceString + getItemName(Blocks.CACTUS)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Items.GOLDEN_PICKAXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_AXE, Items.GOLDEN_HOE, Items.GOLDEN_SWORD, Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS, Items.GOLDEN_HORSE_ARMOR), RecipeCategory.MISC, Items.GOLD_NUGGET, 0.1F, pCookingTime, pSerializer).unlockedBy("has_golden_pickaxe", has(Items.GOLDEN_PICKAXE)).unlockedBy("has_golden_shovel", has(Items.GOLDEN_SHOVEL)).unlockedBy("has_golden_axe", has(Items.GOLDEN_AXE)).unlockedBy("has_golden_hoe", has(Items.GOLDEN_HOE)).unlockedBy("has_golden_sword", has(Items.GOLDEN_SWORD)).unlockedBy("has_golden_helmet", has(Items.GOLDEN_HELMET)).unlockedBy("has_golden_chestplate", has(Items.GOLDEN_CHESTPLATE)).unlockedBy("has_golden_leggings", has(Items.GOLDEN_LEGGINGS)).unlockedBy("has_golden_boots", has(Items.GOLDEN_BOOTS)).unlockedBy("has_golden_horse_armor", has(Items.GOLDEN_HORSE_ARMOR)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getCustomSmeltingRecipeName(Items.GOLD_NUGGET, pSerializer)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_AXE, Items.IRON_HOE, Items.IRON_SWORD, Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS, Items.IRON_HORSE_ARMOR, Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.CHAINMAIL_BOOTS), RecipeCategory.MISC, Items.IRON_NUGGET, 0.1F, pCookingTime, pSerializer).unlockedBy("has_iron_pickaxe", has(Items.IRON_PICKAXE)).unlockedBy("has_iron_shovel", has(Items.IRON_SHOVEL)).unlockedBy("has_iron_axe", has(Items.IRON_AXE)).unlockedBy("has_iron_hoe", has(Items.IRON_HOE)).unlockedBy("has_iron_sword", has(Items.IRON_SWORD)).unlockedBy("has_iron_helmet", has(Items.IRON_HELMET)).unlockedBy("has_iron_chestplate", has(Items.IRON_CHESTPLATE)).unlockedBy("has_iron_leggings", has(Items.IRON_LEGGINGS)).unlockedBy("has_iron_boots", has(Items.IRON_BOOTS)).unlockedBy("has_iron_horse_armor", has(Items.IRON_HORSE_ARMOR)).unlockedBy("has_chainmail_helmet", has(Items.CHAINMAIL_HELMET)).unlockedBy("has_chainmail_chestplate", has(Items.CHAINMAIL_CHESTPLATE)).unlockedBy("has_chainmail_leggings", has(Items.CHAINMAIL_LEGGINGS)).unlockedBy("has_chainmail_boots", has(Items.CHAINMAIL_BOOTS)).save(pFinishedRecipeConsumer, new ResourceLocation(RDSLib.MODID, getCustomSmeltingRecipeName(Items.IRON_NUGGET, pSerializer)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.CLAY), RecipeCategory.BUILDING_BLOCKS, Blocks.TERRACOTTA.asItem(), 0.35F, pCookingTime, pSerializer).unlockedBy("has_clay_block", has(Blocks.CLAY)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.TERRACOTTA) + pFurnaceString + getItemName(Blocks.CLAY)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.NETHERRACK), RecipeCategory.MISC, Items.NETHER_BRICK, 0.1F, pCookingTime, pSerializer).unlockedBy("has_netherrack", has(Blocks.NETHERRACK)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.NETHER_BRICKS) + pFurnaceString + getItemName(Blocks.NETHERRACK)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.NETHER_QUARTZ_ORE), RecipeCategory.MISC, Items.QUARTZ, 0.2F, pCookingTime, pSerializer).unlockedBy("has_nether_quartz_ore", has(Blocks.NETHER_QUARTZ_ORE)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Items.QUARTZ) + pFurnaceString + getItemName(Blocks.NETHER_QUARTZ_ORE)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.WET_SPONGE), RecipeCategory.BUILDING_BLOCKS, Blocks.SPONGE.asItem(), 0.15F, pCookingTime, pSerializer).unlockedBy("has_wet_sponge", has(Blocks.WET_SPONGE)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.SPONGE) + pFurnaceString + getItemName(Blocks.WET_SPONGE)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.COBBLESTONE), RecipeCategory.BUILDING_BLOCKS, Blocks.STONE.asItem(), 0.1F, pCookingTime, pSerializer).unlockedBy("has_cobblestone", has(Blocks.COBBLESTONE)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.STONE) + pFurnaceString + getItemName(Blocks.COBBLESTONE)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.STONE), RecipeCategory.BUILDING_BLOCKS, Blocks.SMOOTH_STONE.asItem(), 0.1F, pCookingTime, pSerializer).unlockedBy("has_stone", has(Blocks.STONE)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.SMOOTH_STONE) + pFurnaceString + getItemName(Blocks.STONE)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.SANDSTONE), RecipeCategory.BUILDING_BLOCKS, Blocks.SMOOTH_SANDSTONE.asItem(), 0.1F, pCookingTime, pSerializer).unlockedBy("has_sandstone", has(Blocks.SANDSTONE)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.SMOOTH_SANDSTONE) + pFurnaceString + getItemName(Blocks.SANDSTONE)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.RED_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, Blocks.SMOOTH_RED_SANDSTONE.asItem(), 0.1F, pCookingTime, pSerializer).unlockedBy("has_red_sandstone", has(Blocks.RED_SANDSTONE)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.SMOOTH_RED_SANDSTONE) + pFurnaceString + getItemName(Blocks.RED_SANDSTONE)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.QUARTZ_BLOCK), RecipeCategory.BUILDING_BLOCKS, Blocks.SMOOTH_QUARTZ.asItem(), 0.1F, pCookingTime, pSerializer).unlockedBy("has_quartz_block", has(Blocks.QUARTZ_BLOCK)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.SMOOTH_QUARTZ) + pFurnaceString + getItemName(Blocks.QUARTZ_BLOCK)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, Blocks.CRACKED_STONE_BRICKS.asItem(), 0.1F, pCookingTime, pSerializer).unlockedBy("has_stone_bricks", has(Blocks.STONE_BRICKS)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.CRACKED_STONE_BRICKS) + pFurnaceString + getItemName(Blocks.STONE_BRICKS)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.ANCIENT_DEBRIS), RecipeCategory.MISC, Items.NETHERITE_SCRAP, 2.0F, pCookingTime, pSerializer).unlockedBy("has_ancient_debris", has(Blocks.ANCIENT_DEBRIS)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Items.NETHERITE_SCRAP) + pFurnaceString + getItemName(Blocks.ANCIENT_DEBRIS)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.BASALT), RecipeCategory.BUILDING_BLOCKS, Blocks.SMOOTH_BASALT, 0.1F, pCookingTime, pSerializer).unlockedBy("has_basalt", has(Blocks.BASALT)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.SMOOTH_BASALT) + pFurnaceString + getItemName(Blocks.BASALT)));
        FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(Blocks.COBBLED_DEEPSLATE), RecipeCategory.BUILDING_BLOCKS, Blocks.DEEPSLATE, 0.1F, pCookingTime, pSerializer).unlockedBy("has_cobbled_deepslate", has(Blocks.COBBLED_DEEPSLATE)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(Blocks.DEEPSLATE) + pFurnaceString + getItemName(Blocks.COBBLED_DEEPSLATE)));
    }

    public void customFurnaceRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pCustomFurnace, ItemLike pOuterBlock, ItemLike pInnerBlock) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, pCustomFurnace)
                .define('i', pInnerBlock)
                .define('o', pOuterBlock)
                .pattern("ooo")
                .pattern("oio")
                .pattern("ooo")
                .group(getItemName(pCustomFurnace))
                .unlockedBy(getHasName(pOuterBlock), inventoryTrigger(ItemPredicate.Builder.item().of(pOuterBlock).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(pCustomFurnace)));
    }

    public final ImmutableList<ItemLike> TERRACOTTAS = ImmutableList.of(Blocks.BLACK_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_TERRACOTTA,
            Blocks.CYAN_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.LIME_TERRACOTTA,
            Blocks.MAGENTA_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.WHITE_TERRACOTTA, Blocks.YELLOW_TERRACOTTA);
    public final ImmutableList<ItemLike> GLAZED_TERRACOTTAS = ImmutableList.of(Blocks.BLACK_GLAZED_TERRACOTTA, Blocks.BLUE_GLAZED_TERRACOTTA, Blocks.BROWN_GLAZED_TERRACOTTA,
            Blocks.CYAN_GLAZED_TERRACOTTA, Blocks.GRAY_GLAZED_TERRACOTTA, Blocks.GREEN_GLAZED_TERRACOTTA, Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA,
            Blocks.LIME_GLAZED_TERRACOTTA, Blocks.MAGENTA_GLAZED_TERRACOTTA, Blocks.ORANGE_GLAZED_TERRACOTTA, Blocks.PINK_GLAZED_TERRACOTTA, Blocks.PURPLE_GLAZED_TERRACOTTA,
            Blocks.RED_GLAZED_TERRACOTTA, Blocks.WHITE_GLAZED_TERRACOTTA, Blocks.YELLOW_GLAZED_TERRACOTTA);

    public final ImmutableList<String> COLORS = ImmutableList.of("black", "blue", "brown", "cyan", "gray", "green", "light_blue", "light_gray", "lime", "magenta", "orange",
            "pink", "purple", "red", "white", "yellow");

    public void customSmeltingList(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, Item pResult, float pExperience, int pCookingTime, String pGroup, RecipeSerializer<? extends Recipe> pSerializer, String pFurnaceString) {
        for(ItemLike itemlike : pIngredients) {
            FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(pResult) + pFurnaceString + getItemName(itemlike)));
        }
    }


    /**
     * This method is now deprecated.
     * Please use {@link RecipeGenHelper#terracottaRecipes(Consumer, FurnaceLikeSerializerV2, String)} instead.
     * @deprecated Since: 4.0.3.0
     */
    @Deprecated(since = "4.0.3.0")
    public void terracottaRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, FurnaceLikeSerializer<? extends Recipe> pSerializer, String pFurnaceString) {
        for(int i = 0; i < TERRACOTTAS.size(); i++) {
            ItemLike terracotta = TERRACOTTAS.get(i);
            ItemLike glazedTerracotta = GLAZED_TERRACOTTAS.get(i);
            String color = COLORS.get(i);

            FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(terracotta), RecipeCategory.DECORATIONS, glazedTerracotta, 0.1F, pSerializer.defaultCookingTime, pSerializer).unlockedBy(String.format("has_%s_terracotta", color), has(Blocks.YELLOW_TERRACOTTA)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(glazedTerracotta) + pFurnaceString + getItemName(terracotta)));
        }
    }

    public void terracottaRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer, FurnaceLikeSerializerV2<? extends Recipe> pSerializer, String pFurnaceString) {
        for(int i = 0; i < TERRACOTTAS.size(); i++) {
            ItemLike terracotta = TERRACOTTAS.get(i);
            ItemLike glazedTerracotta = GLAZED_TERRACOTTAS.get(i);
            String color = COLORS.get(i);

            FurnaceLikeRecipeBuilder.customSmelting(Ingredient.of(terracotta), RecipeCategory.DECORATIONS, glazedTerracotta, 0.1F, pSerializer.defaultCookingTime, pSerializer).unlockedBy(String.format("has_%s_terracotta", color), has(Blocks.YELLOW_TERRACOTTA)).save(pFinishedRecipeConsumer, new ResourceLocation(modid, getItemName(glazedTerracotta) + pFurnaceString + getItemName(terracotta)));
        }
    }

    protected static String getHasName(ItemLike pItemLike) {
        return "has_" + getItemName(pItemLike);
    }

    protected static String getItemName(ItemLike pItemLike) {
        return BuiltInRegistries.ITEM.getKey(pItemLike.asItem()).getPath();
    }

    protected static InventoryChangeTrigger.TriggerInstance has(ItemLike pItemLike) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(pItemLike).build());
    }
    
    protected static InventoryChangeTrigger.TriggerInstance has(TagKey<Item> pTag) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(pTag).build());
    }

    protected static InventoryChangeTrigger.TriggerInstance inventoryTrigger(ItemPredicate... pPredicates) {
        return new InventoryChangeTrigger.TriggerInstance(ContextAwarePredicate.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, pPredicates);
    }
    
    protected String getCustomSmeltingRecipeName(ItemLike itemLike, RecipeSerializer<? extends Recipe> pSerializer) {
        String pSerializerName = ForgeRegistries.RECIPE_SERIALIZERS.getKey(pSerializer).getPath();
        return getItemName(itemLike) + "_from_" + pSerializerName;
    }
}
