package com.fabriccommunity.spookytime.doomtree;

import java.util.function.Consumer;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.doomtree.treeheart.DoomHeartBlockEntity;
import com.fabriccommunity.spookytime.doomtree.treeheart.DoomTreeTracker;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.fabricmc.fabric.api.event.server.ServerStopCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.resource.ResourceType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
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

	public static Block DOOMED_RESIDUE_BLOCK = SpookyBlocks.register("doomed_residue_block", new Block(FabricBlockSettings.of(Material.EARTH).breakByHand(true).sounds(BlockSoundGroup.SAND).build()));
	public static Block WARDING_ESSENCE_BLOCK = SpookyBlocks.register("warding_essence_block", new Block(FabricBlockSettings.of(Material.EARTH).breakByHand(true).sounds(BlockSoundGroup.SAND).build()));
	public static Item WARDING_ESSENCE_BLOCK_ITEM = Item.fromBlock(WARDING_ESSENCE_BLOCK);

	public static Item DOOMED_RESIDUE_ITEM = Registry.register(Registry.ITEM, SpookyTime.id("doomed_residue"), new Item((new Item.Settings()).group(SpookyTime.GROUP)));
	public static Item WARDING_ESSENCE_ITEM = Registry.register(Registry.ITEM, SpookyTime.id("warding_essence"), new Item((new Item.Settings()).group(SpookyTime.GROUP)));
	public static Item DOOM_FRAGMENT_ITEM = Registry.register(Registry.ITEM, SpookyTime.id("doom_fragment"), new Item((new Item.Settings()).group(SpookyTime.GROUP)));
	public static Item ALCHEMICAL_BASIN_FRAME = Registry.register(Registry.ITEM, SpookyTime.id("alchemical_basin_frame"), new Item((new Item.Settings()).group(SpookyTime.GROUP)));

	public static Block ALCHEMICAL_BASIN_BLOCK = SpookyBlocks.register("alchemical_basin", new AlchemicalBasinBlock(FabricBlockSettings
			.of(Material.METAL).breakByHand(true).strength(0.5F, 0.5F).lightLevel(5).build()));

	public static final BlockEntityType<AlchemicalBasinBlockEntity> ALCHEMICAL_BASIN =
			Registry.register(Registry.BLOCK_ENTITY, SpookyTime.id("alchemical_basin"), BlockEntityType.Builder.create(AlchemicalBasinBlockEntity::new, ALCHEMICAL_BASIN_BLOCK).build(null));

	private static final String BASIN_WARDING_ID = "basin_warding";
	public static final RecipeSerializer<BasinWardingRecipe> WARDING_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, SpookyTime.id(BASIN_WARDING_ID), new BasinWardingRecipeSerializer());
	public static final RecipeType<BasinWardingRecipe> WARDING_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, SpookyTime.id(BASIN_WARDING_ID), new RecipeType<BasinWardingRecipe>() {
		@Override
		public String toString() {
			return BASIN_WARDING_ID;
		}
	});

	public static Item WARDED_IRON_INGOT = Registry.register(Registry.ITEM, SpookyTime.id("warded_iron_ingot"), new Item((new Item.Settings()).group(SpookyTime.GROUP)));
	public static Item WARDED_IRON_NUGGET = Registry.register(Registry.ITEM, SpookyTime.id("warded_iron_nugget"), new Item((new Item.Settings()).group(SpookyTime.GROUP)));
	public static Item WARDED_DIAMOND = Registry.register(Registry.ITEM, SpookyTime.id("warded_diamond"), new Item((new Item.Settings()).group(SpookyTime.GROUP)));
	public static Item WARDED_STICK = Registry.register(Registry.ITEM, SpookyTime.id("warded_stick"), new Item((new Item.Settings()).group(SpookyTime.GROUP)));

	public static final ArmorMaterial WARDED_IRON_ARMOR_MATERIAL = new SimpleArmorMaterial("warded_iron", 17, new int[]{2, 5, 6, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.5F, () -> {
		return Ingredient.ofItems(WARDED_IRON_INGOT);
	});

	public static final ArmorMaterial ENCRUSTED_ARMOR_MATERIAL = new SimpleArmorMaterial("encrusted_iron", 37, new int[]{3, 6, 8, 3}, 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5F, () -> {
		return Ingredient.ofItems(WARDED_DIAMOND);
	});

	public static final Item WARDED_IRON_HELMET =  Registry.register(Registry.ITEM, SpookyTime.id("warded_iron_helmet"), new ArmorItem(WARDED_IRON_ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Settings()).group(SpookyTime.GROUP)));
	public static final Item WARDED_IRON_CHESTPLATE =  Registry.register(Registry.ITEM, SpookyTime.id("warded_iron_chestplate"), new ArmorItem(WARDED_IRON_ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Settings()).group(SpookyTime.GROUP)));
	public static final Item WARDED_IRON_LEGGINGS =  Registry.register(Registry.ITEM, SpookyTime.id("warded_iron_leggings"), new ArmorItem(WARDED_IRON_ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Settings()).group(SpookyTime.GROUP)));
	public static final Item WARDED_IRON_BOOTS =  Registry.register(Registry.ITEM, SpookyTime.id("warded_iron_boots"), new ArmorItem(WARDED_IRON_ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Settings()).group(SpookyTime.GROUP)));

	public static final Item ENCRUSTED_HELMET =  Registry.register(Registry.ITEM, SpookyTime.id("warded_iron_helmet"), new ArmorItem(WARDED_IRON_ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Settings()).group(SpookyTime.GROUP)));
	public static final Item ENCRUSTED_CHESTPLATE =  Registry.register(Registry.ITEM, SpookyTime.id("warded_iron_chestplate"), new ArmorItem(WARDED_IRON_ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Settings()).group(SpookyTime.GROUP)));
	public static final Item ENCRUSTED_LEGGINGS =  Registry.register(Registry.ITEM, SpookyTime.id("warded_iron_leggings"), new ArmorItem(WARDED_IRON_ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Settings()).group(SpookyTime.GROUP)));
	public static final Item ENCRUSTED_BOOTS =  Registry.register(Registry.ITEM, SpookyTime.id("warded_iron_boots"), new ArmorItem(WARDED_IRON_ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Settings()).group(SpookyTime.GROUP)));

	
	public static final ToolMaterial WARDED_IRON_TOOL_MATERIAL = new SimpleToolMaterial(2, 300, 6.6F, 2.2F, 15, () -> {
		return Ingredient.ofItems(WARDED_IRON_INGOT);
	});

	public static final ToolMaterial ENCRUSTED_TOOL_MATERIAL = new SimpleToolMaterial(3, 1800, 8.8F, 3.3F, 11, () -> {
		return Ingredient.ofItems(WARDED_DIAMOND);
	});
	
	public static final Item WARDED_IRON_HOE = Registry.register(Registry.ITEM, SpookyTime.id("warded_iron_hoe"), new HoeItem(WARDED_IRON_TOOL_MATERIAL, -1.0F, new Item.Settings().group(SpookyTime.GROUP)){});
	public static final Item WARDED_IRON_SHOVEL = Registry.register(Registry.ITEM, SpookyTime.id("warded_iron_shovel"), new ShovelItem(WARDED_IRON_TOOL_MATERIAL, 1.5F, -3.0F, new Item.Settings().group(SpookyTime.GROUP)){});
	public static final Item WARDED_IRON_PICKAXE = Registry.register(Registry.ITEM, SpookyTime.id("warded_iron_pickaxe"), new PickaxeItem(WARDED_IRON_TOOL_MATERIAL, 1, -2.8F, new Item.Settings().group(SpookyTime.GROUP)){});
	public static final Item WARDED_IRON_AXE = Registry.register(Registry.ITEM, SpookyTime.id("warded_iron_axe"), new AxeItem(WARDED_IRON_TOOL_MATERIAL, 6.0F, -3.1F, new Item.Settings().group(SpookyTime.GROUP)){});
	public static final Item WARDED_IRON_SWORD = Registry.register(Registry.ITEM, SpookyTime.id("warded_iron_sword"), new SwordItem(WARDED_IRON_TOOL_MATERIAL, 3, -2.4F, new Item.Settings().group(SpookyTime.GROUP)){});

	public static Tag<Block> DOOM_TREE_PROTECTED = TagRegistry.block(SpookyTime.id("doom_tree_protected"));
	public static Tag<Block> DOOM_TREE_IGNORED = TagRegistry.block(SpookyTime.id("doom_tree_ignored"));
	public static Tag<Item> WARDED_ITEM_TAG = TagRegistry.item(SpookyTime.id("doom_warded"));

	/**
	 * Server-safe proxy for block entities to call render refresh
	 */
	public static Consumer<BlockPos> RENDER_REFRESH_HANDLER = p -> {};

	public static void init() {
		ServerStopCallback.EVENT.register(s -> DoomTreeTracker.clear());
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(BasinWardingRecipeHelper.INSTANCE);
		ServerStartCallback.EVENT.register(BasinWardingRecipeHelper::init);
		ServerStopCallback.EVENT.register(BasinWardingRecipeHelper::stop);
	}

	private static FabricBlockSettings logSettings() {
		return FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES, 2).strength(3.0F, 20.0F);
	}

	private static Block.Settings doomedSettings() {
		return FabricBlockSettings.of(Material.EARTH).breakByHand(true).breakInstantly().sounds(BlockSoundGroup.SAND).build();
	}
}
