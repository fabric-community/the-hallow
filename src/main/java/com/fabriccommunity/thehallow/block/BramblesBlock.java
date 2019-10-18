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

import com.fabriccommunity.thehallow.registry.HallowBlocks;
import com.fabriccommunity.thehallow.registry.HallowEntities;
import com.fabriccommunity.thehallow.util.HallowDamageSource;

public class BramblesBlock extends PlantBlock {
	public static final DamageSource DAMAGE_SOURCE = new HallowDamageSource("brambles");
	public static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	
	public BramblesBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (entity instanceof LivingEntity && entity.getType() != HallowEntities.CROW && entity.getType() != HallowEntities.PUMPCOWN) {
			entity.slowMovement(state, new Vec3d(0.800000011920929D, 0.75D, 0.800000011920929D));
			if (!world.isClient && (entity.prevRenderX != entity.x || entity.prevRenderZ != entity.z)) {
				double entityX = Math.abs(entity.x - entity.prevRenderX);
				double entityZ = Math.abs(entity.z - entity.prevRenderZ);
				if (entityX >= 0.003000000026077032D || entityZ >= 0.003000000026077032D) {
					entity.damage(DAMAGE_SOURCE, 1.0F);
				}
			}
		}
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
		return SHAPE;
	}
	
	@Override
	protected boolean canPlantOnTop(BlockState state, BlockView view, BlockPos pos) {
		Block block = state.getBlock();
		return block == HallowBlocks.TAINTED_SAND || super.canPlantOnTop(state, view, pos);
	}
}
