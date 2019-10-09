package com.fabriccommunity.spookytime.world.feature;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.function.Function;

public class SpiderLairFeature extends Feature<DefaultFeatureConfig> {

	public SpiderLairFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function) {
		super(function);
	}

	public boolean generate(IWorld iWorld, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig defaultFeatureConfig) {
		if (iWorld.getBlockState(pos.down()).getBlock() == SpookyBlocks.DECEASED_GRASS_BLOCK) {
			iWorld.setBlockState(pos, Blocks.SPAWNER.getDefaultState(), 2);
			BlockEntity be = iWorld.getBlockEntity(pos);
			if (be instanceof MobSpawnerBlockEntity) {
				((MobSpawnerBlockEntity) be).getLogic().setEntityId(EntityType.SPIDER);
			}

			for (int i = 0; i < 64; ++i) {
				BlockPos pos_2 = pos.add(random.nextInt(6) - random.nextInt(6), random.nextInt(3) - random.nextInt(3), random.nextInt(6) - random.nextInt(6));
				if (iWorld.isAir(pos_2) || iWorld.getBlockState(pos_2).getBlock() == SpookyBlocks.DECEASED_GRASS_BLOCK) {
					iWorld.setBlockState(pos_2, Blocks.COBWEB.getDefaultState(), 2);
				}
			}

			BlockPos chestPos = pos.add(random.nextInt(4) - random.nextInt(4), 0, random.nextInt(4) - random.nextInt(4));
			iWorld.setBlockState(chestPos, StructurePiece.method_14916(iWorld, chestPos, Blocks.CHEST.getDefaultState()), 2);
			LootableContainerBlockEntity.setLootTable(iWorld, random, chestPos, SpookyTime.id("chests/spider_lair"));

			return true;
		} else {
			return false;
		}
	}
}
