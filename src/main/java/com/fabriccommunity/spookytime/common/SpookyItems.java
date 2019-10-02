package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.item.SkirtCostume;
import com.fabriccommunity.spookytime.item.tool.ScytheItem;
import com.fabriccommunity.spookytime.item.tool.SpookiumMaterial;
import dev.emi.trinkets.api.TrinketSlots;

import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class SpookyItems {
	public static Item BLAZE_SKIRT;
	
	public static Item SPOOKIUM_INGOT;
	public static Item SPOOKIUM_NUGGET;
	
	public static ToolMaterial SPOOKIUM;
	public static Item REAPERS_SCYTHE;
	
	public static Item SOUL_BOTTLE;
	
	public static Item PUMPCOWN_SPAWN_EGG;
	
	private SpookyItems() {
		// NO-OP
	}
	
	public static void init() {
		TrinketSlots.addSubSlot("legs", "belt", new Identifier("trinkets", "textures/item/empty_trinket_slot_belt.png"));
		
		BLAZE_SKIRT = register("blaze_skirt", new SkirtCostume(new Item.Settings().group(ItemGroup.MISC).maxCount(1)));
		
		SPOOKIUM_INGOT = register("spookium_ingot", new Item(new Item.Settings().group(ItemGroup.MATERIALS).rarity(Rarity.EPIC)));
		SPOOKIUM_NUGGET = register("spookium_nugget", new Item(new Item.Settings().group(ItemGroup.MATERIALS).rarity(Rarity.EPIC)));
		
		SPOOKIUM = new SpookiumMaterial();
		REAPERS_SCYTHE = register("reapers_scythe", new ScytheItem(SPOOKIUM, 3, -2.0F, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).rarity(Rarity.EPIC)));
		
		SOUL_BOTTLE = register("soul_bottle", new Item(new Item.Settings().group(ItemGroup.MISC)));
		
		PUMPCOWN_SPAWN_EGG = register("pumpcown_spawn_egg", new SpawnEggItem(SpookyEntities.PUMPCOWN, 0x7E3D0E, 0xE38A1D, new Item.Settings().group(ItemGroup.MISC)));
	}
	
	static <T extends Item> T register(String name, T item) {
		return Registry.register(Registry.ITEM, SpookyTime.id(name), item);
	}
}
