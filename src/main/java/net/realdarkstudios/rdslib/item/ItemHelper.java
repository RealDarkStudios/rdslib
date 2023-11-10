package net.realdarkstudios.rdslib.item;

import net.realdarkstudios.rdslib.item.rarity.RarityArmorItem;
import net.realdarkstudios.rdslib.rarity.RDSRarity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public class ItemHelper {
    public static ArmorItem helmet(ArmorMaterial pMaterial, Item.Properties pProperties) {
        return new ArmorItem(pMaterial, ArmorItem.Type.HELMET, pProperties);
    }

    public static ArmorItem chestplate(ArmorMaterial pMaterial, Item.Properties pProperties) {
        return new ArmorItem(pMaterial, ArmorItem.Type.CHESTPLATE, pProperties);
    }

    public static ArmorItem leggings(ArmorMaterial pMaterial, Item.Properties pProperties) {
        return new ArmorItem(pMaterial, ArmorItem.Type.LEGGINGS, pProperties);
    }

    public static ArmorItem boots(ArmorMaterial pMaterial, Item.Properties pProperties) {
        return new ArmorItem(pMaterial, ArmorItem.Type.BOOTS, pProperties);
    }

    public static ArmorItem helmet(ArmorMaterial pMaterial, RDSRarity pRarity, Item.Properties pProperties) {
        return new RarityArmorItem(pMaterial, ArmorItem.Type.HELMET, pProperties, pRarity);
    }

    public static ArmorItem chestplate(ArmorMaterial pMaterial, RDSRarity pRarity, Item.Properties pProperties) {
        return new RarityArmorItem(pMaterial, ArmorItem.Type.CHESTPLATE, pProperties, pRarity);
    }

    public static ArmorItem leggings(ArmorMaterial pMaterial, RDSRarity pRarity, Item.Properties pProperties) {
        return new RarityArmorItem(pMaterial, ArmorItem.Type.LEGGINGS, pProperties, pRarity);
    }

    public static ArmorItem boots(ArmorMaterial pMaterial, RDSRarity pRarity, Item.Properties pProperties) {
        return new RarityArmorItem(pMaterial, ArmorItem.Type.BOOTS, pProperties, pRarity);
    }

    public static <T extends ArmorItem> T customHelmet(Class<T> clazz, IArmorEffectMaterial pMaterial, Item.Properties pProperties) {
        try {
            return clazz
                    .getConstructor(IArmorEffectMaterial.class, ArmorItem.Type.class, Item.Properties.class)
                    .newInstance(pMaterial, ArmorItem.Type.HELMET, pProperties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends ArmorItem> T customChestplate(Class<T> clazz, IArmorEffectMaterial pMaterial, Item.Properties pProperties) {
        try {
            return clazz
                    .getConstructor(IArmorEffectMaterial.class, ArmorItem.Type.class, Item.Properties.class)
                    .newInstance(pMaterial, ArmorItem.Type.CHESTPLATE, pProperties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends ArmorItem> T customLeggings(Class<T> clazz, IArmorEffectMaterial pMaterial, Item.Properties pProperties) {
        try {
            return clazz
                    .getConstructor(IArmorEffectMaterial.class, ArmorItem.Type.class, Item.Properties.class)
                    .newInstance(pMaterial, ArmorItem.Type.LEGGINGS, pProperties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends ArmorItem> T customBoots(Class<T> clazz, IArmorEffectMaterial pMaterial, Item.Properties pProperties) {
        try {
            return clazz
                    .getConstructor(IArmorEffectMaterial.class, ArmorItem.Type.class, Item.Properties.class)
                    .newInstance(pMaterial, ArmorItem.Type.BOOTS, pProperties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends ArmorItem> T customHelmet(Class<T> clazz, IArmorEffectMaterial pMaterial, RDSRarity pRarity, Item.Properties pProperties) {
        try {
            return clazz
                    .getConstructor(IArmorEffectMaterial.class, ArmorItem.Type.class, Supplier.class, Item.Properties.class)
                    .newInstance(pMaterial, ArmorItem.Type.HELMET, pRarity, pProperties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends ArmorItem> T customChestplate(Class<T> clazz, IArmorEffectMaterial pMaterial, RDSRarity pRarity, Item.Properties pProperties) {
        try {
            return clazz
                    .getConstructor(IArmorEffectMaterial.class, ArmorItem.Type.class, Supplier.class, Item.Properties.class)
                    .newInstance(pMaterial, ArmorItem.Type.CHESTPLATE, pRarity, pProperties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends ArmorItem> T customLeggings(Class<T> clazz, IArmorEffectMaterial pMaterial, RDSRarity pRarity, Item.Properties pProperties) {
        try {
            return clazz
                    .getConstructor(IArmorEffectMaterial.class, ArmorItem.Type.class, Supplier.class, Item.Properties.class)
                    .newInstance(pMaterial, ArmorItem.Type.LEGGINGS, pRarity, pProperties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends ArmorItem> T customBoots(Class<T> clazz, IArmorEffectMaterial pMaterial, RDSRarity pRarity, Item.Properties pProperties) {
        try {
            return clazz
                    .getConstructor(IArmorEffectMaterial.class, ArmorItem.Type.class, Supplier.class, Item.Properties.class)
                    .newInstance(pMaterial, ArmorItem.Type.BOOTS, pRarity, pProperties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
