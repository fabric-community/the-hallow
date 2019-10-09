package com.fabriccommunity.spookytime.inventory;

import net.minecraft.inventory.BasicInventory;
import net.minecraft.item.ItemStack;

public class InfusionInventory extends BasicInventory {
	ItemStack target;
	ItemStack[] input;

	public InfusionInventory(ItemStack target, ItemStack... input) {
		this.target = target;
		this.input = input;
	}
}
