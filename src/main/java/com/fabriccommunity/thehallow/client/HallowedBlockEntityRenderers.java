package com.fabriccommunity.thehallow.client;

import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;

import com.fabriccommunity.thehallow.client.render.HallowedTreasureChestBlockEntityRenderer;
import com.fabriccommunity.thehallow.client.render.InfusionAltarBlockEntityRenderer;
import com.fabriccommunity.thehallow.client.render.InfusionPillarBlockEntityRenderer;
import com.fabriccommunity.thehallow.client.render.TinyPumpkinRenderer;
import com.fabriccommunity.thehallow.registry.HallowedBlockEntities;

public class HallowedBlockEntityRenderers {
	public static void init() {
		BlockEntityRendererRegistry.INSTANCE.register(HallowedBlockEntities.TINY_PUMPKIN, TinyPumpkinRenderer::new);
		BlockEntityRendererRegistry.INSTANCE.register(HallowedBlockEntities.INFUSION_PILLAR_BLOCK_ENTITY, InfusionPillarBlockEntityRenderer::new);
		BlockEntityRendererRegistry.INSTANCE.register(HallowedBlockEntities.INFUSION_ALTAR_BLOCK_ENTITY, InfusionAltarBlockEntityRenderer::new);
		BlockEntityRendererRegistry.INSTANCE.register(HallowedBlockEntities.HALLOWED_TREASURE_CHEST_BE, HallowedTreasureChestBlockEntityRenderer::new);
	}
}
