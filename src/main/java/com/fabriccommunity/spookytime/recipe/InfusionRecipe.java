package com.fabriccommunity.spookytime.recipe;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InfusionRecipe {
	public static final InfusionRecipe EMPTY = new InfusionRecipe(Collections.singletonList(null), null);

	List<ItemStack> ingredients;
	ItemStack output;

	public InfusionRecipe(List<ItemStack> ingredients, ItemStack output) {
		this.ingredients = ingredients;
		this.output = output;
	}

	public List<ItemStack> getIngredients() {
		return ingredients;
	}

	public ItemStack getOutput() {
		return output;
	}
}
