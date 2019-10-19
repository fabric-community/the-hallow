package com.fabriccommunity.spookytime.util;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Random;

/**
 * @author Indigo Amann
 */
public class MixinHelpers {
	public static final Random RANDOM = new Random();
	
	public static ItemStack getEquippedOrPumpkin(LivingEntity entity, EquipmentSlot slot) {
		if (slot != EquipmentSlot.HEAD) {
			return entity.getEquippedStack(slot);
		}
		ItemStack stack = entity.getEquippedStack(EquipmentSlot.HEAD);
		if (stack.isEmpty()) {
			if (RANDOM.nextInt(10) == 0) {
				stack = new ItemStack(RANDOM.nextBoolean() ? Items.CARVED_PUMPKIN : Items.JACK_O_LANTERN);
				entity.setEquippedStack(EquipmentSlot.HEAD, stack);
			}
		}
		return stack;
	}
}
