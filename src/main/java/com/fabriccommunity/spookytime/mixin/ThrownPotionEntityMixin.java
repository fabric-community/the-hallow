package com.fabriccommunity.spookytime.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.thrown.ThrownPotionEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Causes witches to be damaged by splash potions of water
 *
 * @author Will Toll
 */

@Mixin(ThrownPotionEntity.class)
public class ThrownPotionEntityMixin {
	@Inject(method = "doesWaterHurt", at = @At("RETURN"))
	private static boolean doesWaterHurt(LivingEntity livingEntity_1, CallbackInfoReturnable<Boolean> info) {
		return livingEntity_1 instanceof EndermanEntity || livingEntity_1 instanceof BlazeEntity || livingEntity_1 instanceof WitchEntity;
	}
}
