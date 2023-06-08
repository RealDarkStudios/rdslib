package net.darkstudios.rdslib.util.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

import java.lang.reflect.InvocationTargetException;

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

    public static <T extends ArmorItem> T customHelmet(Class<T> clazz, IArmorEffectMaterial pMaterial, Item.Properties pProperties) {
        try {
            return clazz
                    .getConstructor(IArmorEffectMaterial.class, EquipmentSlot.class, Item.Properties.class)
                    .newInstance(pMaterial, EquipmentSlot.HEAD, pProperties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends ArmorItem> T customChestplate(Class<T> clazz, IArmorEffectMaterial pMaterial, Item.Properties pProperties) {
        try {
            return clazz
                    .getConstructor(IArmorEffectMaterial.class, EquipmentSlot.class, Item.Properties.class)
                    .newInstance(pMaterial, EquipmentSlot.CHEST, pProperties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends ArmorItem> T customLeggings(Class<T> clazz, IArmorEffectMaterial pMaterial, Item.Properties pProperties) {
        try {
            return clazz
                    .getConstructor(IArmorEffectMaterial.class, EquipmentSlot.class, Item.Properties.class)
                    .newInstance(pMaterial, EquipmentSlot.LEGS, pProperties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends ArmorItem> T customBoots(Class<T> clazz, IArmorEffectMaterial pMaterial, Item.Properties pProperties) {
        try {
            return clazz
                    .getConstructor(IArmorEffectMaterial.class, EquipmentSlot.class, Item.Properties.class)
                    .newInstance(pMaterial, EquipmentSlot.FEET, pProperties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
