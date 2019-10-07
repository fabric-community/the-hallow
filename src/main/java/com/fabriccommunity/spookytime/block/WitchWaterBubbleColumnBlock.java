package com.fabriccommunity.spookytime.block;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.fabriccommunity.spookytime.registry.SpookyFluids;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityContext;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.state.StateFactory.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

import java.util.Random;

public class WitchWaterBubbleColumnBlock extends Block implements FluidDrainable {
	public static final BooleanProperty DRAG;

	public WitchWaterBubbleColumnBlock(Settings block$Settings_1) {
		super(block$Settings_1);
		this.setDefaultState((BlockState)((BlockState)this.stateFactory.getDefaultState()).with(DRAG, true));
	}

	public void onEntityCollision(BlockState blockState_1, World world_1, BlockPos blockPos_1, Entity entity_1) {
		BlockState blockState_2 = world_1.getBlockState(blockPos_1.up());
		if (blockState_2.isAir()) {
			entity_1.onBubbleColumnSurfaceCollision((Boolean)blockState_1.get(DRAG));
			if (!world_1.isClient) {
				ServerWorld serverWorld_1 = (ServerWorld)world_1;

				for(int int_1 = 0; int_1 < 2; ++int_1) {
					serverWorld_1.spawnParticles(ParticleTypes.SPLASH, (double)((float)blockPos_1.getX() + world_1.random.nextFloat()), (double)(blockPos_1.getY() + 1), (double)((float)blockPos_1.getZ() + world_1.random.nextFloat()), 1, 0.0D, 0.0D, 0.0D, 1.0D);
					serverWorld_1.spawnParticles(ParticleTypes.BUBBLE, (double)((float)blockPos_1.getX() + world_1.random.nextFloat()), (double)(blockPos_1.getY() + 1), (double)((float)blockPos_1.getZ() + world_1.random.nextFloat()), 1, 0.0D, 0.01D, 0.0D, 0.2D);
				}
			}
		} else {
			entity_1.onBubbleColumnCollision((Boolean)blockState_1.get(DRAG));
		}

	}

	public void onBlockAdded(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2, boolean boolean_1) {
		update(world_1, blockPos_1.up(), calculateDrag(world_1, blockPos_1.down()));
	}

	public void onScheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
		update(world_1, blockPos_1.up(), calculateDrag(world_1, blockPos_1));
	}

	public FluidState getFluidState(BlockState blockState_1) {
		return SpookyFluids.WITCH_WATER.getStill(false);
	}

	public static void update(IWorld iWorld_1, BlockPos blockPos_1, boolean boolean_1) {
		if (isStillWater(iWorld_1, blockPos_1)) {
			iWorld_1.setBlockState(blockPos_1, SpookyBlocks.WITCH_WATER_BUBBLE_COLUMN.getDefaultState().with(DRAG, boolean_1), 2);
		}

	}

	public static boolean isStillWater(IWorld iWorld_1, BlockPos blockPos_1) {
		FluidState fluidState_1 = iWorld_1.getFluidState(blockPos_1);
		return iWorld_1.getBlockState(blockPos_1).getBlock() == SpookyBlocks.WITCH_WATER_BLOCK && fluidState_1.getLevel() >= 8 && fluidState_1.isStill();
	}

	private static boolean calculateDrag(BlockView blockView_1, BlockPos blockPos_1) {
		BlockState blockState_1 = blockView_1.getBlockState(blockPos_1);
		Block block_1 = blockState_1.getBlock();
		if (block_1 == SpookyBlocks.WITCH_WATER_BUBBLE_COLUMN) {
			return (Boolean)blockState_1.get(DRAG);
		} else {
			return block_1 != SpookyBlocks.TAINTED_SAND;
		}
	}

	public int getTickRate(ViewableWorld viewableWorld_1) {
		return 5;
	}

	@Environment(EnvType.CLIENT)
	public void randomDisplayTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
		double double_1 = (double)blockPos_1.getX();
		double double_2 = (double)blockPos_1.getY();
		double double_3 = (double)blockPos_1.getZ();
		if ((Boolean)blockState_1.get(DRAG)) {
			world_1.addImportantParticle(ParticleTypes.CURRENT_DOWN, double_1 + 0.5D, double_2 + 0.8D, double_3, 0.0D, 0.0D, 0.0D);
			if (random_1.nextInt(200) == 0) {
				world_1.playSound(double_1, double_2, double_3, SoundEvents.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, SoundCategory.BLOCKS, 0.2F + random_1.nextFloat() * 0.2F, 0.9F + random_1.nextFloat() * 0.15F, false);
			}
		} else {
			world_1.addImportantParticle(ParticleTypes.BUBBLE_COLUMN_UP, double_1 + 0.5D, double_2, double_3 + 0.5D, 0.0D, 0.04D, 0.0D);
			world_1.addImportantParticle(ParticleTypes.BUBBLE_COLUMN_UP, double_1 + (double)random_1.nextFloat(), double_2 + (double)random_1.nextFloat(), double_3 + (double)random_1.nextFloat(), 0.0D, 0.04D, 0.0D);
			if (random_1.nextInt(200) == 0) {
				world_1.playSound(double_1, double_2, double_3, SoundEvents.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, SoundCategory.BLOCKS, 0.2F + random_1.nextFloat() * 0.2F, 0.9F + random_1.nextFloat() * 0.15F, false);
			}
		}

	}

	public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, IWorld iWorld_1, BlockPos blockPos_1, BlockPos blockPos_2) {
		if (!blockState_1.canPlaceAt(iWorld_1, blockPos_1)) {
			return SpookyBlocks.WITCH_WATER_BLOCK.getDefaultState();
		} else {
			if (direction_1 == Direction.DOWN) {
				iWorld_1.setBlockState(blockPos_1, SpookyBlocks.WITCH_WATER_BUBBLE_COLUMN.getDefaultState().with(DRAG, calculateDrag(iWorld_1, blockPos_2)), 2);
			} else if (direction_1 == Direction.UP && blockState_2.getBlock() != SpookyBlocks.WITCH_WATER_BUBBLE_COLUMN && isStillWater(iWorld_1, blockPos_2)) {
				iWorld_1.getBlockTickScheduler().schedule(blockPos_1, this, this.getTickRate(iWorld_1));
			}

			iWorld_1.getFluidTickScheduler().schedule(blockPos_1, SpookyFluids.WITCH_WATER, SpookyFluids.WITCH_WATER.getTickRate(iWorld_1));
			return super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1, blockPos_2);
		}
	}

	public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
		Block block_1 = viewableWorld_1.getBlockState(blockPos_1.down()).getBlock();
		return block_1 == SpookyBlocks.WITCH_WATER_BUBBLE_COLUMN || block_1 == SpookyBlocks.BLEEDING_BLOCK || block_1 == SpookyBlocks.TAINTED_SAND;
	}

	public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
		return VoxelShapes.empty();
	}

	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	public BlockRenderType getRenderType(BlockState blockState_1) {
		return BlockRenderType.INVISIBLE;
	}

	protected void appendProperties(Builder<Block, BlockState> stateFactory$Builder_1) {
		stateFactory$Builder_1.add(new Property[]{DRAG});
	}

	public Fluid tryDrainFluid(IWorld iWorld_1, BlockPos blockPos_1, BlockState blockState_1) {
		iWorld_1.setBlockState(blockPos_1, Blocks.AIR.getDefaultState(), 11);
		return SpookyFluids.WITCH_WATER;
	}

	static {
		DRAG = Properties.DRAG;
	}
}
