package com.fabriccommunity.thehallow.mixin.client;

import com.fabriccommunity.thehallow.client.screen.HallowedLoadingScreen;
import com.fabriccommunity.thehallow.entity.ShotgunProjectileEntity;
import com.fabriccommunity.thehallow.registry.HallowedDimensions;
import com.fabriccommunity.thehallow.registry.HallowedEntities;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.packet.EntitySpawnS2CPacket;
import net.minecraft.client.network.packet.PlayerRespawnS2CPacket;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

	@Shadow
	private ClientWorld world;

	@Inject(at = @At("TAIL"), method = "onEntitySpawn")
	public void assignProxy(EntitySpawnS2CPacket packet, CallbackInfo info) {
		if (packet.getEntityTypeId() == HallowedEntities.SHOTGUN_PROJECTILE) {
			int id = packet.getId();
			double x = packet.getX();
			double y = packet.getY();
			double z = packet.getZ();
			float yaw = packet.getYaw();
			float pitch = packet.getPitch();
			double velX = packet.getVelocityX();
			double velY = packet.getVelocityX();
			double velZ = packet.getVelocityX();
			Entity entity = world.getEntityById(packet.getEntityData());

			ShotgunProjectileEntity pellet = new ShotgunProjectileEntity(world, entity, x, y, z, yaw, pitch, velX, velY, velZ);
			pellet.setEntityId(id);
			pellet.setUuid(packet.getUuid());
			world.addEntity(id, pellet);
		}
	}
}
