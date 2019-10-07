package com.fabriccommunity.spookytime.registry;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.enchantment.BeheadingEnchantment;
import com.fabriccommunity.spookytime.enchantment.LifestealEnchantment;
import com.fabriccommunity.spookytime.recipe.InfusionRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.*;
import java.util.function.Supplier;

public class SpookyInfusion {
	public static List<InfusionRecipe> INFUSION_RECIPES = new ArrayList<>();

	public static final InfusionRecipe TINY_PUMPKIN_INFUSION = register(new ItemStack(SpookyBlocks.WITCHED_PUMPKIN), new ItemStack(SpookyBlocks.TINY_PUMPKIN), new ItemStack(SpookyItems.BLOOD_BUCKET), new ItemStack(SpookyItems.WITCH_WATER_BUCKET));
	public static final InfusionRecipe PUMPKIN_INFUSION = register(new ItemStack(Items.PUMPKIN), new ItemStack(SpookyBlocks.TINY_PUMPKIN, 9));

	private SpookyInfusion() {
		// NO-OP
	}

	public static void init() {
		// NO-OP
	}

	public static boolean isEqual(List<ItemStack> listA, List<ItemStack> listB) {
		if (listA.size() != listB.size()) {
			return false;
		} else {
			boolean isEqual = true;
			for (int index = 0; index < listA.size(); ++index) {
				ItemStack stackA = listA.get(index);
				ItemStack stackB = listB.get(index);
				isEqual = stackA.getItem() == (stackB).getItem();
				isEqual = stackA.getCount() == stackB.getCount();
				isEqual = stackA.getTag() == stackB.getTag();
			}
			return isEqual;
		}
	}


	public static InfusionRecipe fromInput(List<ItemStack> supportedStacks) {
		return INFUSION_RECIPES.stream()
			.filter((recipe) -> isEqual(recipe.getIngredients(), supportedStacks))
			.findAny().orElse(InfusionRecipe.EMPTY);
	}

	private static InfusionRecipe register(ItemStack outputStack, ItemStack... supportedStacks) {
		INFUSION_RECIPES.add(new InfusionRecipe(Arrays.asList(supportedStacks), outputStack));
		return INFUSION_RECIPES.get(INFUSION_RECIPES.size() - 1);
	}
}
