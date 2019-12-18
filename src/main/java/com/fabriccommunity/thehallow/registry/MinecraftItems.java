package com.fabriccommunity.thehallow.registry;

import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;

import com.fabriccommunity.thehallow.HallowedConfig;

/**
 * This registry class is used to overwrite existing registry entries, here vanilla items
 */
public class MinecraftItems {
	public static void init() {
		if (HallowedConfig.Tweaks.pumpkinPieBlock) {
			replaceEntry(Registry.ITEM, Items.PUMPKIN_PIE, new BlockItem(HallowedBlocks.PUMPKIN_PIE, new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.PUMPKIN_PIE)));
		}
	}
	
	private static <T> T replaceEntry(MutableRegistry<T> registry, T oldEntry, T newEntry) {
		return registry.set(registry.getRawId(oldEntry), registry.getId(oldEntry), newEntry);
	}
}
