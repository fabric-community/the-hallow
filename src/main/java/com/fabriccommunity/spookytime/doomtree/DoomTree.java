package com.fabriccommunity.spookytime.doomtree;

import java.util.function.Consumer;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.doomtree.treeheart.DoomHeartBlockEntity;
import com.fabriccommunity.spookytime.doomtree.treeheart.DoomTreeTracker;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.event.server.ServerStopCallback;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

public class DoomTree {

	public static Block DOOM_SAPLING = SpookyBlocks.register("doom_sapling",
			new DoomSaplingBlock(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).build()));

	public static Block MIASMA_BLOCK = Registry.register(Registry.BLOCK, SpookyTime.id("miasma"), new MiasmaBlock());

	public static Block DOOM_GLEAM_BLOCK = Registry.register(Registry.BLOCK, SpookyTime.id("doom_gleam"), new DoomGleamBlock());

	public static Block DOOM_HEART_BLOCK = Registry.register(Registry.BLOCK, SpookyTime.id("doom_tree_heart"),
			new DoomHeartBlock(FabricBlockSettings.of(Material.WOOD).dropsLike(DOOM_SAPLING).breakByTool(FabricToolTags.AXES, 3).strength(200.0F, 1200.0F).build()));

	public static final BlockEntityType<DoomHeartBlockEntity> DOOM_HEART =
			Registry.register(Registry.BLOCK_ENTITY, SpookyTime.id("doom_tree"), BlockEntityType.Builder.create(DoomHeartBlockEntity::new, DOOM_HEART_BLOCK).build(null));

	public static Block PLACED_DOOM_LOG = SpookyBlocks.register("doom_log_p", new DoomLogBlock(logSettings().build(), true, 1));
	public static Item PLACED_DOOM_LOG_ITEM = Item.fromBlock(PLACED_DOOM_LOG);
	
	public static Block PLACED_DOOM_LOG_CHANNEL = SpookyBlocks.register("doom_log_channel_p", new DoomLogBlock(logSettings().build(), true, 1));
	public static Block PLACED_DOOM_LOG_TERMINAL = SpookyBlocks.register("doom_log_terminal_p", new DoomLogBlock(logSettings().build(), true, 1));

	public static Block DOOM_LOG = Registry.register(Registry.BLOCK, SpookyTime.id("doom_log"),
			new DoomLogBlock.Height(logSettings().dropsLike(PLACED_DOOM_LOG).build(), 0.04f));
	public static Item DOOM_LOG_ITEM = Item.fromBlock(DOOM_LOG);
	
	public static Block DOOM_LOG_CHANNEL = Registry.register(Registry.BLOCK, SpookyTime.id("doom_log_channel"),
			new DoomLogBlock.Height(logSettings().dropsLike(PLACED_DOOM_LOG_CHANNEL).build(), 0.02f));
	public static Block DOOM_LOG_TERMINAL = Registry.register(Registry.BLOCK, SpookyTime.id("doom_log_terminal"),
			new DoomLogBlock(logSettings().dropsLike(PLACED_DOOM_LOG_TERMINAL).build(), false, 0.02f));


	public static Block DOOM_LEAF = SpookyBlocks.register("doom_leaves", new DoomLeafBlock(doomedSettings(), false, 1));
	public static Item DOOM_LEAF_ITEM = Item.fromBlock(DOOM_LEAF);

	public static Block DOOMED_LOG = SpookyBlocks.register("doomed_log", new DoomedLogBlock(doomedSettings()));
	public static Block DOOMED_EARTH = SpookyBlocks.register("doomed_earth", new DoomedBlock(doomedSettings()));
	public static Block DOOMED_STONE = SpookyBlocks.register("doomed_stone", new DoomedBlock(doomedSettings()));
	public static Block DOOMED_DUST = SpookyBlocks.register("doomed_dust", new DoomedBlock(doomedSettings()));

	public static Block DOOMED_RESIDUE_BLOCK = SpookyBlocks.register("doomed_residue_block", new Block(FabricBlockSettings.of(Material.EARTH).breakByHand(true).sounds(BlockSoundGroup.SAND).build()));
	public static Block WARDING_ESSENCE_BLOCK = SpookyBlocks.register("warding_essence_block", new Block(FabricBlockSettings.of(Material.EARTH).breakByHand(true).sounds(BlockSoundGroup.SAND).build()));

	public static Item DOOMED_RESIDUE_ITEM = Registry.register(Registry.ITEM, SpookyTime.id("doomed_residue"), new Item((new Item.Settings()).group(SpookyTime.GROUP)));
	public static Item WARDING_ESSENCE_ITEM = Registry.register(Registry.ITEM, SpookyTime.id("warding_essence"), new Item((new Item.Settings()).group(SpookyTime.GROUP)));
	public static Item DOOM_FRAGMENT_ITEM = Registry.register(Registry.ITEM, SpookyTime.id("doom_fragment"), new Item((new Item.Settings()).group(SpookyTime.GROUP)));
	public static Item ALCHEMICAL_BASIN_FRAME = Registry.register(Registry.ITEM, SpookyTime.id("alchemical_basin_frame"), new Item((new Item.Settings()).group(SpookyTime.GROUP)));

	public static Block ALCHEMICAL_BASIN_BLOCK = SpookyBlocks.register("alchemical_basin", new AlchemicalBasinBlock(FabricBlockSettings.of(Material.METAL).breakByHand(true).strength(0.5F, 0.5F).build()));

	public static final BlockEntityType<AlchemicalBasinBlockEntity> ALCHEMICAL_BASIN =
			Registry.register(Registry.BLOCK_ENTITY, SpookyTime.id("alchemical_basin"), BlockEntityType.Builder.create(AlchemicalBasinBlockEntity::new, ALCHEMICAL_BASIN_BLOCK).build(null));

	public static Tag<Block> DOOM_TREE_PROTECTED = TagRegistry.block(SpookyTime.id("doom_tree_protected"));

	public static Tag<Block> DOOM_TREE_IGNORED = TagRegistry.block(SpookyTime.id("doom_tree_ignored"));

	public static void init() {
		ServerStopCallback.EVENT.register(s -> DoomTreeTracker.clear());
	}

	private static FabricBlockSettings logSettings() {
		return FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES, 2).strength(3.0F, 20.0F);
	}

	private static Block.Settings doomedSettings() {
		return FabricBlockSettings.of(Material.EARTH).breakByHand(true).breakInstantly().sounds(BlockSoundGroup.SAND).build();
	}

	public static final BlockState LOG_STATE = DOOM_LOG.getDefaultState();
	public static final BlockState CHANNEL_STATE = DOOM_LOG_CHANNEL.getDefaultState();
	public static final BlockState TERMINAL_STATE = DOOM_LOG_TERMINAL.getDefaultState();
	public static final BlockState LEAF_STATE = DOOM_LEAF.getDefaultState();
	public static final BlockState MIASMA_STATE = MIASMA_BLOCK.getDefaultState();
	public static final BlockState GLEAM_STATE = DOOM_GLEAM_BLOCK.getDefaultState();

	public static final BlockState DOOMED_LOG_STATE = DOOMED_LOG.getDefaultState();
	public static final BlockState DOOMED_EARTH_STATE = DOOMED_EARTH.getDefaultState();
	public static final BlockState DOOMED_STONE_STATE = DOOMED_STONE.getDefaultState();
	public static final BlockState DOOMED_DUST_STATE = DOOMED_DUST.getDefaultState();

	/**
	 * Server-safe proxy for block entities to call render refresh
	 */
	public static Consumer<BlockPos> RENDER_REFRESH_HANDLER = p -> {};
}
