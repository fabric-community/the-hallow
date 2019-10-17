package com.fabriccommunity.thehallow.registry;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import com.fabriccommunity.thehallow.recipe.InfusionRecipe;
import com.fabriccommunity.thehallow.recipe.InfusionRecipeSerializer;

public class HallowedInfusion {
	public static final RecipeSerializer INFUSION_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, InfusionRecipeSerializer.ID, InfusionRecipeSerializer.INSTANCE);
	public static final RecipeType INFUSION_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier("infusion", InfusionRecipe.Type.ID), InfusionRecipe.Type.INSTANCE);
	
	private HallowedInfusion() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
}
