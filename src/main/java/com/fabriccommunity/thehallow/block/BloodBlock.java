package com.fabriccommunity.thehallow.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.fabriccommunity.thehallow.recipe.fluid.FluidRecipe;

public class BloodBlock extends CraftingFluidBlock {
	public BloodBlock(BaseFluid fluid, Settings settings) {
		super(fluid, settings, FluidRecipe.Type.BLOOD);
	}

	@Override
	public void onEntityCollision(BlockState blockState, World world, BlockPos pos, Entity entity) {
		if(!world.isClient) {
			if (pos.equals(entity.getBlockPos())) {
				if (entity instanceof ItemEntity && !((ItemEntity) entity).getStack().isEmpty()) {
					ItemEntity itemEntity = (ItemEntity) entity;
					ItemStack stack = itemEntity.getStack();
					if (stack.getItem() instanceof DyeableItem) {
						DyeableItem item = (DyeableItem) stack.getItem();
						if (item.hasColor(stack)) item.setColor(stack, 0xFF0000 | item.getColor(stack));
						else item.setColor(stack, 0xFF0000);

						return;
					}
				}
			}
		}

		super.onEntityCollision(blockState, world, pos, entity);
	}
}
