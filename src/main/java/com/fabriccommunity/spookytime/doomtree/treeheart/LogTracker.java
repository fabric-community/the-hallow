package com.fabriccommunity.spookytime.doomtree.treeheart;

import java.util.Random;

import com.fabriccommunity.spookytime.doomtree.DoomLogBlock;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.util.math.BlockPos;

/** Tracks spaces that should be logs */
@SuppressWarnings("serial")
class LogTracker extends IntOpenHashSet {
	private final int originX;
	private final int originY;
	private final int originZ;
	
	private final IntArrayList branches = new IntArrayList();
	
	LogTracker(BlockPos origin) {
		originX = origin.getX();
		originY = origin.getY();
		originZ = origin.getZ();
	}
	
	boolean contains(long pos) {
		return super.contains(RelativePos.relativePos(originX, originY, originZ, pos));
	}
	
	boolean contains(BlockPos pos) {
		return super.contains(RelativePos.relativePos(originX, originY, originZ, pos));
	}
	
	@Override
	public boolean contains(int relativePos) {
		return super.contains(relativePos);
	}
	
	boolean add(long pos) {
		return add(RelativePos.relativePos(originX, originY, originZ, pos));
	}
	
	boolean add(BlockPos pos) {
		return add(RelativePos.relativePos(originX, originY, originZ, pos));
	}
	
	@Override
	public boolean add(int relativePos) {
		final boolean result = super.add(relativePos);
		
		if (result 
				&& RelativePos.ry(relativePos) > DoomLogBlock.TERMINAL_HEIGHT 
				&& Math.abs(RelativePos.rx(relativePos)) > 1
				&& Math.abs(RelativePos.rz(relativePos)) > 1) {
			branches.add(relativePos);
		}
		
		return result;
	}
	
	void fromArray(int[] relativePositions) {
		clear();
		branches.clear();
		for (int pos : relativePositions) {
			add(pos);
		}
	}
	
	boolean hasBranches() {
		return !branches.isEmpty();
	}
	
	int randomBranch(Random rand) {
		return branches.removeInt(rand.nextInt(branches.size()));
	}
}
