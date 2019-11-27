package com.fabriccommunity.thehallow.client.render;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Quaternion;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.block.HallowedTreasureChestBlock;
import com.fabriccommunity.thehallow.client.model.TreasureChestModel;
import com.fabriccommunity.thehallow.entity.HallowedTreasureChestBlockEntity;

public class HallowedTreasureChestBlockEntityRenderer extends BlockEntityRenderer<HallowedTreasureChestBlockEntity> {

	private static final Identifier TEXTURE = new Identifier(TheHallow.MOD_ID, "textures/entity/treasure_chest/default_chest.png");
	private static final Quaternion FLIP_ROTATION_QUAT = new Quaternion(180, 0, 1, 0);
	private static final Quaternion EAST_ROTATION_QUAT = new Quaternion(90, 0, 1, 0);
	private static final Quaternion SOUTH_ROTATION_QUAT = new Quaternion(0, 0, 1, 0);
	private static final Quaternion WEST_ROTATION_QUAT = new Quaternion(270, 0, 1, 0);
	private final TreasureChestModel chestModel = new TreasureChestModel();
	
	
	public HallowedTreasureChestBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}
	
	@Override
	public void render(HallowedTreasureChestBlockEntity treasureChest, float partialTicks, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
		matrixStack.push();
		BlockState state = treasureChest.getCachedState();
		
		// initial translation to center chest
		matrixStack.translate(0.215, 0.57, 0.785);
		
		// rotate based on facing direction
		if (state.contains(HallowedTreasureChestBlock.FACING)) {
			switch (state.get(HallowedTreasureChestBlock.FACING)) {
				case NORTH:
					matrixStack.translate(0.57, 0, -0.57);
					matrixStack.multiply(FLIP_ROTATION_QUAT);
					break;
				case EAST:
					matrixStack.translate(0.57, 0, 0);
					matrixStack.multiply(EAST_ROTATION_QUAT);
					break;
				case SOUTH:
					matrixStack.multiply(SOUTH_ROTATION_QUAT);
					break;
				case WEST:
					matrixStack.translate(0, 0, -0.57);
					matrixStack.multiply(WEST_ROTATION_QUAT);
					break;
				default:
					break;
			}
		}
		
		// flip & scale to size
		matrixStack.multiply(FLIP_ROTATION_QUAT);
		matrixStack.scale(0.57f, 0.57f, 0.57f);
		
		// render chest
		//blockEntityRenderDispatcher.textureManager.bindTexture(TEXTURE);
		chestModel.render(matrixStack, vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(TEXTURE)), i, j, 1f, 1f, 1f, 1f);
		
		matrixStack.pop();
	}
}
