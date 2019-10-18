package com.fabriccommunity.thehallow.registry;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.item.CandyItem;
import com.fabriccommunity.thehallow.item.GoldenCandyCornItem;
import com.fabriccommunity.thehallow.item.PaperBagItem;
import com.fabriccommunity.thehallow.item.PumpkinRing;
import com.fabriccommunity.thehallow.item.SkirtCostume;
import com.fabriccommunity.thehallow.item.TrumpetItem;
import com.fabriccommunity.thehallow.item.tool.ClubItem;
import com.fabriccommunity.thehallow.item.tool.ScytheItem;
import com.fabriccommunity.thehallow.item.tool.SpookiumMaterial;
import com.fabriccommunity.thehallow.util.PumpkinFoods;
import dev.emi.trinkets.api.TrinketSlots;
import net.minecraft.item.BucketItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.MushroomStewItem;
import net.minecraft.item.SignItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class HallowItems {
	/**
	 * Fluids
	 */
	public static final BucketItem WITCH_WATER_BUCKET = register("witch_water_bucket", new BucketItem(HallowFluids.WITCH_WATER, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1).group(TheHallow.GROUP)));
	public static final BucketItem BLOOD_BUCKET = register("blood_bucket", new BucketItem(HallowFluids.BLOOD, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1).group(TheHallow.GROUP)));

	/**
	 * Food
	 */
	public static final Item CARAMEL_APPLE = register("caramel_apple", new CandyItem(new Item.Settings().group(TheHallow.GROUP), 5, 0.3F));
	public static final Item PUMPKIN_CANDY = register("pumpkin_candy", new CandyItem(new Item.Settings().group(TheHallow.GROUP), 2, 0.3F));
	public static final Item RARE_CANDY = register("rare_candy", new CandyItem(new Item.Settings().group(TheHallow.GROUP), 3, 0.3F));
	public static final Item WINGED_STRAWBERRY_CANDY = register("winged_strawberry_candy", new CandyItem(new Item.Settings().group(TheHallow.GROUP), 1, 0.1F));
	public static final Item PEPPERMINT = register("peppermint", new CandyItem(new Item.Settings().group(TheHallow.GROUP), 1, 0.2F));
	public static final Item BAKED_PUMPKIN_SEEDS = register("baked_pumpkin_seeds", new Item(new Item.Settings().group(TheHallow.GROUP).food(new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).snack().build())));
	public static final Item PUMPKIN_STEW = register("pumpkin_stew", new MushroomStewItem(new Item.Settings().group(TheHallow.GROUP).food(new FoodComponent.Builder().hunger(7).saturationModifier(1.4f).build()).recipeRemainder(Items.BOWL)));
	public static final Item GOLDEN_CANDY_CORN = register("golden_candy_corn", new GoldenCandyCornItem(newSettings().maxDamage(250), 1, 0.25f));

	/**
	 * Trinkets
	 */
	public static final Item PUMPKIN_RING = register("pumpkin_ring", new PumpkinRing(new Item.Settings().group(TheHallow.GROUP)));

	/**
	 * Soul stuff
	 */
	public static final Item SOUL_BOTTLE = register("soul_bottle", new Item(new Item.Settings().group(TheHallow.GROUP)));

	/**
	 * Spookium items
	 */
	public static final Item SPOOKIUM_INGOT = register("spookium_ingot", new Item(new Item.Settings().group(TheHallow.GROUP).rarity(Rarity.EPIC)));
	public static final Item SPOOKIUM_NUGGET = register("spookium_nugget", new Item(new Item.Settings().group(TheHallow.GROUP).rarity(Rarity.EPIC)));
	public static final ToolMaterial SPOOKIUM = new SpookiumMaterial();

	/**
	 * Weapons and Tools
	 */
	public static final Item REAPERS_SCYTHE = register("reapers_scythe", new ScytheItem(SPOOKIUM, 3, -2.0F, new Item.Settings().group(TheHallow.GROUP).maxCount(1).rarity(Rarity.EPIC)));
	public static final Item TRUMPET = register("trumpet", new TrumpetItem(new Item.Settings().group(TheHallow.GROUP)));
	public static final Item WOODEN_CLUB = register("wooden_club", new ClubItem(ToolMaterials.WOOD, 9, -3.6F, newSettings().maxCount(1)));
	public static final Item STONE_CLUB = register("stone_club", new ClubItem(ToolMaterials.STONE, 9, -3.6F, newSettings().maxCount(1)));
	public static final Item IRON_CLUB = register("iron_club", new ClubItem(ToolMaterials.IRON, 9, -3.6F, newSettings().maxCount(1)));
	public static final Item GOLD_CLUB = register("gold_club", new ClubItem(ToolMaterials.GOLD, 9, -3.6F, newSettings().maxCount(1)));
	public static final Item DIAMOND_CLUB = register("diamond_club", new ClubItem(ToolMaterials.DIAMOND, 9, -3.6F, newSettings().maxCount(1)));

	/**
	 * Costumes
	 */
	public static final Item PAPER_BAG = register("paper_bag", new PaperBagItem(newSettings().maxCount(1)));
	public static final Item BLAZE_SKIRT = register("blaze_skirt", new SkirtCostume(new Item.Settings().group(TheHallow.GROUP).maxCount(1)));

	/**
	 * Spawn Eggs
	 */
	public static final Item PUMPCOWN_SPAWN_EGG = register("pumpcown_spawn_egg", new SpawnEggItem(HallowEntities.PUMPCOWN, 0x7E3D0E, 0xE38A1D, new Item.Settings().group(TheHallow.GROUP)));
	public static final Item CROW_SPAWN_EGG = register("crow_spawn_egg", new SpawnEggItem(HallowEntities.CROW, 0x161616, 0x454545, new Item.Settings().group(TheHallow.GROUP)));
	public static final Item MUMMY_SPAWN_EGG = register("mummy_spawn_egg", new SpawnEggItem(HallowEntities.MUMMY, 0xCBBBAD, 0x463C34, newSettings()));
	public static final Item CULTIST_SPAWN_EGG = register("cultist_spawn_egg", new SpawnEggItem(HallowEntities.CULTIST, 0x102F4E, 0x01041E, newSettings()));

	/**
	 * Other items
	 */
	public static Item DEADWOOD_SIGN;
	public static final Item BLACK_FEATHER = register("black_feather", new Item(newSettings()));
	public static final Item OLD_BANDAGE = register("old_bandage", new Item(newSettings()));

	private HallowItems() {
		// NO-OP
	}
	
	static Item.Settings newSettings() {
		return new Item.Settings().group(TheHallow.GROUP);
	}
	
	public static void init() {
		TrinketSlots.addSubSlot("legs", "belt", new Identifier("trinkets", "textures/item/empty_trinket_slot_belt.png"));
		TrinketSlots.addSubSlot("hand", "ring", new Identifier("trinkets", "textures/item/empty_trinket_slot_ring.png"));
		TrinketSlots.addSubSlot("head", "mask", new Identifier("trinkets", "textures/item/empty_trinket_slot_mask.png"));
		
		PumpkinFoods.registerPumpkinFood(Items.PUMPKIN_PIE);
		PumpkinFoods.registerPumpkinFood(HallowItems.BAKED_PUMPKIN_SEEDS);
		PumpkinFoods.registerPumpkinFood(HallowItems.PUMPKIN_STEW);
		PumpkinFoods.registerPumpkinFood(HallowItems.PUMPKIN_CANDY);
		PumpkinFoods.registerPumpkinFood(HallowBlocks.TINY_PUMPKIN.asItem());
		PumpkinFoods.registerPumpkinFood(HallowBlocks.WITCHED_PUMPKIN.asItem());
		
		DEADWOOD_SIGN = register("deadwood_sign", new SignItem(newSettings().maxCount(16), HallowBlocks.DEADWOOD_SIGN, HallowBlocks.DEADWOOD_WALL_SIGN));
	}
	
	protected static <T extends Item> T register(String name, T item) {
		return Registry.register(Registry.ITEM, TheHallow.id(name), item);
	}
}
