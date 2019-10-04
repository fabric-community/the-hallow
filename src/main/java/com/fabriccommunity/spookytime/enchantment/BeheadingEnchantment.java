package com.fabriccommunity.spookytime.enchantment;

import java.util.Random;

import com.fabriccommunity.spookytime.registry.SpookyEnchantments;
import com.fabriccommunity.spookytime.registry.SpookyItems;
import com.google.common.collect.ImmutableList;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public class BeheadingEnchantment extends Enchantment {
	protected static final ImmutableList<Integer> dropChance = ImmutableList.of(5, 7, 10, 12, 15);

    public BeheadingEnchantment(Enchantment.Weight weight, EnchantmentTarget target, EquipmentSlot[] slots) {
        super(weight, target, slots);
    }

    public int getMaximumLevel() {
        return 5;
    }

	public static boolean hasBeheading(LivingEntity livingEntity) {
    	return EnchantmentHelper.getEquipmentLevel(SpookyEnchantments.BEHEADING, livingEntity) > 0;
    }

	public static boolean getHead(DamageSource damageSource) {
    	if (damageSource.getAttacker() instanceof LivingEntity && hasBeheading((LivingEntity)damageSource.getAttacker())) {
			LivingEntity attacker = (LivingEntity)damageSource.getAttacker();
			int enchantmentLevel = EnchantmentHelper.getEquipmentLevel(SpookyEnchantments.BEHEADING, attacker);
			int scytheMultiplier = attacker.getActiveItem().getItem() == SpookyItems.REAPERS_SCYTHE ? 2 : 1;
			return new Random().nextInt(100) <= dropChance.get(enchantmentLevel - 1) * scytheMultiplier;
		} else {
    		return false;
		}
    }
}
