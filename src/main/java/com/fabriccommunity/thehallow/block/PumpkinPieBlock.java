package com.fabriccommunity.thehallow.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlacementEnvironment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;

public class PumpkinPieBlock extends Block {
	private static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(13, 0, 3, 15, 5.5, 13), Block.createCuboidShape(1, 0, 3, 3, 5.5, 13), Block.createCuboidShape(1, 0, 1, 15, 5.5, 3), Block.createCuboidShape(1, 0, 13, 15, 5.5, 15), Block.createCuboidShape(3, 0, 3, 13, 4.8999999999999995, 13));

	public PumpkinPieBlock(Settings settings) {
		super(settings);
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView blockView, BlockPos pos, EntityContext entityContext) {
		return SHAPE;
	}

	public BlockState getStateForNeighborUpdate(BlockState state_1, Direction direction, BlockState state_2, IWorld iWorld, BlockPos pos_1, BlockPos pos_2) {
		return direction == Direction.DOWN && !state_1.canPlaceAt(iWorld, pos_1) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state_1, direction, state_2, iWorld, pos_1, pos_2);
	}

	public boolean canPlaceAt(BlockState state, ViewableWorld world, BlockPos pos) {
		return world.getBlockState(pos.down()).getMaterial().isSolid();
	}

	public boolean canPlaceAtSide(BlockState state, BlockView blockView, BlockPos pos, BlockPlacementEnvironment placementEnv) {
		return false;
	}

	public String getTranslationKey() {
		return Items.PUMPKIN_PIE.getTranslationKey();
	}
}
