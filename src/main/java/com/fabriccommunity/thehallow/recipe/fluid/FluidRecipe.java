package com.fabriccommunity.thehallow.recipe.fluid;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.BasicInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class FluidRecipe implements Recipe<BasicInventory> {
	private final List<Ingredient> ingredients;
	private final ItemStack result;
	private final Identifier recipeId;
	private final Type type;
	private final FluidRecipeSerializer serializer;

	FluidRecipe(List<Ingredient> ingredients, ItemStack result, Identifier recipeId, Type type, FluidRecipeSerializer serializer) {
		this.ingredients = ingredients;
		this.result = result;
		this.recipeId = recipeId;
		this.type = type;
		this.serializer = serializer;
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
		return getOutput().copy();
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
		return serializer;
	}

	@Override
	public RecipeType<?> getType() {
		return type;
	}

	public static class Type implements RecipeType<FluidRecipe> {
		public static final Type BLOOD = new Type();
		public static final Type WITCH_WATER = new Type();
		
		private Type() {
			//NO-OP
		}
	}
}
