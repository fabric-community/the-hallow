package com.fabriccommunity.spookytime.recipe;

import com.fabriccommunity.spookytime.inventory.InfusionInventory;
import com.google.gson.JsonArray;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.*;

public class InfusionRecipe implements Recipe<Inventory> {
	public static class Type implements RecipeType<InfusionRecipe> {
		private Type() {
			// NO-OP
		}
		public static final Type INSTANCE = new Type();

		public static final String ID = "infusion";
	}

	class InfusionRecipeFormat {
		JsonArray target;
		JsonArray input;
		JsonArray output;
	}

	public static final InfusionRecipe EMPTY = new InfusionRecipe(null, null, null, null);

	Identifier id;
	Ingredient target;
	Ingredient input;
	ItemStack output;


	public InfusionRecipe(Identifier id, Ingredient target, Ingredient input, Ingredient output) {
		this.id = id;
		this.target = target;
		this.input = input;
		this.output = output != null ? output.getStackArray()[0] : null;
	}

	public boolean isMatch(InfusionInventory infusionInventory) {
		if (infusionInventory.target == null) {
			return false;
		}
		if (infusionInventory.target.getItem() != target.getStackArray()[0].getItem()
		||  infusionInventory.target.getCount() != target.getStackArray()[0].getCount()
		||  infusionInventory.target.getTag() != target.getStackArray()[0].getTag()) {
			return false;
		}
		for (int i = 0; i < infusionInventory.input.length; ++i) {
			if (!infusionInventory.input[i].isEmpty()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean matches(Inventory inputInventory, World inputWorld) {
		InfusionInventory infusionInventory = (InfusionInventory) inputInventory;
		if (isMatch(infusionInventory)) {
			List<ItemStack> treeA = new ArrayList<>();
			List<ItemStack> treeB = new ArrayList<>();

			treeA.addAll(Arrays.asList(input.getStackArray()));

			for (int i = 0; i < infusionInventory.input.length; ++i) {
				if (!infusionInventory.input[i].isEmpty()) {
					treeB.add(infusionInventory.input[i]);
				}
			}

			treeA.sort((stackA, stackB) -> stackA.getItem().getTranslationKey().compareTo(stackB.getTranslationKey()));
			treeB.sort((stackA, stackB) -> stackA.getItem().getTranslationKey().compareTo(stackB.getTranslationKey()));

			Iterator<ItemStack> iteratorA = treeA.iterator();
			Iterator<ItemStack> iteratorB = treeB.iterator();

			while (iteratorA.hasNext() && iteratorB.hasNext()) {
				ItemStack stackA = iteratorA.next();
				ItemStack stackB = iteratorB.next();

				if (stackA.getItem() != (stackB).getItem()) {
					return false;
				}
				if (stackA.getCount() != (stackB).getCount()) {
					return false;
				}
				if (stackA.getTag() != (stackB).getTag()) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ItemStack craft(Inventory inputInventory) {
		if (matches(inputInventory, null)) {
			inputInventory.clear();
			return getOutput();
		} else {
			return ItemStack.EMPTY;
		}
	}

	@Override
	public boolean fits(int var1, int var2) {
		return true;
	}

	@Override
	public Identifier getId() {
		return id;
	}

	public Ingredient getTarget() {
		return target;
	}

	public Ingredient getInput() {
		return input;
	}

	public Ingredient getResult() {
		return Ingredient.ofStacks(this.getOutput());
	}

	@Override
	public ItemStack getOutput() {
		return output.copy();
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return InfusionRecipeSerializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}
}
