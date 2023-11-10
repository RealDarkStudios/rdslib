package net.realdarkstudios.rdslib.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
public class ArmorEffectItem extends ArmorItem {
    private final IArmorEffectMaterial effectMaterial;

    public ArmorEffectItem(IArmorEffectMaterial material, EquipmentSlot slot, Properties settings) {
        super(material, slot, settings);
        this.effectMaterial = material;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(!world.isClientSide() && effectMaterial.getEffects() != null) {
            for (MobEffectInstance entry: effectMaterial.getEffects()) {
                addStatusEffectForMaterial(player, entry);
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, MobEffectInstance effect) {
        boolean hasPlayerEffect = player.hasEffect(effect.getEffect());

        if(hasCorrectArmorOn(effectMaterial, player) && !hasPlayerEffect) {
            player.addEffect(new MobEffectInstance(effect.getEffect(),
                    effect.getDuration(), effect.getAmplifier()));

            //if(new Random().nextFloat() > 0.6f) { // 40% of damaging the armor! Possibly!
            //    player.getInventory().hurtArmor(DamageSource.MAGIC, 1f, new int[]{0, 1, 2, 3});
            //}
        }
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        ItemStack bootsStack = player.getInventory().getArmor(0);
        ItemStack leggingsStack = player.getInventory().getArmor(1);
        ItemStack chestplateStack = player.getInventory().getArmor(2);
        ItemStack helmetStack = player.getInventory().getArmor(3);
        ArmorItem boots, leggings, chestplate, helmet;

        if (!bootsStack.isEmpty() && bootsStack.getItem() instanceof ArmorItem) boots = ((ArmorItem) bootsStack.getItem());
        else return false;
        if (!leggingsStack.isEmpty() && leggingsStack.getItem() instanceof ArmorItem) leggings = ((ArmorItem) leggingsStack.getItem());
        else return false;
        if (!chestplateStack.isEmpty() && chestplateStack.getItem() instanceof ArmorItem) chestplate = ((ArmorItem) chestplateStack.getItem());
        else return false;
        if (!helmetStack.isEmpty() && helmetStack.getItem() instanceof ArmorItem) helmet = ((ArmorItem) helmetStack.getItem());
        else return false;

        return helmet.getMaterial() == material && chestplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }
}
