package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.common.SpookyBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Mixin(PlantBlock.class)
public class MixinPlantBlock {
	@Inject(method = "canPlantOnTop", at = @At("RETURN"), cancellable = true)
	protected void canPlantOnTop(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, CallbackInfoReturnable<Boolean> callbackInfo) {
		if (blockState_1.getBlock() == SpookyBlocks.DECEASED_GRASS_BLOCK)
			callbackInfo.setReturnValue(true);
	}
}
