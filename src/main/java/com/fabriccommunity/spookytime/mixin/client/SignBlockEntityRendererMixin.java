package com.fabriccommunity.spookytime.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;

import com.fabriccommunity.spookytime.block.SpookySign;

@Mixin(SignBlockEntityRenderer.class)
public class SignBlockEntityRendererMixin {
	@Inject(method = "getModelTexture(Lnet/minecraft/block/Block;)Lnet/minecraft/util/Identifier;", at = @At("HEAD"), cancellable = true)
	private void getModelTexture(Block block, CallbackInfoReturnable info) {
		if (block instanceof SpookySign) {
			info.setReturnValue(((SpookySign) block).getTexture());
		}
	}
}
