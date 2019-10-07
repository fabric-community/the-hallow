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
 * Rewrites the water damage functionality of thrown potions to allow witches to be damaged by splash potions of water
 *
 * @author Will Toll
 */

@Mixin(ThrownPotionEntity.class)
public abstract class ThrownPotionEntityMixin
	extends ThrownEntity
	implements FlyingItemEntity {

	private List<Class<? extends LivingEntity>> hurtByWater = new ArrayList<Class<? extends LivingEntity>>();

	public ThrownPotionEntityMixin(EntityType<? extends ThrownPotionEntity> type, World world) {
		super(type, world);
		registerHurtByWater(EndermanEntity.class);
		registerHurtByWater(BlazeEntity.class);
		registerHurtByWater(WitchEntity.class);
	}

	private void damageEntitiesHurtByWater() {
		this.world.getEntities(LivingEntity.class, this.getBoundingBox().expand(4.0D, 2.0D, 4.0D)).forEach((entity) -> {
			if (this.squaredDistanceTo(entity) < 16.0D && isHurtByWater(entity)) {
				entity.damage(DamageSource.magic(entity, this.getOwner()), 1.0F);
			}
		});
	}

	public void registerHurtByWater(Class<? extends LivingEntity> entityclass) {
		this.hurtByWater.add(entityclass);
	}

	private boolean isHurtByWater(LivingEntity entity) {
		for (Class<? extends LivingEntity> entityclass : this.hurtByWater) {
			if (entity.getClass().isInstance(entityclass)) {
				return true;
			}
		}
		return true;
	}
}
