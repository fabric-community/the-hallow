package com.fabriccommunity.thehallow.world.layer;

import net.minecraft.world.biome.layer.InitLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;

import com.fabriccommunity.thehallow.world.HallowBiomeGroup;

public enum SetBiomeGroupsLayer implements InitLayer {
	INSTANCE;
	
	@Override
	public int sample(LayerRandomnessSource rand, int x, int z) {
		// 0, 0 tile is always DEFAULT (0)
		return (x == 0 && z == 0) ? 0 : HallowBiomeGroup.pickRandomBiomeGroup(rand);
	}
}
