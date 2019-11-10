package com.fabriccommunity.thehallow.client;

import com.fabriccommunity.thehallow.entity.HallowedTreasureChestEntity;
import com.fabriccommunity.thehallow.entity.ShotgunProjectileEntity;
import com.fabriccommunity.thehallow.registry.HallowedNetworking;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;

@Environment(EnvType.CLIENT)
public class HallowedClientNetworking {
	private HallowedClientNetworking() {
		// NO-OP
	}

	public static void init() {
		ClientSidePacketRegistry.INSTANCE.register(HallowedNetworking.SHOW_FLOATING_ITEM_S2C, (context, buf) -> {
			int rawId = buf.readVarInt();
			ItemStack stack = Registry.ITEM.get(rawId).getStackForRender();
			MinecraftClient.getInstance().gameRenderer.showFloatingItem(stack);
		});

		registerTreasureChestPacketHandler();
		registerShotgunProjectilePacketHandler();
	}

	private static void registerTreasureChestPacketHandler() {
		ClientSidePacketRegistry.INSTANCE.register(HallowedTreasureChestEntity.ENTITY_ID, (packetContext, packetByteBuf) -> {
			double x = packetByteBuf.readDouble();
			double y = packetByteBuf.readDouble();
			double z = packetByteBuf.readDouble();

			boolean shouldReplace = packetByteBuf.readBoolean();
			float initialRotation = packetByteBuf.readFloat();

			int entityId = packetByteBuf.readInt();

			packetContext.getTaskQueue().execute(() -> {
				HallowedTreasureChestEntity treasureChest = new HallowedTreasureChestEntity(MinecraftClient.getInstance().world, x, y, z, shouldReplace, initialRotation);
				treasureChest.setEntityId(entityId);
				treasureChest.setPosition(x, y, z);
				MinecraftClient.getInstance().world.addEntity(treasureChest.getEntityId(), treasureChest);
			});
		});
	}

	private static void registerShotgunProjectilePacketHandler() {
		ClientSidePacketRegistry.INSTANCE.register(ShotgunProjectileEntity.ENTITY_ID, (packetContext, packetByteBuf) -> {
			double x = packetByteBuf.readDouble();
			double y = packetByteBuf.readDouble();
			double z = packetByteBuf.readDouble();

			float yaw = packetByteBuf.readFloat();
			float pitch = packetByteBuf.readFloat();

			double velX = packetByteBuf.readDouble();
			double velY = packetByteBuf.readDouble();
			double velZ = packetByteBuf.readDouble();

			int entityIdOwner = packetByteBuf.readInt();
			int entityId = packetByteBuf.readInt();

			ClientWorld world = MinecraftClient.getInstance().world;
			ShotgunProjectileEntity pellet = new ShotgunProjectileEntity(
				world,
				world.getEntityById(entityIdOwner),
				x, y, z,
				yaw, pitch,
				velX, velY, velZ
			);
			pellet.setEntityId(entityId);
			world.addEntity(entityId, pellet);
		});
	}
}
