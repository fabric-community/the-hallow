package com.fabriccommunity.spookytime.mixin;

import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
@Mixin(LightmapTextureManager.class)
public class LightMapTextureMixin {
	@Redirect(
		slice=@Slice(
			from=@At(
				value = "FIELD",
				target="texture:Lnet/minecraft/client/texture/NativeImageBackedTexture;",
				opcode = Opcodes.GETFIELD
			)
		), at=@At(
			value="INVOKE",
			target="net.minecraft.client.texture.NativeImageBackedTexture.upload()V"
		), method="net.minecraft.client.render.LightmapTextureManager.update(F)V"
	)
    public void upload(NativeImageBackedTexture self) {
		NativeImage image = self.getImage();
		int height = image.getHeight();
		for (int x = image.getWidth(); x >= 0; x--)
			for (int y = height; y >= 0; y--) {
				int pixelValue = image.getPixelRGBA(x, y);
				
				int alpha = pixelValue & 255;
				int blue = (pixelValue >> 8) & 255;
				int green = (pixelValue >> 16) & 255;
				int red = (pixelValue >> 24) & 255;
				
				blue = 255 - blue;
				green = 255 - green;
				red = 255 - red;
				
				pixelValue = (
					 alpha
				  | (blue >> 8)
				  | (green >> 16)
				  | (red >> 24)
				);
				image.setPixelRGBA(x, y, pixelValue);
			}
		self.upload();
	}
}
