package com.fabriccommunity.spookytime.client.render.blockentity;

import com.fabriccommunity.spookytime.block.entity.CarvablePumpkinBlockEntity;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.block.Blocks;
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
 * @author cplir-c
 */
public class CarvablePumpkinBER extends BlockEntityRenderer<CarvablePumpkinBlockEntity> {
    protected void renderTopAndBottomTwice(){
    	Sprite top = MinecraftClient.getInstance().getSpriteAtlas().getSprite("block/pumpkin_top");
    	drawFace(top, Direction.UP, 0, 0, 16, 16);
        drawFace(top, Direction.DOWN, 0, 0, 16, 16);
    	drawFace(top, Direction.UP, 0, 16, 16, 0);
        drawFace(top, Direction.DOWN, 0, 16, 16, 0);
    }
	@Override
    public void render(CarvablePumpkinBlockEntity blockEntity, double bx, double by, double bz, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();
        GlStateManager.translated(bx, by, bz);
        GlStateManager.scalef(1f / 16f, 1f / 16f, 1f / 16f);
        
        renderManager.textureManager.bindTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX);
        
        GlStateManager.enableTexture();
        Sprite pumpkinSideSprite = MinecraftClient.getInstance().getSpriteAtlas().getSprite("block/pumpkin_side");
        
    	// render all sides twice
		
        renderTopAndBottomTwice();
        for (int f = 0; f < 4; f++) {
        	if(blockEntity.carved[f] == null) {
        		drawFace(pumpkinSideSprite, Direction.byId(f + 2), 0, 0, 16, 16);
        		drawFace(pumpkinSideSprite, Direction.byId(f + 2), 0, 16, 16, 0);
        	} else {
	            for (int x = 0; x < 16; x++) {
	                for (int y = 0; y < 16; y++) {
	                	if(!blockEntity.carved[f].get(16 * x + y)) {
	                        drawFace(pumpkinSideSprite, Direction.byId(f + 2), x + 1, y + 1, x, y);
	                        drawFace(pumpkinSideSprite, Direction.byId(f + 2), x + 1, y, x, y + 1);
	                    }
	                }
	            }
        	}
        }
        
        
        GlStateManager.popMatrix();
    }
    private void drawFace(Sprite sprite, Direction direction, int x, int z, int mx, int mz) {
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBufferBuilder();
        
        bufferBuilder.begin(GL11.GL_QUADS, VertexFormats.POSITION_UV);
        
        double u = sprite.getU(z);
        double mu = sprite.getU(mz);
        
        double v = sprite.getV(x);
        double mv = sprite.getV(mx);
        
        int y;
        switch (direction) {
            case DOWN:
                y = 0;
                bufferBuilder.vertex(mx, y, z) .texture(u, v) .next();  
                bufferBuilder.vertex(mx, y, mz).texture(mu, v)  .next(); 
                bufferBuilder.vertex(x, y, mz) .texture(mu, mv).next();
                bufferBuilder.vertex(x, y, z)  .texture(u, mv) .next();
                break;
            case UP:
                y = 16;
                bufferBuilder.vertex(x, y, z)  .texture(u, v)  .next(); 
                bufferBuilder.vertex(x, y, mz) .texture(mu, v) .next();
                bufferBuilder.vertex(mx, y, mz).texture(mu, mv).next(); 
                bufferBuilder.vertex(mx, y, z) .texture(u, mv) .next();
                break;
            case NORTH:
                y = 0;
                bufferBuilder.vertex(z, x, y)  .texture(mu, mv)  .next();
                bufferBuilder.vertex(z, mx, y) .texture(mu, v) .next();
                bufferBuilder.vertex(mz, mx, y).texture(u, v).next();
                bufferBuilder.vertex(mz, x, y) .texture(u, mv) .next();
                break;
            case SOUTH:
                y = 16;
                bufferBuilder.vertex(mz, x, y) .texture(mu, mv)  .next();
                bufferBuilder.vertex(mz, mx, y).texture(mu, v) .next();
                bufferBuilder.vertex(z, mx, y) .texture(u, v).next();
                bufferBuilder.vertex(z, x, y)  .texture(u, mv) .next();
                break;
            case WEST:
                y = 16;
                bufferBuilder.vertex(y, x, z)  .texture(mu, mv)  .next();
                bufferBuilder.vertex(y, mx, z) .texture(mu, v) .next();
                bufferBuilder.vertex(y, mx, mz).texture(u, v).next();
                bufferBuilder.vertex(y, x, mz) .texture(u, mv) .next();
                break;
            case EAST:
                y = 0;
                bufferBuilder.vertex(y, x, z)  .texture(mu, mv)  .next();
                bufferBuilder.vertex(y, mx, z) .texture(mu, v) .next();
                bufferBuilder.vertex(y, mx, mz).texture(u, v).next();
                bufferBuilder.vertex(y, x, mz) .texture(u, mv) .next();
                break;
        }
        Tessellator.getInstance().draw();
    }
}
