package com.fabriccommunity.thehallow.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import com.fabriccommunity.thehallow.HallowConfig;
import com.fabriccommunity.thehallow.block.entity.TinyPumpkinBlockEntity;

public class TinyPumpkinBlock extends HorizontalFacingBlock implements BlockEntityProvider, Waterloggable {
	protected static final VoxelShape Y_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 8.0D, 10.0D);
	
	public TinyPumpkinBlock(Settings blockSettings) {
		super(blockSettings);
		if (HallowConfig.TinyPumpkin.waterloggable) {
			setDefaultState(getDefaultState().with(Properties.WATERLOGGED, false));
		}
	}
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext placementContext) {
		final BlockState blockState = this.getDefaultState().with(FACING, placementContext.getPlayerFacing().getOpposite());
		if (blockState.contains(Properties.WATERLOGGED)) {
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
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState otherState, IWorld world, BlockPos pos, BlockPos otherPos) {
		if (state.contains(Properties.WATERLOGGED) && state.get(Properties.WATERLOGGED)) {
			world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		
		return super.getStateForNeighborUpdate(state, direction, otherState, world, pos, otherPos);
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
		builder.add(HorizontalFacingBlock.FACING);
		
		if (HallowConfig.TinyPumpkin.waterloggable) {
			builder.add(Properties.WATERLOGGED);
		}
	}
	
	@Override
	public boolean canFillWithFluid(BlockView blockView, BlockPos pos, BlockState state, Fluid fluid) {
		return state.contains(Properties.WATERLOGGED) && Waterloggable.super.canFillWithFluid(blockView, pos, state, fluid);
	}
	
	@Override
	public Fluid tryDrainFluid(IWorld world, BlockPos pos, BlockState state) {
		if (state.contains(Properties.WATERLOGGED)) {
			return Waterloggable.super.tryDrainFluid(world, pos, state);
		} else {
			return Fluids.EMPTY;
		}
	}
	
	@Override
	public boolean tryFillWithFluid(IWorld world, BlockPos pos, BlockState blockState, FluidState fluidState) {
		if (blockState.contains(Properties.WATERLOGGED)) {
			return Waterloggable.super.tryFillWithFluid(world, pos, blockState, fluidState);
		} else {
			return false;
		}
	}
	
	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new TinyPumpkinBlockEntity();
	}
	
	@Override
	public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		BlockEntity be = world.getBlockEntity(pos);
		if (be instanceof TinyPumpkinBlockEntity) {
			return ((TinyPumpkinBlockEntity) be).use(player, hand, hit);
		}
		return false;
	}
	
	@Override
	public void onBlockRemoved(BlockState state1, World world, BlockPos pos, BlockState state2, boolean flag) {
		if (state1.getBlock() != state2.getBlock()) {
			BlockEntity be = world.getBlockEntity(pos);
			if (be instanceof TinyPumpkinBlockEntity) {
				ItemScatterer.spawn(world, pos, ((TinyPumpkinBlockEntity) be).getAllItems());
				world.updateHorizontalAdjacent(pos, this);
			}
			
			super.onBlockRemoved(state1, world, pos, state2, flag);
		}
	}
}
