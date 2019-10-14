package com.fabriccommunity.spookytime.world;

import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

import java.util.function.Supplier;

public class SpookyChunkGeneratorType extends ChunkGeneratorType<SpookyChunkGeneratorConfig, SpookyChunkGenerator> {
	
	public static final SpookyChunkGeneratorType INSTANCE = new SpookyChunkGeneratorType(false, () -> new SpookyChunkGeneratorConfig());
	
	public SpookyChunkGeneratorType(boolean buffetScreen, Supplier<SpookyChunkGeneratorConfig> configSupplier) {
		super(null, buffetScreen, configSupplier);
	}
	
	@Override
	public SpookyChunkGenerator create(World world, BiomeSource biomeSource, SpookyChunkGeneratorConfig config) {
		return new SpookyChunkGenerator(world, biomeSource, config);
	}
}
