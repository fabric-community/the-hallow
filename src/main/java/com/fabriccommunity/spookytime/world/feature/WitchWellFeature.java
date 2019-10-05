package com.fabriccommunity.spookytime.world.feature;

import com.fabriccommunity.spookytime.block.TinyPumpkinBlock;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.function.Function;

public class WitchWellFeature extends Feature<DefaultFeatureConfig> {
	private static final BlockStatePredicate CAN_GENERATE = BlockStatePredicate.forBlock(SpookyBlocks.DECEASED_GRASS_BLOCK);
	
	private final BlockState slab;
	private final BlockState wall;
	private final BlockState fluid;
	private final BlockState lantern;
	
	public WitchWellFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configDeserializer) {
		super(configDeserializer);
		this.slab = SpookyBlocks.TAINTED_SANDSTONE_SLAB.getDefaultState();
		this.wall = SpookyBlocks.TAINTED_SANDSTONE.getDefaultState();
		this.fluid = SpookyBlocks.WITCH_WATER_BLOCK.getDefaultState();
		this.lantern = Blocks.LANTERN.getDefaultState().with(LanternBlock.HANGING, true);
	}
	
	private BlockState getPumpkin(Direction facing) {
		return SpookyBlocks.TINY_PUMPKIN.getDefaultState().with(TinyPumpkinBlock.FACING, facing);
	}
	
	@Override
	public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> gen, Random random, BlockPos pos, DefaultFeatureConfig config) {
		pos = pos.up();
		while (world.isAir(pos) && pos.getY() > 2) {
			pos = pos.down();
		}
		
		if (!CAN_GENERATE.test(world.getBlockState(pos))) {
			return false;
		}
		
		// Check for solid ground
		for (int x = -2; x <= 2; ++x) {
			for (int z = -2; z <= 2; ++z) {
				if (world.isAir(pos.add(x, -1, z)) && world.isAir(pos.add(x, -2, z))) {
					return false;
				}
			}
		}
		
		//System.out.println("Generating at " + pos);
		
		// Below-ground layer
		for (int y = -1; y <= 0; ++y) {
			for (int x = -2; x <= 2; ++x) {
				for (int z = -2; z <= 2; ++z) {
					world.setBlockState(pos.add(x, y, z), this.wall, 2);
				}
			}
		}
		
		// Fluid
		world.setBlockState(pos, this.fluid, 2);
		for (Direction direction : Direction.Type.HORIZONTAL) {
			world.setBlockState(pos.offset(direction), this.fluid, 2);
		}
		
		// Above-ground layer
		for (int x = -2; x <= 2; ++x) {
			for (int z = -2; z <= 2; ++z) {
				if (x == -2 || x == 2 || z == -2 || z == 2) {
					world.setBlockState(pos.add(x, 1, z), this.wall, 2);
				}
			}
		}
		
		// Slabs in the above-ground layer
		world.setBlockState(pos.add(2, 1, 0), this.slab, 2);
		world.setBlockState(pos.add(-2, 1, 0), this.slab, 2);
		world.setBlockState(pos.add(0, 1, 2), this.slab, 2);
		world.setBlockState(pos.add(0, 1, -2), this.slab, 2);
		
		// Witched pumpkin
		int pumpkinX = random.nextBoolean() ? -2 : 2;
		int pumpkinZ = random.nextBoolean() ? -2 : 2;
		Direction pumpkinFacing = Direction.fromHorizontal(random.nextInt(4));
		world.setBlockState(pos.add(pumpkinX, 2, pumpkinZ), getPumpkin(pumpkinFacing), 2);
		world.setBlockState(pos.add(0, 3, 0), this.lantern, 2);
		
		// Roof
		for (int x = -1; x <= 1; ++x) {
			for (int z = -1; z <= 1; ++z) {
				if (x == 0 && z == 0) {
					world.setBlockState(pos.add(x, 4, z), this.wall, 2);
				} else {
					world.setBlockState(pos.add(x, 4, z), this.slab, 2);
				}
			}
		}
		
		// Pillars
		for (int y = 1; y <= 3; ++y) {
			world.setBlockState(pos.add(-1, y, -1), this.wall, 2);
			world.setBlockState(pos.add(-1, y, 1), this.wall, 2);
			world.setBlockState(pos.add(1, y, -1), this.wall, 2);
			world.setBlockState(pos.add(1, y, 1), this.wall, 2);
		}
		
		return true;
	}
}
