package com.fabriccommunity.spookytime.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public abstract class CraftingFluidBlock extends FluidBlock {
	public final SoundEvent craftingSound;
	public HashMap<Ingredient, ItemStack> recipes = new HashMap<Ingredient, ItemStack>();
	
	public CraftingFluidBlock(BaseFluid fluid, Settings settings, SoundEvent craftingSound) {
		super(fluid, settings);
		this.craftingSound = craftingSound;
	}
	
	@Override
	public void onEntityCollision(BlockState blockState, World world, BlockPos pos, Entity entity) {
		super.onEntityCollision(blockState, world, pos, entity);
		if (pos.equals(entity.getBlockPos()) && entity instanceof ItemEntity && !((ItemEntity) entity).getStack().isEmpty()) {
			ItemEntity itemEntity = (ItemEntity) entity;
			ItemStack stack = itemEntity.getStack();
			for (Map.Entry<Ingredient, ItemStack> recipe : recipes.entrySet()) {
				if (recipe.getKey().test(stack)) {
					System.out.println("Found recipe:" + recipe.getKey().toJson().toString());
					System.out.println("Stack Input:" + stack.toString());
					ItemStack newStack = recipe.getValue().copy();
					System.out.println("Stack Output: " + newStack.toString());
					newStack.setCount(stack.getCount());
					newStack.setTag(stack.getTag());
					itemEntity.setStack(newStack);
					world.playSound(null, pos, craftingSound, SoundCategory.BLOCKS, world.random.nextFloat() * 0.5f + 0.5f, 0.75f + world.random.nextFloat() * 0.5f);
				}
			}
		}
	}
	
	public void addRecipe(ItemStack output, Ingredient input) {
		recipes.put(input, output);
	}
	
	public void addRecipe(ItemConvertible output, Ingredient input) {
		recipes.put(input, new ItemStack(output));
	}
	
	public void addRecipe(ItemStack output, ItemConvertible... inputs) {
		addRecipe(output, Ingredient.ofItems(inputs));
	}
	
	public void addRecipe(ItemStack output, ItemStack... inputs) {
		addRecipe(output, Ingredient.ofStacks(inputs));
	}
	
	public void addRecipe(ItemConvertible output, ItemConvertible... inputs) {
		addRecipe(output, Ingredient.ofItems(inputs));
	}
	
	public void addRecipe(ItemConvertible output, ItemStack... inputs) {
		addRecipe(output, Ingredient.ofStacks(inputs));
	}
}
