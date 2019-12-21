package com.fabriccommunity.thehallow.mixin.client;

import com.fabriccommunity.thehallow.api.HallowedFluidInfo;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Makes water fog colors based on the fluid.
 *
 * @author Zundrel
 */
@Mixin(Biome.class)
public class BiomeMixin {
	@Inject(method = "Lnet/minecraft/world/biome/Biome;getWaterFogColor()I", at = @At(value = "HEAD"), cancellable = true)
	private final void getWaterFogColor(CallbackInfoReturnable<Integer> cir) {
		if (MinecraftClient.getInstance().gameRenderer.getCamera().getSubmergedFluidState().getFluid() instanceof HallowedFluidInfo) {
			HallowedFluidInfo fluidInfo = (HallowedFluidInfo) MinecraftClient.getInstance().gameRenderer.getCamera().getSubmergedFluidState().getFluid();
			cir.setReturnValue(fluidInfo.getFogColor());
		}
	}
}
