package com.fabriccommunity.spookytime.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;

/**
 * Allows plants to be placed on top of our custom Deceased Grass Block.
 *
 * @author Zundrel
 */
@Mixin(PlantBlock.class)
public class PlantBlockMixin {
	@Inject(method = "canPlantOnTop(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z", at = @At("RETURN"), cancellable = true)
	protected void canPlantOnTop(BlockState blockState, BlockView blockView, BlockPos blockPos, CallbackInfoReturnable<Boolean> callbackInfo) {
		if (blockState.getBlock() == SpookyBlocks.DECEASED_GRASS_BLOCK || blockState.getBlock() == SpookyBlocks.DECEASED_DIRT) {
			callbackInfo.setReturnValue(true);
		}
	}
}
