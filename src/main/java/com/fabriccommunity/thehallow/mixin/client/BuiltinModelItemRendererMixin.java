package com.fabriccommunity.thehallow.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

import com.fabriccommunity.thehallow.entity.HallowedTreasureChestBlockEntity;
import com.fabriccommunity.thehallow.registry.HallowedBlocks;

@Mixin(BuiltinModelItemRenderer.class)
public class BuiltinModelItemRendererMixin {
	private final HallowedTreasureChestBlockEntity chestEntity = new HallowedTreasureChestBlockEntity();
	
	@Inject(at = @At("HEAD"), method = "render(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", cancellable = true)
	private void renderTreasureChest(final ItemStack itemStack, MatrixStack matrix, VertexConsumerProvider vertexConsumerProvider, int light, int overlay, final CallbackInfo info) {
		if (itemStack.getItem().equals(HallowedBlocks.HALLOWED_TREASURE_CHEST.asItem())) {
			BlockEntityRenderDispatcher.INSTANCE.renderEntity(this.chestEntity, matrix, vertexConsumerProvider, light, overlay);
			info.cancel();
		}
	}
}
