package com.fabriccommunity.spookytime.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;

import com.fabriccommunity.spookytime.block.SpookySign;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
	@Inject(method = "supports", at = @At("HEAD"), cancellable = true)
	private void supports(Block block, CallbackInfoReturnable info) {
		if (BlockEntityType.SIGN.equals(this) && block instanceof SpookySign) {
			info.setReturnValue(true);
		}
	}
}
