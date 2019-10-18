package com.fabriccommunity.thehallow.world.biome;

import com.fabriccommunity.thehallow.registry.HallowEntities;
import com.fabriccommunity.thehallow.registry.HallowFeatures;
import com.fabriccommunity.thehallow.world.feature.HallowBiomeFeatures;
import net.minecraft.entity.EntityCategory;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;

// TODO
public class HallowSwampBiome extends HallowBaseBiome {
	public HallowSwampBiome() {
		super(new Settings().surfaceBuilder(MARSH_SURFACE_BUILDER).precipitation(Precipitation.NONE).category(Category.SWAMP).depth(-0.2f).scale(0.18f).temperature(0.7f).downfall(0.8f).waterColor(0xBB0A1E).waterFogColor(0xBB0A1E));
		
		GRASS_COLOR = 0x2A2A2A;
		FOLIAGE_COLOR = 0x2A2A2A;
		
		this.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL));
		
		HallowBiomeFeatures.addGrass(this);
		HallowBiomeFeatures.addGloomshrooms(this);
		HallowBiomeFeatures.addLakes(this);
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(HallowFeatures.PUMPKIN, FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP_DOUBLE, new ChanceDecoratorConfig(32)));
		HallowBiomeFeatures.addHallowSwampTrees(this);
		HallowBiomeFeatures.addLairs(this);
	}
	
	@Override
	public float getFogIntensity() {
		return 96;
	}
}
