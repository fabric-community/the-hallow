package com.fabriccommunity.spookytime.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;

public class BloodBlock extends FluidBlock {
	public static HashMap<Item, ItemConvertible> recipes = new HashMap<>();

	public BloodBlock(BaseFluid fluid, Settings settings) {
		super(fluid, settings);
	}

	static {

	}
	
	public void onEntityCollision(BlockState blockState, World world, BlockPos blockPos, Entity entity) {
		if (blockPos.equals(entity.getBlockPos())) {
			if (entity instanceof ItemEntity && !((ItemEntity) entity).getStack().isEmpty()) {
				ItemEntity itemEntity = (ItemEntity) entity;
				ItemStack stack = itemEntity.getStack();
				if(stack.getItem() == Items.PUMPKIN) {
					// Spawn stack.getCount() pumpklings
				} else if(recipes.containsKey(stack.getItem())) {
					ItemStack newStack = new ItemStack(recipes.get(stack.getItem()), stack.getCount());
					itemEntity.setStack(newStack);
				}
			}
		}
	}
}
