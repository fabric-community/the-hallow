package com.fabriccommunity.thehallow.world.biome;

import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import com.fabriccommunity.thehallow.api.HallowBiomeInfo;
import com.fabriccommunity.thehallow.registry.HallowEntities;
import com.fabriccommunity.thehallow.registry.HallowFeatures;
import com.fabriccommunity.thehallow.world.feature.HallowBiomeFeatures;

import com.google.common.collect.Lists;
import java.util.List;

public abstract class HallowBaseBiome extends Biome implements HallowBiomeInfo {
	public static final List<Biome> BIOMES = Lists.newArrayList();
	protected static final ConfiguredSurfaceBuilder<?> SURFACE_BUILDER = new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, HallowFeatures.HALLOWED_FOREST);
	protected static final ConfiguredSurfaceBuilder<?> MARSH_SURFACE_BUILDER = new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, HallowFeatures.HALLOWED_MARSH);
	protected static final ConfiguredSurfaceBuilder<?> DESERT_SURFACE_BUILDER = new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, HallowFeatures.GHASTLY_DESERT);
	protected int GRASS_COLOR = 0xFFFFFF;
	protected int FOLIAGE_COLOR = 0xFFFFFF;
	
	protected HallowBaseBiome(Settings settings) {
		super(settings.parent(null));
		
		DefaultBiomeFeatures.addLandCarvers(this);
		HallowBiomeFeatures.addDecoration(this);
		HallowBiomeFeatures.addDisks(this);
		HallowBiomeFeatures.addOres(this);
		HallowBiomeFeatures.addMineables(this);
		this.addSpawn(EntityCategory.CREATURE, new SpawnEntry(HallowEntities.CROW, 40, 1, 2));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.WITCH, 5, 1, 1));
		BIOMES.add(this);
	}
	
	@Override
	public int getSkyColor(float temperature) {
		return 0x360063;
	}
	
	@Override
	public Vec3d getFogColor() {
		return new Vec3d(104F / 255F, 84F / 255F, 117F / 255F);
	}
	
	@Override
	public float getFogIntensity() {
		return 192;
	}
	
	@Override
	public boolean shouldFogRender() {
		return true;
	}
	
	@Override
	public int getGrassColorAt(BlockPos blockPos_1) {
		return GRASS_COLOR;
	}
	
	@Override
	public int getFoliageColorAt(BlockPos blockPos_1) {
		return FOLIAGE_COLOR;
	}
}
