package com.fabriccommunity.spookytime.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.thrown.ThrownPotionEntity;

/**
 * Allow witches to be damaged by splash potions of water
 *
 * @author Will Toll
 */

@Mixin(ThrownPotionEntity.class)
public class ThrownPotionEntityMixin {
	@Inject(method = "doesWaterHurt(Lnet/minecraft/entity/LivingEntity;)Z", at = @At("RETURN"), cancellable = true)
	private static void doesWaterHurt(LivingEntity entity, CallbackInfoReturnable<Boolean> info) {
		info.setReturnValue(info.getReturnValue() || entity instanceof WitchEntity);
	}
}
