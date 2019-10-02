package com.fabriccommunity.spookytime.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

/**
 * @author Indigo Amann
 */
@Mixin(ZombieEntity.class)
public class MobPumpkinMixin {
    @Redirect(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/ZombieEntity;getEquippedStack(Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"))
    private ItemStack getStack(ZombieEntity zombieEntity, EquipmentSlot equipmentSlot_1) {
        ItemStack stack = zombieEntity.getEquippedStack(EquipmentSlot.HEAD);
        if (stack.isEmpty()) {
            Random random = new Random();
            if (random.nextInt(3) == 0) {
                stack = new ItemStack(random.nextBoolean() ? Items.CARVED_PUMPKIN : Items.JACK_O_LANTERN);
                zombieEntity.setEquippedStack(EquipmentSlot.HEAD, stack);
            }
        }
        return stack;
    }
}
