package com.fabriccommunity.thehallow.inventory;

import net.minecraft.inventory.BasicInventory;
import net.minecraft.item.ItemStack;

public class InfusionInventory extends BasicInventory {
	public ItemStack target;
	public ItemStack[] input;
	
	public InfusionInventory(ItemStack target, ItemStack... input) {
		this.target = target;
		this.input = input;
	}
}
