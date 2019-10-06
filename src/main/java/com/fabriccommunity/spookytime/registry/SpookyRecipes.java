package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.recipe.BloodRecipe;
import com.fabriccommunity.spookytime.recipe.BloodRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.registry.Registry;

public class SpookyRecipes {
	public static final RecipeSerializer<BloodRecipe> BLOOD_RECIPE_SERIALIZER = Registry.register(
		Registry.RECIPE_SERIALIZER,
		BloodRecipeSerializer.ID,
		BloodRecipeSerializer.INSTANCE
	);

	public static final RecipeType<BloodRecipe> BLOOD_RECIPE = Registry.register(
		Registry.RECIPE_TYPE,
		BloodRecipe.Type.ID,
		BloodRecipe.Type.INSTANCE
	);

	public static void init() {
		// NO-OP
	}

	private SpookyRecipes() {
		// NO-OP
	}
}
