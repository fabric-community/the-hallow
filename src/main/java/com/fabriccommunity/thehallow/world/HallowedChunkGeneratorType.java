package com.fabriccommunity.thehallow.world;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

import com.fabriccommunity.thehallow.TheHallow;

import java.util.function.Supplier;

public class HallowedChunkGeneratorType extends ChunkGeneratorType<HallowedChunkGeneratorConfig, HallowedChunkGenerator> {
	
	public static final HallowedChunkGeneratorType INSTANCE = Registry.register(Registry.CHUNK_GENERATOR_TYPE, TheHallow.id("spooky"), new HallowedChunkGeneratorType(false, () -> new HallowedChunkGeneratorConfig()));
	
	public HallowedChunkGeneratorType(boolean buffetScreen, Supplier<HallowedChunkGeneratorConfig> configSupplier) {
		super(null, buffetScreen, configSupplier);
	}
	
	public static void init() {
		// NO-OP
	}
	
	@Override
	public HallowedChunkGenerator create(World world, BiomeSource biomeSource, HallowedChunkGeneratorConfig config) {
		return new HallowedChunkGenerator(world, biomeSource, config);
	}
}
