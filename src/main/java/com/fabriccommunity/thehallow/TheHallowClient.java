package com.fabriccommunity.thehallow;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;

import net.minecraft.resource.ResourceType;

import com.fabriccommunity.thehallow.block.entity.InfusionAltarBlockEntity;
import com.fabriccommunity.thehallow.block.entity.InfusionPillarBlockEntity;
import com.fabriccommunity.thehallow.block.entity.TinyPumpkinBlockEntity;
import com.fabriccommunity.thehallow.client.FluidResourceLoader;
import com.fabriccommunity.thehallow.client.HallowedClientNetworking;
import com.fabriccommunity.thehallow.client.HallowedColors;
import com.fabriccommunity.thehallow.client.render.CrowEntityRenderer;
import com.fabriccommunity.thehallow.client.render.CultistEntityRenderer;
import com.fabriccommunity.thehallow.client.render.InfusionAltarBlockEntityRenderer;
import com.fabriccommunity.thehallow.client.render.InfusionPillarBlockEntityRenderer;
import com.fabriccommunity.thehallow.client.render.MummyEntityRenderer;
import com.fabriccommunity.thehallow.client.render.PumpcownEntityRenderer;
import com.fabriccommunity.thehallow.client.render.HallowedCactusEntityRenderer;
import com.fabriccommunity.thehallow.client.render.HallowedTreasureChestBlockEntityRenderer;
import com.fabriccommunity.thehallow.client.render.HallowedTreasureChestEntityRenderer;
import com.fabriccommunity.thehallow.client.render.TinyPumpkinRenderer;
import com.fabriccommunity.thehallow.entity.CrowEntity;
import com.fabriccommunity.thehallow.entity.CultistEntity;
import com.fabriccommunity.thehallow.entity.MummyEntity;
import com.fabriccommunity.thehallow.entity.PumpcownEntity;
import com.fabriccommunity.thehallow.entity.RestlessCactusEntity;
import com.fabriccommunity.thehallow.entity.HallowedTreasureChestBlockEntity;
import com.fabriccommunity.thehallow.entity.HallowedTreasureChestEntity;

public class TheHallowClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(PumpcownEntity.class, (dispatcher, context) -> new PumpcownEntityRenderer(dispatcher));
		EntityRendererRegistry.INSTANCE.register(CrowEntity.class, (dispatcher, context) -> new CrowEntityRenderer(dispatcher));
		EntityRendererRegistry.INSTANCE.register(HallowedTreasureChestEntity.class, ((dispatcher, context) -> new HallowedTreasureChestEntityRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(RestlessCactusEntity.class, (dispatcher, context) -> new HallowedCactusEntityRenderer(dispatcher));
		EntityRendererRegistry.INSTANCE.register(MummyEntity.class, (dispatcher, context) -> new MummyEntityRenderer(dispatcher));
		EntityRendererRegistry.INSTANCE.register(CultistEntity.class, (dispatcher, context) -> new CultistEntityRenderer(dispatcher));
		
		BlockEntityRendererRegistry.INSTANCE.register(TinyPumpkinBlockEntity.class, new TinyPumpkinRenderer());
		BlockEntityRendererRegistry.INSTANCE.register(InfusionPillarBlockEntity.class, new InfusionPillarBlockEntityRenderer());
		BlockEntityRendererRegistry.INSTANCE.register(InfusionAltarBlockEntity.class, new InfusionAltarBlockEntityRenderer());
		BlockEntityRendererRegistry.INSTANCE.register(HallowedTreasureChestBlockEntity.class, new HallowedTreasureChestBlockEntityRenderer());
		
		HallowedClientNetworking.init();
		HallowedColors.init();
		
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new FluidResourceLoader());
	}
}
