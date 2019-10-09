package com.fabriccommunity.spookytime.entity;

import java.util.Optional;

import net.minecraft.block.BlockState;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.SnowGolemEntity;

public interface SnowGolemEntityModifiers {
	public static final TrackedData<Optional<BlockState>> HEAD_BLOCK_STATE = DataTracker.registerData(SnowGolemEntity.class, TrackedDataHandlerRegistry.OPTIONAL_BLOCK_STATE);
	
	public Optional<BlockState> getHeadState();
	
	public void setHeadState(BlockState state);
}
