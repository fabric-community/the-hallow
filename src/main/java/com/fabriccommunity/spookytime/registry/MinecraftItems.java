package com.fabriccommunity.spookytime.registry;

import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

/*
 * This registry class is used to overwrite existing registry entries, here vanilla items
 * First parameter of 'register()' method (int) is the index of the specified registry
 */

public class MinecraftItems {
	
	public static final Item PUMPKIN_PIE = register(776, "pumpkin_pie", new BlockItem(SpookyBlocks.PUMPKIN_PIE, new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.PUMPKIN_PIE)));
	
	private static Item register(int index, String name, Item item) {
		return Registry.register(Registry.ITEM, index, name, item);
	}
	
	public static void init() {
		// NO-OP
	}
}
