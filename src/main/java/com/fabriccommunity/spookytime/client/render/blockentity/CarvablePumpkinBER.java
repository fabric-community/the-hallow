package com.fabriccommunity.spookytime.client.render.blockentity;

import com.fabriccommunity.spookytime.block.entity.CarvablePumpkinBlockEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import org.lwjgl.opengl.GL11;

/**
 * @author Indigo Amann
 */
public class CarvablePumpkinBER extends BlockEntityRenderer<CarvablePumpkinBlockEntity> {
    @Override
    public void render(CarvablePumpkinBlockEntity blockEntity, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();
        GlStateManager.translated(x, y, z);
        GlStateManager.scalef(1f / 16f, 1f / 16f, 1f / 16f);
        renderManager.textureManager.bindTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX);
        GlStateManager.enableTexture();
        {
            Sprite top = MinecraftClient.getInstance().getSpriteAtlas().getSprite("block/pumpkin_top");
            drawFace(top, 0, 0, 16, 16, 16);
        }
        GlStateManager.popMatrix();
    }
    private void drawFace(Sprite sprite, int x, int z, int mx, int mz, int y) {
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBufferBuilder();
        bufferBuilder.begin(GL11.GL_QUADS, VertexFormats.POSITION_UV);
        float float_1 = sprite.getU(2), float_2 = sprite.getU(14), float_3 = sprite.getV(2), float_4 = sprite.getV(14);
        bufferBuilder.vertex((double) x, (double) y, (double) z).texture((double) float_1, (double) float_4).next();
        bufferBuilder.vertex((double) x, (double) y, (double) mz).texture((double) float_2, (double) float_4).next();
        bufferBuilder.vertex((double) mx, (double) y, (double) mz).texture((double) float_2, (double) float_3).next();
        bufferBuilder.vertex((double) mx, (double) y, (double) z).texture((double) float_1, (double) float_3).next();
        Tessellator.getInstance().draw();
    }
}
