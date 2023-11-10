package net.realdarkstudios.rdslib.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public interface IArmorEffectMaterial extends ArmorMaterial {
    int getDurabilityForType(ArmorItem.Type pType);

    int getDefenseForType(ArmorItem.Type pType);

    int getEnchantmentValue();

    SoundEvent getEquipSound();

    Ingredient getRepairIngredient();

    String getName();

    float getToughness();

    /**
     * Gets the percentage of knockback resistance provided by armor of the material.
     */
    float getKnockbackResistance();

    List<MobEffectInstance> getEffects();
}
