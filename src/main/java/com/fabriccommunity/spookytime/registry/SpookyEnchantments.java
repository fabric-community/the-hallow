package com.fabriccommunity.spookytime.registry;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.enchantment.BeheadingEnchantment;
import com.fabriccommunity.spookytime.enchantment.LifestealEnchantment;

public class SpookyEnchantments {
	public static final Enchantment BEHEADING = register("beheading", new BeheadingEnchantment(Enchantment.Weight.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
	public static final Enchantment LIFESTEAL = register("lifesteal", new LifestealEnchantment(Enchantment.Weight.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
	
	private SpookyEnchantments() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	protected static <T extends Enchantment> T register(String name, T enchantment) {
		return Registry.register(Registry.ENCHANTMENT, SpookyTime.id(name), enchantment);
	}
}
