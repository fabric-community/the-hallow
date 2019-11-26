package com.fabriccommunity.thehallow.world.biome;

import com.fabriccommunity.thehallow.world.feature.HallowedBiomeFeatures;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;

// TODO
public class HallowedSwampBiome extends HallowedBaseBiome {
	public HallowedSwampBiome() {
		super(new Settings().surfaceBuilder(MARSH_SURFACE_BUILDER).precipitation(Precipitation.NONE).category(Category.SWAMP).depth(-0.2f).scale(0.18f).temperature(0.7f).downfall(0.8f).waterColor(0xBB0A1E).waterFogColor(0xBB0A1E));
		
		GRASS_COLOR = 0x2A2A2A;
		FOLIAGE_COLOR = 0x2A2A2A;
		
		this.addStructureFeature(Feature.MINESHAFT.configure(new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL)));
		
		HallowedBiomeFeatures.addGrass(this);
		HallowedBiomeFeatures.addGloomshrooms(this);
		HallowedBiomeFeatures.addLakes(this);
		HallowedBiomeFeatures.addColoredPumpkins(this);
		HallowedBiomeFeatures.addHallowedSwampTrees(this);
		HallowedBiomeFeatures.addLairs(this);
	}
	
	@Override
	public float getFogIntensity() {
		return 96;
	}
}
