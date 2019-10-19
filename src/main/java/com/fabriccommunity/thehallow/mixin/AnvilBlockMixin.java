package com.fabriccommunity.thehallow.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.AnvilBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import com.fabriccommunity.thehallow.registry.HallowedBlocks;

/**
 * Turns pumpkins into a tiny pumpkin when an anvil is dropped on top.
 *
 * @author vini2003
 */
@Mixin(AnvilBlock.class)
public class AnvilBlockMixin {
	@Inject(method = "onLanding(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/block/BlockState;)V", at = @At("HEAD"))
	protected void onLanding(World world, BlockPos upPosition, BlockState blockState_1, BlockState blockState_2, CallbackInfo info) {
		BlockPos downPosition = upPosition.offset(Direction.DOWN);
		Block checkBlock = world.getBlockState(downPosition).getBlock();
		Block anvilBlock = world.getBlockState(upPosition).getBlock();
		if (checkBlock instanceof PumpkinBlock) {
			Block.dropStack(world, upPosition, anvilBlock.getPickStack(world, upPosition, world.getBlockState(downPosition)));
			world.clearBlockState(upPosition, true);
			Block.dropStack(world, downPosition, new ItemStack(HallowedBlocks.TINY_PUMPKIN));
			world.clearBlockState(downPosition, true);
		}
	}
}
