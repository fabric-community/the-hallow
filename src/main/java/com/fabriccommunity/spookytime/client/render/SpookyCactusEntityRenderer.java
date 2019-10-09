package com.fabriccommunity.spookytime.client.render;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.entity.SpookyCactusEntity;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;

public class SpookyCactusEntityRenderer extends MobEntityRenderer<SpookyCactusEntity, EntityModel<SpookyCactusEntity>> {
	private static final Identifier SKIN = new Identifier(SpookyTime.MOD_ID, "textures/entity/pumpcown.png");

	public SpookyCactusEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher, null, 0.7F);
	}

	@Override
	public void render(SpookyCactusEntity entity, double x, double y, double z, float float_1, float float_2) {
		BlockRenderManager manager = MinecraftClient.getInstance().getBlockRenderManager();
		BlockState state = SpookyBlocks.SPOOKY_CACTUS.getDefaultState();
		this.bindTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX);

		GlStateManager.pushMatrix();
		GlStateManager.translated(x, y, z);
		GlStateManager.rotatef(entity.yaw, 0.0F, -1.0F, 0.0F);
		GlStateManager.translated(-0.5F, 0.0F, 0.5F);

		for (int i = 0; i < entity.getCactusHeight(); i++) {
			GlStateManager.pushMatrix();
			manager.renderDynamic(state, 1.0F);
			GlStateManager.popMatrix();
			GlStateManager.translated(0.0F, 1.0F, 0.0F);
		}

		GlStateManager.popMatrix();
	}

	@Override
	protected Identifier getTexture(SpookyCactusEntity cactus) {
		return SKIN;
	}
}
