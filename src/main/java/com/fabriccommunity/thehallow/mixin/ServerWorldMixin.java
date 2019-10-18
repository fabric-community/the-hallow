package com.fabriccommunity.thehallow.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.level.LevelProperties;

import com.fabriccommunity.thehallow.HallowConfig;

import java.util.Random;

/**
 * Allows increasing the chance of thunder during a thunderstorm, and thunderstorms themselves.
 *
 * @author Indigo Amann
 */
@Mixin(ServerWorld.class)
public class ServerWorldMixin {
	@Redirect(method = "tickChunk(Lnet/minecraft/world/chunk/WorldChunk;I)V", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 0))
	private int makeItThunderrr(Random random, int bound) {
		return random.nextInt(bound) / HallowConfig.SpookyWeather.thunderModifier;
	}
	
	@Redirect(method = "tick(Ljava/util/function/BooleanSupplier;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/LevelProperties;getClearWeatherTime()I", ordinal = 0))
	private int ofcWeAllLoveThunderstorms(LevelProperties levelProperties) {
		return HallowConfig.SpookyWeather.lessClearSkies ? levelProperties.getClearWeatherTime() > 1 ? levelProperties.getClearWeatherTime() - 50 : 0 : levelProperties.getClearWeatherTime();
	}
}
