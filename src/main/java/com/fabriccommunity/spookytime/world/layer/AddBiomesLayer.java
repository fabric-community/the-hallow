package com.fabriccommunity.spookytime.world.layer;

import com.fabriccommunity.spookytime.world.SpookyBiomeGroup;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.layer.IdentitySamplingLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;

public enum AddBiomesLayer implements IdentitySamplingLayer {
	INSTANCE;

	@Override
	public int sample(LayerRandomnessSource rand, int groupId) {
		SpookyBiomeGroup group = SpookyBiomeGroup.getById(groupId);
		
		return Registry.BIOME.getRawId(group.pickBiome(rand));
	}
	
	
}
