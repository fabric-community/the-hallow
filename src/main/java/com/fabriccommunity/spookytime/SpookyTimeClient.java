package com.fabriccommunity.spookytime;

import com.fabriccommunity.spookytime.block.entity.InfusionAltarBlockEntity;
import com.fabriccommunity.spookytime.block.entity.InfusionPillarBlockEntity;
import com.fabriccommunity.spookytime.block.entity.TinyPumpkinBlockEntity;
import com.fabriccommunity.spookytime.client.FluidResourceLoader;
import com.fabriccommunity.spookytime.client.SpookyClientNetworking;
import com.fabriccommunity.spookytime.client.SpookyColors;
import com.fabriccommunity.spookytime.client.render.CrowEntityRenderer;
import com.fabriccommunity.spookytime.client.render.InfusionAltarBlockEntityRenderer;
import com.fabriccommunity.spookytime.client.render.InfusionPillarBlockEntityRenderer;
import com.fabriccommunity.spookytime.client.render.PumpcownEntityRenderer;
import com.fabriccommunity.spookytime.client.render.SpookyCactusEntityRenderer;
import com.fabriccommunity.spookytime.client.render.SpookyTreasureChestBlockEntityRenderer;
import com.fabriccommunity.spookytime.client.render.SpookyTreasureChestEntityRenderer;
import com.fabriccommunity.spookytime.client.render.TinyPumpkinRenderer;
import com.fabriccommunity.spookytime.entity.CrowEntity;
import com.fabriccommunity.spookytime.entity.PumpcownEntity;
import com.fabriccommunity.spookytime.entity.SpookyCactusEntity;
import com.fabriccommunity.spookytime.entity.SpookyTreasureChestBlockEntity;
import com.fabriccommunity.spookytime.entity.SpookyTreasureChestEntity;
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
