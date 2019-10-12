package com.fabriccommunity.spookytime.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.thrown.ThrownEntity;
import net.minecraft.entity.thrown.ThrownPotionEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Allow witches to be damaged by splash potions of water
 *
 * @author Will Toll
 */

@Mixin(ThrownPotionEntity.class)
public class ThrownPotionEntityMixin {
	@Inject(method = "doesWaterHurt", at = @At("RETURN"), cancellable = true)
	private static void doesWaterHurt(LivingEntity entity, CallbackInfoReturnable<Boolean> info) {
		info.setReturnValue(info.getReturnValue() || entity instanceof WitchEntity);
	}
}
