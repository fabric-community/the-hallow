package com.fabriccommunity.spookytime.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlacementEnvironment;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

import com.fabriccommunity.spookytime.entity.SpookyCactusEntity;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.fabriccommunity.spookytime.registry.SpookyEntities;

import java.util.Iterator;
import java.util.Random;

public class SpookyCactusBlock extends Block {
	public static final IntProperty AGE = Properties.AGE_15;
	protected static final VoxelShape COLLISION_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
	protected static final VoxelShape OUTLINE_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	
	public SpookyCactusBlock(Block.Settings settings) {
		super(settings);
		this.setDefaultState(this.stateFactory.getDefaultState().with(AGE, 0));
	}
	
	@Override
	public void onScheduledTick(BlockState state, World world, BlockPos pos, Random random) {
		if (!state.canPlaceAt(world, pos)) {
			world.breakBlock(pos, true);
		} else {
			BlockPos pos2 = pos.up();
			if (world.isAir(pos2)) {
				int height = 1;
				while (world.getBlockState(pos.down(height)).getBlock() == this) {
					height++;
				}
				
				if (height < 3) {
					int age = state.get(AGE);
					if (age == 15) {
						world.setBlockState(pos2, this.getDefaultState());
						BlockState state2 = state.with(AGE, 0);
						world.setBlockState(pos, state2, 4);
						state2.neighborUpdate(world, pos2, this, pos, false);
					} else {
						world.setBlockState(pos, state.with(AGE, age + 1), 4);
					}
					
				}
			}
			if (world.getBlockState(pos.down(1)).getBlock() == SpookyBlocks.TAINTED_SAND && random.nextInt(2) == 0) {
				int height = 0;
				int age = 0;
				for (height = 0; world.getBlockState(pos.up(height)).getBlock() == this; height++) {
					age = world.getBlockState(pos.up(height)).get(AGE);
					world.setBlockState(pos.up(height), Blocks.AIR.getDefaultState());
				}
				
				SpookyCactusEntity entity = new SpookyCactusEntity(SpookyEntities.SPOOKY_CACTUS, world);
				entity.setPosition(pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5f);
				entity.setCactusHeight(height);
				entity.age = age;
				world.spawnEntity(entity);
			}
		}
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
		return COLLISION_SHAPE;
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
		return OUTLINE_SHAPE;
	}
	
	@Override
	public boolean isOpaque(BlockState state) {
		return true;
	}
	
	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState state2, IWorld world, BlockPos pos, BlockPos pos2) {
		if (!state.canPlaceAt(world, pos)) {
			world.getBlockTickScheduler().schedule(pos, this, 1);
		}
		
		return super.getStateForNeighborUpdate(state, direction, state2, world, pos, pos2);
	}
	
	@Override
	public boolean canPlaceAt(BlockState state, ViewableWorld world, BlockPos pos) {
		Iterator<Direction> iterator = Direction.Type.HORIZONTAL.iterator();
		
		Direction direction;
		Material material;
		do {
			if (!iterator.hasNext()) {
				Block block = world.getBlockState(pos.down()).getBlock();
				return (block == SpookyBlocks.SPOOKY_CACTUS || block == SpookyBlocks.TAINTED_SAND) && !world.getBlockState(pos.up()).getMaterial().isLiquid();
			}
			
			direction = iterator.next();
			BlockState state2 = world.getBlockState(pos.offset(direction));
			material = state2.getMaterial();
		} while (!material.isSolid() && !world.getFluidState(pos.offset(direction)).matches(FluidTags.LAVA));
		
		return false;
	}
	
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		entity.damage(DamageSource.CACTUS, 1.0F);
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}
	
	@Override
	public boolean canPlaceAtSide(BlockState state, BlockView view, BlockPos pos, BlockPlacementEnvironment environment) {
		return false;
	}
}
