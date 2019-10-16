package com.fabriccommunity.spookytime.world.biome;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.fabriccommunity.spookytime.world.feature.SpookyBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

// TODO
public class SpookyShoreBiome extends SpookyBaseBiome {
	private static final TernarySurfaceConfig TAINTED_GRAVEL_CONFIG = new TernarySurfaceConfig(
		SpookyBlocks.TAINTED_GRAVEL.getDefaultState(),
		SpookyBlocks.TAINTED_GRAVEL.getDefaultState(),
		SpookyBlocks.TAINTED_GRAVEL.getDefaultState()
	);
	
	public SpookyShoreBiome() {
		super(new Settings().surfaceBuilder(new ConfiguredSurfaceBuilder<TernarySurfaceConfig>(SurfaceBuilder.DEFAULT, TAINTED_GRAVEL_CONFIG)).precipitation(Precipitation.NONE).category(Category.OCEAN).depth(0.02f).scale(0.025f).temperature(0.5f).downfall(0.8f).waterColor(0x5900A3).waterFogColor(0x5900A3));
		
		this.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL));
		
		SpookyBiomeFeatures.addGrass(this);
		SpookyBiomeFeatures.addLakes(this);
		SpookyBiomeFeatures.addDefaultSpookyTrees(this);
	}
}
