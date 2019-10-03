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
import net.minecraft.util.math.Direction;
import org.lwjgl.opengl.GL11;

/**
 * @author Indigo Amann
 */
public class CarvablePumpkinBER extends BlockEntityRenderer<CarvablePumpkinBlockEntity> {
    @Override
    public void render(CarvablePumpkinBlockEntity blockEntity, double bx, double by, double bz, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();
        GlStateManager.translated(bx, by, bz);
        GlStateManager.scalef(1f / 16f, 1f / 16f, 1f / 16f);
        renderManager.textureManager.bindTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX);
        GlStateManager.enableTexture();
        Sprite side = MinecraftClient.getInstance().getSpriteAtlas().getSprite("block/pumpkin_side");
        {
            Sprite top = MinecraftClient.getInstance().getSpriteAtlas().getSprite("block/pumpkin_top");
            drawFace(top, Direction.UP, 0, 0, 16, 16);
            drawFace(top, Direction.DOWN, 0, 0, 16, 16);
        }
        for (int f = 0; f < 4; f++) {
            for (int x = 0; x < 16; x++) {
                for (int y = 0; y < 16; y++) {
                    if(!blockEntity.carving[f][x][y]) {
                        //drawFace(side, Direction.byId(f + 2), x + 1, 16 - x, 16 - y, y + 1);
                        drawFace(side, Direction.byId(f + 2), x, y, x+1, y + 1);
                    }
                }
            }
        }
        /*drawFace(side, Direction.NORTH, 0, 0, 16, 16);
        drawFace(side, Direction.SOUTH, 0, 0, 16, 16);
        drawFace(side, Direction.EAST, 0, 0, 16, 16);
        drawFace(side, Direction.WEST, 0, 0, 16, 16);*/
        GlStateManager.popMatrix();
    }
    private void drawFace(Sprite sprite, Direction direction, int x, int z, int mx, int mz) {
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBufferBuilder();
        bufferBuilder.begin(GL11.GL_QUADS, VertexFormats.POSITION_UV);
        float u = sprite.getU(z), mu = sprite.getU(mz), v = sprite.getV(x), mv = sprite.getV(mx);
        int y;
        switch (direction) {
            case DOWN:
                y = 0;
                bufferBuilder.vertex((double) mx, (double) y, (double) z).texture((double) mu, (double) v).next();
                bufferBuilder.vertex((double) mx, (double) y, (double) mz).texture((double) u, (double) v).next();
                bufferBuilder.vertex((double) x, (double) y, (double) mz).texture((double) u, (double) mv).next();
                bufferBuilder.vertex((double) x, (double) y, (double) z).texture((double) mu, (double) mv).next();
                break;
            case UP:
                y = 16;
                bufferBuilder.vertex((double) x, (double) y, (double) z).texture((double) u, (double) v).next();
                bufferBuilder.vertex((double) x, (double) y, (double) mz).texture((double) mu, (double) v).next();
                bufferBuilder.vertex((double) mx, (double) y, (double) mz).texture((double) mu, (double) mv).next();
                bufferBuilder.vertex((double) mx, (double) y, (double) z).texture((double) u, (double) mv).next();
                break;
            case NORTH:
                y = 0;
                bufferBuilder.vertex((double) z, (double) x, (double) y).texture((double) mu, (double) mv).next();
                bufferBuilder.vertex((double) z, (double) mx, (double) y).texture((double) mu, (double) v).next();
                bufferBuilder.vertex((double) mz, (double) mx, (double) y).texture((double) u, (double) v).next();
                bufferBuilder.vertex((double) mz, (double) x, (double) y).texture((double) u, (double) mv).next();
                break;
            case SOUTH:
                y = 16;
                bufferBuilder.vertex((double) mz, (double) x, (double) y).texture((double) mu, (double) mv).next();
                bufferBuilder.vertex((double) mz, (double) mx, (double) y).texture((double) mu, (double) v).next();
                bufferBuilder.vertex((double) z, (double) mx, (double) y).texture((double) u, (double) v).next();
                bufferBuilder.vertex((double) z, (double) x, (double) y).texture((double) u, (double) mv).next();
                break;
            case WEST:
                y = 16;
                bufferBuilder.vertex((double) y, (double) x, (double) z).texture((double) mu, (double) mv).next();
                bufferBuilder.vertex((double) y, (double) mx, (double) z).texture((double) mu, (double) v).next();
                bufferBuilder.vertex((double) y, (double) mx, (double) mz).texture((double) u, (double) v).next();
                bufferBuilder.vertex((double) y, (double) x, (double) mz).texture((double) u, (double) mv).next();
                break;
            case EAST:
                y = 0;
                bufferBuilder.vertex((double) y, (double) x, (double) mz).texture((double) u, (double) mv).next();
                bufferBuilder.vertex((double) y, (double) mx, (double) mz).texture((double) u, (double) v).next();
                bufferBuilder.vertex((double) y, (double) mx, (double) z).texture((double) mu, (double) v).next();
                bufferBuilder.vertex((double) y, (double) x, (double) z).texture((double) mu, (double) mv).next();
                break;
        }
        Tessellator.getInstance().draw();
    }
}
