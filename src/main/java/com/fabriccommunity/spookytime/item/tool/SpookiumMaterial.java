package com.fabriccommunity.spookytime.item.tool;

import com.fabriccommunity.spookytime.registry.SpookyItems;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class SpookiumMaterial implements ToolMaterial {
	@Override
	public int getDurability() {
		return 1024;
	}
	
	@Override
	public float getMiningSpeed() {
		return 7.0F;
	}
	
	@Override
	public float getAttackDamage() {
		return 3.5F;
	}
	
	@Override
	public int getMiningLevel() {
		return 3;
	}
	
	@Override
	public int getEnchantability() {
		return 20;
	}
	
	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(SpookyItems.SPOOKIUM_INGOT);
	}
}
