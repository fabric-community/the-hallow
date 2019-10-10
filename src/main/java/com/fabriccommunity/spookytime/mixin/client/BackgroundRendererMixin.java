package com.fabriccommunity.spookytime.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.fabriccommunity.spookytime.SpookyConfig;
import com.fabriccommunity.spookytime.api.SpookyBiomeInfo;
import com.fabriccommunity.spookytime.registry.SpookyDimensions;

/**
 * Allows changing fog distance per biome for our custom biomes.
 *
 * @author Zundrel
 */
@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
	@ModifyVariable(method = "applyFog", at = @At(value = "INVOKE_ASSIGN", target = "net/minecraft/client/render/GameRenderer.getViewDistance()F", shift = At.Shift.AFTER))
	public float applyFog(float oldIntensity) {
		MinecraftClient client = MinecraftClient.getInstance();
		World world = client.world;
		PlayerEntity player = client.player;
		float totalIntensity = 0;
		int count = 0;
		int radius = SpookyConfig.SpookyFog.fogSmoothingRadius;
		
		if (world.getDimension().getType() == SpookyDimensions.SPOOKY) {
			for (int x = 0; x < radius; x++) {
				for (int z = 0; z < radius; z++) {
					BlockPos pos = player.getBlockPos().add(x - (radius / 2), 0, z - (radius / 2));
					
					if (world.getBiome(pos) instanceof SpookyBiomeInfo) {
						SpookyBiomeInfo biomeInfo = (SpookyBiomeInfo) world.getBiome(pos);
						totalIntensity += biomeInfo.getFogIntensity();
						count++;
					}
				}
			}
			return Math.min(totalIntensity / count, oldIntensity);
		}
		
		return oldIntensity;
	}
}
