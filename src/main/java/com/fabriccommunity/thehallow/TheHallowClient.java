package com.fabriccommunity.thehallow;

import com.fabriccommunity.thehallow.client.FluidResourceLoader;
import com.fabriccommunity.thehallow.client.HallowedClientNetworking;
import com.fabriccommunity.thehallow.client.HallowedColors;
import com.fabriccommunity.thehallow.client.render.*;
import com.fabriccommunity.thehallow.registry.HallowedBlockEntities;
import com.fabriccommunity.thehallow.registry.HallowedBlocks;
import com.fabriccommunity.thehallow.registry.HallowedEntities;
import com.fabriccommunity.thehallow.registry.HallowedFluids;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.render.RenderLayer;
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

		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), HallowedBlocks.TAINTED_GLASS, HallowedBlocks.TAINTED_GLASS_PANE, 
				HallowedBlocks.SOUL_GLASS, HallowedBlocks.SOUL_GLASS_PANE, HallowedBlocks.HALLOWED_GATE,
				HallowedBlocks.WITCH_WATER_BUBBLE_COLUMN);
		
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), HallowedBlocks.BREAD_CRUMBS, HallowedBlocks.HALLOWED_TREASURE_CHEST,
				HallowedBlocks.INFUSION_ALTAR_BLOCK, HallowedBlocks.INFUSION_PILLAR_BLOCK, HallowedBlocks.RESTLESS_CACTUS,
				HallowedBlocks.TINY_PUMPKIN, HallowedBlocks.TOMBSTONE, HallowedBlocks.BRAMBLES,
				HallowedBlocks.DEADER_BUSH, HallowedBlocks.GLOOMSHROOM, HallowedBlocks.EERIE_GRASS,
				HallowedBlocks.TALL_EERIE_GRASS, HallowedBlocks.DEADWOOD_DOOR, HallowedBlocks.DEADWOOD_TRAPDOOR,
				HallowedBlocks.DEADWOOD_VINES);
		
		BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getSolid(), HallowedFluids.BLOOD, HallowedFluids.FLOWING_BLOOD,
				HallowedFluids.WITCH_WATER, HallowedFluids.FLOWING_WITCH_WATER);
		
		HallowedClientNetworking.init();
		HallowedColors.init();

		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new FluidResourceLoader());
	}
}
