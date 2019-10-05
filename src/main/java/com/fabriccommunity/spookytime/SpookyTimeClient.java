package com.fabriccommunity.spookytime;

import com.fabriccommunity.spookytime.block.entity.TinyPumpkinBlockEntity;
import com.fabriccommunity.spookytime.client.FluidResourceLoader;
import com.fabriccommunity.spookytime.client.SpookyColors;
import com.fabriccommunity.spookytime.client.render.PumpcownEntityRenderer;
import com.fabriccommunity.spookytime.client.render.TinyPumpkinRenderer;
import com.fabriccommunity.spookytime.entity.PumpcownEntity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;

import net.minecraft.resource.ResourceType;

public class SpookyTimeClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(PumpcownEntity.class, (dispatcher, context) -> new PumpcownEntityRenderer(dispatcher));
		BlockEntityRendererRegistry.INSTANCE.register(TinyPumpkinBlockEntity.class, new TinyPumpkinRenderer());
		
		SpookyColors.init();
		
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new FluidResourceLoader());
	}
}
