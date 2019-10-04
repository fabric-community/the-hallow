package com.fabriccommunity.spookytime.networking;

import com.fabriccommunity.spookytime.entity.SpookyTreasureChestEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class ClientPacketHandlers {

	public static void registerPacketHandlers() {
		ClientSidePacketRegistry.INSTANCE.register(SpookyTreasureChestEntity.ENTITY_ID, (packetContext, packetByteBuf) -> {
			double x = packetByteBuf.readDouble();
			double y = packetByteBuf.readDouble();
			double z = packetByteBuf.readDouble();

			boolean shouldReplace = packetByteBuf.readBoolean();
			float initialRotation = packetByteBuf.readFloat();

			int entityId = packetByteBuf.readInt();

			packetContext.getTaskQueue().execute(() -> {
				SpookyTreasureChestEntity treasureChest = new SpookyTreasureChestEntity(MinecraftClient.getInstance().world, x, y, z, shouldReplace, initialRotation);
				treasureChest.setEntityId(entityId);
				treasureChest.setPosition(x, y, z);
				MinecraftClient.getInstance().world.addEntity(treasureChest.getEntityId(), treasureChest);
			});
		});
	}

	private ClientPacketHandlers() {
		// NO-OP
	}
}
