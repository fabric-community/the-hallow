package com.fabriccommunity.thehallow.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.world.ClientWorld;

import com.fabriccommunity.thehallow.client.screen.HallowedLoadingScreen;
import com.fabriccommunity.thehallow.registry.HallowedDimensions;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
	@Shadow
	protected abstract void reset(Screen screen);
	
	@Redirect(method = "joinWorld(Lnet/minecraft/client/world/ClientWorld;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;reset(Lnet/minecraft/client/gui/screen/Screen;)V", ordinal = 0))
	private void redirectReset_joinWorld(MinecraftClient client, Screen screen, ClientWorld world) {
		if (world.getDimension().getType() == HallowedDimensions.SPOOKY) {
			reset(new HallowedLoadingScreen());
		} else {
			reset(screen);
		}
	}
}
