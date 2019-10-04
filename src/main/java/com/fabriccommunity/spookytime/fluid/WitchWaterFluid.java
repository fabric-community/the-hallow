package com.fabriccommunity.spookytime.fluid;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.fabriccommunity.spookytime.registry.SpookyFluidTags;
import com.fabriccommunity.spookytime.registry.SpookyFluids;
import com.fabriccommunity.spookytime.registry.SpookyItems;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

import java.util.Random;

public class WitchWaterFluid extends BaseFluid {
	@Override
	public Fluid getFlowing() {
		return SpookyFluids.FLOWING_WITCH_WATER;
	}
	
	@Override
	public Fluid getStill() {
		return SpookyFluids.WITCH_WATER;
	}
	
	@Override
	protected boolean isInfinite() {
		return true;
	}
	
	@Override
	protected BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.SOLID;
	}
	
	@Override
	public Item getBucketItem() {
		return SpookyItems.WITCH_WATER_BUCKET;
	}
	
	@Environment(EnvType.CLIENT)
	public ParticleEffect getParticle() {
		return ParticleTypes.DRIPPING_WATER;
	}
	
	@Override
	public boolean method_15777(FluidState fluidState, BlockView blockView, BlockPos blockPos, Fluid fluid, Direction direction) {
		return direction == Direction.DOWN && !fluid.matches(SpookyFluidTags.WITCH_WATER);
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public void randomDisplayTick(World world, BlockPos blockPos, FluidState fluidState, Random random) {
		if (random.nextInt(10) == 0) {
			world.addParticle(ParticleTypes.BUBBLE_POP,
				(double) blockPos.getX() + 0.5D + (random.nextFloat() - 0.5F),
				(double) blockPos.getY() + (fluidState.getHeight(world, blockPos) * (1F / 7F)) + 1F,
				(double) blockPos.getZ() + 0.5D + (random.nextFloat() - 0.5F),
				0.0D, 0.0D, 0.0D);
		}
		
		if (random.nextInt(15) == 0) {
			world.addParticle(ParticleTypes.BUBBLE,
				(double) blockPos.getX() + 0.5D + (random.nextFloat() - 0.5F),
				(double) blockPos.getY() + (fluidState.getHeight(world, blockPos) * (1F / 7F)) + 1F,
				(double) blockPos.getZ() + 0.5D + (random.nextFloat() - 0.5F),
				0.0D, 0.0D, 0.0D);
		}
	}
	
	@Override
	public int getTickRate(ViewableWorld viewableWorld) {
		return 5;
	}
	
	@Override
	public boolean matchesType(Fluid fluid) {
		return fluid == getStill() || fluid == getFlowing();
	}
	
	@Override
	public void beforeBreakingBlock(IWorld iWorld, BlockPos blockPos, BlockState blockState) {
		BlockEntity blockEntity = blockState.getBlock().hasBlockEntity() ? iWorld.getBlockEntity(blockPos) : null;
		Block.dropStacks(blockState, iWorld.getWorld(), blockPos, blockEntity);
	}
	
	@Override
	public int method_15733(ViewableWorld viewableWorld) {
		return 4;
	}
	
	@Override
	public int getLevelDecreasePerBlock(ViewableWorld viewableWorld) {
		return 1;
	}
	
	@Override
	public boolean hasRandomTicks() {
		return true;
	}
	
	@Override
	public float getBlastResistance() {
		return 100.f;
	}
	
	@Override
	public BlockState toBlockState(FluidState fluidState) {
		return SpookyBlocks.WITCH_WATER_BLOCK.getDefaultState().with(FluidBlock.LEVEL, method_15741(fluidState));
	}
	
	@Override
	public boolean isStill(FluidState fluidState) {
		return false;
	}
	
	@Override
	public int getLevel(FluidState fluidState) {
		return 0;
	}
	
	public static class Flowing extends WitchWaterFluid {
		public Flowing() {
		
		}
		
		@Override
		protected void appendProperties(StateFactory.Builder<Fluid, FluidState> stateBuilder) {
			super.appendProperties(stateBuilder);
			stateBuilder.add(LEVEL);
		}
		
		@Override
		public int getLevel(FluidState fluidState) {
			return fluidState.get(LEVEL);
		}
		
		@Override
		public boolean isStill(FluidState fluidState) {
			return false;
		}
	}
	
	public static class Still extends WitchWaterFluid {
		public Still() {
		
		}
		
		@Override
		public int getLevel(FluidState fluidState) {
			return 8;
		}
		
		@Override
		public boolean isStill(FluidState fluidState) {
			return true;
		}
	}
}
