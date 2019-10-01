package com.fabriccommunity.spookytime;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.DataCache;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;
import net.minecraft.util.NumberRange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

public final class SpookyRecipeProvider implements DataProvider
{
	private static final Logger LOGGER = LogManager.getLogger();
	private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
	private final DataGenerator root;

	SpookyRecipeProvider(DataGenerator dataGenerator_1)
	{
		this.root = dataGenerator_1;
	}

	// Add actual recipes here!
	private void generate(Consumer<RecipeJsonProvider> consumer)
	{
		ShapedRecipeJsonFactory.create(Items.DIAMOND)
				.pattern("   ")
				.pattern("   ")
				.pattern("  b")
				.input('b', Items.DIAMOND)
				.criterion("has_diamond", hasItem(Items.DIAMOND))
				.offerTo(consumer, SpookyTime.id("example_recipe"));
	}

	@Override
	public String getName()
	{
		return "Spooky Recipe Provider";
	}

	@Override
	public void run(DataCache dataCache_1)
	{
		Path path_1 = this.root.getOutput();
		Set<Identifier> set_1 = Sets.newHashSet();
		this.generate((recipeJsonProvider_1) ->
		{
			if (!set_1.add(recipeJsonProvider_1.getRecipeId()))
			{
				throw new IllegalStateException("Duplicate recipe " + recipeJsonProvider_1.getRecipeId());
			} else
			{
				this.writeRecipeJson(dataCache_1, recipeJsonProvider_1.toJson(), path_1.resolve("data/" + recipeJsonProvider_1.getRecipeId().getNamespace() + "/recipes/" + recipeJsonProvider_1.getRecipeId().getPath() + ".json"));
				JsonObject jsonObject_1 = recipeJsonProvider_1.toAdvancementJson();
				if (jsonObject_1 != null)
				{
					this.generateProgressionAdvancements(dataCache_1, jsonObject_1, path_1.resolve("data/" + recipeJsonProvider_1.getRecipeId().getNamespace() + "/advancements/" + recipeJsonProvider_1.getAdvancementId().getPath() + ".json"));
				}

			}
		});
	}

	private void writeRecipeJson(DataCache dataCache_1, JsonObject jsonObject_1, Path path_1)
	{
		try
		{
			String string_1 = GSON.toJson(jsonObject_1);
			String string_2 = SHA1.hashUnencodedChars(string_1).toString();
			if (!Objects.equals(dataCache_1.getOldSha1(path_1), string_2) || !Files.exists(path_1))
			{
				Files.createDirectories(path_1.getParent());
				try (BufferedWriter bufferedWriter_1 = Files.newBufferedWriter(path_1))
				{
					bufferedWriter_1.write(string_1);
				}
			}

			dataCache_1.updateSha1(path_1, string_2);
		} catch (IOException var19)
		{
			LOGGER.error("Couldn't save recipe {}", path_1, var19);
		}

	}

	private void generateProgressionAdvancements(DataCache dataCache_1, JsonObject jsonObject_1, Path path_1)
	{
		try
		{
			String string_1 = GSON.toJson(jsonObject_1);
			String string_2 = SHA1.hashUnencodedChars(string_1).toString();
			if (!Objects.equals(dataCache_1.getOldSha1(path_1), string_2) || !Files.exists(path_1))
			{
				Files.createDirectories(path_1.getParent());
				try (BufferedWriter bufferedWriter_1 = Files.newBufferedWriter(path_1))
				{
					bufferedWriter_1.write(string_1);
				}
			}

			dataCache_1.updateSha1(path_1, string_2);
		} catch (IOException var19)
		{
			LOGGER.error("Couldn't save recipe advancement {}", path_1, var19);
		}

	}

	private CriterionConditions hasItem(ItemConvertible item)
	{
		return hasItem(item, 1);
	}

	private CriterionConditions hasItem(ItemConvertible item, int count)
	{
		return InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create().item(item).count(NumberRange.IntRange.atLeast(count)).build());
	}
}
