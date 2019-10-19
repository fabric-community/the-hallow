package com.fabriccommunity.thehallow.registry;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.enchantment.BeheadingEnchantment;
import com.fabriccommunity.thehallow.enchantment.LifestealEnchantment;

public class HallowedEnchantments {
	public static final Enchantment BEHEADING = register("beheading", new BeheadingEnchantment(Enchantment.Weight.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
	public static final Enchantment LIFESTEAL = register("lifesteal", new LifestealEnchantment(Enchantment.Weight.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
	
	private HallowedEnchantments() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	protected static <T extends Enchantment> T register(String name, T enchantment) {
		return Registry.register(Registry.ENCHANTMENT, TheHallow.id(name), enchantment);
	}
}
