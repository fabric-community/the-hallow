package com.fabriccommunity.spookytime.recipe;

import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class InfusionRecipeSerializer implements RecipeSerializer<InfusionRecipe> {
	public static final InfusionRecipeSerializer INSTANCE = new InfusionRecipeSerializer();
	public static final Identifier ID = new Identifier("spookytime:infusion");

	private InfusionRecipeSerializer() {
		// NO-OP
	}

	public static Ingredient fromJson(@Nullable JsonElement jsonElement) {
		List<ItemStack> arrayStacks = new ArrayList<ItemStack>();
		JsonArray jsonArray = jsonElement.getAsJsonArray();
		jsonArray.forEach((element) -> {
			JsonObject inputObject = element.getAsJsonObject();
			if (inputObject.size() == 1) {
				arrayStacks.add(new ItemStack(Registry.ITEM.getOrEmpty(new Identifier(inputObject.get("item").getAsString())).get()));
			} else {
				arrayStacks.add(new ItemStack(Registry.ITEM.getOrEmpty(new Identifier(inputObject.get("item").getAsString())).get(), inputObject.get("count").getAsInt()));
			}
		});
		return Ingredient.ofStacks(Iterables.toArray(arrayStacks, ItemStack.class));
	}

	@Override
	public InfusionRecipe read(Identifier ID, JsonObject json) {
		InfusionRecipe.InfusionRecipeFormat recipe = new Gson().fromJson(json, InfusionRecipe.InfusionRecipeFormat.class);
		return new InfusionRecipe(ID, fromJson(recipe.target), fromJson(recipe.input), fromJson(recipe.output));
	}

	@Override
	public void write(PacketByteBuf buffer, InfusionRecipe recipe) {
		recipe.getTarget().write(buffer);
		recipe.getInput().write(buffer);
		recipe.getResult().write(buffer);
	}

	@Override
	public InfusionRecipe read(Identifier ID, PacketByteBuf buffer) {
		return new InfusionRecipe(ID, Ingredient.fromPacket(buffer), Ingredient.fromPacket(buffer), Ingredient.fromPacket(buffer));
	}
}
