package com.fabriccommunity.spookytime.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.EntityContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;

public class TinyPumpkinBlock extends HorizontalFacingBlock implements Waterloggable {
	protected static final VoxelShape Y_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 8.0D, 10.0D);
	
	public TinyPumpkinBlock(Settings blockSettings) {
		super(blockSettings);
	}
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext placementContext) {
		final BlockState blockState = this.getDefaultState().with(FACING, placementContext.getPlayerFacing().getOpposite());
		if (blockState.contains(Properties.WATERLOGGED))
		{
			final FluidState fluidState = placementContext.getWorld().getFluidState(placementContext.getBlockPos());
			return blockState.with(Properties.WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
		}
		
		return blockState;
	}
	
	@Override
	public FluidState getFluidState(BlockState blockState) {
		return blockState.contains(Properties.WATERLOGGED) && blockState.get(Properties.WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(blockState);
	}
	
	@Override
	public BlockState getStateForNeighborUpdate(BlockState blockState, Direction direction, BlockState blockStateOther, IWorld world, BlockPos blockPosition, BlockPos blockPositionOther) {
		if (blockState.contains(Properties.WATERLOGGED) && blockState.get(Properties.WATERLOGGED))
		{
			world.getFluidTickScheduler().schedule(blockPosition, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		
		return super.getStateForNeighborUpdate(blockState, direction, blockStateOther, world, blockPosition, blockPosition);
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPosition, EntityContext entityContext) {
		return Y_SHAPE;
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
		builder.add(HorizontalFacingBlock.FACING, Properties.WATERLOGGED);
	}
}