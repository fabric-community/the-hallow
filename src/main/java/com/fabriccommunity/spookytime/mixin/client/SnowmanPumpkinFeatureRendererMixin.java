package com.fabriccommunity.spookytime.mixin.client;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fabriccommunity.spookytime.api.SnowGolemEntityModifiers;
import com.mojang.blaze3d.platform.GlStateManager;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.feature.SnowmanPumpkinFeatureRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
@Mixin(SnowmanPumpkinFeatureRenderer.class)
public class SnowmanPumpkinFeatureRendererMixin {
	// renderPumpkin
	@Inject(at = @At(value = "INVOKE", target = "net/minecraft/client/render/FirstPersonRenderer.renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Type;)V"), method = "method_4201(Lnet/minecraft/entity/passive/SnowGolemEntity;FFFFFFF)V", cancellable = true)
	private void method_4201(SnowGolemEntity snowGolemEntity, float float1unused, float float2unusued, float float3unusued, float float4unusued, float float5unused, float float6unused, float float7unused, CallbackInfo ci) {
		Optional<BlockState> blockStateHead = ((SnowGolemEntityModifiers) snowGolemEntity).getHeadState();
		
		if(!blockStateHead.isPresent()) {
			MinecraftClient.getInstance().getFirstPersonRenderer().renderItem(snowGolemEntity, new ItemStack(Blocks.CARVED_PUMPKIN), ModelTransformation.Type.HEAD);
			GlStateManager.popMatrix();
			ci.cancel();
			return;
		}
		
		MinecraftClient.getInstance().getFirstPersonRenderer().renderItem(snowGolemEntity, new ItemStack(blockStateHead.get().getBlock()), ModelTransformation.Type.HEAD);
		GlStateManager.popMatrix();
		ci.cancel();
	}
}
