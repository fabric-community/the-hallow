package com.fabriccommunity.thehallow.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.Heightmap;

import com.fabriccommunity.thehallow.registry.HallowedEntities;

@Mixin(SpawnRestriction.class)
public class SpawnRestrictionMixin {
	@Shadow
	private static <T extends MobEntity> void setRestrictions(EntityType<T> entityType_1, SpawnRestriction.Location spawnRestriction$Location_1, Heightmap.Type heightmap$Type_1, SpawnRestriction.class_4306<T> spawnRestriction$class_4306_1) {}
	
	@SuppressWarnings("UnresolvedMixinReference")
	@Inject(method = "<clinit>", at = @At("TAIL"))
	private static void clinit(CallbackInfo ci) {
		setRestrictions(HallowedEntities.MUMMY, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::method_20680);
	}
}
