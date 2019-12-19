package com.fabriccommunity.thehallow.client;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;

import net.minecraft.client.render.RenderLayer;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;
import com.fabriccommunity.thehallow.registry.HallowedFluids;

public class HallowedRenderLayers {
	public static void init() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), HallowedBlocks.TAINTED_GLASS, HallowedBlocks.TAINTED_GLASS_PANE,
			HallowedBlocks.SOUL_GLASS, HallowedBlocks.SOUL_GLASS_PANE, HallowedBlocks.HALLOWED_GATE,
			HallowedBlocks.WITCH_WATER_BUBBLE_COLUMN);
		
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(), HallowedBlocks.DECEASED_GRASS_BLOCK);
		
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), HallowedBlocks.BREAD_CRUMBS, HallowedBlocks.HALLOWED_TREASURE_CHEST,
			HallowedBlocks.INFUSION_ALTAR_BLOCK, HallowedBlocks.INFUSION_PILLAR_BLOCK, HallowedBlocks.RESTLESS_CACTUS,
			HallowedBlocks.TINY_PUMPKIN, HallowedBlocks.TOMBSTONE, HallowedBlocks.BRAMBLES,
			HallowedBlocks.DEADER_BUSH, HallowedBlocks.GLOOMSHROOM, HallowedBlocks.EERIE_GRASS,
			HallowedBlocks.TALL_EERIE_GRASS, HallowedBlocks.DEADWOOD_SAPLING, HallowedBlocks.DEADWOOD_DOOR,
			HallowedBlocks.DEADWOOD_TRAPDOOR, HallowedBlocks.DEADWOOD_VINES);
		
		BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getSolid(), HallowedFluids.BLOOD, HallowedFluids.FLOWING_BLOOD);
		
		BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), HallowedFluids.WITCH_WATER, HallowedFluids.FLOWING_WITCH_WATER);
	}
}
