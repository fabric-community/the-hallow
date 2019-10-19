package com.fabriccommunity.thehallow.client.render;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.util.Identifier;
import com.mojang.blaze3d.platform.GlStateManager;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.block.HallowedTreasureChestBlock;
import com.fabriccommunity.thehallow.client.model.TreasureChestModel;
import com.fabriccommunity.thehallow.entity.HallowedTreasureChestBlockEntity;

public class HallowedTreasureChestBlockEntityRenderer extends BlockEntityRenderer<HallowedTreasureChestBlockEntity> {
	private static final Identifier TEXTURE = new Identifier(TheHallow.MOD_ID, "textures/entity/treasure_chest/default_chest.png");
	private final TreasureChestModel chestModel = new TreasureChestModel();
	
	@Override
	public void render(HallowedTreasureChestBlockEntity treasureChest, double x, double y, double z, float partialTicks, int breakStage) {
		GlStateManager.pushMatrix();
		BlockState state = getWorld().getBlockState(treasureChest.getPos());
		
		// initial translation to center chest
		GlStateManager.translatef((float) x + .215f, (float) y + .57f, (float) z + .785f);
		
		// rotate based on facing direction
		if (state.contains(HallowedTreasureChestBlock.FACING)) {
			switch (state.get(HallowedTreasureChestBlock.FACING)) {
				case NORTH:
					GlStateManager.translatef(.57f, 0, -.57f);
					GlStateManager.rotatef(180, 0, 1, 0);
					break;
				case EAST:
					GlStateManager.translatef(.57f, 0, 0);
					GlStateManager.rotatef(90, 0, 1, 0);
					break;
				case SOUTH:
					GlStateManager.rotatef(0, 0, 1, 0);
					break;
				case WEST:
					GlStateManager.translatef(0, 0, -.57f);
					GlStateManager.rotatef(270, 0, 1, 0);
					break;
				default:
					break;
			}
		}
		
		// flip & scale to size
		GlStateManager.rotatef(180, 1, 0, 0);
		GlStateManager.scalef(.57f, .57f, .57f);
		
		// render chest
		bindTexture(TEXTURE);
		chestModel.render();
		
		GlStateManager.popMatrix();
	}
}
