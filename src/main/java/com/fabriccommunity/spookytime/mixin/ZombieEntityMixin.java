package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.MixinHelpers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;

/**
 * Handles zombies sometimes spawning wearing pumpkins on their heads.
 *
 * @author Indigo Amann
 */
@Mixin(ZombieEntity.class)
public class ZombieEntityMixin {
	@Redirect(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/ZombieEntity;getEquippedStack(Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"))
	private ItemStack getStack(ZombieEntity zombieEntity, EquipmentSlot equipmentSlot) {
		return MixinHelpers.getEquippedOrPumpkin(zombieEntity, equipmentSlot);
	}
}
