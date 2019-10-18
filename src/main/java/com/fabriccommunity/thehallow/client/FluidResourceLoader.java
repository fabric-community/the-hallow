package com.fabriccommunity.thehallow.client;

import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.resource.ResourceReloadListenerKeys;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.fluid.FluidState;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ExtendedBlockView;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.registry.HallowFluids;

import java.util.Arrays;
import java.util.Collection;

public class FluidResourceLoader implements SimpleSynchronousResourceReloadListener {
	@Override
	public Identifier getFabricId() {
		return TheHallow.id("fluid_resource_loader");
	}
	
	@Override
	public Collection<Identifier> getFabricDependencies() {
		return Arrays.asList(ResourceReloadListenerKeys.MODELS, ResourceReloadListenerKeys.TEXTURES);
	}
	
	@Override
	public void apply(ResourceManager resourceManager) {
		FluidRenderHandler witchWaterRenderHandler = new FluidRenderHandler() {
			@Override
			public Sprite[] getFluidSprites(ExtendedBlockView extendedBlockView, BlockPos blockPos, FluidState fluidState) {
				return new Sprite[]{MinecraftClient.getInstance().getSpriteAtlas().getSprite("block/water_still"), MinecraftClient.getInstance().getSpriteAtlas().getSprite("block/water_flow")};
			}
			
			@Override
			public int getFluidColor(ExtendedBlockView view, BlockPos pos, FluidState state) {
				return 0x5900A3;
			}
		};
		
		FluidRenderHandler bloodRenderHandler = new FluidRenderHandler() {
			@Override
			public Sprite[] getFluidSprites(ExtendedBlockView extendedBlockView, BlockPos blockPos, FluidState fluidState) {
				return new Sprite[]{MinecraftClient.getInstance().getSpriteAtlas().getSprite("block/water_still"), MinecraftClient.getInstance().getSpriteAtlas().getSprite("block/water_flow")};
			}
			
			@Override
			public int getFluidColor(ExtendedBlockView view, BlockPos pos, FluidState state) {
				return 0xBB0A1E;
			}
		};
		
		FluidRenderHandlerRegistry.INSTANCE.register(HallowFluids.WITCH_WATER, witchWaterRenderHandler);
		FluidRenderHandlerRegistry.INSTANCE.register(HallowFluids.FLOWING_WITCH_WATER, witchWaterRenderHandler);
		
		FluidRenderHandlerRegistry.INSTANCE.register(HallowFluids.BLOOD, bloodRenderHandler);
		FluidRenderHandlerRegistry.INSTANCE.register(HallowFluids.FLOWING_BLOOD, bloodRenderHandler);
	}
}
