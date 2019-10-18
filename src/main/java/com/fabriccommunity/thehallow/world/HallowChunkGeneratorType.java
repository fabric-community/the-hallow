package com.fabriccommunity.thehallow.world;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

import com.fabriccommunity.thehallow.TheHallow;

import java.util.function.Supplier;

public class HallowChunkGeneratorType extends ChunkGeneratorType<HallowChunkGeneratorConfig, HallowChunkGenerator> {
	
	public static final HallowChunkGeneratorType INSTANCE = Registry.register(Registry.CHUNK_GENERATOR_TYPE, TheHallow.id("spooky"), new HallowChunkGeneratorType(false, () -> new HallowChunkGeneratorConfig()));
	
	public HallowChunkGeneratorType(boolean buffetScreen, Supplier<HallowChunkGeneratorConfig> configSupplier) {
		super(null, buffetScreen, configSupplier);
	}
	
	public static void init() {
		// NO-OP
	}
	
	@Override
	public HallowChunkGenerator create(World world, BiomeSource biomeSource, HallowChunkGeneratorConfig config) {
		return new HallowChunkGenerator(world, biomeSource, config);
	}
}
