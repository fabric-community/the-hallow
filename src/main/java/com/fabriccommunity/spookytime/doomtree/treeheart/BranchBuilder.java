package com.fabriccommunity.spookytime.doomtree.treeheart;

import java.util.Random;

import com.fabriccommunity.spookytime.doomtree.DoomLogBlock;
import com.fabriccommunity.spookytime.doomtree.DoomTree;

import io.netty.util.internal.ThreadLocalRandom;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntHeapPriorityQueue;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.World;

/** Tracks log spaces needing placement */
@SuppressWarnings("serial")
class BranchBuilder extends IntHeapPriorityQueue {
	private final int originX;
	private final int originY;
	private final int originZ;
	private final IntArrayList candidates = new IntArrayList();

	int placeCount = 0;

	BranchBuilder(BlockPos origin) {
		super((i0, i1) -> Integer.compare(RelativePos.squaredDistance(i0), RelativePos.squaredDistance(i1)));

		originX = origin.getX();
		originY = origin.getY();
		originZ = origin.getZ();
	}

	void enqueue(long pos) {
		super.enqueue(RelativePos.relativePos(originX, originY, originZ, pos));
	}

	void enqueue(BlockPos pos) {
		super.enqueue(RelativePos.relativePos(originX, originY, originZ, pos));
	}

	@Override
	public void enqueue(int relativePos) {
		super.enqueue(relativePos);
	}

	void build(DoomHeartBlockEntity heart) {
		if (isEmpty()) return;

		final int start = this.dequeueInt();
		final World world = heart.getWorld();
		final BlockPos.Mutable mPos = heart.mPos;
		final BlockState currentState = world.getBlockState(RelativePos.set(mPos, originX,  originY, originZ, start));

		if (currentState.getBlock() != DoomTree.DOOM_LOG) {
			if (TreeDesigner.canReplace(currentState)) {
				heart.builds.enqueue(start);
				enqueue(start);
			}
			return;
		}

		final Random rand = ThreadLocalRandom.current();
		placeCount = 0;
		placeBranch(world, mPos, heart.logs, rand, start, 4);

		if (placeCount > 0) {
			heart.markDirty();
			heart.resetTickCounter();
			heart.power -= placeCount * 20;
		}
	}
	
	private final static BlockState LOG_STATE = DoomTree.LOG_STATE.with(DoomLogBlock.HEIGHT, DoomLogBlock.MAX_HEIGHT);

	private void placeBranch(World world, BlockPos.Mutable mPos, LogTracker logs, Random rand, int startPos, int allowance) {
		final int rx = RelativePos.rx(startPos);
		final int ry = RelativePos.ry(startPos);
		final int rz = RelativePos.rz(startPos);

		final IntArrayList candidates = this.candidates;
		candidates.clear();

		addCandidate(world, mPos, logs, startPos, rx, ry, rz - 1, candidates);
		addCandidate(world, mPos, logs, startPos, rx, ry, rz + 1, candidates);
		addCandidate(world, mPos, logs, startPos, rx - 1, ry, rz, candidates);
		addCandidate(world, mPos, logs, startPos, rx + 1, ry, rz, candidates);
		
		if (candidates.isEmpty()) return;

		final int first = candidates.removeInt(rand.nextInt(candidates.size()));
		final int second = candidates.isEmpty() || rand.nextInt(12) != 0 ? -1 : candidates.removeInt(rand.nextInt(candidates.size()));

		setLog(world, mPos, logs, first);
		placeCount++;

		if (second != -1) {
			setLog(world, mPos, logs, second);
			placeCount++;
		}

		if (allowance > 1) {
			placeBranch(world, mPos, logs, rand, first, allowance - 1);

			if (second != -1) {
				placeBranch(world, mPos, logs, rand, second, allowance - 1);
			}
		} else {
			enqueue(first);

			if (second != -1) {
				enqueue(second);
			}
		}
	}

	private void setLog(World world, BlockPos.Mutable mPos, LogTracker logs, int relPos) {
		logs.add(relPos);
		world.setBlockState(RelativePos.set(mPos, originX, originY, originZ, relPos), LOG_STATE);
		
		BlockState underState = world.getBlockState(mPos.setOffset(Direction.DOWN));
		if(underState != DoomTree.MIASMA_STATE && underState != DoomTree.GLEAM_STATE 
				&& (underState == DoomTree.LEAF_STATE || underState.isAir() || TreeDesigner.canReplace(underState))) {
			Troll.placeMiasma(mPos, world);
		}
	}
	
	private void addCandidate(World world, Mutable mPos, LogTracker logs, int startPos, int x, int y, int z, IntArrayList candidates) {
		final int p = RelativePos.relativePos(x, y, z);

		final int rsq = TreeDesigner.canopyRadiusSquared(y);

		if (x * x + z * z > rsq) return;

		if (!isOutgoing(startPos, x, y, z)) return;

		if (logs.contains(p)) return;

		if (isCrowded(logs, startPos, x, y, z)) return;

		mPos.set(originX + x, originY + y, originZ + z);

		if (!World.isValid(mPos) || !world.isBlockLoaded(mPos)) return;

		if (!TreeDesigner.canReplace(world, mPos)) return;

		candidates.add(p);
	}

	private static boolean isOutgoing(int startPos, int x, int y, int z) {
		final Direction face = BranchStarter.branchFace(y);
		final int ox = face.getOffsetX();
		final int oz = face.getOffsetZ();
		
		if(oz == 0) {
			final int dx = x - RelativePos.rx(startPos);
			return dx == 0 || dx == ox;
		} else {
			final int dz = z - RelativePos.rz(startPos);
			return dz == 0 || dz == oz;
		}
	}
	
	private static boolean isCrowded(LogTracker logs, int startPos, int x, int y, int z) {
		if (isCrowding(logs, startPos, x-1, y, z-1)) return true;
		if (isCrowding(logs, startPos, x-1, y, z)) return true;
		if (isCrowding(logs, startPos, x-1, y, z+1)) return true;
		
		if (isCrowding(logs, startPos, x, y, z-1)) return true;
		if (isCrowding(logs, startPos, x, y, z+1)) return true;
		
		if (isCrowding(logs, startPos, x+1, y, z-1)) return true;
		if (isCrowding(logs, startPos, x+1, y, z)) return true;
		if (isCrowding(logs, startPos, x+1, y, z+1)) return true;
		
		if (isCrowding(logs, startPos, x-2, y, z)) return true;
		if (isCrowding(logs, startPos, x+2, y, z)) return true;
		if (isCrowding(logs, startPos, x, y, z-2)) return true;
		if (isCrowding(logs, startPos, x, y, z+2)) return true;
		
		return false;
	}

	private static boolean isCrowding(LogTracker logs, int startPos, int x, int y, int z) {
		final int p = RelativePos.relativePos(x, y, z); 
		return p != startPos && logs.contains(p) && !isAdjacent(startPos, x, y, z);
	}

	private static boolean isAdjacent(int startPos, int x, int y, int z) {
		return Math.abs(RelativePos.rx(startPos) - x) + Math.abs(RelativePos.ry(startPos) - y) + Math.abs(RelativePos.rz(startPos) - z) == 1;
	}

	int[] toIntArray() {
		int[] result = new int[size];
		System.arraycopy(heap, 0, result, 0, size);
		return result;
	}

	void fromArray(int[] relativePositions) {
		clear();
		for (int p : relativePositions) {
			enqueue(p);
		}
	}
	
	int buildDistanceSquared() {
		return isEmpty() ? Integer.MAX_VALUE : RelativePos.squaredDistance(firstInt());
	}
}
