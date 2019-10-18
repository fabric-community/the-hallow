package com.fabriccommunity.thehallow.world.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.FeatureConfig;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import com.google.common.collect.ImmutableMap;

public class HallowOreFeatureConfig implements FeatureConfig {
	public final BlockState state;
	public final int size;
	
	public HallowOreFeatureConfig(BlockState blockState, int size) {
		this.state = blockState;
		this.size = size;
	}
	
	public static HallowOreFeatureConfig deserialize(Dynamic<?> dynamic) {
		int int_1 = dynamic.get("size").asInt(0);
		BlockState blockState_1 = dynamic.get("state").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		return new HallowOreFeatureConfig(blockState_1, int_1);
	}
	
	@Override
	public <T> Dynamic<T> serialize(DynamicOps<T> dynamicOps) {
		return new Dynamic(dynamicOps, dynamicOps.createMap(ImmutableMap.of(dynamicOps.createString("size"), dynamicOps.createInt(this.size), dynamicOps.createString("state"), BlockState.serialize(dynamicOps, this.state).getValue())));
	}
}
