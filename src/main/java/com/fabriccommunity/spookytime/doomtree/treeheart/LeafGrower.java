package com.fabriccommunity.spookytime.doomtree.treeheart;

import java.util.Random;

import com.fabriccommunity.spookytime.doomtree.DoomTree;

import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.World;

public class LeafGrower {
	static void growLeaves(DoomHeartBlockEntity heart) {
		if (!heart.logs.hasBranches()) return;
		
		final BlockPos.Mutable mPos = heart.mPos;
		final Random r = ThreadLocalRandom.current();
		final World world = heart.getWorld();

		final BlockPos origin = heart.getPos();
		
		for (int i = 0; i < 8; i++) {
			final int rp = heart.logs.randomBranch(r);
			RelativePos.set(mPos, origin, rp);
			final long pos = mPos.asLong();
			if  (world.getBlockState(mPos).getBlock() != DoomTree.DOOM_LOG) continue;
	
			final int placeCount = addLeaves(world, origin, heart.logs, pos, mPos, r);
			
			if (placeCount > 0) {
				heart.power -= placeCount * 5;
				return;
			}
			
			if (!heart.logs.hasBranches()) return;
		}
	}
	
	private static int addLeaves(World world, BlockPos origin, LogTracker logs, long pos, Mutable mPos, Random r)  {
		int placeCount = 0;
		
		for (int x = -4; x <= 4; x++) {
			for (int z = -4; z <= 4; z++) {
				final int sqd = x * x + z * z;
				
				if (x * x + z * z > 16) continue;

				if(sqd > 1) {
					placeCount += setLeaf(world, mPos.set(BlockPos.add(pos, x, 0, z)));
				}
				
				if (sqd > 9) continue;
				
				placeCount += setLeaf(world, mPos.set(BlockPos.add(pos, x, 1, z)));
				
				
				if(sqd > 1) {
					if(clearAround(logs, RelativePos.relativePos(origin, BlockPos.add(pos, x, 0, z)))) {
						placeCount += setLeaf(world, mPos.set(BlockPos.add(pos, x, -1, z)));
					}
				}
				
				if (sqd > 4) continue;
				
				placeCount += setLeaf(world, mPos.set(BlockPos.add(pos, x, 2, z)));
				
				if (sqd == 0) {
					placeCount += setLeaf(world, mPos.set(BlockPos.add(pos, x, 3, z)));
				}
			}
		}
		return placeCount;
	}

	private static boolean clearAround(LogTracker logs, int relPos) {
		final int x = RelativePos.rx(relPos);
		final int y = RelativePos.ry(relPos);
		final int z = RelativePos.rz(relPos);
		
		if (logs.contains(RelativePos.relativePos(x - 1, y, z - 1))) return false;
		if (logs.contains(RelativePos.relativePos(x - 1, y, z))) return false;
		if (logs.contains(RelativePos.relativePos(x - 1, y, z + 1))) return false;
		if (logs.contains(RelativePos.relativePos(x, y, z - 1))) return false;
		if (logs.contains(RelativePos.relativePos(x, y, z))) return false;
		if (logs.contains(RelativePos.relativePos(x, y, z + 1))) return false;
		if (logs.contains(RelativePos.relativePos(x + 1, y, z - 1))) return false;
		if (logs.contains(RelativePos.relativePos(x + 1, y, z))) return false;
		if (logs.contains(RelativePos.relativePos(x + 1, y, z + 1))) return false;
		
		return true;
	}
	
	private static int setLeaf(World world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		if (state.isAir() || state.getBlock() == DoomTree.MIASMA_BLOCK) {
			return world.setBlockState(pos, DoomTree.LEAF_STATE, 18) ? 1 : 0;
		} else {
			return 0;
		}
	}
}
