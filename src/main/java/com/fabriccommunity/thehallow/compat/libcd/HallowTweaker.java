package com.fabriccommunity.thehallow.compat.libcd;

import com.fabriccommunity.thehallow.recipe.fluid.FluidRecipe;
import com.fabriccommunity.thehallow.recipe.fluid.FluidRecipeSerializer;
import com.fabriccommunity.thehallow.recipe.infusion.InfusionRecipe;
import io.github.cottonmc.libcd.tweaker.RecipeParser;
import io.github.cottonmc.libcd.tweaker.RecipeTweaker;
import io.github.cottonmc.libcd.tweaker.Tweaker;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class HallowTweaker {
	public static final HallowTweaker INSTANCE = new HallowTweaker();
	private RecipeTweaker tweaker = RecipeTweaker.INSTANCE;

	private HallowTweaker() {
		//NO-OP
	}

	public void addBlood(Object[] input, Object output) {
		try {
			ItemStack stack = RecipeParser.processItemStack(output);
			Identifier id = tweaker.getRecipeId(stack);
			List<Ingredient> ingredients = new ArrayList<>();
			for (Object obj : input) {
				ingredients.add(RecipeParser.processIngredient(obj));
			}
			tweaker.addRecipe(new FluidRecipe(ingredients, stack, id, FluidRecipe.Type.BLOOD, FluidRecipeSerializer.BLOOD));
		} catch (Exception e) {
			tweaker.getLogger().error("Error parsing blood recipe - " + e.getMessage());
		}
	}

	public void addWitchWater(Object[] input, Object output) {
		try {
			ItemStack stack = RecipeParser.processItemStack(output);
			Identifier id = tweaker.getRecipeId(stack);
			List<Ingredient> ingredients = new ArrayList<>();
			for (Object obj : input) {
				ingredients.add(RecipeParser.processIngredient(obj));
			}
			tweaker.addRecipe(new FluidRecipe(ingredients, stack, id, FluidRecipe.Type.WITCH_WATER, FluidRecipeSerializer.WITCH_WATER));
		} catch (Exception e) {
			tweaker.getLogger().error("Error parsing witch water recipe - " + e.getMessage());
		}
	}

	public void addInfusion(Object target, Object input, Object output) {
		try {
			ItemStack stack = RecipeParser.processItemStack(output);
			Identifier id = tweaker.getRecipeId(stack);
			Ingredient targ = RecipeParser.processIngredient(target);
			Ingredient in = RecipeParser.processIngredient(input);
			tweaker.addRecipe(new InfusionRecipe(id, targ, in, stack));
		} catch (Exception e) {
			tweaker.getLogger().error("Error parsing hallowed infusion recipe - " + e.getMessage());
		}
	}

	public static void init() {
		Tweaker.addAssistant("HallowTweaker", INSTANCE);
	}
}
