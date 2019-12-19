package com.fabriccommunity.thehallow.registry;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.world.feature.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleStateProvider;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

/**
 * @author Indigo Amann
 */
public class HallowedFeatures {
	public static final TernarySurfaceConfig HALLOWED_FOREST = new TernarySurfaceConfig(HallowedBlocks.DECEASED_GRASS_BLOCK.getDefaultState(), HallowedBlocks.DECEASED_DIRT.getDefaultState(), HallowedBlocks.TAINTED_GRAVEL.getDefaultState());
	public static final TernarySurfaceConfig HALLOWED_MARSH = new TernarySurfaceConfig(HallowedBlocks.DECEASED_MOSS.getDefaultState(), HallowedBlocks.DECEASED_DIRT.getDefaultState(), HallowedBlocks.TAINTED_GRAVEL.getDefaultState());
	public static final TernarySurfaceConfig GHASTLY_DESERT = new TernarySurfaceConfig(HallowedBlocks.TAINTED_SAND.getDefaultState(), HallowedBlocks.TAINTED_SAND.getDefaultState(), HallowedBlocks.TAINTED_GRAVEL.getDefaultState());
	public static final Feature<HallowedOreFeatureConfig> ORE = register("ore", new HallowedOreFeature(HallowedOreFeatureConfig::deserialize));
	public static final Feature<DefaultFeatureConfig> COLORED_PUMPKIN = register("colored_pumpkin", new RandomizedWildCropFeature(DefaultFeatureConfig::deserialize, ColoredPumpkinFeature.COLORED_PUMPKINS));
	public static final Feature<DefaultFeatureConfig> RESTLESS_CACTUS = register("restless_cactus", new HallowedCactusFeature(DefaultFeatureConfig::deserialize));
	public static final Feature<DefaultFeatureConfig> DEADER_BUSH = register("deader_bush", new DeaderBushFeature(DefaultFeatureConfig::deserialize, HallowedBlocks.DEADER_BUSH.getDefaultState()));
	public static final Feature<DefaultFeatureConfig> BRAMBLES = register("brambles", new DeaderBushFeature(DefaultFeatureConfig::deserialize, HallowedBlocks.BRAMBLES.getDefaultState()));
	public static final Feature<TreeFeatureConfig> SMALL_SKELETON_TREE = register("skeleton_tree_small", new SmallSkeletalTreeFeature(TreeFeatureConfig::deserialize));
	public static final Feature<TreeFeatureConfig> LARGE_SKELETON_TREE = register("skeleton_tree_large", new LargeSkeletalTreeFeature(TreeFeatureConfig::deserialize));
	public static final Feature<DefaultFeatureConfig> SPIDER_LAIR = register("spider_lair", new SpiderLairFeature(DefaultFeatureConfig::deserialize));
	public static final Feature<DefaultFeatureConfig> WITCH_WELL = register("witch_well", new WitchWellFeature(DefaultFeatureConfig::deserialize));
	public static final Feature<DefaultFeatureConfig> BARROW = register("barrow", new BarrowFeature());
	public static final Feature<DefaultFeatureConfig> STONE_CIRCLE = register("stone_circle", new StoneCircleFeature());
	public static final Feature<BranchedTreeFeatureConfig> SMALL_DEADWOOD_TREE = register("small_deadwood_tree", new SmallDeadwoodTreeFeature(BranchedTreeFeatureConfig::deserialize2));
	public static final Feature<MegaTreeFeatureConfig> LARGE_DEADWOOD_TREE = register("large_deadwood_tree", new LargeDeadwoodTreeFeature(MegaTreeFeatureConfig::method_23408));

	public static final BranchedTreeFeatureConfig SMALL_DEADWOOD_TREE_CONFIG = (new BranchedTreeFeatureConfig.Builder(new SimpleStateProvider(HallowedBlocks.DEADWOOD_LOG.getDefaultState()), new SimpleStateProvider(HallowedBlocks.DEADWOOD_LEAVES.getDefaultState()), new BlobFoliagePlacer(2, 0))).baseHeight(4).heightRandA(2).foliageHeight(3).noVines().build();
	public static final MegaTreeFeatureConfig LARGE_DEADWOOD_TREE_CONFIG = (new MegaTreeFeatureConfig.Builder(new SimpleStateProvider(HallowedBlocks.DEADWOOD_LOG.getDefaultState()), new SimpleStateProvider(HallowedBlocks.DEADWOOD_LEAVES.getDefaultState()))).baseHeight(6).build();

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
