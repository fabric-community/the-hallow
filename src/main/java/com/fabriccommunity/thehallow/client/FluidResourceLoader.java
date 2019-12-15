package com.fabriccommunity.thehallow.client;

import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.resource.ResourceReloadListenerKeys;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.fluid.FluidState;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.registry.HallowedFluids;

import java.util.Arrays;
import java.util.Collection;

public class FluidResourceLoader implements SimpleSynchronousResourceReloadListener {
	
	private static final Identifier STILL_WATER_TEX_ID = new Identifier("minecraft", "block/water_still");
	private static final Identifier FLOWING_WATER_TEX_ID = new Identifier("minecraft", "block/water_flow");
	
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
			public Sprite[] getFluidSprites(BlockRenderView extendedBlockView, BlockPos blockPos, FluidState fluidState) {
				return new Sprite[]{MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEX).apply(STILL_WATER_TEX_ID), MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEX).apply(FLOWING_WATER_TEX_ID)};
			}
			
			@Override
			public int getFluidColor(BlockRenderView view, BlockPos pos, FluidState state) {
				return 0x5900A3;
			}
		};
		
		FluidRenderHandler bloodRenderHandler = new FluidRenderHandler() {
			@Override
			public Sprite[] getFluidSprites(BlockRenderView extendedBlockView, BlockPos blockPos, FluidState fluidState) {
				return new Sprite[]{MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEX).apply(STILL_WATER_TEX_ID), MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEX).apply(FLOWING_WATER_TEX_ID)};
			}
			
			@Override
			public int getFluidColor(BlockRenderView view, BlockPos pos, FluidState state) {
				return 0xBB0A1E;
			}
		};
		
		FluidRenderHandlerRegistry.INSTANCE.register(HallowedFluids.WITCH_WATER, witchWaterRenderHandler);
		FluidRenderHandlerRegistry.INSTANCE.register(HallowedFluids.FLOWING_WITCH_WATER, witchWaterRenderHandler);
		
		FluidRenderHandlerRegistry.INSTANCE.register(HallowedFluids.BLOOD, bloodRenderHandler);
		FluidRenderHandlerRegistry.INSTANCE.register(HallowedFluids.FLOWING_BLOOD, bloodRenderHandler);
	}
}
