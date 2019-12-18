package com.fabriccommunity.thehallow;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;

import net.minecraft.resource.ResourceType;

import com.fabriccommunity.thehallow.client.FluidResourceLoader;
import com.fabriccommunity.thehallow.client.HallowedBlockEntityRenderers;
import com.fabriccommunity.thehallow.client.HallowedClientNetworking;
import com.fabriccommunity.thehallow.client.HallowedColors;
import com.fabriccommunity.thehallow.client.HallowedEntityRenderers;
import com.fabriccommunity.thehallow.client.HallowedRenderLayers;

public class TheHallowClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HallowedEntityRenderers.init();
		HallowedBlockEntityRenderers.init();
		HallowedRenderLayers.init();
		HallowedClientNetworking.init();
		HallowedColors.init();

		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new FluidResourceLoader());
	}
}
