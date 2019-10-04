package com.fabriccommunity.spookytime.block;

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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WitchWaterBlock extends FluidBlock {
    public WitchWaterBlock(BaseFluid fluid, Settings settings) {
        super(fluid, settings);
    }

    public void onEntityCollision(BlockState blockState, World world, BlockPos blockPos, Entity entity) {
        if (entity instanceof PlayerEntity) {
            ((LivingEntity) entity).addPotionEffect(new StatusEffectInstance(StatusEffects.POISON, 20));
			((LivingEntity) entity).addPotionEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20));
			((LivingEntity) entity).addPotionEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 20));
        }

		if (blockPos.equals(entity.getBlockPos())) {
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
			} else if (entity.getType() == EntityType.VILLAGER) {
				WitchEntity witchEntity = new WitchEntity(EntityType.WITCH, world);
				witchEntity.copyPositionAndRotation(entity);
				entity.remove();

				world.spawnEntity(witchEntity);
			} else if (entity instanceof ItemEntity && !((ItemEntity) entity).getStack().isEmpty() && ((ItemEntity) entity).getStack().getItem() == Items.PUMPKIN) {
				//Unimplemented
			}
		}
    }
}
