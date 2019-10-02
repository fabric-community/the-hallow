package com.fabriccommunity.spookytime;

import com.fabriccommunity.spookytime.client.SpookyColors;
import com.fabriccommunity.spookytime.client.render.PumpcownEntityRenderer;
import com.fabriccommunity.spookytime.entity.PumpcownEntity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;

public class SpookyTimeClient implements ClientModInitializer
{
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(PumpcownEntity.class, (dispatcher, context) -> new PumpcownEntityRenderer(dispatcher));

        SpookyColors.init();
	}
}
