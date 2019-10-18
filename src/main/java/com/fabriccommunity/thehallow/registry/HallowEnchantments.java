package com.fabriccommunity.thehallow.registry;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.enchantment.BeheadingEnchantment;
import com.fabriccommunity.thehallow.enchantment.LifestealEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class HallowEnchantments {
	public static final Enchantment BEHEADING = register("beheading", new BeheadingEnchantment(Enchantment.Weight.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
	public static final Enchantment LIFESTEAL = register("lifesteal", new LifestealEnchantment(Enchantment.Weight.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));
	
	private HallowEnchantments() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	protected static <T extends Enchantment> T register(String name, T enchantment) {
		return Registry.register(Registry.ENCHANTMENT, TheHallow.id(name), enchantment);
	}
}
