package com.fabriccommunity.thehallow.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.fabriccommunity.thehallow.HallowedConfig;
import com.fabriccommunity.thehallow.api.HallowedBiomeInfo;
import com.fabriccommunity.thehallow.registry.HallowedDimensions;

/**
 * Allows changing fog distance per biome for our custom biomes.
 *
 * @author Zundrel
 */
@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
	@ModifyVariable(method = "applyFog(Lnet/minecraft/client/render/Camera;I)V", at = @At(value = "INVOKE_ASSIGN", target = "net/minecraft/client/render/GameRenderer.getViewDistance()F", shift = At.Shift.AFTER))
	public float applyFog(float oldIntensity) {
		MinecraftClient client = MinecraftClient.getInstance();
		World world = client.world;
		PlayerEntity player = client.player;
		float totalIntensity = 0;
		int count = 0;
		int radius = HallowedConfig.HallowedFog.fogSmoothingRadius;
		
		if (world.getDimension().getType() == HallowedDimensions.SPOOKY) {
			for (int x = 0; x < radius; x++) {
				for (int z = 0; z < radius; z++) {
					BlockPos pos = player.getBlockPos().add(x - (radius / 2), 0, z - (radius / 2));
					
					if (world.getBiome(pos) instanceof HallowedBiomeInfo) {
						HallowedBiomeInfo biomeInfo = (HallowedBiomeInfo) world.getBiome(pos);
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
