package com.fabriccommunity.thehallow.client;

import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

import com.fabriccommunity.thehallow.client.render.CrowEntityRenderer;
import com.fabriccommunity.thehallow.client.render.CultistEntityRenderer;
import com.fabriccommunity.thehallow.client.render.HallowedCactusEntityRenderer;
import com.fabriccommunity.thehallow.client.render.HallowedTreasureChestEntityRenderer;
import com.fabriccommunity.thehallow.client.render.MummyEntityRenderer;
import com.fabriccommunity.thehallow.client.render.PumpcownEntityRenderer;
import com.fabriccommunity.thehallow.client.render.ShotgunProjectileEntityRenderer;
import com.fabriccommunity.thehallow.registry.HallowedEntities;

public class HallowedEntityRenderers {
	public static void init() {
		EntityRendererRegistry.INSTANCE.register(HallowedEntities.PUMPCOWN, (dispatcher, context) -> new PumpcownEntityRenderer(dispatcher));
		EntityRendererRegistry.INSTANCE.register(HallowedEntities.CROW, (dispatcher, context) -> new CrowEntityRenderer(dispatcher));
		EntityRendererRegistry.INSTANCE.register(HallowedEntities.HALLOWED_TREASURE_CHEST, ((dispatcher, context) -> new HallowedTreasureChestEntityRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(HallowedEntities.RESTLESS_CACTUS, (dispatcher, context) -> new HallowedCactusEntityRenderer(dispatcher));
		EntityRendererRegistry.INSTANCE.register(HallowedEntities.MUMMY, (dispatcher, context) -> new MummyEntityRenderer(dispatcher));
		EntityRendererRegistry.INSTANCE.register(HallowedEntities.CULTIST, (dispatcher, context) -> new CultistEntityRenderer(dispatcher));
		EntityRendererRegistry.INSTANCE.register(HallowedEntities.SHOTGUN_PROJECTILE, (dispatcher, context) -> new ShotgunProjectileEntityRenderer(dispatcher));
	}
}
