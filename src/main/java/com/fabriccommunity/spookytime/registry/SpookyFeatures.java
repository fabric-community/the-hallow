package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.world.feature.*;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
/**
 * @author Indigo Amann
 */
public class SpookyFeatures {

	private ImmutableList<Block> SELECTABLE_PUMPKIN_COLORS = SpookyBlocks.CARVED_PUMPKIN_COLORS.values().asList();

	public static final TernarySurfaceConfig SPOOKY_FOREST = new TernarySurfaceConfig(SpookyBlocks.DECEASED_GRASS_BLOCK.getDefaultState(), SpookyBlocks.DECEASED_DIRT.getDefaultState(), SpookyBlocks.TAINTED_GRAVEL.getDefaultState());
	public static final TernarySurfaceConfig GHASTLY_DESERT = new TernarySurfaceConfig(SpookyBlocks.TAINTED_SAND.getDefaultState(), SpookyBlocks.TAINTED_SAND.getDefaultState(), SpookyBlocks.TAINTED_GRAVEL.getDefaultState());
	public static final Feature<SpookyOreFeatureConfig> ORE = register("ore", new SpookyOreFeature(SpookyOreFeatureConfig::deserialize));
	public static final Feature<DefaultFeatureConfig> PUMPKIN = register("new_pumpkin", new RandomizedWildCropFeature(DefaultFeatureConfig::deserialize, ColoredPumpkinFeature.COLORED_PUMPKINS));
	public static final Feature<DefaultFeatureConfig> SPOOKY_CACTUS = register("spooky_cactus", new SpookyCactusFeature(DefaultFeatureConfig::deserialize));
	public static final Feature<DefaultFeatureConfig> DEADER_BUSH = register("deader_bush", new DeaderBushFeature(DefaultFeatureConfig::deserialize));
	public static final Feature<DefaultFeatureConfig> SMALL_SKELETON_TREE = register("skeleton_tree_small", new SmallSkeletalTreeFeature(DefaultFeatureConfig::deserialize, false));
	public static final Feature<DefaultFeatureConfig> LARGE_SKELETON_TREE = register("skeleton_tree_large", new LargeSkeletalTreeFeature(DefaultFeatureConfig::deserialize, false));
	public static final Feature<DefaultFeatureConfig> SPIDER_LAIR = register("spider_lair", new SpiderLairFeature(DefaultFeatureConfig::deserialize));
	public static final Feature<DefaultFeatureConfig> WITCH_WELL = register("witch_well", new WitchWellFeature(DefaultFeatureConfig::deserialize));
	public static final Feature<DefaultFeatureConfig> BARROW = register("barrow", new BarrowFeature());
	public static final Feature<DefaultFeatureConfig> STONE_CIRCLE = register("stone_circle", new StoneCircleFeature());
	
	private SpookyFeatures() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	public static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
		return Registry.register(Registry.FEATURE, SpookyTime.id(name), feature);
	}
	
	private static <C extends SurfaceConfig, F extends SurfaceBuilder<C>> F register(String name, F surfaceBuilder) {
		return Registry.register(Registry.SURFACE_BUILDER, SpookyTime.id(name), surfaceBuilder);
	}
}
