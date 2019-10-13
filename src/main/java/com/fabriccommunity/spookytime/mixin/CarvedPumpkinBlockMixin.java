package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.util.MixinHelpers;
import net.minecraft.block.BlockState;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Predicate;

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
	 * @author i509vcb -- shutup mixin errors
	 * @reason @see if block below. Also this is for future handling of Bone Golem Logic
	 */
	@Overwrite
	private void trySpawnEntity(World world_1, BlockPos blockPos_1) {
		MixinHelpers.trySpawnEntity(world_1, blockPos_1, (CarvedPumpkinBlock) (Object) this);
	}
}
