package com.fabriccommunity.spookytime;

import com.fabriccommunity.spookytime.block.entity.TinyPumpkinBlockEntity;
import com.fabriccommunity.spookytime.client.SpookyColors;
import com.fabriccommunity.spookytime.client.FluidResourceLoader;
import com.fabriccommunity.spookytime.client.render.PumpcownEntityRenderer;
import com.fabriccommunity.spookytime.client.render.SpookyTreasureChestBlockEntityRenderer;
import com.fabriccommunity.spookytime.client.render.SpookyTreasureChestEntityRenderer;
import com.fabriccommunity.spookytime.client.render.TinyPumpkinRenderer;
import com.fabriccommunity.spookytime.entity.PumpcownEntity;

import com.fabriccommunity.spookytime.entity.SpookyTreasureChestBlockEntity;
import com.fabriccommunity.spookytime.entity.SpookyTreasureChestEntity;
import com.fabriccommunity.spookytime.networking.ClientPacketHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;

import net.minecraft.resource.ResourceType;

public class SpookyTimeClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(PumpcownEntity.class, (dispatcher, context) -> new PumpcownEntityRenderer(dispatcher));
		EntityRendererRegistry.INSTANCE.register(SpookyTreasureChestEntity.class, ((dispatcher, context) -> new SpookyTreasureChestEntityRenderer(dispatcher)));

		BlockEntityRendererRegistry.INSTANCE.register(TinyPumpkinBlockEntity.class, new TinyPumpkinRenderer());
		BlockEntityRendererRegistry.INSTANCE.register(SpookyTreasureChestBlockEntity.class, new SpookyTreasureChestBlockEntityRenderer());

		SpookyColors.init();
		ClientPacketHandlers.registerPacketHandlers();

		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new FluidResourceLoader());
	}
}
