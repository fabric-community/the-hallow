package com.fabriccommunity.thehallow.world.biome;

import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import com.fabriccommunity.thehallow.api.HallowedBiomeInfo;
import com.fabriccommunity.thehallow.registry.HallowedEntities;
import com.fabriccommunity.thehallow.registry.HallowedFeatures;
import com.fabriccommunity.thehallow.world.feature.HallowedBiomeFeatures;

import com.google.common.collect.Lists;
import java.util.List;

public abstract class HallowedBaseBiome extends Biome implements HallowedBiomeInfo {
	public static final List<Biome> BIOMES = Lists.newArrayList();
	protected static final ConfiguredSurfaceBuilder<?> SURFACE_BUILDER = new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, HallowedFeatures.HALLOWED_FOREST);
	protected static final ConfiguredSurfaceBuilder<?> MARSH_SURFACE_BUILDER = new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, HallowedFeatures.HALLOWED_MARSH);
	protected static final ConfiguredSurfaceBuilder<?> DESERT_SURFACE_BUILDER = new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, HallowedFeatures.GHASTLY_DESERT);
	protected int GRASS_COLOR = 0xFFFFFF;
	protected int FOLIAGE_COLOR = 0xFFFFFF;
	
	protected HallowedBaseBiome(Settings settings) {
		super(settings.parent(null));
		
		DefaultBiomeFeatures.addLandCarvers(this);
		HallowedBiomeFeatures.addDecoration(this);
		HallowedBiomeFeatures.addDisks(this);
		HallowedBiomeFeatures.addOres(this);
		HallowedBiomeFeatures.addMineables(this);
		this.addSpawn(EntityCategory.CREATURE, new SpawnEntry(HallowedEntities.CROW, 40, 1, 2));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.WITCH, 5, 1, 1));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(HallowedEntities.MUMMY, 95, 4, 4));
		BIOMES.add(this);
	}
	
	@Override
	public int getSkyColor() {
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
	public int getGrassColorAt(double x, double z) {
		return GRASS_COLOR;
	}
	
	@Override
	public int getFoliageColorAt() {
		return FOLIAGE_COLOR;
	}
}
