package com.fabriccommunity.spookytime.block;

import com.fabriccommunity.spookytime.entity.PumpcownEntity;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.fabriccommunity.spookytime.registry.SpookyEntities;
import net.minecraft.block.BlockState;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class WitchWaterBlock extends CraftingFluidBlock {
	public WitchWaterBlock(BaseFluid fluid, Settings settings) {
		super(fluid, settings, SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL);
		addRecipe(SpookyBlocks.DECEASED_DIRT, Items.DIRT);
		addRecipe(SpookyBlocks.DECEASED_GRASS_BLOCK, Items.GRASS_BLOCK);
		addRecipe(SpookyBlocks.TAINTED_SAND, Items.SAND, Items.RED_SAND);
		addRecipe(SpookyBlocks.TAINTED_GRAVEL, Items.GRAVEL);
		addRecipe(SpookyBlocks.TAINTED_GLASS, Items.GLASS);
		addRecipe(SpookyBlocks.TAINTED_GLASS_PANE, Items.GLASS_PANE);
		addRecipe(SpookyBlocks.TAINTED_SANDSTONE, Items.SANDSTONE, Items.RED_SANDSTONE);
		addRecipe(SpookyBlocks.SMOOTH_TAINTED_SANDSTONE, Items.SMOOTH_SANDSTONE, Items.SMOOTH_RED_SANDSTONE);
		addRecipe(SpookyBlocks.CUT_TAINTED_SANDSTONE, Items.CUT_SANDSTONE, Items.CUT_RED_SANDSTONE);
		addRecipe(SpookyBlocks.CHISELED_TAINTED_SANDSTONE, Items.CHISELED_SANDSTONE, Items.CHISELED_RED_SANDSTONE);
		addRecipe(SpookyBlocks.TAINTED_SANDSTONE_STAIRS, Items.SANDSTONE_STAIRS, Items.RED_SANDSTONE_STAIRS);
		addRecipe(SpookyBlocks.SMOOTH_TAINTED_SANDSTONE_STAIRS, Items.SMOOTH_SANDSTONE_STAIRS, Items.SMOOTH_RED_SANDSTONE_STAIRS);
		addRecipe(SpookyBlocks.TAINTED_SANDSTONE_SLAB, Items.SANDSTONE_SLAB, Items.RED_SANDSTONE_SLAB);
		addRecipe(SpookyBlocks.SMOOTH_TAINTED_SANDSTONE_SLAB, Items.SMOOTH_SANDSTONE_SLAB, Items.SMOOTH_RED_SANDSTONE_SLAB);
		addRecipe(SpookyBlocks.CUT_TAINTED_SANDSTONE_SLAB, Items.CUT_SANDSTONE_SLAB, Items.CUT_RED_SANDSTONE_SLAB);
		addRecipe(SpookyBlocks.TAINTED_SANDSTONE_WALL, Items.SANDSTONE_WALL, Items.RED_SANDSTONE_WALL);
		addRecipe(SpookyBlocks.WITCHED_PUMPKIN, SpookyBlocks.TINY_PUMPKIN);
	}

	@Override
	public void onEntityCollision(BlockState blockState, World world, BlockPos pos, Entity entity) {
		super.onEntityCollision(blockState, world, pos, entity);

		if (entity instanceof LivingEntity) {
			LivingEntity livingEntity = (LivingEntity) entity;
			if (!livingEntity.isUndead() && !(livingEntity instanceof PumpcownEntity)) {
				livingEntity.addPotionEffect(new StatusEffectInstance(StatusEffects.POISON, 20));
				livingEntity.addPotionEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20));
				livingEntity.addPotionEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 20));
			}
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
				PumpcownEntity pumpcownEntity = new PumpcownEntity(SpookyEntities.PUMPCOWN, world);
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
				}
			}
		}
	}
}
