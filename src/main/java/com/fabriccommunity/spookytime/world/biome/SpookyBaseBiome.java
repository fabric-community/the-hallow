package com.fabriccommunity.spookytime.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import com.fabriccommunity.spookytime.registry.SpookyFeatures;
import com.fabriccommunity.spookytime.world.feature.SpookyBiomeFeatures;

import com.google.common.collect.Lists;
import java.util.List;

public abstract class SpookyBaseBiome extends Biome {
	public static final List<Biome> BIOMES = Lists.newArrayList();
	
	protected static final ConfiguredSurfaceBuilder<?> SURFACE_BUILDER = new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, SpookyFeatures.SPOOKY_FOREST);
	protected static final ConfiguredSurfaceBuilder<?> DESERT_SURFACE_BUILDER = new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, SpookyFeatures.GHASTLY_DESERT);
	
	protected SpookyBaseBiome(Settings settings) {
		super(settings.parent(null));
		
		DefaultBiomeFeatures.addLandCarvers(this);
		SpookyBiomeFeatures.addDisks(this);
		DefaultBiomeFeatures.addDefaultOres(this);
		
		BIOMES.add(this);
	}
	
	@Override
	public int getSkyColor(float float_1) {
		return 0x360063;
	}
}
