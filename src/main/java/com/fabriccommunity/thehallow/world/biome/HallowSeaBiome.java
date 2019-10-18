package com.fabriccommunity.thehallow.world.biome;

import com.fabriccommunity.thehallow.registry.HallowEntities;
import com.fabriccommunity.thehallow.world.feature.HallowBiomeFeatures;
import net.minecraft.entity.EntityCategory;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;

// TODO
public class HallowSeaBiome extends HallowBaseBiome {
	public HallowSeaBiome() {
		super(new Settings().surfaceBuilder(SURFACE_BUILDER).precipitation(Precipitation.NONE).category(Category.OCEAN).depth(-1.2f).scale(0.1f).temperature(0.5f).downfall(0.8f).waterColor(0x5900A3).waterFogColor(0x5900A3));
		
		this.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL));
		
		HallowBiomeFeatures.addGrass(this);
		HallowBiomeFeatures.addLakes(this);
		HallowBiomeFeatures.addDefaultHallowTrees(this);
		
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(HallowEntities.MUMMY, 95, 4, 4));
	}
}
