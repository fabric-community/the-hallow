package com.fabriccommunity.spookytime.doomtree.treeheart;

import static com.fabriccommunity.spookytime.doomtree.treeheart.TreeDesigner.canReplace;

import it.unimi.dsi.fastutil.ints.IntHeapPriorityQueue;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** Tracks and builds in log spaces needing placement */
@SuppressWarnings("serial")
class TrunkBuilder extends IntHeapPriorityQueue {

	private final IntOpenHashSet set = new IntOpenHashSet();

	private final int originX;
	private final int originY;
	private final int originZ;

	TrunkBuilder(BlockPos origin) {
		super((i0, i1) -> Integer.compare(RelativePos.squaredDistance(i0), RelativePos.squaredDistance(i1)));

		originX = origin.getX();
		originY = origin.getY();
		originZ = origin.getZ();
	}

	void enqueue(long pos) {
		enqueue(RelativePos.relativePos(originX, originY, originZ, pos));
	}

	void enqueue(BlockPos pos) {
		enqueue(RelativePos.relativePos(originX, originY, originZ, pos));
	}

	@Override
	public void enqueue(int relativePos) {
		if (set.add(relativePos)) {
			super.enqueue(relativePos);
		}
	}

	@Override
	public int dequeueInt() {
		final int result = super.dequeueInt();
		set.remove(result);
		return result;
	}

	static final int PLACE_LIMIT = 16;
	static final int CHECK_LIMIT = 64;

	void build(DoomHeartBlockEntity heart) {
		final World world = heart.getWorld();
		final BlockPos.Mutable mPos = heart.mPos;
		int placeCount = 0;

		for (int i = 0; i < CHECK_LIMIT; i++) {
			if (heart.power >= 50 && !this.isEmpty()) {
				mPos.set(RelativePos.absolutePos(originX, originY, originZ, dequeueInt()));
				final BlockState currentState = world.getBlockState(mPos);

				if(currentState.getBlock() == Blocks.BEDROCK) {
					continue;
				}

				final BlockState targetState = TreeDesigner.logState(mPos, heart);

				if (targetState != currentState && canReplace(world, mPos)) {
					world.setBlockState(mPos, targetState, 18);
					heart.power -= 50;

					if (++placeCount >= PLACE_LIMIT) break;
				}
			}
		}

		if (placeCount > 0) {
			heart.resetTickCounter();
		}
	}

	int buildDistanceSquared() {
		return isEmpty() ? Integer.MAX_VALUE : RelativePos.squaredDistance(firstInt());
	}
}
