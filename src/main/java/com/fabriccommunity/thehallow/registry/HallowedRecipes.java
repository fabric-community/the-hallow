package com.fabriccommunity.thehallow.registry;

import com.fabriccommunity.thehallow.recipe.fluid.FluidRecipe;
import com.fabriccommunity.thehallow.recipe.fluid.FluidRecipeSerializer;
import com.fabriccommunity.thehallow.recipe.infusion.InfusionRecipe;
import com.fabriccommunity.thehallow.recipe.infusion.InfusionRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HallowedRecipes {
	public static final RecipeSerializer<InfusionRecipe> INFUSION_RECIPE_SERIALIZER = Registry.register(
		Registry.RECIPE_SERIALIZER,
		InfusionRecipeSerializer.ID,
		InfusionRecipeSerializer.INSTANCE);

	public static final RecipeType INFUSION_RECIPE = Registry.register(
		Registry.RECIPE_TYPE,
		InfusionRecipe.Type.ID,
		InfusionRecipe.Type.INSTANCE);

	public static final RecipeSerializer<FluidRecipe> BLOOD_RECIPE_SERIALIZER = Registry.register(
		Registry.RECIPE_SERIALIZER,
		FluidRecipeSerializer.BLOOD.id,
		FluidRecipeSerializer.BLOOD
	);

	public static final RecipeType<FluidRecipe> BLOOD_RECIPE = Registry.register(
		Registry.RECIPE_TYPE,
		FluidRecipeSerializer.BLOOD.id,
		FluidRecipe.Type.BLOOD
	);

	public static final RecipeSerializer<FluidRecipe> WITCH_WATER_RECIPE_SERIALIZER = Registry.register(
		Registry.RECIPE_SERIALIZER,
		FluidRecipeSerializer.WITCH_WATER.id,
		FluidRecipeSerializer.WITCH_WATER
	);

	public static final RecipeType<FluidRecipe> WITCH_WATER_RECIPE = Registry.register(
		Registry.RECIPE_TYPE,
		FluidRecipeSerializer.WITCH_WATER.id,
		FluidRecipe.Type.WITCH_WATER
	);

	public static void init() {
		// NO-OP
	}

	private HallowedRecipes() {
		// NO-OP
	}
}
