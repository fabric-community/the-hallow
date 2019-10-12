package com.fabriccommunity.spookytime.world.biome;

import net.minecraft.entity.EntityCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import com.fabriccommunity.spookytime.api.SpookyBiomeInfo;
import com.fabriccommunity.spookytime.registry.SpookyEntities;
import com.fabriccommunity.spookytime.registry.SpookyFeatures;
import com.fabriccommunity.spookytime.world.feature.SpookyBiomeFeatures;

import com.google.common.collect.Lists;
import java.util.List;

public abstract class SpookyBaseBiome extends Biome implements SpookyBiomeInfo {
	public static final List<Biome> BIOMES = Lists.newArrayList();
	
	protected static final ConfiguredSurfaceBuilder<?> SURFACE_BUILDER = new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, SpookyFeatures.SPOOKY_FOREST);
	protected static final ConfiguredSurfaceBuilder<?> DESERT_SURFACE_BUILDER = new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, SpookyFeatures.GHASTLY_DESERT);
	
	protected SpookyBaseBiome(Settings settings) {
		super(settings.parent(null));
		
		DefaultBiomeFeatures.addLandCarvers(this);
		SpookyBiomeFeatures.addDecoration(this);
		SpookyBiomeFeatures.addDisks(this);
		SpookyBiomeFeatures.addOres(this);
		SpookyBiomeFeatures.addMineables(this);
		this.addSpawn(EntityCategory.CREATURE, new SpawnEntry(SpookyEntities.CROW, 40, 1, 2));
		this.addSpawn(EntityCategory.CREATURE, new SpawnEntry(SpookyEntities.PUMPCOWN, 8, 4, 8));
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
}
