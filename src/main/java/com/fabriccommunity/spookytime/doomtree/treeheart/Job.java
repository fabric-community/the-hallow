package com.fabriccommunity.spookytime.doomtree.treeheart;

@FunctionalInterface interface Job {
	Job apply(DoomHeartBlockEntity heart);
}