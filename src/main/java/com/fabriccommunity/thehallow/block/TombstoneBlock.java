package com.fabriccommunity.thehallow.block;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TombstoneBlock extends HorizontalFacingBlock implements Fertilizable {
	private static final IntProperty AGE = Properties.AGE_1;
	private static final Map<Direction, VoxelShape> SHAPES = Stream.of(
		new AbstractMap.SimpleEntry<>(Direction.NORTH, Block.createCuboidShape(3.0D, 0.0D, 10.0D, 13.0D, 15.0D, 13.0D)),
		new AbstractMap.SimpleEntry<>(Direction.SOUTH, Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 15.0D, 6.0D)),
		new AbstractMap.SimpleEntry<>(Direction.EAST, Block.createCuboidShape(3.0D, 0.0D, 3.0D, 6.0D, 15.0D, 13.0D)),
		new AbstractMap.SimpleEntry<>(Direction.WEST, Block.createCuboidShape(10.0D, 0.0D, 3.0D, 13.0D, 15.0D, 13.0D))
		)
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

	public TombstoneBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(AGE, 0));
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext placementContext) {
		return this.getDefaultState().with(FACING, placementContext.getPlayerFacing().getOpposite());
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(HorizontalFacingBlock.FACING);
		builder.add(Properties.AGE_1);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView blockView, BlockPos pos, EntityContext context) {
		return SHAPES.get(state.get(FACING));
	}

	//@Override
	//public BlockRenderLayer getRenderLayer() {
	//	return BlockRenderLayer.CUTOUT;
	//}
	//FIXME TombstoneBlock render layer: CUTOUT

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		BlockPos down = pos.down();
		return Block.isFaceFullSquare(world.getBlockState(down).getCollisionShape(world, down), Direction.UP);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockView blockView, BlockPos blockPos, EntityContext entityContext) {
		return SHAPES.get(state.get(FACING));
	}

	@Override
	public boolean isFertilizable(BlockView blockView, BlockPos blockPos, BlockState state, boolean bool) {
		return state.get(AGE) == 0;
	}

	@Override
	public boolean canGrow(World world, Random random, BlockPos blockPos, BlockState state) {
		return state.get(AGE) == 0;
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
		if (state.get(AGE) == 0 && (isPlacedOnSoil(world, pos))) {
			world.setBlockState(pos, state.with(AGE, 1));
		}
	}

	private boolean isPlacedOnSoil(World world, BlockPos pos) {
		Block block = world.getBlockState(pos.down()).getBlock();
		
		return (block == Blocks.DIRT 
			|| block == Blocks.COARSE_DIRT 
			|| block == Blocks.PODZOL
			|| block == HallowedBlocks.DECEASED_DIRT
			|| block == HallowedBlocks.DECEASED_GRASS_BLOCK
			|| block == Blocks.GRASS_BLOCK
			|| block == HallowedBlocks.DECEASED_MOSS);
	}
}
