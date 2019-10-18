package com.fabriccommunity.spookytime.doomtree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.fabriccommunity.spookytime.SpookyTime;

import net.fabricmc.fabric.api.resource.ResourceReloadListenerKeys;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

public class BasinWardingRecipeHelper implements SimpleSynchronousResourceReloadListener {
	public static BasinWardingRecipeHelper INSTANCE = new BasinWardingRecipeHelper();

	static MinecraftServer server;

	private static final ArrayList<BasinWardingRecipe> recipes = new ArrayList<>();

	private static final Collection<Identifier> RELOAD_DEPS = Collections.singletonList(ResourceReloadListenerKeys.RECIPES);
	private static int idCounter = 0;

	private final Identifier id;

	private BasinWardingRecipeHelper() {
		this.id = SpookyTime.id("private/basin_recipes_" + (++idCounter));
	}

	@Override
	public Identifier getFabricId() {
		return id;
	}

	@Override
	public void apply(ResourceManager resourceManager) {
		recipes.clear();
		reload();
	}

	@Override
	public Collection<Identifier> getFabricDependencies() {
		return RELOAD_DEPS;
	}

	private static void reload() {
		if (server != null) {
			RecipeManager rm = server.getRecipeManager();

			for (Recipe<?> r : rm.values()) {
				if (r.getType() == DoomTree.WARDING_RECIPE_TYPE) {
					recipes.add((BasinWardingRecipe) r);
				}
			}
		}
	}

	public static void init(MinecraftServer serverIn) {
		server = serverIn;
		reload();
	}

	public static void stop(MinecraftServer serverIn) {
		server = null;
	}
	
	public static BasinWardingRecipe get(ItemStack stack) {
		if (recipes != null) {
			for (BasinWardingRecipe r : recipes) {
				if (r.matches(stack)) {
					return r;
				}
			}
		}
		
		return null;
	}
}
