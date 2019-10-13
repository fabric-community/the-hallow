package com.fabriccommunity.spookytime.block;

import com.fabriccommunity.spookytime.mixin.CropBlockAccessor;
import com.fabriccommunity.spookytime.world.feature.RandomBlockSelector;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.GourdBlock;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class RandomOutputStemBlock extends PlantBlock implements Fertilizable {
	public static final IntProperty AGE;
	protected static final VoxelShape[] AGE_TO_SHAPE;
	private final RandomBlockSelector randomBlockSelector;

	protected RandomOutputStemBlock(RandomBlockSelector possibleYields, Settings block$Settings_1) {
		super(block$Settings_1);
		this.randomBlockSelector = possibleYields;
		this.setDefaultState(this.stateFactory.getDefaultState()).with(AGE, 0);
	}

	public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
		return AGE_TO_SHAPE[(Integer)blockState_1.get(AGE)];
	}

	protected boolean canPlantOnTop(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
		return blockState_1.getBlock() == Blocks.FARMLAND;
	}

	public void onScheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
		super.onScheduledTick(blockState_1, world_1, blockPos_1, random_1);
		if (world_1.getLightLevel(blockPos_1, 0) >= 9) {
			float float_1 = CropBlockAccessor.callGetAvailableMoisture(this, world_1, blockPos_1);
			if (random_1.nextInt((int)(25.0F / float_1) + 1) == 0) {
				int int_1 = blockState_1.get(AGE);
				if (int_1 < 7) {
					blockState_1 = blockState_1.with(AGE, int_1 + 1);
					world_1.setBlockState(blockPos_1, blockState_1, 2);
				} else {
					Direction direction_1 = Direction.Type.HORIZONTAL.random(random_1);
					BlockPos blockPos_2 = blockPos_1.offset(direction_1);
					Block block_1 = world_1.getBlockState(blockPos_2.down()).getBlock();
					if (world_1.getBlockState(blockPos_2).isAir() && (block_1 == Blocks.FARMLAND || block_1 == Blocks.DIRT || block_1 == Blocks.COARSE_DIRT || block_1 == Blocks.PODZOL || block_1 == Blocks.GRASS_BLOCK)) {
						BlockState selected = this.randomBlockSelector.getSelection(new Random(), blockPos_2);
						world_1.setBlockState(blockPos_2, selected);
						world_1.setBlockState(blockPos_1, ((GourdBlock) selected.getBlock()).getAttachedStem().getDefaultState().with(HorizontalFacingBlock.FACING, direction_1));
					}
				}
			}

		}
	}

	@Nullable
	@Environment(EnvType.CLIENT)
	protected Item getPickItem() {
		return Items.PUMPKIN_SEEDS;
	}

	@Environment(EnvType.CLIENT)
	public ItemStack getPickStack(BlockView blockView_1, BlockPos blockPos_1, BlockState blockState_1) {
		Item item_1 = this.getPickItem();
		return item_1 == null ? ItemStack.EMPTY : new ItemStack(item_1);
	}

	public boolean isFertilizable(BlockView blockView_1, BlockPos blockPos_1, BlockState blockState_1, boolean boolean_1) {
		return blockState_1.get(AGE) != 7;
	}

	public boolean canGrow(World world_1, Random random_1, BlockPos blockPos_1, BlockState blockState_1) {
		return true;
	}

	public void grow(World world_1, Random random_1, BlockPos blockPos_1, BlockState blockState_1) {
		int int_1 = Math.min(7, (Integer)blockState_1.get(AGE) + MathHelper.nextInt(world_1.random, 2, 5));
		BlockState blockState_2 = (BlockState)blockState_1.with(AGE, int_1);
		world_1.setBlockState(blockPos_1, blockState_2, 2);
		if (int_1 == 7) {
			blockState_2.scheduledTick(world_1, blockPos_1, world_1.random);
		}

	}

	protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
		stateFactory$Builder_1.add(new Property[]{AGE});
	}

	static {
		AGE = Properties.AGE_7;
		AGE_TO_SHAPE = new VoxelShape[]{Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 2.0D, 9.0D), Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 4.0D, 9.0D), Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 6.0D, 9.0D), Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 8.0D, 9.0D), Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 10.0D, 9.0D), Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 12.0D, 9.0D), Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 14.0D, 9.0D), Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D)};
	}
}
