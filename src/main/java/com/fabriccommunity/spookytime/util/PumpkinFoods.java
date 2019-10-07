package com.fabriccommunity.spookytime.util;

import net.minecraft.item.Item;

import java.util.ArrayList;

public class PumpkinFoods {
	private static final ArrayList<Item> validPumpkinFoods = new ArrayList<>();
	
	public static void registerPumpkinFood(Item item) {
		validPumpkinFoods.add(item);
	}
	
	public static boolean isItemPumpkin(Item item) {
		return validPumpkinFoods.contains(item);
	}
}
