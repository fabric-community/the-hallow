package com.fabriccommunity.spookytime.world;

import java.util.List;

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

public class SpookyChunkGenerator extends SurfaceChunkGenerator<SpookyChunkGeneratorConfig> {

	private static final float[] BIOME_WEIGHT_TABLE = (float[])SystemUtil.consume(new float[25], (floats_1) -> {
		for(int int_1 = -2; int_1 <= 2; ++int_1) {
			for(int int_2 = -2; int_2 <= 2; ++int_2) {
				float float_1 = 10.0F / MathHelper.sqrt((float)(int_1 * int_1 + int_2 * int_2) + 0.2F);
				floats_1[int_1 + 2 + (int_2 + 2) * 5] = float_1;
			}
		}

	});
	
	private final OctavePerlinNoiseSampler noiseSampler;

	public SpookyChunkGenerator(IWorld world, BiomeSource biomeSource, SpookyChunkGeneratorConfig chunkGeneratorConfig) {
		super(world, biomeSource, 4, 8, 256, chunkGeneratorConfig, true);
		this.random.consume(2620);
		this.noiseSampler = new OctavePerlinNoiseSampler(this.random, 16);
	}

	public void populateEntities(ChunkRegion chunkRegion_1) {
		int int_1 = chunkRegion_1.getCenterChunkX();
		int int_2 = chunkRegion_1.getCenterChunkZ();
		Biome biome_1 = chunkRegion_1.getChunk(int_1, int_2).getBiomeArray()[0];
		ChunkRandom chunkRandom_1 = new ChunkRandom();
		chunkRandom_1.setSeed(chunkRegion_1.getSeed(), int_1 << 4, int_2 << 4);
		SpawnHelper.populateEntities(chunkRegion_1, biome_1, int_1, int_2, chunkRandom_1);
	}

	protected void sampleNoiseColumn(double[] doubles_1, int int_1, int int_2) {
		double double_1 = 684.4119873046875D;
		double double_2 = 684.4119873046875D;
		double double_3 = 8.555149841308594D;
		double double_4 = 4.277574920654297D;
		int int_3 = 3;
		int int_4 = -10;
		this.sampleNoiseColumn(doubles_1, int_1, int_2, double_1, double_2, double_3, double_4, int_3, int_4);
	}

	protected double computeNoiseFalloff(double depth, double scale, int y) {
		double double_4 = ((double)y - (8.5D + depth * 8.5D / 8.0D * 4.0D)) * 12.0D * 128.0D / 256.0D / scale;
		if (double_4 < 0.0D) {
			double_4 *= 4.0D;
		}

		return double_4;
	}

	protected double[] computeNoiseRange(int x, int z) {
		double[] values = new double[2];
		float scaleResult = 0.0F;
		float depthResult = 0.0F;
		float divisor = 0.0F;
		float someBiomeDepth = this.biomeSource.getBiomeForNoiseGen(x, z).getDepth();

		for(int xOffset = -2; xOffset <= 2; ++xOffset) {
			for(int zOffset = -2; zOffset <= 2; ++zOffset) {
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
		values[0] = (double)depthResult + this.sampleNoise(x, z);
		values[1] = (double)scaleResult;
		return values;
	}

	private double sampleNoise(int int_1, int int_2) {
		double double_1 = this.noiseSampler.sample((double)(int_1 * 200), 10.0D, (double)(int_2 * 200), 1.0D, 0.0D, true) / 8000.0D;
		if (double_1 < 0.0D) {
			double_1 = -double_1 * 0.3D;
		}

		double_1 = double_1 * 3.0D - 2.0D;
		if (double_1 < 0.0D) {
			double_1 /= 28.0D;
		} else {
			if (double_1 > 1.0D) {
				double_1 = 1.0D;
			}

			double_1 /= 40.0D;
		}

		return double_1;
	}

	public List<Biome.SpawnEntry> getEntitySpawnList(EntityCategory entityCategory_1, BlockPos blockPos_1) {
		// Custom feature spawn stuff goes here

		return super.getEntitySpawnList(entityCategory_1, blockPos_1);
	}

	public void spawnEntities(ServerWorld serverWorld_1, boolean boolean_1, boolean boolean_2) {
		
	}

	public int getSpawnHeight() {
		return this.world.getSeaLevel() + 1;
	}

	public int getSeaLevel() {
		return 63;
	}

}
