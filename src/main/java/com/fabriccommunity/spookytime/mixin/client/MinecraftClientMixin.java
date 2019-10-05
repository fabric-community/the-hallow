package com.fabriccommunity.spookytime.mixin.client;

import com.fabriccommunity.spookytime.client.screen.SpookyLoadingScreen;
import com.fabriccommunity.spookytime.registry.SpookyDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.world.ClientWorld;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
	@Shadow
	protected abstract void reset(Screen screen);
	
	@Redirect(method = "joinWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;reset(Lnet/minecraft/client/gui/screen/Screen;)V", ordinal = 0))
	private void redirectReset_joinWorld(MinecraftClient client, Screen screen, ClientWorld world) {
		if (world.getDimension().getType() == SpookyDimensions.SPOOKY) {
			reset(new SpookyLoadingScreen());
		} else {
			reset(screen);
		}
	}
}
