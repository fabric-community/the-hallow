package com.fabriccommunity.spookytime;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Random;

/**
 * @author Indigo Amann
 */
public class MixinHelpers {
    public static final Random random = new Random();
    public static ItemStack getEquippedPumpkin(LivingEntity entity, EquipmentSlot slot) {
        if (slot != EquipmentSlot.HEAD || !SpookyConfig.PumpkinMobs.headArmor) return entity.getEquippedStack(slot);
        ItemStack stack = entity.getEquippedStack(EquipmentSlot.HEAD);
        if (stack.isEmpty()) {
            if (random.nextInt(3) == 0) {
                stack = new ItemStack(random.nextBoolean() ? Items.CARVED_PUMPKIN : Items.JACK_O_LANTERN);
                entity.setEquippedStack(EquipmentSlot.HEAD, stack);
            }
        }
        return stack;
    }
}
