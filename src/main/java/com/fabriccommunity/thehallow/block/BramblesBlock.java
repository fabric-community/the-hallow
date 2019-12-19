package com.fabriccommunity.thehallow.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;
import com.fabriccommunity.thehallow.registry.HallowedEntities;
import com.fabriccommunity.thehallow.util.HallowedDamageSource;

public class BramblesBlock extends PlantBlock {
	public static final DamageSource DAMAGE_SOURCE = new HallowedDamageSource("brambles");
	public static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	
	public BramblesBlock(Settings settings) {
		super(settings);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (entity instanceof LivingEntity && entity.getType() != HallowedEntities.CROW && entity.getType() != HallowedEntities.PUMPCOWN) {
			entity.slowMovement(state, new Vec3d(0.800000011920929D, 0.75D, 0.800000011920929D));
			if (!world.isClient && (entity.lastRenderX != entity.getX() || entity.lastRenderZ != entity.getZ())) {
				double entityX = Math.abs(entity.getX() - entity.lastRenderX);
				double entityZ = Math.abs(entity.getZ() - entity.lastRenderZ);
				if (entityX >= 0.003000000026077032D || entityZ >= 0.003000000026077032D) {
					entity.damage(DAMAGE_SOURCE, 1.0F);
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
		return SHAPE;
	}
	
	@Override
	protected boolean canPlantOnTop(BlockState state, BlockView view, BlockPos pos) {
		Block block = state.getBlock();
		return block == HallowedBlocks.TAINTED_SAND || super.canPlantOnTop(state, view, pos);
	}
}
