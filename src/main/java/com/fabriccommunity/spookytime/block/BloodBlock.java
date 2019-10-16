package com.fabriccommunity.spookytime.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BloodBlock extends CraftingFluidBlock {
	public BloodBlock(BaseFluid fluid, Settings settings) {
		super(fluid, settings, SoundEvents.ENTITY_PLAYER_SPLASH);
		addRecipe(Items.RED_BANNER, Items.WHITE_BANNER);
		addRecipe(Items.RED_BED, Items.WHITE_BED);
		addRecipe(Items.RED_CARPET, Items.WHITE_CARPET);
		addRecipe(Items.RED_CONCRETE, Items.WHITE_CONCRETE);
		addRecipe(Items.RED_CONCRETE_POWDER, Items.WHITE_CONCRETE_POWDER);
		addRecipe(Items.RED_SHULKER_BOX, Items.SHULKER_BOX);
		addRecipe(Items.RED_STAINED_GLASS, Items.GLASS);
		addRecipe(Items.RED_STAINED_GLASS_PANE, Items.GLASS_PANE);
		addRecipe(Items.RED_WOOL, Items.WHITE_WOOL);
		addRecipe(Items.RED_SAND, Items.SAND);
		addRecipe(Items.RED_SANDSTONE, Items.SANDSTONE);
		addRecipe(Items.SMOOTH_RED_SANDSTONE, Items.SMOOTH_SANDSTONE);
		addRecipe(Items.CUT_RED_SANDSTONE, Items.CUT_SANDSTONE);
		addRecipe(Items.CHISELED_RED_SANDSTONE, Items.CHISELED_SANDSTONE);
		addRecipe(Items.RED_SANDSTONE_STAIRS, Items.SANDSTONE_STAIRS);
		addRecipe(Items.SMOOTH_RED_SANDSTONE_STAIRS, Items.SMOOTH_SANDSTONE_STAIRS);
		addRecipe(Items.RED_SANDSTONE_SLAB, Items.SANDSTONE_SLAB);
		addRecipe(Items.SMOOTH_RED_SANDSTONE_SLAB, Items.SMOOTH_SANDSTONE_SLAB);
		addRecipe(Items.CUT_RED_SANDSTONE_SLAB, Items.CUT_SANDSTONE_SLAB);
		addRecipe(Items.RED_SANDSTONE_WALL, Items.SANDSTONE_WALL);
		addRecipe(Items.RED_TULIP, Items.WHITE_TULIP);
		addRecipe(Items.RED_NETHER_BRICKS, Items.NETHER_BRICKS);
		addRecipe(Items.RED_NETHER_BRICK_SLAB, Items.NETHER_BRICK_SLAB);
		addRecipe(Items.RED_NETHER_BRICK_STAIRS, Items.NETHER_BRICK_STAIRS);
		addRecipe(Items.RED_NETHER_BRICK_WALL, Items.NETHER_BRICK_WALL);
	}
	
	@Override
	public void onEntityCollision(BlockState blockState, World world, BlockPos pos, Entity entity) {
		super.onEntityCollision(blockState, world, pos, entity);
		
		if (pos.equals(entity.getBlockPos())) {
			if (entity instanceof ItemEntity && !((ItemEntity) entity).getStack().isEmpty()) {
				ItemEntity itemEntity = (ItemEntity) entity;
				ItemStack stack = itemEntity.getStack();
				if (stack.getItem() instanceof DyeableItem) {
					DyeableItem item = (DyeableItem) stack.getItem();
					if (item.hasColor(stack)) item.setColor(stack, 0xFF0000 | item.getColor(stack));
					else item.setColor(stack, 0xFF0000);
				}
			}
		}
	}
}
