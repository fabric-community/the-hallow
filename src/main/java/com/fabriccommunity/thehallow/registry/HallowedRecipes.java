package com.fabriccommunity.thehallow.registry;

import com.fabriccommunity.thehallow.recipe.blood.BloodRecipe;
import com.fabriccommunity.thehallow.recipe.blood.BloodRecipeSerializer;
import com.fabriccommunity.thehallow.recipe.infusion.InfusionRecipe;
import com.fabriccommunity.thehallow.recipe.infusion.InfusionRecipeSerializer;
import com.fabriccommunity.thehallow.recipe.witchwater.WitchWaterRecipe;
import com.fabriccommunity.thehallow.recipe.witchwater.WitchWaterRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.registry.Registry;

public class HallowedRecipes {
	public static final RecipeSerializer<InfusionRecipe> INFUSION_RECIPE_SERIALIZER = Registry.register(
		Registry.RECIPE_SERIALIZER,
		InfusionRecipeSerializer.ID,
		InfusionRecipeSerializer.INSTANCE);

	public static final RecipeType<InfusionRecipe> INFUSION_RECIPE = Registry.register(
		Registry.RECIPE_TYPE,
		InfusionRecipe.Type.ID,
		InfusionRecipe.Type.INSTANCE);

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

	public static final RecipeSerializer<WitchWaterRecipe> WITCH_WATER_RECIPE_SERIALIZER = Registry.register(
		Registry.RECIPE_SERIALIZER,
		WitchWaterRecipeSerializer.ID,
		WitchWaterRecipeSerializer.INSTANCE
	);

	public static final RecipeType<WitchWaterRecipe> WITCH_WATER_RECIPE = Registry.register(
		Registry.RECIPE_TYPE,
		WitchWaterRecipe.Type.ID,
		WitchWaterRecipe.Type.INSTANCE
	);

	public static void init() {
		// NO-OP
	}

	private HallowedRecipes() {
		// NO-OP
	}
}
