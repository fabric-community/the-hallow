package com.fabriccommunity.thehallow.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.Heightmap;

@Mixin(SpawnRestriction.class)
public interface SpawnRestrictionInvoker {
	@Invoker
	public static <T extends MobEntity> void invokeRegister(EntityType<T> type, SpawnRestriction.Location location, Heightmap.Type heightmapType, SpawnRestriction.SpawnPredicate<T> restriction) {
		throw new UnsupportedOperationException();
	}
}
