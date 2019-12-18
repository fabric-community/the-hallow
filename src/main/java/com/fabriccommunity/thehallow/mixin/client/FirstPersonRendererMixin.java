package com.fabriccommunity.thehallow.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

import com.fabriccommunity.thehallow.item.tool.ClubItem;

@Mixin(HeldItemRenderer.class)
public class FirstPersonRendererMixin {
	
	@Inject(method = "renderFirstPersonItem(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/util/Hand;FLnet/minecraft/item/ItemStack;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("HEAD"), cancellable = true)
	private void dontRenderOffHandItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float f, ItemStack item, float equipProgress, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo info) {
		if(hand == Hand.OFF_HAND && player.getStackInHand(Hand.MAIN_HAND).getItem() instanceof ClubItem) {
			info.cancel();
		}
	}
}
