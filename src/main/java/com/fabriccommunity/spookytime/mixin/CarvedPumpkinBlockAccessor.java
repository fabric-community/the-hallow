package com.fabriccommunity.spookytime.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.pattern.BlockPattern;

@Mixin(CarvedPumpkinBlock.class)
public interface CarvedPumpkinBlockAccessor {
	@Invoker
	public BlockPattern getGetSnowGolemDispenserPattern();
	
	@Invoker
	public BlockPattern getGetSnowGolemPattern();
	
	@Invoker
	public BlockPattern getGetIronGolemDispenserPattern();
	
	@Invoker
    public BlockPattern getGetIronGolemPattern();
}
