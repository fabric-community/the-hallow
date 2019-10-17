package com.fabriccommunity.thehallow.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.ItemDynamicRenderer;
import net.minecraft.item.ItemStack;

import com.fabriccommunity.thehallow.entity.HallowedTreasureChestBlockEntity;
import com.fabriccommunity.thehallow.registry.HallowedBlocks;

@Mixin(ItemDynamicRenderer.class)
public class ItemDynamicRendererMixin {
	private final HallowedTreasureChestBlockEntity chestEntity = new HallowedTreasureChestBlockEntity();
	
	@Inject(at = @At("HEAD"), method = "render(Lnet/minecraft/item/ItemStack;)V", cancellable = true)
	private void renderTreasureChest(final ItemStack itemStack, final CallbackInfo info) {
		if (itemStack.getItem().equals(HallowedBlocks.SPOOKY_TREASURE_CHEST.asItem())) {
			BlockEntityRenderDispatcher.INSTANCE.renderEntity(this.chestEntity);
			info.cancel();
		}
	}
}
