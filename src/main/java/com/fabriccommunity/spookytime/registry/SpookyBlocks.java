package com.fabriccommunity.spookytime.registry;

import net.fabricmc.fabric.api.block.FabricBlockSettings;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.block.BloodBlock;
import com.fabriccommunity.spookytime.block.BreadCrumbsBlock;
import com.fabriccommunity.spookytime.block.DeaderBushBlock;
import com.fabriccommunity.spookytime.block.DeceasedGrassBlock;
import com.fabriccommunity.spookytime.block.InfusionAltarBlock;
import com.fabriccommunity.spookytime.block.InfusionPillarBlock;
import com.fabriccommunity.spookytime.block.SpookyCactusBlock;
import com.fabriccommunity.spookytime.block.SpookyStairsBlock;
import com.fabriccommunity.spookytime.block.SpookyTreasureChestBlock;
import com.fabriccommunity.spookytime.block.TinyPumpkinBlock;
import com.fabriccommunity.spookytime.block.TranslucentGlassBlock;
import com.fabriccommunity.spookytime.block.TranslucentGlassPaneBlock;
import com.fabriccommunity.spookytime.block.WitchWaterBlock;
import com.fabriccommunity.spookytime.item.WitchedPumpkinItem;

import java.util.function.Function;

public class SpookyBlocks {
	public static final Block TINY_PUMPKIN = register("tiny_pumpkin", new TinyPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).strength(1.0F, 1.0F).sounds(BlockSoundGroup.LANTERN).build()), new Item.Settings().group(SpookyTime.GROUP).food(new FoodComponent.Builder().hunger(2).saturationModifier(0.4f).build()));
	public static final Block WITCHED_PUMPKIN = register("witched_pumpkin", new TinyPumpkinBlock(Block.Settings.copy(TINY_PUMPKIN)), block -> new WitchedPumpkinItem(block, new Item.Settings().group(SpookyTime.GROUP).food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 200), 1).hunger(1).saturationModifier(0.1f).alwaysEdible().build())));
	public static final Block DECEASED_DIRT = register("deceased_dirt", new Block(FabricBlockSettings.copy(Blocks.DIRT).materialColor(MaterialColor.PURPLE).build()));
	public static final Block DECEASED_GRASS_BLOCK = register("deceased_grass_block", new DeceasedGrassBlock(FabricBlockSettings.copy(Blocks.GRASS_BLOCK).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_STONE = register("tainted_stone", new Block(FabricBlockSettings.copy(Blocks.STONE).build()));
	public static final Block TAINTED_COBBLESTONE = register("tainted_cobblestone", new Block(FabricBlockSettings.copy(Blocks.COBBLESTONE).build()));
	public static final Block TAINTED_SAND = register("tainted_sand", new FallingBlock(FabricBlockSettings.copy(Blocks.SAND).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_GRAVEL = register("tainted_gravel", new FallingBlock(FabricBlockSettings.copy(Blocks.GRAVEL).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_GLASS = register("tainted_glass", new TranslucentGlassBlock(FabricBlockSettings.copy(Blocks.GLASS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_GLASS_PANE = register("tainted_glass_pane", new TranslucentGlassPaneBlock(FabricBlockSettings.copy(Blocks.GLASS_PANE).build()));
	public static final Block TAINTED_SANDSTONE = register("tainted_sandstone", new Block(FabricBlockSettings.copy(Blocks.SANDSTONE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block SMOOTH_TAINTED_SANDSTONE = register("smooth_tainted_sandstone", new Block(FabricBlockSettings.copy(Blocks.SMOOTH_SANDSTONE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block CUT_TAINTED_SANDSTONE = register("cut_tainted_sandstone", new Block(FabricBlockSettings.copy(Blocks.CUT_SANDSTONE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block CHISELED_TAINTED_SANDSTONE = register("chiseled_tainted_sandstone", new Block(FabricBlockSettings.copy(Blocks.CHISELED_SANDSTONE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_SANDSTONE_STAIRS = register("tainted_sandstone_stairs", new SpookyStairsBlock(TAINTED_SANDSTONE, FabricBlockSettings.copy(Blocks.SANDSTONE_STAIRS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block SMOOTH_TAINTED_SANDSTONE_STAIRS = register("smooth_tainted_sandstone_stairs", new SpookyStairsBlock(SMOOTH_TAINTED_SANDSTONE, FabricBlockSettings.copy(Blocks.SMOOTH_SANDSTONE_STAIRS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_SANDSTONE_SLAB = register("tainted_sandstone_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.SANDSTONE_SLAB).materialColor(MaterialColor.PURPLE).build()));
	public static final Block SMOOTH_TAINTED_SANDSTONE_SLAB = register("smooth_tainted_sandstone_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.SMOOTH_SANDSTONE_SLAB).materialColor(MaterialColor.PURPLE).build()));
	public static final Block CUT_TAINTED_SANDSTONE_SLAB = register("cut_tainted_sandstone_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.CUT_SANDSTONE_SLAB).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_SANDSTONE_WALL = register("tainted_sandstone_wall", new WallBlock(FabricBlockSettings.copy(Blocks.SANDSTONE_WALL).materialColor(MaterialColor.PURPLE).build()));
	public static final Block SOUL_GLASS = register("soul_glass", new TranslucentGlassBlock(FabricBlockSettings.copy(Blocks.GLASS).materialColor(MaterialColor.BROWN).build()));
	public static final Block SOUL_GLASS_PANE = register("soul_glass_pane", new TranslucentGlassPaneBlock(FabricBlockSettings.copy(Blocks.GLASS_PANE).build()));
	public static final Block SPOOKIUM_ORE = register("spookium_ore", new OreBlock(FabricBlockSettings.copy(Blocks.IRON_ORE).build()), new Item.Settings().group(SpookyTime.GROUP).rarity(Rarity.EPIC));
	public static final Block SPOOKIUM_BLOCK = register("spookium_block", new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).materialColor(MaterialColor.RED).build()), new Item.Settings().group(SpookyTime.GROUP).rarity(Rarity.EPIC));
	public static final Block WITCH_WATER_BLOCK = register("witch_water", new WitchWaterBlock(SpookyFluids.WITCH_WATER, FabricBlockSettings.copy(Blocks.WATER).build()), (BlockItem) null);
	public static final Block SPOOKY_TREASURE_CHEST = register("spooky_treasure_chest", new SpookyTreasureChestBlock(FabricBlockSettings.of(Material.METAL).build()), new Item.Settings().group(SpookyTime.GROUP));
	public static final Block BLOOD_BLOCK = register("blood", new BloodBlock(SpookyFluids.BLOOD, FabricBlockSettings.copy(Blocks.WATER).build()), (BlockItem) null);
	public static final Block SPOOKY_CACTUS = register("spooky_cactus", new SpookyCactusBlock(FabricBlockSettings.copy(Blocks.CACTUS).materialColor(MaterialColor.BROWN).build()), new Item.Settings().group(SpookyTime.GROUP));
	public static final Block DEADER_BUSH = register("deader_bush", new DeaderBushBlock(FabricBlockSettings.copy(Blocks.DEAD_BUSH).materialColor(MaterialColor.BROWN).build()), new Item.Settings().group(SpookyTime.GROUP));
	public static final Block BREAD_CRUMBS = register("bread_crumbs", new BreadCrumbsBlock(FabricBlockSettings.of(Material.CAKE).breakByHand(true).collidable(false).noCollision().build()), new Item.Settings().group(SpookyTime.GROUP).food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).snack().build()));
	public static final Block INFUSION_PILLAR_BLOCK = register("infusion_pillar", new InfusionPillarBlock(FabricBlockSettings.copy(Blocks.COBBLESTONE_WALL).build()));
	public static final Block INFUSION_ALTAR_BLOCK = register("infusion_altar", new InfusionAltarBlock(FabricBlockSettings.copy(Blocks.COBBLESTONE_WALL).build()));
	public static final Block MOIST_FLESH_BLOCK = register("moist_flesh_block", new Block(FabricBlockSettings.copy(Blocks.NETHER_WART_BLOCK).materialColor(MaterialColor.PINK).sounds(BlockSoundGroup.SLIME).build()));
	
	private SpookyBlocks() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
	
	static <T extends Block> T register(String name, T block, Item.Settings settings) {
		return register(name, block, new BlockItem(block, settings));
	}
	
	static <T extends Block> T register(String name, T block) {
		return register(name, block, new Item.Settings().group(SpookyTime.GROUP));
	}
	
	static <T extends Block> T register(String name, T block, Function<T, BlockItem> itemFactory) {
		return register(name, block, itemFactory.apply(block));
	}
	
	static <T extends Block> T register(String name, T block, BlockItem item) {
		T b = Registry.register(Registry.BLOCK, SpookyTime.id(name), block);
		if (item != null) {
			BlockItem bi = SpookyItems.register(name, item);
			bi.appendBlocks(BlockItem.BLOCK_ITEMS, bi);
		}
		return b;
	}
}
