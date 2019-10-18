package com.fabriccommunity.spookytime.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.pattern.BlockPattern;

@Mixin(CarvedPumpkinBlock.class)
public interface CarvedPumpkinBlockAccessor {
	@Invoker
	public BlockPattern callGetSnowGolemDispenserPattern();
	
	@Invoker
	public BlockPattern callGetSnowGolemPattern();
	
	@Invoker
	public BlockPattern callGetIronGolemDispenserPattern();
	
	@Invoker
    public BlockPattern callGetIronGolemPattern();
}
