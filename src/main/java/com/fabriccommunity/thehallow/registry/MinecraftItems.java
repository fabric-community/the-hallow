package com.fabriccommunity.thehallow.registry;

import com.fabriccommunity.thehallow.HallowedConfig;

import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;

/*
 * This registry class is used to overwrite existing registry entries, here vanilla items
 * First parameter of 'register()' method (int) is the raw id in the specified registry
 */

public class MinecraftItems {
	
	public static Item PUMPKIN_PIE;
	
	public static void init() {
		if (HallowedConfig.Tweaks.pumpkinPieBlock) {
			PUMPKIN_PIE = register(Item.getRawId(Items.PUMPKIN_PIE), "pumpkin_pie", new BlockItem(HallowedBlocks.PUMPKIN_PIE, new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.PUMPKIN_PIE)));
		}
	}
	
	private static Item register(int rawId, String name, Item item) {
		return Registry.register(Registry.ITEM, rawId, name, item);
	}
}
