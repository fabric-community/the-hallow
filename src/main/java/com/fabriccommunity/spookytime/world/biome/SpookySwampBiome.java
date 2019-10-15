package com.fabriccommunity.spookytime.world.biome;

import com.fabriccommunity.spookytime.registry.SpookyFeatures;
import com.fabriccommunity.spookytime.world.feature.SpookyBiomeFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;

// TODO
public class SpookySwampBiome extends SpookyBaseBiome {
	public SpookySwampBiome() {
		super(new Settings().surfaceBuilder(SURFACE_BUILDER).precipitation(Precipitation.NONE).category(Category.SWAMP).depth(-0.2f).scale(0.18f).temperature(0.7f).downfall(0.8f).waterColor(0xBB0A1E).waterFogColor(0xBB0A1E));

		GRASS_COLOR = 0x2A2A2A;
		FOLIAGE_COLOR = 0x2A2A2A;

		this.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL));
		
		SpookyBiomeFeatures.addGrass(this);
		SpookyBiomeFeatures.addLakes(this);
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(SpookyFeatures.PUMPKIN, FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP_DOUBLE, new ChanceDecoratorConfig(32)));
		SpookyBiomeFeatures.addSpookySwampTrees(this);
		SpookyBiomeFeatures.addLairs(this);
	}
	
	@Override
	public float getFogIntensity() {
		return 96;
	}
}
