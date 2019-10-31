package com.fabriccommunity.thehallow.registry;

import com.fabriccommunity.thehallow.block.*;
import net.fabricmc.fabric.api.block.FabricBlockSettings;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.OreBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.VineBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.item.WitchedPumpkinItem;
import com.fabriccommunity.thehallow.world.DeadwoodSaplingGenerator;

import java.util.function.Function;

public class HallowedBlocks {
	public static final Block TINY_PUMPKIN = register("tiny_pumpkin", new TinyPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).strength(1.0F, 1.0F).sounds(BlockSoundGroup.LANTERN).build()), new Item.Settings().group(TheHallow.GROUP).food(new FoodComponent.Builder().hunger(2).saturationModifier(0.4f).build()));
	public static final Block WITCHED_PUMPKIN = register("witched_pumpkin", new TinyPumpkinBlock(Block.Settings.copy(TINY_PUMPKIN)), block -> new WitchedPumpkinItem(block, new Item.Settings().group(TheHallow.GROUP).food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 200), 1).hunger(1).saturationModifier(0.1f).alwaysEdible().build())));
	public static final Block DECEASED_DIRT = register("deceased_dirt", new Block(FabricBlockSettings.copy(Blocks.DIRT).materialColor(MaterialColor.PURPLE).build()));
	public static final Block DECEASED_GRASS_BLOCK = register("deceased_grass_block", new DeceasedGrassBlock(FabricBlockSettings.copy(Blocks.GRASS_BLOCK).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_STONE = register("tainted_stone", new Block(FabricBlockSettings.copy(Blocks.STONE).build()));
	public static final Block TAINTED_COBBLESTONE = register("tainted_cobblestone", new Block(FabricBlockSettings.copy(Blocks.COBBLESTONE).build()));
	public static final Block SMOOTH_TAINTED_STONE = register("smooth_tainted_stone", new Block(FabricBlockSettings.copy(Blocks.SMOOTH_STONE).build()));
	public static final Block TAINTED_STONE_BRICKS = register("tainted_stone_bricks", new Block(FabricBlockSettings.copy(Blocks.STONE_BRICKS).build()));
	public static final Block CHISELED_TAINTED_STONE_BRICKS = register("chiseled_tainted_stone_bricks", new Block(FabricBlockSettings.copy(Blocks.CHISELED_STONE_BRICKS).build()));
	public static final Block CRACKED_TAINTED_STONE_BRICKS = register("cracked_tainted_stone_bricks", new Block(FabricBlockSettings.copy(Blocks.CRACKED_STONE_BRICKS).build()));
	public static final Block TAINTED_STONE_STAIRS = register("tainted_stone_stairs", new HallowedStairsBlock(TAINTED_STONE, FabricBlockSettings.copy(Blocks.STONE_STAIRS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_COBBLESTONE_STAIRS = register("tainted_cobblestone_stairs", new HallowedStairsBlock(TAINTED_COBBLESTONE, FabricBlockSettings.copy(Blocks.COBBLESTONE_STAIRS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_STONE_BRICK_STAIRS = register("tainted_stone_brick_stairs", new HallowedStairsBlock(TAINTED_STONE_BRICKS, FabricBlockSettings.copy(Blocks.STONE_BRICK_STAIRS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_STONE_SLAB = register("tainted_stone_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.STONE_SLAB).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_COBBLESTONE_SLAB = register("tainted_cobblestone_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.COBBLESTONE_SLAB).materialColor(MaterialColor.PURPLE).build()));
	public static final Block SMOOTH_TAINTED_STONE_SLAB = register("smooth_tainted_stone_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.SMOOTH_STONE_SLAB).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_STONE_BRICK_SLAB = register("tainted_stone_brick_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.STONE_BRICK_SLAB).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_COBBLESTONE_WALL = register("tainted_cobblestone_wall", new WallBlock(FabricBlockSettings.copy(Blocks.COBBLESTONE_WALL).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_STONE_BRICK_WALL = register("tainted_stone_brick_wall", new WallBlock(FabricBlockSettings.copy(Blocks.STONE_BRICK_WALL).materialColor(MaterialColor.PURPLE).build()));
	public static final Block INFESTED_TAINTED_STONE = register("infested_tainted_stone", new HallowedInfestedBlock(TAINTED_STONE, FabricBlockSettings.copy(Blocks.INFESTED_STONE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block INFESTED_TAINTED_COBBLESTONE = register("infested_tainted_cobblestone", new HallowedInfestedBlock(TAINTED_COBBLESTONE, FabricBlockSettings.copy(Blocks.INFESTED_COBBLESTONE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block INFESTED_TAINTED_STONE_BRICKS = register("infested_tainted_stone_bricks", new HallowedInfestedBlock(TAINTED_STONE_BRICKS, FabricBlockSettings.copy(Blocks.INFESTED_STONE_BRICKS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block INFESTED_CHISELED_TAINTED_STONE_BRICKS = register("infested_chiseled_tainted_stone_bricks", new HallowedInfestedBlock(CHISELED_TAINTED_STONE_BRICKS, FabricBlockSettings.copy(Blocks.INFESTED_CHISELED_STONE_BRICKS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block INFESTED_CRACKED_TAINTED_STONE_BRICKS = register("infested_cracked_tainted_stone_bricks", new HallowedInfestedBlock(CRACKED_TAINTED_STONE_BRICKS, FabricBlockSettings.copy(Blocks.INFESTED_CRACKED_STONE_BRICKS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_SAND = register("tainted_sand", new FallingBlock(FabricBlockSettings.copy(Blocks.SAND).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_GRAVEL = register("tainted_gravel", new FallingBlock(FabricBlockSettings.copy(Blocks.GRAVEL).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_GLASS = register("tainted_glass", new TranslucentGlassBlock(FabricBlockSettings.copy(Blocks.GLASS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_GLASS_PANE = register("tainted_glass_pane", new TranslucentGlassPaneBlock(FabricBlockSettings.copy(Blocks.GLASS_PANE).build()));
	public static final Block TAINTED_SANDSTONE = register("tainted_sandstone", new Block(FabricBlockSettings.copy(Blocks.SANDSTONE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block SMOOTH_TAINTED_SANDSTONE = register("smooth_tainted_sandstone", new Block(FabricBlockSettings.copy(Blocks.SMOOTH_SANDSTONE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block CUT_TAINTED_SANDSTONE = register("cut_tainted_sandstone", new Block(FabricBlockSettings.copy(Blocks.CUT_SANDSTONE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block CHISELED_TAINTED_SANDSTONE = register("chiseled_tainted_sandstone", new Block(FabricBlockSettings.copy(Blocks.CHISELED_SANDSTONE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_SANDSTONE_STAIRS = register("tainted_sandstone_stairs", new HallowedStairsBlock(TAINTED_SANDSTONE, FabricBlockSettings.copy(Blocks.SANDSTONE_STAIRS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block SMOOTH_TAINTED_SANDSTONE_STAIRS = register("smooth_tainted_sandstone_stairs", new HallowedStairsBlock(SMOOTH_TAINTED_SANDSTONE, FabricBlockSettings.copy(Blocks.SMOOTH_SANDSTONE_STAIRS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_SANDSTONE_SLAB = register("tainted_sandstone_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.SANDSTONE_SLAB).materialColor(MaterialColor.PURPLE).build()));
	public static final Block SMOOTH_TAINTED_SANDSTONE_SLAB = register("smooth_tainted_sandstone_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.SMOOTH_SANDSTONE_SLAB).materialColor(MaterialColor.PURPLE).build()));
	public static final Block CUT_TAINTED_SANDSTONE_SLAB = register("cut_tainted_sandstone_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.CUT_SANDSTONE_SLAB).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_SANDSTONE_WALL = register("tainted_sandstone_wall", new WallBlock(FabricBlockSettings.copy(Blocks.SANDSTONE_WALL).materialColor(MaterialColor.PURPLE).build()));
	public static final Block SOUL_GLASS = register("soul_glass", new TranslucentGlassBlock(FabricBlockSettings.copy(Blocks.GLASS).materialColor(MaterialColor.BROWN).build()));
	public static final Block SOUL_GLASS_PANE = register("soul_glass_pane", new TranslucentGlassPaneBlock(FabricBlockSettings.copy(Blocks.GLASS_PANE).build()));
	public static final Block HALLOWED_ORE = register("hallowed_ore", new OreBlock(FabricBlockSettings.copy(Blocks.IRON_ORE).build()), new Item.Settings().group(TheHallow.GROUP).rarity(Rarity.EPIC));
	public static final Block HALLOWED_BLOCK = register("hallowed_block", new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).materialColor(MaterialColor.RED).build()), new Item.Settings().group(TheHallow.GROUP).rarity(Rarity.EPIC));
	public static final Block WITCH_WATER_BLOCK = register("witch_water", new WitchWaterBlock(HallowedFluids.WITCH_WATER, FabricBlockSettings.copy(Blocks.WATER).build()), (BlockItem) null);
	public static final Block WITCH_WATER_BUBBLE_COLUMN = register("witch_water_bubble_column", new WitchWaterBubbleColumnBlock(FabricBlockSettings.copy(Blocks.BUBBLE_COLUMN).build()), (BlockItem) null);
	public static final Block HALLOWED_TREASURE_CHEST = register("hallowed_treasure_chest", new HallowedTreasureChestBlock(FabricBlockSettings.of(Material.METAL).build()), new Item.Settings().group(TheHallow.GROUP));
	public static final Block BLOOD_BLOCK = register("blood", new BloodBlock(HallowedFluids.BLOOD, FabricBlockSettings.copy(Blocks.WATER).build()), (BlockItem) null);
	public static final Block BLEEDING_BLOCK = register("bleeding_block", new BleedingBlock(FabricBlockSettings.copy(Blocks.MAGMA_BLOCK).lightLevel(0).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TOMBSTONE = register("tombstone", new TombstoneBlock(FabricBlockSettings.of(Material.STONE).build()), new Item.Settings().group(TheHallow.GROUP));
	public static final Block RESTLESS_CACTUS = register("restless_cactus", new RestlessCactusBlock(FabricBlockSettings.copy(Blocks.CACTUS).materialColor(MaterialColor.BROWN).build()), new Item.Settings().group(TheHallow.GROUP));
	public static final Block DEADER_BUSH = register("deader_bush", new DeaderBushBlock(FabricBlockSettings.copy(Blocks.DEAD_BUSH).materialColor(MaterialColor.BROWN).build()), new Item.Settings().group(TheHallow.GROUP));
	public static final Block BREAD_CRUMBS = register("bread_crumbs", new BreadCrumbsBlock(FabricBlockSettings.of(Material.CAKE).breakByHand(true).collidable(false).noCollision().build()), new Item.Settings().group(TheHallow.GROUP).food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).snack().build()));
	public static final Block INFUSION_PILLAR_BLOCK = register("infusion_pillar", new InfusionPillarBlock(FabricBlockSettings.copy(Blocks.COBBLESTONE_WALL).build()));
	public static final Block INFUSION_ALTAR_BLOCK = register("infusion_altar", new InfusionAltarBlock(FabricBlockSettings.copy(Blocks.COBBLESTONE_WALL).build()));
	public static final Block MOIST_FLESH_BLOCK = register("moist_flesh_block", new Block(FabricBlockSettings.copy(Blocks.NETHER_WART_BLOCK).materialColor(MaterialColor.PINK).sounds(BlockSoundGroup.SLIME).build()));
	public static final Block ROTTEN_FLESH_BLOCK = register("rotten_flesh_block", new Block(FabricBlockSettings.copy(Blocks.NETHER_WART_BLOCK).materialColor(MaterialColor.RED).sounds(BlockSoundGroup.SLIME).build()));
	public static final Block BRAIN_MATTER = register("brain_matter", new Block(FabricBlockSettings.copy(Blocks.NETHER_WART_BLOCK).materialColor(MaterialColor.PINK).sounds(BlockSoundGroup.SLIME).build()));
	public static final Block CONGEALED_BLOOD = register("congealed_blood", new CongealedBloodBlock(FabricBlockSettings.copy(Blocks.NETHER_WART_BLOCK).materialColor(MaterialColor.RED).sounds(BlockSoundGroup.SLIME).build()));
	public static final Block DEADWOOD_LOG = register("deadwood_log", new HallowedLogBlock(MaterialColor.PURPLE, FabricBlockSettings.copy(Blocks.OAK_LOG).materialColor(MaterialColor.PURPLE).build()));
	public static final Block STRIPPED_DEADWOOD_LOG = register("stripped_deadwood_log", new LogBlock(MaterialColor.PURPLE, FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG).materialColor(MaterialColor.PURPLE).build()));
	public static final Block DEADWOOD_WOOD = register("deadwood_wood", new HallowedLogBlock(MaterialColor.PURPLE, FabricBlockSettings.copy(Blocks.OAK_WOOD).materialColor(MaterialColor.PURPLE).build()));
	public static final Block STRIPPED_DEADWOOD_WOOD = register("stripped_deadwood_wood", new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD).materialColor(MaterialColor.PURPLE).build()));
	public static final Block DEADWOOD_LEAVES = register("deadwood_leaves", new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).materialColor(MaterialColor.YELLOW).build()));
	public static final Block DEADWOOD_SAPLING = register("deadwood_sapling", new HallowedSaplingBlock(new DeadwoodSaplingGenerator(), FabricBlockSettings.copy(Blocks.OAK_SAPLING).build()));
	public static final Block DEADWOOD_PLANKS = register("deadwood_planks", new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block DEADWOOD_STAIRS = register("deadwood_stairs", new HallowedStairsBlock(DEADWOOD_PLANKS, FabricBlockSettings.copy(Blocks.OAK_STAIRS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block DEADWOOD_SLAB = register("deadwood_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB).materialColor(MaterialColor.PURPLE).build()));
	public static final Block DEADWOOD_FENCE = register("deadwood_fence", new FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block DEADWOOD_FENCE_GATE = register("deadwood_fence_gate", new FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block DEADWOOD_PRESSURE_PLATE = register("deadwood_pressure_plate", new HallowedPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(Blocks.OAK_PRESSURE_PLATE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block DEADWOOD_BUTTON = register("deadwood_button", new HallowedButtonBlock(true, FabricBlockSettings.copy(Blocks.OAK_BUTTON).materialColor(MaterialColor.PURPLE).build()));
	public static final Block DEADWOOD_DOOR = register("deadwood_door", new HallowedDoorBlock(FabricBlockSettings.copy(Blocks.OAK_DOOR).materialColor(MaterialColor.PURPLE).build()));
	public static final Block DEADWOOD_TRAPDOOR = register("deadwood_trapdoor", new HallowedTrapdoorBlock(FabricBlockSettings.copy(Blocks.OAK_TRAPDOOR).materialColor(MaterialColor.PURPLE).build()));
	public static final Block DEADWOOD_SIGN = register("deadwood_sign", new HallowedSignBlock(TheHallow.id("textures/entity/signs/deadwood.png"), FabricBlockSettings.copy(Blocks.OAK_SIGN).materialColor(MaterialColor.PURPLE).build()), (BlockItem) null);
	public static final Block DEADWOOD_WALL_SIGN = register("deadwood_wall_sign", new HallowedWallSignBlock(TheHallow.id("textures/entity/signs/deadwood.png"), FabricBlockSettings.copy(Blocks.OAK_WALL_SIGN).materialColor(MaterialColor.PURPLE).build()), (BlockItem) null);
	public static final Block EERIE_GRASS = register("eerie_grass", new HallowedFernBlock(FabricBlockSettings.copy(Blocks.GRASS).build()));
	public static final Block TALL_EERIE_GRASS = register("tall_eerie_grass", new TallPlantBlock(FabricBlockSettings.copy(Blocks.TALL_GRASS).build()));
	public static final Block BRAMBLES = register("brambles", new BramblesBlock(FabricBlockSettings.copy(Blocks.DEAD_BUSH).build()));
	public static final Block DEADWOOD_VINES = register("deadwood_vines", new VineBlock(FabricBlockSettings.copy(Blocks.VINE).build()));
	public static final Block GLOOMSHROOM = register("gloomshroom", new HallowedMushroomPlantBlock(FabricBlockSettings.copy(Blocks.VINE).build()));
	public static final Block DECEASED_MOSS = register("deceased_moss", new Block(FabricBlockSettings.copy(Blocks.PODZOL).materialColor(MaterialColor.PURPLE).build()));
	public static final Block HALLOWED_GATE = register("hallowed_gate", new HallowedGateBlock(FabricBlockSettings.copy(Blocks.ENCHANTING_TABLE).materialColor(MaterialColor.BROWN).build()));
	public static final Block PUMPKIN_PIE = register("pumpkin_pie", new PumpkinPieBlock(FabricBlockSettings.of(Material.CAKE).strength(0.5F, 0.5F).sounds(BlockSoundGroup.WOOL).build()), (BlockItem) null);
	public static final Block WHITE_CLOTH = register("white_cloth", new Block(FabricBlockSettings.copy(Blocks.WHITE_WOOL).build()));
	public static final Block ORANGE_CLOTH = register("orange_cloth", new Block(FabricBlockSettings.copy(Blocks.ORANGE_WOOL).build()));
	public static final Block MAGENTA_CLOTH = register("magenta_cloth", new Block(FabricBlockSettings.copy(Blocks.MAGENTA_WOOL).build()));
	public static final Block LIGHT_BLUE_CLOTH = register("light_blue_cloth", new Block(FabricBlockSettings.copy(Blocks.LIGHT_BLUE_WOOL).build()));
	public static final Block YELLOW_CLOTH = register("yellow_cloth", new Block(FabricBlockSettings.copy(Blocks.YELLOW_WOOL).build()));
	public static final Block LIME_CLOTH = register("lime_cloth", new Block(FabricBlockSettings.copy(Blocks.LIME_WOOL).build()));
	public static final Block PINK_CLOTH = register("pink_cloth", new Block(FabricBlockSettings.copy(Blocks.PINK_WOOL).build()));
	public static final Block GRAY_CLOTH = register("gray_cloth", new Block(FabricBlockSettings.copy(Blocks.GRAY_WOOL).build()));
	public static final Block LIGHT_GRAY_CLOTH = register("light_gray_cloth", new Block(FabricBlockSettings.copy(Blocks.LIGHT_GRAY_WOOL).build()));
	public static final Block CYAN_CLOTH = register("cyan_cloth", new Block(FabricBlockSettings.copy(Blocks.CYAN_WOOL).build()));
	public static final Block PURPLE_CLOTH = register("purple_cloth", new Block(FabricBlockSettings.copy(Blocks.PURPLE_WOOL).build()));
	public static final Block BLUE_CLOTH = register("blue_cloth", new Block(FabricBlockSettings.copy(Blocks.BLUE_WOOL).build()));
	public static final Block BROWN_CLOTH = register("brown_cloth", new Block(FabricBlockSettings.copy(Blocks.BROWN_WOOL).build()));
	public static final Block GREEN_CLOTH = register("green_cloth", new Block(FabricBlockSettings.copy(Blocks.GREEN_WOOL).build()));
	public static final Block RED_CLOTH = register("red_cloth", new Block(FabricBlockSettings.copy(Blocks.RED_WOOL).build()));
	public static final Block BLACK_CLOTH = register("black_cloth", new Block(FabricBlockSettings.copy(Blocks.BLACK_WOOL).build()));
	public static final Block WHITE_RUG = register("white_rug", new HallowedCarpetBlock(DyeColor.WHITE, FabricBlockSettings.copy(Blocks.WHITE_CARPET).build()));
	public static final Block ORANGE_RUG = register("orange_rug", new HallowedCarpetBlock(DyeColor.ORANGE, FabricBlockSettings.copy(Blocks.ORANGE_CARPET).build()));
	public static final Block MAGENTA_RUG = register("magenta_rug", new HallowedCarpetBlock(DyeColor.MAGENTA, FabricBlockSettings.copy(Blocks.MAGENTA_CARPET).build()));
	public static final Block LIGHT_BLUE_RUG = register("light_blue_rug", new HallowedCarpetBlock(DyeColor.LIGHT_BLUE, FabricBlockSettings.copy(Blocks.LIGHT_BLUE_CARPET).build()));
	public static final Block YELLOW_RUG = register("yellow_rug", new HallowedCarpetBlock(DyeColor.YELLOW, FabricBlockSettings.copy(Blocks.YELLOW_CARPET).build()));
	public static final Block LIME_RUG = register("lime_rug", new HallowedCarpetBlock(DyeColor.LIME, FabricBlockSettings.copy(Blocks.LIME_CARPET).build()));
	public static final Block PINK_RUG = register("pink_rug", new HallowedCarpetBlock(DyeColor.PINK, FabricBlockSettings.copy(Blocks.PINK_CARPET).build()));
	public static final Block GRAY_RUG = register("gray_rug", new HallowedCarpetBlock(DyeColor.GRAY, FabricBlockSettings.copy(Blocks.GRAY_CARPET).build()));
	public static final Block LIGHT_GRAY_RUG = register("light_gray_rug", new HallowedCarpetBlock(DyeColor.LIGHT_GRAY, FabricBlockSettings.copy(Blocks.LIGHT_GRAY_CARPET).build()));
	public static final Block CYAN_RUG = register("cyan_rug", new HallowedCarpetBlock(DyeColor.CYAN, FabricBlockSettings.copy(Blocks.CYAN_CARPET).build()));
	public static final Block PURPLE_RUG = register("purple_rug", new HallowedCarpetBlock(DyeColor.PURPLE, FabricBlockSettings.copy(Blocks.PURPLE_CARPET).build()));
	public static final Block BLUE_RUG = register("blue_rug", new HallowedCarpetBlock(DyeColor.BLUE, FabricBlockSettings.copy(Blocks.BLUE_CARPET).build()));
	public static final Block BROWN_RUG = register("brown_rug", new HallowedCarpetBlock(DyeColor.BROWN, FabricBlockSettings.copy(Blocks.BROWN_CARPET).build()));
	public static final Block GREEN_RUG = register("green_rug", new HallowedCarpetBlock(DyeColor.GREEN, FabricBlockSettings.copy(Blocks.GREEN_CARPET).build()));
	public static final Block RED_RUG = register("red_rug", new HallowedCarpetBlock(DyeColor.RED, FabricBlockSettings.copy(Blocks.RED_CARPET).build()));
	public static final Block BLACK_RUG = register("black_rug", new HallowedCarpetBlock(DyeColor.BLACK, FabricBlockSettings.copy(Blocks.BLACK_CARPET).build()));
	
	private HallowedBlocks() {
		// NO-OP
	}
	
	public static void init() {
		((HallowedLogBlock) DEADWOOD_LOG).setStripped(STRIPPED_DEADWOOD_LOG);
		((HallowedLogBlock) DEADWOOD_WOOD).setStripped(STRIPPED_DEADWOOD_WOOD);
	}
	
	static <T extends Block> T register(String name, T block, Item.Settings settings) {
		return register(name, block, new BlockItem(block, settings));
	}
	
	static <T extends Block> T register(String name, T block) {
		return register(name, block, new Item.Settings().group(TheHallow.GROUP));
	}
	
	static <T extends Block> T register(String name, T block, Function<T, BlockItem> itemFactory) {
		return register(name, block, itemFactory.apply(block));
	}
	
	static <T extends Block> T register(String name, T block, BlockItem item) {
		T b = Registry.register(Registry.BLOCK, TheHallow.id(name), block);
		if (item != null) {
			BlockItem bi = HallowedItems.register(name, item);
			bi.appendBlocks(BlockItem.BLOCK_ITEMS, bi);
		}
		return b;
	}
}
