package com.fabriccommunity.spookytime.world.layer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.layer.CrossSamplingLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;

import java.util.HashMap;
import java.util.Map;

public enum AddSpookyEdgeLayer implements CrossSamplingLayer {
	INSTANCE;

	private final Map<Biome, Biome> EDGE_BIOME_MAP = new HashMap<Biome, Biome>();

	@Override
	public int sample(LayerRandomnessSource rand, int border1, int border2, int border3, int border4, int centre) {
		Biome biome = Registry.BIOME.get(centre);

		if (EDGE_BIOME_MAP.containsKey(biome)) {
			if ((border1 != centre || border2 != centre || border3 != centre || border4 != centre)) {
				Biome result = EDGE_BIOME_MAP.getOrDefault(biome, biome);
				return Registry.BIOME.getRawId(result);
			}
		}

		return centre;
	}

	public void setEdgeBiome(Biome parent, Biome edge) {
		EDGE_BIOME_MAP.put(parent, edge == null ? parent : edge);
	}
}
