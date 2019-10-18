package com.fabriccommunity.thehallow.world.biome;

import net.minecraft.entity.EntityCategory;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;

import com.fabriccommunity.thehallow.registry.HallowEntities;
import com.fabriccommunity.thehallow.registry.HallowFeatures;
import com.fabriccommunity.thehallow.world.feature.HallowBiomeFeatures;

// TODO
public class PumpkinPatchBiome extends HallowBaseBiome {
	public PumpkinPatchBiome() {
		super(new Settings().surfaceBuilder(SURFACE_BUILDER).precipitation(Precipitation.NONE).category(Category.PLAINS).depth(0.125f).scale(0.07f).temperature(0.7f).downfall(0.8f).waterColor(0x5900A3).waterFogColor(0x5900A3));
		
		GRASS_COLOR = 0xC9C92A;
		FOLIAGE_COLOR = 0xC9C92A;
		
		this.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL));
		
		HallowBiomeFeatures.addGrass(this);
		HallowBiomeFeatures.addLakes(this);
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(HallowFeatures.PUMPKIN, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(10)));
		HallowBiomeFeatures.addDefaultHallowTrees(this);
		
		this.addSpawn(EntityCategory.CREATURE, new SpawnEntry(HallowEntities.PUMPCOWN, 8, 4, 8));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(HallowEntities.MUMMY, 95, 4, 4));
	}
}
