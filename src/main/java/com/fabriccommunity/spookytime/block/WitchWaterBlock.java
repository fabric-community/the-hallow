package com.fabriccommunity.spookytime.block;

import com.fabriccommunity.spookytime.entity.PumpcownEntity;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.fabriccommunity.spookytime.registry.SpookyEntities;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

import java.util.HashMap;

public class WitchWaterBlock extends FluidBlock {
	public static HashMap<Item, ItemConvertible> recipes = new HashMap<>();
	
	public WitchWaterBlock(BaseFluid fluid, Settings settings) {
		super(fluid, settings);
	}
	
	static {
		recipes.put(Items.DIRT, SpookyBlocks.DECEASED_DIRT);
		recipes.put(Items.GRASS_BLOCK, SpookyBlocks.DECEASED_GRASS_BLOCK);
		recipes.put(Items.SAND, SpookyBlocks.TAINTED_SAND);
		recipes.put(Items.GRAVEL, SpookyBlocks.TAINTED_GRAVEL);
		recipes.put(Items.GLASS, SpookyBlocks.TAINTED_GLASS);
		recipes.put(Items.GLASS_PANE, SpookyBlocks.TAINTED_GLASS_PANE);
		recipes.put(Items.SANDSTONE, SpookyBlocks.TAINTED_SANDSTONE);
		recipes.put(Items.SMOOTH_SANDSTONE, SpookyBlocks.SMOOTH_TAINTED_SANDSTONE);
		recipes.put(Items.CUT_SANDSTONE, SpookyBlocks.CUT_TAINTED_SANDSTONE);
		recipes.put(Items.CHISELED_SANDSTONE, SpookyBlocks.CHISELED_TAINTED_SANDSTONE);
		recipes.put(Items.SANDSTONE_STAIRS, SpookyBlocks.TAINTED_SANDSTONE_STAIRS);
		recipes.put(Items.SMOOTH_SANDSTONE_STAIRS, SpookyBlocks.SMOOTH_TAINTED_SANDSTONE_STAIRS);
		recipes.put(Items.SANDSTONE_SLAB, SpookyBlocks.TAINTED_SANDSTONE_SLAB);
		recipes.put(Items.SMOOTH_SANDSTONE_SLAB, SpookyBlocks.SMOOTH_TAINTED_SANDSTONE_SLAB);
		recipes.put(Items.CUT_SANDSTONE_SLAB, SpookyBlocks.CUT_TAINTED_SANDSTONE_SLAB);
		recipes.put(Items.SANDSTONE_WALL, SpookyBlocks.TAINTED_SANDSTONE_WALL);
		recipes.put(Items.RED_SANDSTONE, SpookyBlocks.TAINTED_SANDSTONE);
		recipes.put(Items.SMOOTH_RED_SANDSTONE, SpookyBlocks.SMOOTH_TAINTED_SANDSTONE);
		recipes.put(Items.CUT_RED_SANDSTONE, SpookyBlocks.CUT_TAINTED_SANDSTONE);
		recipes.put(Items.CHISELED_RED_SANDSTONE, SpookyBlocks.CHISELED_TAINTED_SANDSTONE);
		recipes.put(Items.RED_SANDSTONE_STAIRS, SpookyBlocks.TAINTED_SANDSTONE_STAIRS);
		recipes.put(Items.SMOOTH_RED_SANDSTONE_STAIRS, SpookyBlocks.SMOOTH_TAINTED_SANDSTONE_STAIRS);
		recipes.put(Items.RED_SANDSTONE_SLAB, SpookyBlocks.TAINTED_SANDSTONE_SLAB);
		recipes.put(Items.SMOOTH_RED_SANDSTONE_SLAB, SpookyBlocks.SMOOTH_TAINTED_SANDSTONE_SLAB);
		recipes.put(Items.CUT_RED_SANDSTONE_SLAB, SpookyBlocks.CUT_TAINTED_SANDSTONE_SLAB);
		recipes.put(Items.RED_SANDSTONE_WALL, SpookyBlocks.TAINTED_SANDSTONE_WALL);
		recipes.put(SpookyBlocks.TINY_PUMPKIN.asItem(), SpookyBlocks.WITCHED_PUMPKIN);
	}

	@Override
	public void onEntityCollision(BlockState blockState, World world, BlockPos pos, Entity entity) {
		if (entity instanceof LivingEntity) {
			LivingEntity livingEntity = (LivingEntity) entity;
			if(!livingEntity.isUndead() && !(livingEntity instanceof PumpcownEntity)) {
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
			}  else if (entity.getType() == EntityType.COW) {
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
				if(stack.getItem() == Items.PUMPKIN) {
					// Spawn stack.getCount() pumpklings
				} else if(recipes.containsKey(stack.getItem())) {
					ItemStack newStack = new ItemStack(recipes.get(stack.getItem()), stack.getCount());
					itemEntity.setStack(newStack);
					world.playSound(null, pos, SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.BLOCKS, world.random.nextFloat() * 0.5f + 0.5f, 0.75f + world.random.nextFloat() * 0.5f);
				}
			}
		}
	}
}
