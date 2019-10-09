package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.recipe.InfusionRecipe;
import com.fabriccommunity.spookytime.recipe.InfusionRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpookyInfusion {
	public static final RecipeSerializer INFUSION_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, InfusionRecipeSerializer.ID, InfusionRecipeSerializer.INSTANCE);
	public static final RecipeType INFUSION_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier("infusion", InfusionRecipe.Type.ID), InfusionRecipe.Type.INSTANCE);

	private SpookyInfusion() {
		// NO-OP
	}

	public static void init() {
		// NO-OP
	}
}
