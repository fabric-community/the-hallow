package com.fabriccommunity.thehallow.block;

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

import com.fabriccommunity.thehallow.recipe.fluid.FluidRecipe;

import java.util.List;
import java.util.Optional;

public abstract class CraftingFluidBlock extends FluidBlock {
	public final FluidRecipe.Type recipeType;
	
	public CraftingFluidBlock(BaseFluid fluid, Settings settings, FluidRecipe.Type recipeType) {
		super(fluid, settings);
		this.recipeType = recipeType;
	}
	
	@Override
	public void onEntityCollision(BlockState blockState, World world, BlockPos pos, Entity entity) {
		super.onEntityCollision(blockState, world, pos, entity);
		if(entity instanceof ItemEntity) {
			List<ItemEntity> entities = world.getEntities(ItemEntity.class, new Box(pos), e -> true);
			BasicInventory inventory = new BasicInventory(entities.size());
			
			entities.forEach(itemEntity -> { //required for multi-input recipes
				ItemStack stack = itemEntity.getStack();
				inventory.add(stack);
			});
			
			Optional<FluidRecipe> match = world.getRecipeManager()
				.getFirstMatch(recipeType, inventory, world);

			if (match.isPresent()) {
				spawnCraftingResult(world, pos, match.get().craft(inventory));

				for (Ingredient ingredient : match.get().getIngredients()) {
					for (ItemEntity testEntity : entities) {
						if (ingredient.test(testEntity.getStack())) {
							testEntity.getStack().decrement(1);
							break;
						}
					}
				}
			}
		}
	}
	
	private void spawnCraftingResult(World world, BlockPos pos, ItemStack result) {
		ItemEntity itemEntity = new ItemEntity(world, pos.getX() + .5, pos.getY() + 1, pos.getZ() + .5, result);
		world.spawnEntity(itemEntity);
		// todo: add particles and/or an animation when dropping the recipe result
	}
}
