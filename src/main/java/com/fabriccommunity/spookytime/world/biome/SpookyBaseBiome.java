package com.fabriccommunity.spookytime.world.biome;

import com.fabriccommunity.spookytime.common.SpookyBiomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public abstract class SpookyBaseBiome extends Biome {
	
	protected static final ConfiguredSurfaceBuilder<?> SURFACE_BUILDER = new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, SpookyBiomeFeatures.SPOOKY_FOREST);
	
	protected SpookyBaseBiome(Settings settings) {
		super(settings.parent(null));
		
		DefaultBiomeFeatures.addLandCarvers(this);
		SpookyBiomeFeatures.addDisks(this);
		DefaultBiomeFeatures.addDefaultOres(this);
		
		SpookyBiomes.BIOMES.add(this);
	}

}
