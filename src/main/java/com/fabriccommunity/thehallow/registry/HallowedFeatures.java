package com.fabriccommunity.thehallow.registry;

import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.world.feature.BarrowFeature;
import com.fabriccommunity.thehallow.world.feature.DeaderBushFeature;
import com.fabriccommunity.thehallow.world.feature.DeceasedWildCropFeature;
import com.fabriccommunity.thehallow.world.feature.LargeDeadwoodTreeFeature;
import com.fabriccommunity.thehallow.world.feature.LargeSkeletalTreeFeature;
import com.fabriccommunity.thehallow.world.feature.SmallDeadwoodTreeFeature;
import com.fabriccommunity.thehallow.world.feature.SmallSkeletalTreeFeature;
import com.fabriccommunity.thehallow.world.feature.SpiderLairFeature;
import com.fabriccommunity.thehallow.world.feature.HallowedCactusFeature;
import com.fabriccommunity.thehallow.world.feature.HallowedOreFeature;
import com.fabriccommunity.thehallow.world.feature.HallowedOreFeatureConfig;
import com.fabriccommunity.thehallow.world.feature.StoneCircleFeature;
import com.fabriccommunity.thehallow.world.feature.WitchWellFeature;

/**
 * @author Indigo Amann
 */
public class HallowedFeatures {
	public static final TernarySurfaceConfig HALLOWED_FOREST = new TernarySurfaceConfig(HallowedBlocks.DECEASED_GRASS_BLOCK.getDefaultState(), HallowedBlocks.DECEASED_DIRT.getDefaultState(), HallowedBlocks.TAINTED_GRAVEL.getDefaultState());
	public static final TernarySurfaceConfig HALLOWED_MARSH = new TernarySurfaceConfig(HallowedBlocks.DECEASED_MOSS.getDefaultState(), HallowedBlocks.DECEASED_DIRT.getDefaultState(), HallowedBlocks.TAINTED_GRAVEL.getDefaultState());
	public static final TernarySurfaceConfig GHASTLY_DESERT = new TernarySurfaceConfig(HallowedBlocks.TAINTED_SAND.getDefaultState(), HallowedBlocks.TAINTED_SAND.getDefaultState(), HallowedBlocks.TAINTED_GRAVEL.getDefaultState());
	public static final Feature<HallowedOreFeatureConfig> ORE = register("ore", new HallowedOreFeature(HallowedOreFeatureConfig::deserialize));
	public static final Feature<DefaultFeatureConfig> PUMPKIN = register("pumpkin", new DeceasedWildCropFeature(DefaultFeatureConfig::deserialize, Blocks.PUMPKIN.getDefaultState()));
	public static final Feature<DefaultFeatureConfig> RESTLESS_CACTUS = register("restless_cactus", new HallowedCactusFeature(DefaultFeatureConfig::deserialize));
	public static final Feature<DefaultFeatureConfig> DEADER_BUSH = register("deader_bush", new DeaderBushFeature(DefaultFeatureConfig::deserialize, HallowedBlocks.DEADER_BUSH.getDefaultState()));
	public static final Feature<DefaultFeatureConfig> BRAMBLES = register("brambles", new DeaderBushFeature(DefaultFeatureConfig::deserialize, HallowedBlocks.BRAMBLES.getDefaultState()));
	public static final Feature<DefaultFeatureConfig> SMALL_SKELETON_TREE = register("skeleton_tree_small", new SmallSkeletalTreeFeature(DefaultFeatureConfig::deserialize, false));
	public static final Feature<DefaultFeatureConfig> LARGE_SKELETON_TREE = register("skeleton_tree_large", new LargeSkeletalTreeFeature(DefaultFeatureConfig::deserialize, false));
	public static final Feature<DefaultFeatureConfig> SPIDER_LAIR = register("spider_lair", new SpiderLairFeature(DefaultFeatureConfig::deserialize));
	public static final Feature<DefaultFeatureConfig> WITCH_WELL = register("witch_well", new WitchWellFeature(DefaultFeatureConfig::deserialize));
	public static final Feature<DefaultFeatureConfig> BARROW = register("barrow", new BarrowFeature());
	public static final Feature<DefaultFeatureConfig> STONE_CIRCLE = register("stone_circle", new StoneCircleFeature());
	public static final Feature<DefaultFeatureConfig> SMALL_DEADWOOD_TREE = register("small_deadwood_tree", new SmallDeadwoodTreeFeature(DefaultFeatureConfig::deserialize, false));
	public static final Feature<DefaultFeatureConfig> LARGE_DEADWOOD_TREE = register("large_deadwood_tree", new LargeDeadwoodTreeFeature(DefaultFeatureConfig::deserialize, false));
	
	private HallowedFeatures() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	public static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
		return Registry.register(Registry.FEATURE, TheHallow.id(name), feature);
	}
	
	public static <C extends SurfaceConfig, F extends SurfaceBuilder<C>> F register(String name, F surfaceBuilder) {
		return Registry.register(Registry.SURFACE_BUILDER, TheHallow.id(name), surfaceBuilder);
	}
}
