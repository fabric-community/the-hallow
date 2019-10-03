package com.fabriccommunity.spookytime.world;

import com.fabriccommunity.spookytime.registry.SpookyBiomes;
import com.fabriccommunity.spookytime.world.layer.SpookyBiomeLayers;
import com.google.common.collect.Sets;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.layer.BiomeLayerSampler;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SpookyBiomeSource extends BiomeSource {
	public final Biome[] allowedBiomes;
	
	public final BiomeLayerSampler biomeLayer, noiseLayer;
	
	public SpookyBiomeSource(long seed) {
		allowedBiomes = new Biome[]{
				SpookyBiomes.SPOOKY_FOREST,
				SpookyBiomes.SPOOKY_LOWLANDS,
				SpookyBiomes.SPOOKY_LOWLANDS_BARROWS,
				SpookyBiomes.SPOOKY_LOWLANDS_PUMPKINS,
				SpookyBiomes.SPOOKY_RIVER
		};
		
		BiomeLayerSampler[] samplers = SpookyBiomeLayers.build(seed);
		
		noiseLayer = samplers[0];
		biomeLayer = samplers[1];
	}
	
	@Override
	public Biome getBiome(int x, int z) {
		return biomeLayer.sample(x, z);
	}
	
	@Override
	public Biome getBiomeForNoiseGen(int x, int z) {
		return noiseLayer.sample(x, z);
	}
	
	@Override
	public Biome[] sampleBiomes(int x, int z, int xSize, int zSize, boolean cacheFlag) {
		return this.biomeLayer.sample(x, z, xSize, zSize);
	}
	
	@Override
	public Set<Biome> getBiomesInArea(int x, int z, int range) {
		int int_4 = x - range >> 2;
		int int_5 = z - range >> 2;
		int int_6 = x + range >> 2;
		int int_7 = z + range >> 2;
		int int_8 = int_6 - int_4 + 1;
		int int_9 = int_7 - int_5 + 1;
		Set<Biome> set_1 = Sets.newHashSet();
		Collections.addAll(set_1, this.noiseLayer.sample(int_4, int_5, int_8, int_9));
		return set_1;
	}
	
	@Override
	public BlockPos locateBiome(int x, int z, int range, List<Biome> biomes, Random random) {
		int i = x - range >> 2;
		int j = z - range >> 2;
		int k = x + range >> 2;
		int l = z + range >> 2;
		int i1 = k - i + 1;
		int j1 = l - j + 1;
		BlockPos blockpos = null;
		int k1 = 0;
		Biome[] biomesInArea = this.sampleBiomes(x, z, i1, j1);
		
		for (int l1 = 0; l1 < i1 * j1; ++l1) {
			int i2 = i + l1 % i1 << 2;
			int j2 = j + l1 / i1 << 2;
			Biome biome = biomesInArea[l1];
			
			if (biomes.contains(biome) && (blockpos == null || random.nextInt(k1 + 1) == 0)) {
				blockpos = new BlockPos(i2, 0, j2);
				++k1;
			}
		}
		return blockpos;
	}
	
	@Override
	public boolean hasStructureFeature(StructureFeature<?> var1) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Set<BlockState> getTopMaterials() {
		if (this.topMaterials.isEmpty()) {
			Biome[] var1 = this.allowedBiomes;
			int var2 = var1.length;
			
			for (int var3 = 0; var3 < var2; ++var3) {
				Biome biome_1 = var1[var3];
				this.topMaterials.add(biome_1.getSurfaceConfig().getTopMaterial());
			}
		}
		
		return this.topMaterials;
	}
}
