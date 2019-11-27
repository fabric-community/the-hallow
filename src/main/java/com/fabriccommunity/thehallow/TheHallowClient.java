package com.fabriccommunity.thehallow;

import com.fabriccommunity.thehallow.client.FluidResourceLoader;
import com.fabriccommunity.thehallow.client.HallowedClientNetworking;
import com.fabriccommunity.thehallow.client.HallowedColors;
import com.fabriccommunity.thehallow.client.render.*;
import com.fabriccommunity.thehallow.registry.HallowedBlockEntities;
import com.fabriccommunity.thehallow.registry.HallowedEntities;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class TheHallowClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(HallowedEntities.PUMPCOWN, (dispatcher, context) -> new PumpcownEntityRenderer(dispatcher));
		EntityRendererRegistry.INSTANCE.register(HallowedEntities.CROW, (dispatcher, context) -> new CrowEntityRenderer(dispatcher));
		EntityRendererRegistry.INSTANCE.register(HallowedEntities.HALLOWED_TREASURE_CHEST, ((dispatcher, context) -> new HallowedTreasureChestEntityRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(HallowedEntities.RESTLESS_CACTUS, (dispatcher, context) -> new HallowedCactusEntityRenderer(dispatcher));
		EntityRendererRegistry.INSTANCE.register(HallowedEntities.MUMMY, (dispatcher, context) -> new MummyEntityRenderer(dispatcher));
		EntityRendererRegistry.INSTANCE.register(HallowedEntities.CULTIST, (dispatcher, context) -> new CultistEntityRenderer(dispatcher));
		EntityRendererRegistry.INSTANCE.register(HallowedEntities.SHOTGUN_PROJECTILE, (dispatcher, context) -> new ShotgunProjectileEntityRenderer(dispatcher));

		BlockEntityRendererRegistry.INSTANCE.register(HallowedBlockEntities.TINY_PUMPKIN, TinyPumpkinRenderer::new);
		BlockEntityRendererRegistry.INSTANCE.register(HallowedBlockEntities.INFUSION_PILLAR_BLOCK_ENTITY, InfusionPillarBlockEntityRenderer::new);
		BlockEntityRendererRegistry.INSTANCE.register(HallowedBlockEntities.INFUSION_ALTAR_BLOCK_ENTITY, InfusionAltarBlockEntityRenderer::new);
		BlockEntityRendererRegistry.INSTANCE.register(HallowedBlockEntities.HALLOWED_TREASURE_CHEST_BE, HallowedTreasureChestBlockEntityRenderer::new);

		HallowedClientNetworking.init();
		HallowedColors.init();

		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new FluidResourceLoader());
	}
}
