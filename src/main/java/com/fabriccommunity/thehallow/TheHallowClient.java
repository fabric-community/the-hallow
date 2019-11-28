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

		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.TAINTED_GLASS, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.TAINTED_GLASS_PANE, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.SOUL_GLASS, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.SOUL_GLASS_PANE, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.BREAD_CRUMBS, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.HALLOWED_GATE, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.HALLOWED_TREASURE_CHEST, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.INFUSION_ALTAR_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.INFUSION_PILLAR_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.RESTLESS_CACTUS, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.TINY_PUMPKIN, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.TOMBSTONE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.WITCH_WATER_BUBBLE_COLUMN, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.BRAMBLES, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.DEADER_BUSH, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.GLOOMSHROOM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.EERIE_GRASS, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.TALL_EERIE_GRASS, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.DEADWOOD_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.DEADWOOD_TRAPDOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(HallowedBlocks.DEADWOOD_VINES, RenderLayer.getCutout());
		
		BlockRenderLayerMap.INSTANCE.putFluid(HallowedFluids.BLOOD, RenderLayer.getSolid());
		BlockRenderLayerMap.INSTANCE.putFluid(HallowedFluids.FLOWING_BLOOD, RenderLayer.getSolid());
		BlockRenderLayerMap.INSTANCE.putFluid(HallowedFluids.WITCH_WATER, RenderLayer.getSolid());
		BlockRenderLayerMap.INSTANCE.putFluid(HallowedFluids.FLOWING_WITCH_WATER, RenderLayer.getSolid());
		
		HallowedClientNetworking.init();
		HallowedColors.init();

		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new FluidResourceLoader());
	}
}
