package com.fabriccommunity.thehallow.world.biome;

import com.fabriccommunity.thehallow.registry.HallowBlocks;
import com.fabriccommunity.thehallow.registry.HallowEntities;
import com.fabriccommunity.thehallow.world.feature.HallowBiomeFeatures;
import net.minecraft.entity.EntityCategory;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

// TODO
public class HallowShoreBiome extends HallowBaseBiome {
	private static final TernarySurfaceConfig TAINTED_GRAVEL_CONFIG = new TernarySurfaceConfig(
		HallowBlocks.TAINTED_GRAVEL.getDefaultState(),
		HallowBlocks.TAINTED_GRAVEL.getDefaultState(),
		HallowBlocks.TAINTED_GRAVEL.getDefaultState()
	);
	
	public HallowShoreBiome() {
		super(new Settings().surfaceBuilder(new ConfiguredSurfaceBuilder<TernarySurfaceConfig>(SurfaceBuilder.DEFAULT, TAINTED_GRAVEL_CONFIG)).precipitation(Precipitation.NONE).category(Category.OCEAN).depth(0.02f).scale(0.025f).temperature(0.5f).downfall(0.8f).waterColor(0x5900A3).waterFogColor(0x5900A3));
		
		this.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL));
		
		HallowBiomeFeatures.addGrass(this);
		HallowBiomeFeatures.addLakes(this);
		HallowBiomeFeatures.addDefaultHallowTrees(this);
		
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(HallowEntities.MUMMY, 95, 4, 4));
	}
}
