package com.fabriccommunity.spookytime.recipe.witchwater;

import com.fabriccommunity.spookytime.SpookyTime;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WitchWaterRecipe implements Recipe<BasicInventory> {
	private final List<Ingredient> ingredients;
	private final ItemStack result;
	private final Identifier recipeId;

	WitchWaterRecipe(Ingredient ingredient, ItemStack result, Identifier recipeId) {
		this.ingredients = Collections.singletonList(ingredient);
		this.result = result;
		this.recipeId = recipeId;
	}

	WitchWaterRecipe(ItemStack result, Identifier recipeId, Ingredient... ingredients) {
		this.ingredients = Arrays.asList(ingredients);
		this.result = result;
		this.recipeId = recipeId;
	}

	WitchWaterRecipe(List<Ingredient> ingredients, ItemStack result, Identifier recipeId) {
		this.ingredients = ingredients;
		this.result = result;
		this.recipeId = recipeId;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	@Override
	public boolean matches(BasicInventory inventory, World world) {
		ArrayList<ItemStack> inventoryList = new ArrayList<>();
		for(int i = 0; i < inventory.getInvSize(); i++) {
			inventoryList.add(inventory.getInvStack(i));
		}

		return hasRequiredIngredients(inventoryList);
	}

	private boolean hasRequiredIngredients(List<ItemStack> toCheck) {
		for (Ingredient ingredient : ingredients) {
			boolean hasIngredient = false;
			for (ItemStack potentialIngredient : toCheck) {
				if (ingredient.method_8093(potentialIngredient)) {
					toCheck.remove(potentialIngredient);
					hasIngredient = true;
					break;
				}
			}

			if (!hasIngredient) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack craft(BasicInventory inventory) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean fits(int var1, int var2) {
		return false;
	}

	@Override
	public ItemStack getOutput() {
		return result;
	}

	@Override
	public Identifier getId() {
		return recipeId;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return WitchWaterRecipeSerializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}

	public static class Type implements RecipeType<WitchWaterRecipe> {
		public static final Type INSTANCE = new Type();
		public static final Identifier ID = SpookyTime.id("witch_water_recipe");

		private Type() {
			// NO-OP
		}
	}
}
