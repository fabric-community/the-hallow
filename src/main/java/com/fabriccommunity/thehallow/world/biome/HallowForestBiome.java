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
public class HallowForestBiome extends HallowBaseBiome {
	public HallowForestBiome(float depth, float scale) {
		super(new Settings().surfaceBuilder(SURFACE_BUILDER).precipitation(Precipitation.NONE).category(Category.FOREST).depth(depth).scale(scale).temperature(0.7f).downfall(0.8f).waterColor(0x5900A3).waterFogColor(0x5900A3));
		
		GRASS_COLOR = 0x6B6B6B;
		FOLIAGE_COLOR = 0x6B6B6B;
		
		this.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL));
		
		HallowBiomeFeatures.addGrass(this);
		HallowBiomeFeatures.addLakes(this);
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(HallowFeatures.PUMPKIN, FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP_DOUBLE, new ChanceDecoratorConfig(32)));
		HallowBiomeFeatures.addDefaultHallowTrees(this);
		HallowBiomeFeatures.addHallowForestTrees(this);
		HallowBiomeFeatures.addWells(this);
		HallowBiomeFeatures.addLairs(this);
		
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(HallowEntities.MUMMY, 95, 4, 4));
	}
}
