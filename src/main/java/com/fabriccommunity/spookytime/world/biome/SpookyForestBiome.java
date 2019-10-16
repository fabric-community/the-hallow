package com.fabriccommunity.spookytime.world.biome;

import com.fabriccommunity.spookytime.registry.SpookyEntities;
import com.fabriccommunity.spookytime.registry.SpookyFeatures;
import com.fabriccommunity.spookytime.world.feature.SpookyBiomeFeatures;
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
public class SpookyForestBiome extends SpookyBaseBiome {
	public SpookyForestBiome(float depth, float scale) {
		super(new Settings().surfaceBuilder(SURFACE_BUILDER).precipitation(Precipitation.NONE).category(Category.FOREST).depth(depth).scale(scale).temperature(0.7f).downfall(0.8f).waterColor(0x5900A3).waterFogColor(0x5900A3));
		
		GRASS_COLOR = 0x6B6B6B;
		FOLIAGE_COLOR = 0x6B6B6B;
		
		this.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL));
		
		SpookyBiomeFeatures.addGrass(this);
		SpookyBiomeFeatures.addLakes(this);
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(SpookyFeatures.PUMPKIN, FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP_DOUBLE, new ChanceDecoratorConfig(32)));
		SpookyBiomeFeatures.addDefaultSpookyTrees(this);
		SpookyBiomeFeatures.addSpookyForestTrees(this);
		SpookyBiomeFeatures.addWells(this);
		SpookyBiomeFeatures.addLairs(this);
		
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(SpookyEntities.MUMMY, 95, 4, 4));
	}
}
