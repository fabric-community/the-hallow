package com.fabriccommunity.thehallow.block;

import com.fabriccommunity.spookytime.entity.PumpcownEntity;
import com.fabriccommunity.spookytime.recipe.blood.BloodRecipe;
import com.fabriccommunity.spookytime.recipe.witchwater.WitchWaterRecipe;
import com.fabriccommunity.spookytime.registry.SpookyEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

<<<<<<< HEAD:src/main/java/com/fabriccommunity/thehallow/block/WitchWaterBlock.java
import com.fabriccommunity.thehallow.entity.PumpcownEntity;
import com.fabriccommunity.thehallow.registry.HallowedBlocks;
import com.fabriccommunity.thehallow.registry.HallowedEntities;
=======
import java.util.List;
import java.util.Optional;
>>>>>>> 81f864bf03a717f4b7530172ac5d0dfa9d0ba81d:src/main/java/com/fabriccommunity/spookytime/block/WitchWaterBlock.java

public class WitchWaterBlock extends FluidBlock {
	public WitchWaterBlock(BaseFluid fluid, Settings settings) {
<<<<<<< HEAD:src/main/java/com/fabriccommunity/thehallow/block/WitchWaterBlock.java
		super(fluid, settings, SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL);
		addRecipe(HallowedBlocks.DECEASED_DIRT, Items.DIRT);
		addRecipe(HallowedBlocks.DECEASED_GRASS_BLOCK, Items.GRASS_BLOCK);
		addRecipe(HallowedBlocks.TAINTED_SAND, Items.SAND, Items.RED_SAND);
		addRecipe(HallowedBlocks.TAINTED_GRAVEL, Items.GRAVEL);
		addRecipe(HallowedBlocks.TAINTED_GLASS, Items.GLASS);
		addRecipe(HallowedBlocks.TAINTED_GLASS_PANE, Items.GLASS_PANE);
		addRecipe(HallowedBlocks.TAINTED_SANDSTONE, Items.SANDSTONE, Items.RED_SANDSTONE);
		addRecipe(HallowedBlocks.SMOOTH_TAINTED_SANDSTONE, Items.SMOOTH_SANDSTONE, Items.SMOOTH_RED_SANDSTONE);
		addRecipe(HallowedBlocks.CUT_TAINTED_SANDSTONE, Items.CUT_SANDSTONE, Items.CUT_RED_SANDSTONE);
		addRecipe(HallowedBlocks.CHISELED_TAINTED_SANDSTONE, Items.CHISELED_SANDSTONE, Items.CHISELED_RED_SANDSTONE);
		addRecipe(HallowedBlocks.TAINTED_SANDSTONE_STAIRS, Items.SANDSTONE_STAIRS, Items.RED_SANDSTONE_STAIRS);
		addRecipe(HallowedBlocks.SMOOTH_TAINTED_SANDSTONE_STAIRS, Items.SMOOTH_SANDSTONE_STAIRS, Items.SMOOTH_RED_SANDSTONE_STAIRS);
		addRecipe(HallowedBlocks.TAINTED_SANDSTONE_SLAB, Items.SANDSTONE_SLAB, Items.RED_SANDSTONE_SLAB);
		addRecipe(HallowedBlocks.SMOOTH_TAINTED_SANDSTONE_SLAB, Items.SMOOTH_SANDSTONE_SLAB, Items.SMOOTH_RED_SANDSTONE_SLAB);
		addRecipe(HallowedBlocks.CUT_TAINTED_SANDSTONE_SLAB, Items.CUT_SANDSTONE_SLAB, Items.CUT_RED_SANDSTONE_SLAB);
		addRecipe(HallowedBlocks.TAINTED_SANDSTONE_WALL, Items.SANDSTONE_WALL, Items.RED_SANDSTONE_WALL);
		addRecipe(HallowedBlocks.WITCHED_PUMPKIN, HallowedBlocks.TINY_PUMPKIN);
=======
		super(fluid, settings);
>>>>>>> 81f864bf03a717f4b7530172ac5d0dfa9d0ba81d:src/main/java/com/fabriccommunity/spookytime/block/WitchWaterBlock.java
	}

	@Override
	public void onEntityCollision(BlockState blockState, World world, BlockPos pos, Entity entity) {
		if(!world.isClient) {
			if (entity instanceof LivingEntity) {
				LivingEntity livingEntity = (LivingEntity) entity;
				if (!livingEntity.isUndead() && !(livingEntity instanceof PumpcownEntity)) {
					livingEntity.addPotionEffect(new StatusEffectInstance(StatusEffects.POISON, 20));
					livingEntity.addPotionEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20));
					livingEntity.addPotionEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 20));
				}
			}
<<<<<<< HEAD:src/main/java/com/fabriccommunity/thehallow/block/WitchWaterBlock.java
		}
		
		if (pos.equals(entity.getBlockPos())) {
			if (entity.getType() == EntityType.SKELETON) {
				WitherSkeletonEntity witherSkeletonEntity = new WitherSkeletonEntity(EntityType.WITHER_SKELETON, world);
				witherSkeletonEntity.copyPositionAndRotation(entity);
				entity.remove();
				
				world.spawnEntity(witherSkeletonEntity);
			} else if (entity.getType() == EntityType.SPIDER) {
				CaveSpiderEntity caveSpiderEntity = new CaveSpiderEntity(EntityType.CAVE_SPIDER, world);
				caveSpiderEntity.copyPositionAndRotation(entity);
				entity.remove();
				
				world.spawnEntity(caveSpiderEntity);
			} else if (entity.getType() == EntityType.COW) {
				PumpcownEntity pumpcownEntity = new PumpcownEntity(HallowedEntities.PUMPCOWN, world);
				pumpcownEntity.copyPositionAndRotation(entity);
				entity.remove();
				
				world.spawnEntity(pumpcownEntity);
			} else if (entity.getType() == EntityType.VILLAGER && world.getDifficulty() != Difficulty.PEACEFUL) {
				WitchEntity witchEntity = new WitchEntity(EntityType.WITCH, world);
				witchEntity.copyPositionAndRotation(entity);
				entity.remove();
				
				world.spawnEntity(witchEntity);
			} else if (entity instanceof ItemEntity && !((ItemEntity) entity).getStack().isEmpty()) {
				ItemEntity itemEntity = (ItemEntity) entity;
				ItemStack stack = itemEntity.getStack();
				if (stack.getItem() == Items.PUMPKIN) {
					// Spawn stack.getCount() pumpklings
=======

			if(entity instanceof ItemEntity) {
				List<ItemEntity> entities = world.getEntities(ItemEntity.class, new Box(pos));
				BasicInventory inventory = new BasicInventory(entities.size());

				entities.forEach(itemEntity -> {
					ItemStack stack = itemEntity.getStack();
					inventory.add(stack);
				});

				Optional<WitchWaterRecipe> match = world.getRecipeManager()
					.getFirstMatch(WitchWaterRecipe.Type.INSTANCE, inventory, world);

				if (match.isPresent()) {
					spawnCraftingResult(world, pos, match.get().getOutput());

					for (Ingredient ingredient : match.get().getIngredients()) {
						for (ItemEntity testEntity : entities) {
							if (ingredient.method_8093(testEntity.getStack())) {
								testEntity.getStack().decrement(1);
								break;
							}
						}
					}
				}
			} else {
				if (pos.equals(entity.getBlockPos())) {
					if (entity.getType() == EntityType.SKELETON) {
						WitherSkeletonEntity witherSkeletonEntity = new WitherSkeletonEntity(EntityType.WITHER_SKELETON, world);
						witherSkeletonEntity.copyPositionAndRotation(entity);
						entity.remove();

						world.spawnEntity(witherSkeletonEntity);
					} else if (entity.getType() == EntityType.SPIDER) {
						CaveSpiderEntity caveSpiderEntity = new CaveSpiderEntity(EntityType.CAVE_SPIDER, world);
						caveSpiderEntity.copyPositionAndRotation(entity);
						entity.remove();

						world.spawnEntity(caveSpiderEntity);
					} else if (entity.getType() == EntityType.COW) {
						PumpcownEntity pumpcownEntity = new PumpcownEntity(SpookyEntities.PUMPCOWN, world);
						pumpcownEntity.copyPositionAndRotation(entity);
						entity.remove();

						world.spawnEntity(pumpcownEntity);
					} else if (entity.getType() == EntityType.VILLAGER && world.getDifficulty() != Difficulty.PEACEFUL) {
						WitchEntity witchEntity = new WitchEntity(EntityType.WITCH, world);
						witchEntity.copyPositionAndRotation(entity);
						entity.remove();

						world.spawnEntity(witchEntity);
					}
>>>>>>> 81f864bf03a717f4b7530172ac5d0dfa9d0ba81d:src/main/java/com/fabriccommunity/spookytime/block/WitchWaterBlock.java
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
