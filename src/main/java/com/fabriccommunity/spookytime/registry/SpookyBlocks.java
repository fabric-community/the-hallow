package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.block.*;
import com.fabriccommunity.spookytime.block.ColoredPumpkinBlock.PumpkinColor;
import com.fabriccommunity.spookytime.item.WitchedPumpkinItem;
import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.function.Function;

public class SpookyBlocks {
	/**
	 * :tiny pumpkin:
	 */
	public static final Block TINY_PUMPKIN = register("tiny_pumpkin", new TinyPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).strength(1.0F, 1.0F).sounds(BlockSoundGroup.LANTERN).build()), new Item.Settings().group(SpookyTime.PUMPKINS).food(new FoodComponent.Builder().hunger(2).saturationModifier(0.4f).build()));
	public static final Block TINY_WITCHED_PUMPKIN = register("tiny_witched_pumpkin", new TinyPumpkinBlock(Block.Settings.copy(TINY_PUMPKIN)), block -> new WitchedPumpkinItem(block, new Item.Settings().group(SpookyTime.PUMPKINS).food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 200), 1).hunger(1).saturationModifier(0.1f).alwaysEdible().build())));

	/**
	 * Deceased Blocks
	 */
	public static final Block DECEASED_DIRT = register("deceased_dirt", new Block(FabricBlockSettings.copy(Blocks.DIRT).materialColor(MaterialColor.PURPLE).build()));
	public static final Block DECEASED_GRASS_BLOCK = register("deceased_grass_block", new DeceasedGrassBlock(FabricBlockSettings.copy(Blocks.GRASS_BLOCK).materialColor(MaterialColor.PURPLE).build()));

	/**
	 * Tainted Stones
	 */
	public static final Block TAINTED_STONE = register("tainted_stone", new Block(FabricBlockSettings.copy(Blocks.STONE).build()));
	public static final Block TAINTED_COBBLESTONE = register("tainted_cobblestone", new Block(FabricBlockSettings.copy(Blocks.COBBLESTONE).build()));
	public static final Block SMOOTH_TAINTED_STONE = register("smooth_tainted_stone", new Block(FabricBlockSettings.copy(Blocks.SMOOTH_STONE).build()));
	public static final Block TAINTED_STONE_BRICKS = register("tainted_stone_bricks", new Block(FabricBlockSettings.copy(Blocks.STONE_BRICKS).build()));
	public static final Block CHISELED_TAINTED_STONE_BRICKS = register("chiseled_tainted_stone_bricks", new Block(FabricBlockSettings.copy(Blocks.CHISELED_STONE_BRICKS).build()));
	public static final Block CRACKED_TAINTED_STONE_BRICKS = register("cracked_tainted_stone_bricks", new Block(FabricBlockSettings.copy(Blocks.CRACKED_STONE_BRICKS).build()));
	public static final Block TAINTED_STONE_STAIRS = register("tainted_stone_stairs", new SpookyStairsBlock(TAINTED_STONE, FabricBlockSettings.copy(Blocks.STONE_STAIRS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_COBBLESTONE_STAIRS = register("tainted_cobblestone_stairs", new SpookyStairsBlock(TAINTED_COBBLESTONE, FabricBlockSettings.copy(Blocks.COBBLESTONE_STAIRS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_STONE_BRICK_STAIRS = register("tainted_stone_brick_stairs", new SpookyStairsBlock(TAINTED_STONE_BRICKS, FabricBlockSettings.copy(Blocks.STONE_BRICK_STAIRS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_STONE_SLAB = register("tainted_stone_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.STONE_SLAB).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_COBBLESTONE_SLAB = register("tainted_cobblestone_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.COBBLESTONE_SLAB).materialColor(MaterialColor.PURPLE).build()));
	public static final Block SMOOTH_TAINTED_STONE_SLAB = register("smooth_tainted_stone_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.SMOOTH_STONE_SLAB).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_STONE_BRICK_SLAB = register("tainted_stone_brick_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.STONE_BRICK_SLAB).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_COBBLESTONE_WALL = register("tainted_cobblestone_wall", new WallBlock(FabricBlockSettings.copy(Blocks.COBBLESTONE_WALL).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_STONE_BRICK_WALL = register("tainted_stone_brick_wall", new WallBlock(FabricBlockSettings.copy(Blocks.STONE_BRICK_WALL).materialColor(MaterialColor.PURPLE).build()));
	public static final Block INFESTED_TAINTED_STONE = register("infested_tainted_stone", new InfestedBlock(TAINTED_STONE, FabricBlockSettings.copy(Blocks.INFESTED_STONE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block INFESTED_TAINTED_COBBLESTONE = register("infested_tainted_cobblestone", new InfestedBlock(TAINTED_COBBLESTONE, FabricBlockSettings.copy(Blocks.INFESTED_COBBLESTONE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block INFESTED_TAINTED_STONE_BRICKS = register("infested_tainted_stone_bricks", new InfestedBlock(TAINTED_STONE_BRICKS, FabricBlockSettings.copy(Blocks.INFESTED_STONE_BRICKS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block INFESTED_CHISELED_TAINTED_STONE_BRICKS = register("infested_chiseled_tainted_stone_bricks", new InfestedBlock(CHISELED_TAINTED_STONE_BRICKS, FabricBlockSettings.copy(Blocks.INFESTED_CHISELED_STONE_BRICKS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block INFESTED_CRACKED_TAINTED_STONE_BRICKS = register("infested_cracked_tainted_stone_bricks", new InfestedBlock(CRACKED_TAINTED_STONE_BRICKS, FabricBlockSettings.copy(Blocks.INFESTED_CRACKED_STONE_BRICKS).materialColor(MaterialColor.PURPLE).build()));

	public static final Block TAINTED_SAND = register("tainted_sand", new FallingBlock(FabricBlockSettings.copy(Blocks.SAND).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_GRAVEL = register("tainted_gravel", new FallingBlock(FabricBlockSettings.copy(Blocks.GRAVEL).materialColor(MaterialColor.PURPLE).build()));

	/**
	 * Sandstones
	 */
	public static final Block TAINTED_SANDSTONE = register("tainted_sandstone", new Block(FabricBlockSettings.copy(Blocks.SANDSTONE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_SANDSTONE_STAIRS = register("tainted_sandstone_stairs", new SpookyStairsBlock(TAINTED_SANDSTONE, FabricBlockSettings.copy(Blocks.SANDSTONE_STAIRS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_SANDSTONE_SLAB = register("tainted_sandstone_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.SANDSTONE_SLAB).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_SANDSTONE_WALL = register("tainted_sandstone_wall", new WallBlock(FabricBlockSettings.copy(Blocks.SANDSTONE_WALL).materialColor(MaterialColor.PURPLE).build()));
	public static final Block CHISELED_TAINTED_SANDSTONE = register("chiseled_tainted_sandstone", new Block(FabricBlockSettings.copy(Blocks.CHISELED_SANDSTONE).materialColor(MaterialColor.PURPLE).build()));

	/**
	 * Cut Standstones
	 */
	public static final Block CUT_TAINTED_SANDSTONE = register("cut_tainted_sandstone", new Block(FabricBlockSettings.copy(Blocks.CUT_SANDSTONE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block CUT_TAINTED_SANDSTONE_SLAB = register("cut_tainted_sandstone_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.CUT_SANDSTONE_SLAB).materialColor(MaterialColor.PURPLE).build()));

	/**
	 * Smooth Sandstones
	 */
	public static final Block SMOOTH_TAINTED_SANDSTONE = register("smooth_tainted_sandstone", new Block(FabricBlockSettings.copy(Blocks.SMOOTH_SANDSTONE).materialColor(MaterialColor.PURPLE).build()));
	public static final Block SMOOTH_TAINTED_SANDSTONE_STAIRS = register("smooth_tainted_sandstone_stairs", new SpookyStairsBlock(SMOOTH_TAINTED_SANDSTONE, FabricBlockSettings.copy(Blocks.SMOOTH_SANDSTONE_STAIRS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block SMOOTH_TAINTED_SANDSTONE_SLAB = register("smooth_tainted_sandstone_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.SMOOTH_SANDSTONE_SLAB).materialColor(MaterialColor.PURPLE).build()));

	/**
	 * Flesh and Bloody blocks
	 */
	public static final Block MOIST_FLESH_BLOCK = register("moist_flesh_block", new Block(FabricBlockSettings.copy(Blocks.NETHER_WART_BLOCK).materialColor(MaterialColor.PINK).sounds(BlockSoundGroup.SLIME).build()));
	public static final Block BRAIN_MATTER = register("brain_matter", new Block(FabricBlockSettings.copy(Blocks.NETHER_WART_BLOCK).materialColor(MaterialColor.PINK).sounds(BlockSoundGroup.SLIME).build()));
	public static final Block BLEEDING_BLOCK = register("bleeding_block", new BleedingBlock(FabricBlockSettings.copy(Blocks.MAGMA_BLOCK).lightLevel(0).materialColor(MaterialColor.PURPLE).build()));

	/**
	 * Glass
	 */
	public static final Block TAINTED_GLASS = register("tainted_glass", new TranslucentGlassBlock(FabricBlockSettings.copy(Blocks.GLASS).materialColor(MaterialColor.PURPLE).build()));
	public static final Block TAINTED_GLASS_PANE = register("tainted_glass_pane", new TranslucentGlassPaneBlock(FabricBlockSettings.copy(Blocks.GLASS_PANE).build()));
	public static final Block SOUL_GLASS = register("soul_glass", new TranslucentGlassBlock(FabricBlockSettings.copy(Blocks.GLASS).materialColor(MaterialColor.BROWN).build()));
	public static final Block SOUL_GLASS_PANE = register("soul_glass_pane", new TranslucentGlassPaneBlock(FabricBlockSettings.copy(Blocks.GLASS_PANE).build()));

	/**
	 * Plants
	 */
	public static final Block SPOOKY_CACTUS = register("spooky_cactus", new SpookyCactusBlock(FabricBlockSettings.copy(Blocks.CACTUS).materialColor(MaterialColor.BROWN).build()), new Item.Settings().group(SpookyTime.GROUP));
	public static final Block DEADER_BUSH = register("deader_bush", new DeaderBushBlock(FabricBlockSettings.copy(Blocks.DEAD_BUSH).materialColor(MaterialColor.BROWN).build()), new Item.Settings().group(SpookyTime.GROUP));
	
	/**
	 * Spookium ores
	 */
	public static final Block SPOOKIUM_ORE = register("spookium_ore", new OreBlock(FabricBlockSettings.copy(Blocks.IRON_ORE).build()), new Item.Settings().group(SpookyTime.GROUP).rarity(Rarity.EPIC));
	public static final Block SPOOKIUM_BLOCK = register("spookium_block", new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).materialColor(MaterialColor.RED).build()), new Item.Settings().group(SpookyTime.GROUP).rarity(Rarity.EPIC));		
		
	/**
	 * Pumpkins
	 */
	public static final Block RAINBOW_PUMPKIN = register("rainbow_pumpkin", new ColoredPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.ORANGE).build(), PumpkinColor.RAINBOW), new Item.Settings().group(SpookyTime.PUMPKINS).rarity(Rarity.UNCOMMON));
	public static final Block RAINBOW_CARVED_PUMPKIN = register("rainbow_carved_pumpkin", new ColoredCarvedPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.LIME).build(), PumpkinColor.RAINBOW), new Item.Settings().group(SpookyTime.PUMPKINS).rarity(Rarity.UNCOMMON));
	public static final Block RAINBOW_JACK_O_LANTERN = register("rainbow_jack_o_lantern", new ColoredCarvedPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.LIME).lightLevel(15).build(), PumpkinColor.RAINBOW), new Item.Settings().group(SpookyTime.PUMPKINS).rarity(Rarity.UNCOMMON));
	
	public static final Block RED_PUMPKIN = register("red_pumpkin", new ColoredPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.RED).build(), PumpkinColor.RED), new Item.Settings().group(SpookyTime.PUMPKINS));
	public static final Block RED_JACK_O_LANTERN = register("red_jack_o_lantern", new ColoredCarvedPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.RED).lightLevel(15).build(), PumpkinColor.RED), new Item.Settings().group(SpookyTime.PUMPKINS));
	public static final Block RED_CARVED_PUMPKIN = register("red_carved_pumpkin", new ColoredCarvedPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.RED).build(), PumpkinColor.RED), new Item.Settings().group(SpookyTime.PUMPKINS));
	
	public static final Block YELLOW_PUMPKIN = register("yellow_pumpkin", new ColoredPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.YELLOW).build(), PumpkinColor.YELLOW), new Item.Settings().group(SpookyTime.PUMPKINS));
	public static final Block YELLOW_JACK_O_LANTERN = register("yellow_jack_o_lantern", new ColoredCarvedPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.YELLOW).lightLevel(15).build(), PumpkinColor.YELLOW), new Item.Settings().group(SpookyTime.PUMPKINS));
	public static final Block YELLOW_CARVED_PUMPKIN = register("yellow_carved_pumpkin", new ColoredCarvedPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.YELLOW).build(), PumpkinColor.YELLOW), new Item.Settings().group(SpookyTime.PUMPKINS));

	public static final Block BLUE_PUMPKIN = register("blue_pumpkin", new ColoredPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.BLUE).build(), PumpkinColor.BLUE), new Item.Settings().group(SpookyTime.PUMPKINS));
	public static final Block BLUE_JACK_O_LANTERN = register("blue_jack_o_lantern", new ColoredCarvedPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.BLUE).lightLevel(15).build(), PumpkinColor.BLUE), new Item.Settings().group(SpookyTime.PUMPKINS));
	public static final Block BLUE_CARVED_PUMPKIN = register("blue_carved_pumpkin", new ColoredCarvedPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.BLUE).build(), PumpkinColor.BLUE), new Item.Settings().group(SpookyTime.PUMPKINS));

	public static final Block TAN_PUMPKIN = register("tan_pumpkin", new ColoredPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.GRAY).build(), PumpkinColor.TAN), new Item.Settings().group(SpookyTime.PUMPKINS));
	public static final Block TAN_JACK_O_LANTERN = register("tan_jack_o_lantern", new ColoredCarvedPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.GRAY).lightLevel(15).build(), PumpkinColor.TAN), new Item.Settings().group(SpookyTime.PUMPKINS));
	public static final Block TAN_CARVED_PUMPKIN = register("tan_carved_pumpkin", new ColoredCarvedPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.GRAY).build(), PumpkinColor.TAN), new Item.Settings().group(SpookyTime.PUMPKINS));
	
	public static final Block WHITE_PUMPKIN = register("white_pumpkin", new ColoredPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.WHITE).build(), PumpkinColor.WHITE), new Item.Settings().group(SpookyTime.PUMPKINS));
	public static final Block WHITE_JACK_O_LANTERN = register("white_jack_o_lantern", new ColoredCarvedPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.WHITE).lightLevel(15).build(), PumpkinColor.WHITE), new Item.Settings().group(SpookyTime.PUMPKINS));
	public static final Block WHITE_CARVED_PUMPKIN = register("white_carved_pumpkin", new ColoredCarvedPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.WHITE).build(), PumpkinColor.WHITE), new Item.Settings().group(SpookyTime.PUMPKINS));
	
	public static final Block WITCHED_PUMPKIN = register("witched_pumpkin", new ColoredPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.PURPLE).build(), PumpkinColor.WITCHED), new Item.Settings().group(SpookyTime.PUMPKINS));
	public static final Block WITCHED_JACK_O_LANTERN = register("witched_jack_o_lantern", new ColoredCarvedPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.PURPLE).lightLevel(15).build(), PumpkinColor.WITCHED), new Item.Settings().group(SpookyTime.PUMPKINS));
	public static final Block WITCHED_CARVED_PUMPKIN = register("witched_carved_pumpkin", new ColoredCarvedPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).materialColor(MaterialColor.PURPLE).build(), PumpkinColor.WITCHED), new Item.Settings().group(SpookyTime.PUMPKINS));

	/**
	 * Misc
	 */
	public static final Block BREAD_CRUMBS = register("bread_crumbs", new BreadCrumbsBlock(FabricBlockSettings.of(Material.CAKE).breakByHand(true).collidable(false).noCollision().build()), new Item.Settings().group(SpookyTime.GROUP).food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).snack().build()));
	public static final Block SPOOKY_TREASURE_CHEST = register("spooky_treasure_chest", new SpookyTreasureChestBlock(FabricBlockSettings.of(Material.METAL).build()), new Item.Settings().group(SpookyTime.GROUP));
	public static final Block INFUSION_PILLAR_BLOCK = register("infusion_pillar", new InfusionPillarBlock(FabricBlockSettings.copy(Blocks.COBBLESTONE_WALL).build()));
	public static final Block INFUSION_ALTAR_BLOCK = register("infusion_altar", new InfusionAltarBlock(FabricBlockSettings.copy(Blocks.COBBLESTONE_WALL).build()));

	/**
	 * Fluids
	 */
	public static final Block WITCH_WATER_BLOCK = register("witch_water", new WitchWaterBlock(SpookyFluids.WITCH_WATER, FabricBlockSettings.copy(Blocks.WATER).build()), (BlockItem) null);
	public static final Block BLOOD_BLOCK = register("blood", new BloodBlock(SpookyFluids.BLOOD, FabricBlockSettings.copy(Blocks.LAVA).lightLevel(0).build()), (BlockItem) null);
	public static final Block WITCH_WATER_BUBBLE_COLUMN = registerNoItem("witch_water_bubble_column", new WitchWaterBubbleColumnBlock(FabricBlockSettings.copy(Blocks.BUBBLE_COLUMN).build()));

	public static final ImmutableMap<PumpkinColor, Block> CARVED_PUMPKIN_COLORS = new ImmutableMap.Builder<PumpkinColor, Block>()
			.put(PumpkinColor.RED, SpookyBlocks.RED_CARVED_PUMPKIN)
			.put(PumpkinColor.YELLOW, SpookyBlocks.YELLOW_CARVED_PUMPKIN)
			.put(PumpkinColor.BLUE, SpookyBlocks.BLUE_CARVED_PUMPKIN)
			.put(PumpkinColor.TAN, SpookyBlocks.TAN_CARVED_PUMPKIN)
			.put(PumpkinColor.WHITE, SpookyBlocks.WHITE_CARVED_PUMPKIN)
			.put(PumpkinColor.WITCHED, SpookyBlocks.WITCHED_CARVED_PUMPKIN)
			.put(PumpkinColor.RAINBOW, SpookyBlocks.RAINBOW_CARVED_PUMPKIN)
			.build();
		
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

	static <T extends Block> T registerNoItem(String name, T block) {
		T b = Registry.register(Registry.BLOCK, SpookyTime.id(name), block);
		return b;
	}
}
