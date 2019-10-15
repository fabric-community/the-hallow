package com.fabriccommunity.spookytime.world.biome;

import com.fabriccommunity.spookytime.world.feature.SpookyBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;

// TODO
public class SpookySeaBiome extends SpookyBaseBiome {
	public SpookySeaBiome() {
		super(new Settings().surfaceBuilder(SURFACE_BUILDER).precipitation(Precipitation.NONE).category(Category.OCEAN).depth(-1.2f).scale(0.1f).temperature(0.5f).downfall(0.8f).waterColor(0x5900A3).waterFogColor(0x5900A3));
		
		this.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL));
		
		SpookyBiomeFeatures.addGrass(this);
		SpookyBiomeFeatures.addLakes(this);
		SpookyBiomeFeatures.addDefaultSpookyTrees(this);
	}
}
