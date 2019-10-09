package com.fabriccommunity.spookytime.mixin.client;

import com.fabriccommunity.spookytime.item.tool.ClubItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.FirstPersonRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FirstPersonRenderer.class)
public class FirstPersonRendererMixin {
	@Final
	@Shadow
	private MinecraftClient client;

	@ModifyVariable(method = "renderFirstPersonItem(F)V", ordinal = 1, at =
	@At(value = "INVOKE", target = "Lnet/minecraft/client/render/FirstPersonRenderer;rotate(FF)V"))
	private boolean dontRenderOffHandItem(boolean prevBool) {
		AbstractClientPlayerEntity player = this.client.player;
		if (player.getMainHandStack().getItem() instanceof ClubItem) {
			return false;
		}
		return prevBool;
	}
}
