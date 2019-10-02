package com.fabriccommunity.spookytime.doomtree.treeheart;

import it.unimi.dsi.fastutil.ints.IntIterator;

public class BuildPopulator implements Job {

	final IntIterator it;

	BuildPopulator(DoomHeartBlockEntity heart) {
		it = heart.logs.iterator();
	}

	@Override
	public Job apply(DoomHeartBlockEntity heart) {
		final TrunkBuilder builds = heart.builds;

		for (int i = 0; i < 256; i++) {
			if (it.hasNext()) {
				builds.enqueue(it.nextInt());
			} else {
				return BranchStarter::addBranches;
			}
		}

		return this;
	}
}
