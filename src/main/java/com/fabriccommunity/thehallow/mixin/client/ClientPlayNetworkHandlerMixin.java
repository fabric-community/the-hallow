package com.fabriccommunity.thehallow.mixin.client;

import com.fabriccommunity.thehallow.client.screen.HallowedLoadingScreen;
import com.fabriccommunity.thehallow.registry.HallowedDimensions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.packet.PlayerRespawnS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
	@Redirect(method = "onPlayerRespawn(Lnet/minecraft/client/network/packet/PlayerRespawnS2CPacket;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;openScreen(Lnet/minecraft/client/gui/screen/Screen;)V", ordinal = 0))
	private void redirectOpenScreen_onPlayerRespawn(MinecraftClient client, Screen screen, PlayerRespawnS2CPacket packet) {
		if (packet.getDimension() == HallowedDimensions.THE_HALLOW) {
			Screen current = client.currentScreen;
			if (!(current instanceof HallowedLoadingScreen)) {
				client.openScreen(new HallowedLoadingScreen());
			}
		} else {
			client.openScreen(screen);
		}
	}
}
