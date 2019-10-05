package com.fabriccommunity.spookytime.client.screen;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.concurrent.ThreadLocalRandom;

public class SpookyLoadingScreen extends Screen {
	private static final Identifier[] BACKGROUNDS = {
		SpookyTime.id("textures/block/deceased_dirt.png"),
		SpookyTime.id("textures/block/tainted_sand.png"),
		SpookyTime.id("textures/block/tainted_sandstone_bottom.png"),
		SpookyTime.id("textures/block/tainted_sandstone_top.png")
	};
	
	private static final String[] MESSAGES = {
		"text.spookytime.loading.spookiness",
		"text.spookytime.loading.pumpkins",
		"text.spookytime.loading.candies",
		"text.spookytime.loading.costumes",
		"text.spookytime.loading.witch_water",
		"text.spookytime.loading.pumpcowns",
	};
	
	private final Identifier backgroundTexture;
	private final String message;
	private final BlockState pumpkinState;
	
	private int floatingTick = 0;
	private float rotation = 0f;
	
	public SpookyLoadingScreen() {
		super(NarratorManager.EMPTY);
		this.backgroundTexture = BACKGROUNDS[ThreadLocalRandom.current().nextInt(BACKGROUNDS.length)];
		this.message = MESSAGES[ThreadLocalRandom.current().nextInt(MESSAGES.length)];
		this.pumpkinState = SpookyBlocks.TINY_PUMPKIN.getDefaultState();
	}
	
	@Override
	public void tick() {
		super.tick();
		
		floatingTick++;
		if (floatingTick == Integer.MAX_VALUE) {
			floatingTick = 0;
		}
		rotation += 4f;
		if (rotation >= 360f) {
			rotation = 0f;
		}
	}
	
	@Override
	public void render(int mouseX, int mouseY, float delta) {
		renderDirtBackground(0);
		this.drawCenteredString(font, I18n.translate(message), width / 2, height / 2 - 50, 0xFFFFFF);
		
		float scale = 100f;
		GlStateManager.pushMatrix();
		GlStateManager.translatef(width / 2f, height / 2f + 65 + MathHelper.sin(floatingTick / 6.5f) * 25, 500f);
		GlStateManager.scalef(scale, scale, scale);
		GlStateManager.rotatef(180, 1, 0, 0);
		GlStateManager.rotatef(rotation, 0, 1, 0);
		
		minecraft.getTextureManager().bindTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX);
		minecraft.getBlockRenderManager().renderDynamic(pumpkinState, 1f);
		
		GlStateManager.popMatrix();
	}
	
	@Override
	public void renderDirtBackground(int i) {
		// Copied mostly from renderDirtBackground()
		GlStateManager.disableLighting();
		GlStateManager.disableFog();
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder builder = tessellator.getBufferBuilder();
		minecraft.getTextureManager().bindTexture(backgroundTexture);
		int brightness = 130;
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		builder.begin(7, VertexFormats.POSITION_UV_COLOR);
		builder.vertex(0, height, 0).texture(0, height / 32.0F + i).color(brightness, brightness, brightness, 255).next();
		builder.vertex(width, height, 0).texture(width / 32.0F, height / 32.0F + i).color(brightness, brightness, brightness, 255).next();
		builder.vertex(width, 0, 0).texture(width / 32.0F, i).color(brightness, brightness, brightness, 255).next();
		builder.vertex(0, 0, 0).texture(0, i).color(brightness, brightness, brightness, 255).next();
		tessellator.draw();
	}
}
