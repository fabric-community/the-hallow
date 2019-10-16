package com.fabriccommunity.spookytime.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;

import com.fabriccommunity.spookytime.registry.SpookyEntities;
import com.fabriccommunity.spookytime.registry.SpookyFeatures;
import com.fabriccommunity.spookytime.world.feature.SpookyBiomeFeatures;

// TODO
public class HauntedUplandsBiome extends SpookyBaseBiome {
	public HauntedUplandsBiome() {
		super(new Settings().surfaceBuilder(SURFACE_BUILDER).precipitation(Precipitation.NONE).category(Category.PLAINS).depth(1.75f).scale(0.03f).temperature(0.5f).downfall(0.4f).waterColor(0xBB0A1E).waterFogColor(0xBB0A1E));
		
		this.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL));
		
		SpookyBiomeFeatures.addGrass(this);
		SpookyBiomeFeatures.addLakes(this);
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(SpookyFeatures.PUMPKIN, FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP_DOUBLE, new ChanceDecoratorConfig(32)));
		SpookyBiomeFeatures.addDefaultUplandsGeneration(this);
		SpookyBiomeFeatures.addWells(this);
		SpookyBiomeFeatures.addLairs(this);
		
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(SpookyEntities.MUMMY, 95, 4, 4));
	}
}
