package com.fabriccommunity.spookytime.world.biome;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.fabriccommunity.spookytime.world.feature.SpookyBiomeFeatures;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

// TODO
public class SpookyShoreBiome extends SpookyBaseBiome {
	protected static final int GRASS_COLOR = 0x20003B;
	protected static final int FOLIAGE_COLOR = 0x20003B;
	
	private static final TernarySurfaceConfig TAINTED_GRAVEL_CONFIG = new TernarySurfaceConfig(
		SpookyBlocks.TAINTED_GRAVEL.getDefaultState(),
		SpookyBlocks.TAINTED_GRAVEL.getDefaultState(),
		SpookyBlocks.TAINTED_GRAVEL.getDefaultState()
	);
	
	public SpookyShoreBiome() {
		super(new Settings().surfaceBuilder(new ConfiguredSurfaceBuilder<TernarySurfaceConfig>(SurfaceBuilder.DEFAULT, TAINTED_GRAVEL_CONFIG)).precipitation(Precipitation.NONE).category(Category.OCEAN).depth(0.02f).scale(0.025f).temperature(0.5f).downfall(0.8f).waterColor(0x5900A3).waterFogColor(0x5900A3));
		
		this.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL));
		
		DefaultBiomeFeatures.addDefaultGrass(this);
		SpookyBiomeFeatures.addLakes(this);
		SpookyBiomeFeatures.addDefaultSpookyTrees(this);
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
