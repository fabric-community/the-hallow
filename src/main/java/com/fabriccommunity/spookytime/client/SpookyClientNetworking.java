package com.fabriccommunity.spookytime.client;

import com.fabriccommunity.spookytime.entity.SpookyTreasureChestEntity;
import com.fabriccommunity.spookytime.registry.SpookyNetworking;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;

@Environment(EnvType.CLIENT)
public class SpookyClientNetworking {
	private SpookyClientNetworking() {
		// NO-OP
	}

	public static void init() {
		ClientSidePacketRegistry.INSTANCE.register(SpookyNetworking.SHOW_FLOATING_ITEM_S2C, (context, buf) -> {
			int rawId = buf.readVarInt();
			ItemStack stack = Registry.ITEM.get(rawId).getStackForRender();
			MinecraftClient.getInstance().gameRenderer.showFloatingItem(stack);
		});

		registerTreasureChestPacketHandler();
	}

	private static void registerTreasureChestPacketHandler() {
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
}
