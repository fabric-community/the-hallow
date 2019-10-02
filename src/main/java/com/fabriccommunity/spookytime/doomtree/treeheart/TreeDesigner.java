package com.fabriccommunity.spookytime.doomtree.treeheart;

import java.util.Random;

import com.fabriccommunity.spookytime.doomtree.DoomLogBlock;
import com.fabriccommunity.spookytime.doomtree.DoomTree;

import it.unimi.dsi.fastutil.longs.LongArrayList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.IWorld;

public class TreeDesigner {
	static final int RADIUS = 48;
	static final int MAX_TRUNK_HEIGHT = 56;
	static final int MAX_GEN_HEIGHT = 64;
	
	static boolean canReplace(IWorld world, BlockPos pos) {
		return canReplace(world.getBlockState(pos));
	}
		
	static boolean canReplace(BlockState blockState) {
		return blockState.isAir()
				|| blockState == DoomTree.MIASMA_STATE 
				|| blockState == DoomTree.LEAF_STATE 
				|| blockState == DoomTree.GLEAM_STATE 
				|| !blockState.getBlock().matches(DoomTree.DOOM_TREE_PROTECTED);
	}

	static int DISTANCES[] = new int[64];

	static {
		for (int i = 0; i < 64; i++) {
			DISTANCES[i] = (int) Math.round(Math.sqrt(i));
		}
	}

	static int squaredDistance(int dx, int dz) {
		return dx * dx + dz * dz;
	}

	static final int MIN_BRANCH_HEIGHT = DoomLogBlock.TERMINAL_HEIGHT + 1;
	
	public static int canopyRadius(int height) {
		final int damping = height - MIN_BRANCH_HEIGHT;
		return (int) (36 * ((1f - (damping * damping) / 4096f)));
	}
	
	public static int canopyRadiusSquared(int height) {
		final int r = canopyRadius(height);
		return r * r;
	}
	
	static BlockState logState(BlockPos pos,DoomHeartBlockEntity heart) {
		final BlockPos heartPos = heart.getPos();
		final int dy = pos.getY() - heart.getPos().getY();
		final int x  = heartPos.getX();
		final int z = heartPos.getZ();
		
		if (pos.getX() == x && Math.abs(pos.getZ() - z) == 1 || pos.getZ() == z && Math.abs(pos.getX() - x) == 1) {
			if (dy >= 0 && dy < DoomLogBlock.TERMINAL_HEIGHT) {
				return DoomTree.CHANNEL_STATE.with(DoomLogBlock.HEIGHT, MathHelper.clamp(dy, 0, DoomLogBlock.MAX_HEIGHT));
			} else if (dy == DoomLogBlock.TERMINAL_HEIGHT) {
				return DoomTree.TERMINAL_STATE;
			}
		} 
		
		return DoomTree.LOG_STATE.with(DoomLogBlock.HEIGHT, MathHelper.clamp(dy, 0, DoomLogBlock.MAX_HEIGHT));
	}
	
	public static LongArrayList designTrunk(IWorld world, BlockPos posIn, Random rand) {
		final BlockPos.Mutable mPos = new BlockPos.Mutable();
		final LongArrayList blocks = new LongArrayList();
		
		final int x = posIn.getX();
		final int y = posIn.getY();
		final int z = posIn.getZ();
		
		final int centerHeight = y + MAX_TRUNK_HEIGHT;
		
		if (!placeTrunkSection(world, blocks, mPos.set(x, 0, z), centerHeight)) return null;
		
		blocks.rem(posIn.asLong());
		
		if (!placeTrunkSection(world, blocks, mPos.set(x - 1, 0, z), centerHeight - rand.nextInt(5) - 1)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x, 0, z - 1), centerHeight - rand.nextInt(5) - 1)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x + 1, 0, z), centerHeight - rand.nextInt(5) - 1)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x, 0, z + 1), centerHeight - rand.nextInt(5) - 1)) return null;

		if (!placeTrunkSection(world, blocks, mPos.set(x - 1, 0, z - 1), centerHeight - rand.nextInt(5) - 1)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x - 1, 0, z + 1), centerHeight - rand.nextInt(5) - 1)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x + 1, 0, z - 1), centerHeight - rand.nextInt(5) - 1)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x + 1, 0, z + 1), centerHeight - rand.nextInt(5) - 1)) return null;

		if (!placeTrunkSection(world, blocks, mPos.set(x + 2, 0, z), y + 2)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x - 2, 0, z), y + 2)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x, 0, z - 2), y + 2)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x, 0, z + 2), y + 2)) return null;

		if (!placeTrunkSection(world, blocks, mPos.set(x + 2, 0, z - 1), y + 1)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x + 2, 0, z + 1), y + 1)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x - 2, 0, z - 1), y + 1)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x - 2, 0, z + 1), y + 1)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x - 1, 0, z - 2), y + 1)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x + 1, 0, z - 2), y + 1)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x - 1, 0, z + 2), y + 1)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x + 1, 0, z + 2), y + 1)) return null;

		if (!placeTrunkSection(world, blocks, mPos.set(x - 2, 0, z - 2), y)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x - 2, 0, z + 2), y)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x + 2, 0, z - 2), y)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x + 2, 0, z + 2), y)) return null;

		if (!placeTrunkSection(world, blocks, mPos.set(x + 3, 0, z), y)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x - 3, 0, z), y)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x, 0, z - 3), y)) return null;
		if (!placeTrunkSection(world, blocks, mPos.set(x, 0, z + 3), y)) return null;
		
		return blocks;
	}

	static boolean placeTrunkSection(IWorld world, LongArrayList blocks, BlockPos.Mutable pos, int height) {
		final int x = pos.getX();
		final int z = pos.getZ();
		final int limit = world.getTop(Type.MOTION_BLOCKING_NO_LEAVES, x, z);
		for (int y = 0; y <= height; y++) {
			pos.setY(y); 
			
			if (y < limit) {
				BlockState state = world.getBlockState(pos);
				
				if (state.getBlock() == Blocks.BEDROCK) continue;
				
				if (!TreeDesigner.canReplace(state)) return false;
			}
			
			blocks.add(pos.asLong());
		}

		return true;
	}
}
