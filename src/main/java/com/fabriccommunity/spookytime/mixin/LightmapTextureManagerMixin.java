package com.fabriccommunity.spookytime.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
@Mixin(LightmapTextureManager.class)
public class LightmapTextureManagerMixin {
	@Redirect(
		/*slice=@Slice(
			from=@At(
				value="FIELD",
 				target="texture:Lnet.minecraft.client.texture.NativeImageBackedTexture;",
				opcode=Opcodes.GETFIELD
			)
		),*/ at=@At(
			value="INVOKE",
			target="net.minecraft.client.texture.NativeImageBackedTexture.upload()V"
		), method="net.minecraft.client.render.LightmapTextureManager.update(F)V"
	)
    public void upload(NativeImageBackedTexture self) {
		NativeImage image = self.getImage();
		int height = image.getHeight();
		for (int x = image.getWidth(); x >= 0; x--)
			for (int y = x; y < height; y++) {
				int pixelValue = image.getPixelRGBA(x, y);
				image.setPixelRGBA(x, y, image.getPixelRGBA(y, x));
				image.setPixelRGBA(y, x, pixelValue);
			}
		self.upload();
	}
}
