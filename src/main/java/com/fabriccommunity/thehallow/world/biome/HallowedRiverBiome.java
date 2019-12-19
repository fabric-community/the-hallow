package com.fabriccommunity.thehallow.world.biome;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;

import com.fabriccommunity.thehallow.world.feature.HallowedBiomeFeatures;

// TODO
public class HallowedRiverBiome extends HallowedBaseBiome {
	public HallowedRiverBiome() {
		super(new Settings().surfaceBuilder(SURFACE_BUILDER).precipitation(Precipitation.NONE).category(Category.RIVER).depth(-0.5f).scale(0.1f).temperature(0.7f).downfall(0.8f).waterColor(4159204).waterFogColor(329011));
		
		this.addStructureFeature(Feature.MINESHAFT.configure(new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL)));
		
		HallowedBiomeFeatures.addGrass(this);
		HallowedBiomeFeatures.addLakes(this);
		HallowedBiomeFeatures.addColoredPumpkins(this);
		HallowedBiomeFeatures.addDefaultHallowedTrees(this);
	}
}
