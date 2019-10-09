package com.fabriccommunity.spookytime.mixin;

import java.util.function.Predicate;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.fabriccommunity.spookytime.MixinHelpers;

import net.minecraft.block.BlockState;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(CarvedPumpkinBlock.class)
public abstract class CarvedPumpkinBlockMixin extends HorizontalFacingBlock {
	
	protected CarvedPumpkinBlockMixin(Settings block$Settings_1) {
		super(block$Settings_1);
	}

	static {
		IS_PUMPKIN_PREDICATE = MixinHelpers.IS_PUMPKIN;
	}
	
	@Shadow
	@Final
	@Mutable
	private static Predicate<BlockState> IS_PUMPKIN_PREDICATE;
	
	/**
	 * @reason @see if block below. Also this is for future handling of Bone Golem Logic
	 * @param world_1
	 * @param blockPos_1
	 */
	@Overwrite
	private void trySpawnEntity(World world_1, BlockPos blockPos_1) {
		MixinHelpers.trySpawnEntity(world_1, blockPos_1, (CarvedPumpkinBlock) (Object) this);
    }
}
