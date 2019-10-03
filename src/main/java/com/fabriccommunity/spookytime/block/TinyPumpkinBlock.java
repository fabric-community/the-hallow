package com.fabriccommunity.spookytime.block;

import com.fabriccommunity.spookytime.block.entity.TinyPumpkinBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class TinyPumpkinBlock extends HorizontalFacingBlock implements BlockEntityProvider {
	protected static final VoxelShape Y_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 8.0D, 10.0D);
	
	public TinyPumpkinBlock(Settings blockSettings) {
		super(blockSettings);
	}
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext placementContext) {
		return this.getDefaultState().with(FACING, placementContext.getPlayerFacing().getOpposite());
	}
	
	
	@Override
	public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPosition, EntityContext entityContext) {
		return Y_SHAPE;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState blockState, BlockView blockView, BlockPos blockPosition, EntityContext entityContext) {
		return this.collidable ? blockState.getOutlineShape(blockView, blockPosition) : VoxelShapes.empty();
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
		builder.add(HorizontalFacingBlock.FACING);
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
