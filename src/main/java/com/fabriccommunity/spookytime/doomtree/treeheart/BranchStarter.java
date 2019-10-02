package com.fabriccommunity.spookytime.doomtree.treeheart;

import com.fabriccommunity.spookytime.doomtree.DoomLogBlock;

import net.minecraft.util.math.Direction;

public class BranchStarter  {
	
	private static final Direction[] BRANCH_FACES = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
	
	static Direction branchFace(int y) {
		int dy = y - DoomLogBlock.TERMINAL_HEIGHT + 2;
		dy /= 2;
		return BRANCH_FACES[dy & 3];
	}
	
	static Job addBranches(DoomHeartBlockEntity heart) {
		int y = DoomLogBlock.TERMINAL_HEIGHT + 2;
		final TrunkBuilder builds = heart.builds;
		final LogTracker logs = heart.logs;
		final BranchBuilder branches = heart.branches;
		final int limit = TreeDesigner.MAX_TRUNK_HEIGHT - 4;
		
		while (y < limit) {
			addBranch(builds, logs, branches, y);
			y += 2;
		}

		heart.markDirty();
		
		return null;
	}

	private static void addBranch(TrunkBuilder builds, LogTracker logs, BranchBuilder branches, int y) {
		Direction face = branchFace(y);
		final int relPos = RelativePos.relativePos(face.getOffsetX() * 2, y, face.getOffsetZ() * 2);
		
		if (!logs.contains(relPos)) {
			logs.add(relPos);
			builds.enqueue(relPos);
			branches.enqueue(relPos);
		}
	}
}
