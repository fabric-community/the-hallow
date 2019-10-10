package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Allows plants to be placed on top of our custom Deceased Grass Block.
 *
 * @author Zundrel
 */
@Mixin(PlantBlock.class)
public class PlantBlockMixin {
	@Inject(method = "canPlantOnTop", at = @At("RETURN"), cancellable = true)
	protected void canPlantOnTop(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, CallbackInfoReturnable<Boolean> callbackInfo) {
		if (blockState_1.getBlock() == SpookyBlocks.DECEASED_GRASS_BLOCK) {
			callbackInfo.setReturnValue(true);
		}
	}
}
