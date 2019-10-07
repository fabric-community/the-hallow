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

	public WitchWaterBubbleColumnBlock(Settings settings) {
		super(settings);
		this.setDefaultState((this.stateFactory.getDefaultState()).with(DRAG, true));
	}

	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		BlockState state2 = world.getBlockState(pos.up());
		if (state2.isAir()) {
			entity.onBubbleColumnSurfaceCollision(state.get(DRAG));
			if (!world.isClient) {
				ServerWorld serverworld = (ServerWorld)world;

				for(int i = 0; i < 2; i++) {
					serverworld.spawnParticles(ParticleTypes.SPLASH, (double)((float)pos.getX() + world.random.nextFloat()), (double)(pos.getY() + 1), (double)((float)pos.getZ() + world.random.nextFloat()), 1, 0.0D, 0.0D, 0.0D, 1.0D);
					serverworld.spawnParticles(ParticleTypes.BUBBLE, (double)((float)pos.getX() + world.random.nextFloat()), (double)(pos.getY() + 1), (double)((float)pos.getZ() + world.random.nextFloat()), 1, 0.0D, 0.01D, 0.0D, 0.2D);
				}
			}
		} else {
			entity.onBubbleColumnCollision(state.get(DRAG));
		}
	}

	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState state2, boolean bool) {
		update(world, pos.up(), calculateDrag(world, pos.down()));
	}

	public void onScheduledTick(BlockState state, World world, BlockPos pos, Random rand) {
		update(world, pos.up(), calculateDrag(world, pos));
	}

	public FluidState getFluidState(BlockState state) {
		return SpookyFluids.WITCH_WATER.getStill(false);
	}

	public static void update(IWorld world, BlockPos pos, boolean bool) {
		if (isStillWater(world, pos)) {
			world.setBlockState(pos, SpookyBlocks.WITCH_WATER_BUBBLE_COLUMN.getDefaultState().with(DRAG, bool), 2);
		}
	}

	public static boolean isStillWater(IWorld world, BlockPos pos) {
		FluidState state = world.getFluidState(pos);
		return world.getBlockState(pos).getBlock() == SpookyBlocks.WITCH_WATER_BLOCK && state.getLevel() >= 8 && state.isStill();
	}

	private static boolean calculateDrag(BlockView view, BlockPos pos) {
		BlockState state = view.getBlockState(pos);
		Block block = state.getBlock();
		if (block == SpookyBlocks.WITCH_WATER_BUBBLE_COLUMN) {
			return state.get(DRAG);
		} else {
			return block != SpookyBlocks.TAINTED_SAND;
		}
	}

	public int getTickRate(ViewableWorld world) {
		return 5;
	}

	@Environment(EnvType.CLIENT)
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random rand) {
		double x = (double)pos.getX();
		double y = (double)pos.getY();
		double z = (double)pos.getZ();
		if ((Boolean)state.get(DRAG)) {
			world.addImportantParticle(ParticleTypes.CURRENT_DOWN, x + 0.5D, y + 0.8D, z, 0.0D, 0.0D, 0.0D);
			if (rand.nextInt(200) == 0) {
				world.playSound(x, y, z, SoundEvents.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
			}
		} else {
			world.addImportantParticle(ParticleTypes.BUBBLE_COLUMN_UP, x + 0.5D, y, z + 0.5D, 0.0D, 0.04D, 0.0D);
			world.addImportantParticle(ParticleTypes.BUBBLE_COLUMN_UP, x + (double)rand.nextFloat(), y + (double)rand.nextFloat(), z + (double)rand.nextFloat(), 0.0D, 0.04D, 0.0D);
			if (rand.nextInt(200) == 0) {
				world.playSound(x, y, z, SoundEvents.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
			}
		}
	}

	public BlockState getStateForNeighborUpdate(BlockState state, Direction dir, BlockState state2, IWorld world, BlockPos pos, BlockPos pos2) {
		if (!state.canPlaceAt(world, pos)) {
			return SpookyBlocks.WITCH_WATER_BLOCK.getDefaultState();
		} else {
			if (dir == Direction.DOWN) {
				world.setBlockState(pos, SpookyBlocks.WITCH_WATER_BUBBLE_COLUMN.getDefaultState().with(DRAG, calculateDrag(world, pos2)), 2);
			} else if (dir == Direction.UP && state2.getBlock() != SpookyBlocks.WITCH_WATER_BUBBLE_COLUMN && isStillWater(world, pos2)) {
				world.getBlockTickScheduler().schedule(pos, this, this.getTickRate(world));
			}
			world.getFluidTickScheduler().schedule(pos, SpookyFluids.WITCH_WATER, SpookyFluids.WITCH_WATER.getTickRate(world));
			return super.getStateForNeighborUpdate(state, dir, state2, world, pos, pos2);
		}
	}

	public boolean canPlaceAt(BlockState state, ViewableWorld world, BlockPos pos) {
		Block block = world.getBlockState(pos.down()).getBlock();
		return block == SpookyBlocks.WITCH_WATER_BUBBLE_COLUMN || block == SpookyBlocks.BLEEDING_BLOCK || block == SpookyBlocks.TAINTED_SAND;
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
		return VoxelShapes.empty();
	}

	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.INVISIBLE;
	}

	protected void appendProperties(Builder<Block, BlockState> stateFactory$Builder_1) {
		stateFactory$Builder_1.add(new Property[]{DRAG});
	}

	public Fluid tryDrainFluid(IWorld world, BlockPos pos, BlockState state) {
		world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
		return SpookyFluids.WITCH_WATER;
	}

	static {
		DRAG = Properties.DRAG;
	}
}
