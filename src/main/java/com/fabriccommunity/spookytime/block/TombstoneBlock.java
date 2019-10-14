package com.fabriccommunity.spookytime.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlacementEnvironment;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;

public class TombstoneBlock extends Block {
	private static final VoxelShape SHAPE = Block.createCuboidShape(3.0D, 0.0D, 10.0D, 13.0D, 15.0D, 13.0D);

	public TombstoneBlock(Settings settings) {
		super(settings);
	}

	@Override
	public boolean canPlaceAtSide(BlockState state, BlockView blockView, BlockPos pos, BlockPlacementEnvironment bpe) {
		return bpe == BlockPlacementEnvironment.LAND;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView blockView, BlockPos pos, EntityContext context) {
		return SHAPE;
	}

	@Override
	public boolean canPlaceAt(BlockState state, ViewableWorld world, BlockPos pos) {
		BlockPos down = pos.down();
		return Block.isFaceFullSquare(world.getBlockState(down).getCollisionShape(world, down), Direction.UP);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState state2, IWorld world, BlockPos pos, BlockPos pos2) {
		return !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, state2, world, pos, pos2);
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}
