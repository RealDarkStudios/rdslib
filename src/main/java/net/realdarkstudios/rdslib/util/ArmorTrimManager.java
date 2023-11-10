package net.realdarkstudios.rdslib.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;

import java.util.*;

public class ArmorTrimManager {
    private static Map<ResourceKey<TrimMaterial>, Float> trimMap = new HashMap<>();
    public static final float AMETHYST = registerArmorTrim(TrimMaterials.AMETHYST, 1.0F);
    public static final float COPPER = registerArmorTrim(TrimMaterials.COPPER, 0.5F);
    public static final float DIAMOND = registerArmorTrim(TrimMaterials.DIAMOND, 0.8F);
    public static final float EMERALD = registerArmorTrim(TrimMaterials.EMERALD, 0.7F);
    public static final float GOLD = registerArmorTrim(TrimMaterials.GOLD, 0.6F);
    public static final float IRON = registerArmorTrim(TrimMaterials.IRON, 0.2F);
    public static final float LAPIS = registerArmorTrim(TrimMaterials.LAPIS, 0.9F);
    public static final float NETHERITE = registerArmorTrim(TrimMaterials.NETHERITE, 0.3F);
    public static final float QUARTZ = registerArmorTrim(TrimMaterials.QUARTZ, 0.1F);
    public static final float REDSTONE = registerArmorTrim(TrimMaterials.REDSTONE, 0.4F);

    public static float registerArmorTrim(ResourceKey<TrimMaterial> trim, float itemModelIndex) {
        trimMap.put(trim, itemModelIndex);
        return itemModelIndex;
    }

    public static float getIndexFromTrim(ResourceKey<TrimMaterial> trim) {
        return trimMap.getOrDefault(trim, 69.0F);
    }

    public static List<ResourceKey<TrimMaterial>> getTrims() {
        return trimMap.keySet().stream().sorted((ResourceKey<TrimMaterial> t1, ResourceKey<TrimMaterial> t2) -> Float.compare(getIndexFromTrim(t1), getIndexFromTrim(t2))).toList();
    }
}
