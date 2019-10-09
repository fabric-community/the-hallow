package com.fabriccommunity.spookytime;

import com.fabriccommunity.spookytime.block.entity.InfusionAltarBlockEntity;
import com.fabriccommunity.spookytime.block.entity.InfusionPillarBlockEntity;
import com.fabriccommunity.spookytime.block.entity.TinyPumpkinBlockEntity;
import com.fabriccommunity.spookytime.client.FluidResourceLoader;
import com.fabriccommunity.spookytime.client.SpookyClientNetworking;
import com.fabriccommunity.spookytime.client.SpookyColors;
import com.fabriccommunity.spookytime.client.render.*;
import com.fabriccommunity.spookytime.entity.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class SpookyTimeClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(PumpcownEntity.class, (dispatcher, context) -> new PumpcownEntityRenderer(dispatcher));
		EntityRendererRegistry.INSTANCE.register(CrowEntity.class, (dispatcher, context) -> new CrowEntityRenderer(dispatcher));
		EntityRendererRegistry.INSTANCE.register(SpookyTreasureChestEntity.class, ((dispatcher, context) -> new SpookyTreasureChestEntityRenderer(dispatcher)));
		EntityRendererRegistry.INSTANCE.register(SpookyCactusEntity.class, (dispatcher, context) -> new SpookyCactusEntityRenderer(dispatcher));

		BlockEntityRendererRegistry.INSTANCE.register(TinyPumpkinBlockEntity.class, new TinyPumpkinRenderer());
		BlockEntityRendererRegistry.INSTANCE.register(InfusionPillarBlockEntity.class, new InfusionPillarBlockEntityRenderer());
		BlockEntityRendererRegistry.INSTANCE.register(InfusionAltarBlockEntity.class, new InfusionAltarBlockEntityRenderer());
		BlockEntityRendererRegistry.INSTANCE.register(SpookyTreasureChestBlockEntity.class, new SpookyTreasureChestBlockEntityRenderer());

		SpookyColors.init();
		SpookyClientNetworking.init();

		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new FluidResourceLoader());
	}
}
