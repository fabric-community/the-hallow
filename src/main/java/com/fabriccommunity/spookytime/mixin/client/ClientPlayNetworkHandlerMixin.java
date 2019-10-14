package com.fabriccommunity.spookytime.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.packet.PlayerRespawnS2CPacket;

import com.fabriccommunity.spookytime.client.screen.SpookyLoadingScreen;
import com.fabriccommunity.spookytime.registry.SpookyDimensions;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
	@Redirect(method = "onPlayerRespawn(Lnet/minecraft/client/network/packet/PlayerRespawnS2CPacket;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;openScreen(Lnet/minecraft/client/gui/screen/Screen;)V", ordinal = 0))
	private void redirectOpenScreen_onPlayerRespawn(MinecraftClient client, Screen screen, PlayerRespawnS2CPacket packet) {
		if (packet.getDimension() == SpookyDimensions.SPOOKY) {
			Screen current = client.currentScreen;
			if (!(current instanceof SpookyLoadingScreen)) {
				client.openScreen(new SpookyLoadingScreen());
			}
		} else {
			client.openScreen(screen);
		}
	}
}
