package com.fabriccommunity.thehallow.world.layer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.layer.IdentitySamplingLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;

import com.fabriccommunity.thehallow.world.HallowedBiomeGroup;

public enum AddBiomesLayer implements IdentitySamplingLayer {
	INSTANCE;
	
	@Override
	public int sample(LayerRandomnessSource rand, int groupId) {
		HallowedBiomeGroup group = HallowedBiomeGroup.getById(groupId);
		
		return Registry.BIOME.getRawId(group.pickBiome(rand));
	}
}
