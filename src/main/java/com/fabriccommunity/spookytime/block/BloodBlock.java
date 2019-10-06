package com.fabriccommunity.spookytime.block;

import com.fabriccommunity.spookytime.recipe.BloodRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public class BloodBlock extends FluidBlock {
	public BloodBlock(BaseFluid fluid, Settings settings) {
		super(fluid, settings);
	}

	@Override
	public void onEntityCollision(BlockState blockState, World world, BlockPos pos, Entity entity) {
		if(!world.isClient) {
			List<ItemEntity> entities = world.getEntities(ItemEntity.class, new Box(pos));
			BasicInventory inventory = new BasicInventory(entities.size());

			entities.forEach(itemEntity -> {
				ItemStack stack = itemEntity.getStack();
				inventory.add(stack);
			});

			Optional<BloodRecipe> match = world.getRecipeManager()
				.getFirstMatch(BloodRecipe.Type.INSTANCE, inventory, world);

			if(match.isPresent()) {
				spawnCraftingResult(world, pos, match.get().getOutput());

				for(Ingredient ingredient : match.get().getIngredients()) {
					for(ItemEntity testEntity : entities) {
						if(ingredient.method_8093(testEntity.getStack())) {
							testEntity.getStack().decrement(1);
							break;
						}
					}
				}
			}
		}

		super.onEntityCollision(blockState, world, pos, entity);
	}

	private void spawnCraftingResult(World world, BlockPos pos, ItemStack result) {
		ItemEntity itemEntity = new ItemEntity(world, pos.getX() + .5, pos.getY() + 1, pos.getZ() + .5, result);
		world.spawnEntity(itemEntity);
		// todo: add particles and/or an animation when dropping the recipe result
	}
}
