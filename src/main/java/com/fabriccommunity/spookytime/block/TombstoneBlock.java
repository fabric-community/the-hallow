package com.fabriccommunity.spookytime.block;

import net.minecraft.block.*;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TombstoneBlock extends HorizontalFacingBlock {
	private static final Map<Direction, VoxelShape> SHAPES = Stream.of(
		new AbstractMap.SimpleEntry<>(Direction.NORTH, Block.createCuboidShape(3.0D, 0.0D, 10.0D, 13.0D, 15.0D, 13.0D)),
		new AbstractMap.SimpleEntry<>(Direction.SOUTH, Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 15.0D, 6.0D)),
		new AbstractMap.SimpleEntry<>(Direction.EAST, Block.createCuboidShape(3.0D, 0.0D, 3.0D, 6.0D, 15.0D, 13.0D)),
		new AbstractMap.SimpleEntry<>(Direction.WEST, Block.createCuboidShape(10.0D, 0.0D, 3.0D, 13.0D, 15.0D, 13.0D))
		)
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

	public TombstoneBlock(Settings settings) {
		super(settings);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext placementContext) {

		return this.getDefaultState().with(FACING, placementContext.getPlayerFacing().getOpposite());
	}

	@Override
	protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
		builder.add(HorizontalFacingBlock.FACING);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView blockView, BlockPos pos, EntityContext context) {
		return SHAPES.get(state.get(FACING));
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockView blockView, BlockPos blockPos, EntityContext entityContext) {
		return SHAPES.get(state.get(FACING));
	}
}
