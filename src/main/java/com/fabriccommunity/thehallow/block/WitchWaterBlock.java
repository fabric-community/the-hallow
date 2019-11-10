package com.fabriccommunity.thehallow.block;

import com.fabriccommunity.thehallow.entity.PumpcownEntity;
import com.fabriccommunity.thehallow.recipe.fluid.FluidRecipe;
import com.fabriccommunity.thehallow.registry.HallowedEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class WitchWaterBlock extends CraftingFluidBlock {
	public WitchWaterBlock(BaseFluid fluid, Settings settings) {
		super(fluid, settings, FluidRecipe.Type.WITCH_WATER);
	}

	@Override
	public void onEntityCollision(BlockState blockState, World world, BlockPos pos, Entity entity) {
		if(!world.isClient) {
			if (entity instanceof LivingEntity) {
				LivingEntity livingEntity = (LivingEntity) entity;
				if (!livingEntity.isUndead() && !(livingEntity instanceof PumpcownEntity) && !(livingEntity instanceof WitchEntity)) {
					livingEntity.addPotionEffect(new StatusEffectInstance(StatusEffects.POISON, 100));
					livingEntity.addPotionEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100));
					livingEntity.addPotionEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 100));
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
					}
				}
			}
		}

		super.onEntityCollision(blockState, world, pos, entity);
	}
}
