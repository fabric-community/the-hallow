package com.fabriccommunity.spookytime.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.level.LevelProperties;

import java.util.Random;

/**
 * @author Indigo Amann
 */
@Mixin(ServerWorld.class)
public class ServerWorldLightningMixin {
	@Redirect(method = "tickChunk", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 0))
	private int makeItThunderrr(Random random, int bound) {
		return random.nextInt(bound) / 80;
	}
	
	@Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/LevelProperties;getClearWeatherTime()I", ordinal = 0))
	private int ofcWeAllLoveThunderstorms(LevelProperties levelProperties) {
		return levelProperties.getClearWeatherTime() > 1 ? levelProperties.getClearWeatherTime() - 50 : 0;
	}
}
