package com.fabriccommunity.spookytime.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.ItemDynamicRenderer;
import net.minecraft.item.ItemStack;

import com.fabriccommunity.spookytime.entity.SpookyTreasureChestBlockEntity;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;

@Mixin(ItemDynamicRenderer.class)
public class ItemDynamicRendererMixin {
	private final SpookyTreasureChestBlockEntity chestEntity = new SpookyTreasureChestBlockEntity();
	
	@Inject(at = @At("HEAD"), method = "render", cancellable = true)
	private void renderTreasureChest(final ItemStack itemStack, final CallbackInfo info) {
		if (itemStack.getItem().equals(SpookyBlocks.SPOOKY_TREASURE_CHEST.asItem())) {
			BlockEntityRenderDispatcher.INSTANCE.renderEntity(this.chestEntity);
			info.cancel();
		}
	}
}
