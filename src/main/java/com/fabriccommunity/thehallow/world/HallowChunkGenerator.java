package com.fabriccommunity.thehallow.world;

import net.minecraft.entity.EntityCategory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.SystemUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.IWorld;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;

import java.util.List;

public class HallowChunkGenerator extends SurfaceChunkGenerator<HallowChunkGeneratorConfig> {
	
	private static final float[] BIOME_WEIGHT_TABLE = SystemUtil.consume(new float[25], (array) -> {
		for (int xOffset = -2; xOffset <= 2; ++xOffset) {
			for (int zOffset = -2; zOffset <= 2; ++zOffset) {
				float value = 10.0F / MathHelper.sqrt((float) (xOffset * xOffset + zOffset * zOffset) + 0.2F);
				array[xOffset + 2 + (zOffset + 2) * 5] = value;
			}
		}
		
	});
	
	private static final double noiseFrequency = 200;
	
	private final OctavePerlinNoiseSampler noiseSampler;
	
	public HallowChunkGenerator(IWorld world, BiomeSource biomeSource, HallowChunkGeneratorConfig chunkGeneratorConfig) {
		super(world, biomeSource, 4, 8, 256, chunkGeneratorConfig, true);
		this.random.consume(2620);
		this.noiseSampler = new OctavePerlinNoiseSampler(this.random, 16);
	}
	
	public void populateEntities(ChunkRegion region) {
		int centreX = region.getCenterChunkX();
		int centreZ = region.getCenterChunkZ();
		Biome biome = region.getChunk(centreX, centreZ).getBiomeArray()[0];
		ChunkRandom rand = new ChunkRandom();
		rand.setSeed(region.getSeed(), centreX << 4, centreZ << 4);
		SpawnHelper.populateEntities(region, biome, centreX, centreZ, rand);
	}
	
	protected void sampleNoiseColumn(double[] array, int x, int z) {
		this.sampleNoiseColumn(array, x, z, 684.4119873046875D, 684.4119873046875D, 8.555149841308594D, 4.277574920654297D, 3, -10);
	}
	
	protected double computeNoiseFalloff(double depth, double scale, int y) {
		double result = ((double) y - (8.5D + depth * 8.5D / 8.0D * 4.0D)) * 12.0D * 128.0D / 256.0D / scale;
		if (result < 0.0D) {
			result *= 4.0D;
		}
		
		return result;
	}
	
	protected double[] computeNoiseRange(int x, int z) {
		double[] values = new double[2];
		float scaleResult = 0.0F;
		float depthResult = 0.0F;
		float divisor = 0.0F;
		float someBiomeDepth = this.biomeSource.getBiomeForNoiseGen(x, z).getDepth();
		
		for (int xOffset = -2; xOffset <= 2; ++xOffset) {
			for (int zOffset = -2; zOffset <= 2; ++zOffset) {
				Biome biome = this.biomeSource.getBiomeForNoiseGen(x + xOffset, z + zOffset);
				float depth = biome.getDepth();
				float scale = biome.getScale();
				
				float multiplier = BIOME_WEIGHT_TABLE[xOffset + 2 + (zOffset + 2) * 5] / (depth + 2.0F);
				if (biome.getDepth() > someBiomeDepth) {
					multiplier /= 2.0F;
				}
				
				scaleResult += scale * multiplier;
				depthResult += depth * multiplier;
				divisor += multiplier;
			}
		}
		
		scaleResult /= divisor;
		depthResult /= divisor;
		scaleResult = scaleResult * 0.9F + 0.1F;
		depthResult = (depthResult * 4.0F - 1.0F) / 8.0F;
		values[0] = (double) depthResult + this.sampleNoise(x, z);
		values[1] = scaleResult;
		return values;
	}
	
	private double sampleNoise(int x, int z) {
		double result = this.noiseSampler.sample(x * noiseFrequency, 10.0D, z * noiseFrequency, 1.0D, 0.0D, true) / 8000.0D;
		if (result < 0.0D) {
			result = -result * 0.3D;
		}
		
		result = result * 3.0D - 2.0D;
		if (result < 0.0D) {
			result /= 28.0D;
		} else {
			if (result > 1.0D) {
				result = 1.0D;
			}
			
			result /= 40.0D;
		}
		
		return result;
	}
	
	public List<Biome.SpawnEntry> getEntitySpawnList(EntityCategory category, BlockPos pos) {
		// Custom feature spawn stuff goes here
		
		return super.getEntitySpawnList(category, pos);
	}
	
	public void spawnEntities(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals) {
	
	}
	
	public int getSpawnHeight() {
		return this.world.getSeaLevel() + 1;
	}
	
	public int getSeaLevel() {
		return 63;
	}
	
}
