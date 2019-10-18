package com.fabriccommunity.spookytime.doomtree;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;

public class BasinWardingRecipeSerializer implements RecipeSerializer<BasinWardingRecipe> {
	@Override
	public BasinWardingRecipe read(Identifier identifier, JsonObject jsonObject) {
		String group = JsonHelper.getString(jsonObject, "group", "");
		Ingredient ingredient;
		if (JsonHelper.hasArray(jsonObject, "ingredient")) {
			ingredient = Ingredient.fromJson(JsonHelper.getArray(jsonObject, "ingredient"));
		} else {
			ingredient = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "ingredient"));
		}

		String result = JsonHelper.getString(jsonObject, "result");
		ItemStack itemStack = new ItemStack((ItemConvertible)Registry.ITEM.get(new Identifier(result)), 1);
		int cost = JsonHelper.getInt(jsonObject, "cost");
		return new BasinWardingRecipe(identifier, group, ingredient, cost, itemStack);
	}

	@Override
	public BasinWardingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
		String group = packetByteBuf.readString(32767);
		Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
		ItemStack result = packetByteBuf.readItemStack();
		int cost = packetByteBuf.readVarInt();
		return new BasinWardingRecipe(identifier, group, ingredient, cost, result);
	}

	@Override
	public void write(PacketByteBuf packetByteBuf, BasinWardingRecipe recipe) {
		packetByteBuf.writeString(recipe.group);
		recipe.ingredient.write(packetByteBuf);
		packetByteBuf.writeItemStack(recipe.result);
		packetByteBuf.writeVarInt(recipe.cost);
	}
}
