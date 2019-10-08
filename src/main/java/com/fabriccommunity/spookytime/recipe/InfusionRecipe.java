package com.fabriccommunity.spookytime.recipe;

import com.fabriccommunity.spookytime.registry.SpookyInfusion;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;

public class InfusionRecipe {
	public static final InfusionRecipe EMPTY = new InfusionRecipe(null, null);

	Ingredient input;
	ItemStack output;

	public InfusionRecipe(Ingredient ingredients, ItemStack output) {
		this.input = ingredients;
		this.output = output;
	}

	public Ingredient getInput() {
		return input;
	}

	public ItemStack getOutput() {
		return output;
	}

	public static boolean match(Ingredient ingredientA, Ingredient ingredientB) {
		if (ingredientA.getStackArray().length != ingredientB.getStackArray().length) {
			return false;
		} else {
			boolean isEqual = true;
			for (int index = 0; index < ingredientA.getStackArray().length; ++index) {
				ItemStack stackA = ingredientA.getStackArray()[index];
				ItemStack stackB = ingredientA.getStackArray()[index];
				isEqual = stackA.getItem() == (stackB).getItem();
				isEqual = stackA.getCount() == stackB.getCount();
				isEqual = stackA.getTag() == stackB.getTag();
			}
			return isEqual;
		}
	}

	public static InfusionRecipe fromInput(Ingredient ingredients) {
		return SpookyInfusion.INFUSION_RECIPES.stream()
			.filter((recipe) -> match(recipe.getInput(), ingredients))
			.findAny().orElse(InfusionRecipe.EMPTY);
	}
}
