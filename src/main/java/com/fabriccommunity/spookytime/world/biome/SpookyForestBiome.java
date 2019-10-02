package com.fabriccommunity.spookytime.world.biome;

import com.fabriccommunity.spookytime.common.SpookyBlocks;
import com.fabriccommunity.spookytime.common.SpookyEntities;
import com.fabriccommunity.spookytime.common.SpookyWorldGen;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class SpookyForestBiome extends Biome {
	
	static final ConfiguredSurfaceBuilder SURFACE_BUILDER = new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, SpookyBiomeFeatures.SPOOKY_FOREST);
	static final Precipitation PRECIPITATION = Precipitation.NONE;
	static final Category CATEGORY = Category.FOREST;
	static final float DEPTH = 0.1F;
	static final float SCALE = 0.2F;
	static final float TEMPERATURE = 0.7F;
	static final float DOWNFALL = 0.8F;
	static final int WATER_COLOR = 0xBB0A1E;
	static final int WATER_FOG_COLOR = 0xBB0A1E;
	static final int GRASS_COLOR = 0x20003B;
	static final int FOLIAGE_COLOR = 0x20003B;
	static final String PARENT = null;
	
	public SpookyForestBiome() {
		super(new Settings().surfaceBuilder(SURFACE_BUILDER).precipitation(PRECIPITATION).category(CATEGORY).depth(DEPTH).scale(SCALE).temperature(TEMPERATURE).downfall(DOWNFALL).waterColor(WATER_COLOR).waterFogColor(WATER_FOG_COLOR).parent(PARENT));
		this.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL));
		this.addStructureFeature(Feature.STRONGHOLD, FeatureConfig.DEFAULT);
		DefaultBiomeFeatures.addLandCarvers(this);
		DefaultBiomeFeatures.addDefaultStructures(this);
		DefaultBiomeFeatures.addDungeons(this);
		SpookyBiomeFeatures.addDisks(this);
		DefaultBiomeFeatures.addDefaultOres(this);
		DefaultBiomeFeatures.addDefaultGrass(this);
		DefaultBiomeFeatures.addDefaultLakes(this);
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(net.minecraft.world.gen.feature.Feature.PUMPKIN, FeatureConfig.DEFAULT, Decorator.CHANCE_HEIGHTMAP_DOUBLE, new ChanceDecoratorConfig(32)));
		this.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, SpookyBlocks.SPOOKIUM_ORE.getDefaultState(), 5), Decorator.COUNT_RANGE, new RangeDecoratorConfig(1, 0, 0, 16)));
		SpookyBiomeFeatures.addSpookyForestTrees(this);
		this.addSpawn(EntityCategory.CREATURE, new SpawnEntry(SpookyEntities.PUMPCOWN, 8, 4, 8));
		this.addSpawn(EntityCategory.AMBIENT, new SpawnEntry(EntityType.BAT, 10, 8, 8));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.SPIDER, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.SKELETON, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.CREEPER, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.SLIME, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.WITCH, 5, 1, 1));
	}
	
	@Override
	public int getGrassColorAt(BlockPos blockPos) {
		return GRASS_COLOR;
	}
	
	@Override
	public int getFoliageColorAt(BlockPos blockPos) {
		return FOLIAGE_COLOR;
	}
}