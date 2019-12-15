package com.fabriccommunity.thehallow.world.biome;

import com.fabriccommunity.thehallow.world.feature.HallowedBiomeFeatures;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;

// TODO
public class HauntedMoorBiome extends HallowedBaseBiome {
	public HauntedMoorBiome() {
		super(new Settings().surfaceBuilder(SURFACE_BUILDER).precipitation(Precipitation.NONE).category(Category.PLAINS).depth(3.15f).scale(0.22f).temperature(0.5f).downfall(0.4f).waterColor(0xBB0A1E).waterFogColor(0xBB0A1E));
		
		this.addStructureFeature(Feature.MINESHAFT.configure(new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL)));
		
		HallowedBiomeFeatures.addGrass(this);
		HallowedBiomeFeatures.addExtraLakes(this);
		HallowedBiomeFeatures.addColoredPumpkins(this);
		HallowedBiomeFeatures.addDefaultUplandsGeneration(this);
	}
}
