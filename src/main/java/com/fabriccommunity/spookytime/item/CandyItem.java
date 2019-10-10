package com.fabriccommunity.spookytime.item;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;

public class CandyItem extends Item {
	public CandyItem(Settings settings, int hunger, float saturation) {
		super(settings.food((new FoodComponent.Builder()).hunger(hunger).saturationModifier(saturation).build()));
	}
}
