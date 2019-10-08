package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.recipe.InfusionRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;

import java.util.*;

public class SpookyInfusion {
	public static List<InfusionRecipe> INFUSION_RECIPES = new ArrayList<>();

	private SpookyInfusion() {
		// NO-OP
	}

	public static void init() {
		// NO-OP
	}

	private static InfusionRecipe register(ItemStack outputStack, Ingredient ingredient) {
		INFUSION_RECIPES.add(new InfusionRecipe(ingredient, outputStack));
		return INFUSION_RECIPES.get(INFUSION_RECIPES.size() - 1);
	}
}
