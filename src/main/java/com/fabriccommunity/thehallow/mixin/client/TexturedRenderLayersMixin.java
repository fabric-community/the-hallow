package com.fabriccommunity.thehallow.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;

import com.fabriccommunity.thehallow.block.HallowedSign;
import com.fabriccommunity.thehallow.registry.HallowedBlocks;

import java.util.function.Consumer;

@Mixin(TexturedRenderLayers.class)
public class TexturedRenderLayersMixin {
	@Inject(method = "addDefaultTextures", at = @At("RETURN"))
	private static void injectCustomSigns(Consumer<SpriteIdentifier> consumer, CallbackInfo info) {
		consumer.accept(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, ((HallowedSign)HallowedBlocks.DEADWOOD_SIGN).getTexture()));
	}
}
