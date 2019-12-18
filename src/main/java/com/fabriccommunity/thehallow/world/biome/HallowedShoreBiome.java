package com.fabriccommunity.thehallow.world.biome;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;
import com.fabriccommunity.thehallow.world.feature.HallowedBiomeFeatures;

// TODO
public class HallowedShoreBiome extends HallowedBaseBiome {
	private static final TernarySurfaceConfig TAINTED_GRAVEL_CONFIG = new TernarySurfaceConfig(
		HallowedBlocks.TAINTED_GRAVEL.getDefaultState(),
		HallowedBlocks.TAINTED_GRAVEL.getDefaultState(),
		HallowedBlocks.TAINTED_GRAVEL.getDefaultState()
	);
	
	public HallowedShoreBiome() {
		super(new Settings().surfaceBuilder(new ConfiguredSurfaceBuilder<TernarySurfaceConfig>(SurfaceBuilder.DEFAULT, TAINTED_GRAVEL_CONFIG)).precipitation(Precipitation.NONE).category(Category.OCEAN).depth(0.02f).scale(0.025f).temperature(0.5f).downfall(0.8f).waterColor(0x5900A3).waterFogColor(0x5900A3));
		
		this.addStructureFeature(Feature.MINESHAFT.configure(new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL)));
		
		HallowedBiomeFeatures.addGrass(this);
		HallowedBiomeFeatures.addLakes(this);
		HallowedBiomeFeatures.addDefaultHallowedTrees(this);
	}
}
