package com.fabriccommunity.spookytime.doomtree;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class BasinWardingRecipe implements Recipe<Inventory> {
	public final Ingredient ingredient;
	public final ItemStack result;
	public final Identifier id;
	public final String group;
	public final int cost;

	public BasinWardingRecipe(Identifier id, String group, Ingredient ingredient, int cost, ItemStack result) {
		this.id = id;
		this.group = group;
		this.ingredient = ingredient;
		this.result = result;
		this.cost = cost;
	}

	@Override
	public RecipeType<?> getType() {
		return DoomTree.WARDING_RECIPE_TYPE;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return DoomTree.WARDING_RECIPE_SERIALIZER;
	}

	@Override
	public Identifier getId() {
		return id;
	}
	
	@Override
	public boolean matches(Inventory inventory, World world) {
		return ingredient.method_8093(inventory.getInvStack(0));
	}

	public boolean matches(ItemStack stack) {
		return ingredient.method_8093(stack);
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public ItemStack getRecipeKindIcon() {
		return new ItemStack(DoomTree.ALCHEMICAL_BASIN_BLOCK);
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public String getGroup() {
		return group;
	}

	@Override
	public ItemStack getOutput() {
		return result;
	}

	@Override
	public DefaultedList<Ingredient> getPreviewInputs() {
		DefaultedList<Ingredient> defaultedList = DefaultedList.of();
		defaultedList.add(this.ingredient);
		return defaultedList;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public boolean fits(int width, int height) {
		return true;
	}

	@Override
	public ItemStack craft(Inventory inventory) {
		return result.copy();
	}
}