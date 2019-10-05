package com.fabriccommunity.spookytime.world.layer;

import com.fabriccommunity.spookytime.registry.SpookyBiomes;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.layer.CrossSamplingLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;

import java.util.HashMap;
import java.util.Map;

public enum AddSpookyShoreLayer implements CrossSamplingLayer {
	DEFAULT(SpookyBiomes.SPOOKY_SHORE);
	
	private final Biome defaultEdge;
	private final Map<Biome, Biome> EDGE_BIOME_MAP = new HashMap<>();
	
	AddSpookyShoreLayer(Biome defaultEdge) {
		this.defaultEdge = defaultEdge;
	}
	
	@Override
	public int sample(LayerRandomnessSource rand, int border1, int border2, int border3, int border4, int centre) {
		final int SEA_ID = Registry.BIOME.getRawId(SpookyBiomes.SPOOKY_SEA); // not a constant since this could change on registry remapping
		
		if ((centre != SEA_ID) && (border1 == SEA_ID || border2 == SEA_ID || border3 == SEA_ID || border4 == SEA_ID)) {
			Biome biome = Registry.BIOME.get(centre);
			Biome result = EDGE_BIOME_MAP.getOrDefault(biome, defaultEdge);
			return Registry.BIOME.getRawId(result);
		}
		
		return centre;
	}
	
	public void setEdgeBiome(Biome parent, Biome edge) {
		EDGE_BIOME_MAP.put(parent, edge == null ? parent : edge);
	}
}
