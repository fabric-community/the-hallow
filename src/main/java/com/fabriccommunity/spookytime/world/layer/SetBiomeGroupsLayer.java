package com.fabriccommunity.spookytime.world.layer;

import net.minecraft.world.biome.layer.InitLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;

import com.fabriccommunity.spookytime.world.SpookyBiomeGroup;

public enum SetBiomeGroupsLayer implements InitLayer {
	INSTANCE;
	
	@Override
	public int sample(LayerRandomnessSource rand, int x, int z) {
		// 0, 0 tile is always DEFAULT (0)
		return (x == 0 && z == 0) ? 0 : SpookyBiomeGroup.pickRandomBiomeGroup(rand);
	}
}
