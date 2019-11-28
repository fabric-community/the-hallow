package com.fabriccommunity.thehallow.client.render;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Quaternion;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.block.HallowedTreasureChestBlock;
import com.fabriccommunity.thehallow.client.model.TreasureChestModel;
import com.fabriccommunity.thehallow.entity.HallowedTreasureChestBlockEntity;
import com.fabriccommunity.thehallow.registry.HallowedBlocks;

public class HallowedTreasureChestBlockEntityRenderer extends BlockEntityRenderer<HallowedTreasureChestBlockEntity> {

	private static final Identifier TEXTURE = new Identifier(TheHallow.MOD_ID, "textures/entity/treasure_chest/default_chest.png");
	private static final Quaternion RIGHTSIDE_UP = Vector3f.POSITIVE_X.getDegreesQuaternion(180);
	private static final Quaternion NORTH_ROTATION_QUAT = Vector3f.POSITIVE_Y.getDegreesQuaternion(180);
	private static final Quaternion EAST_ROTATION_QUAT = Vector3f.POSITIVE_Y.getDegreesQuaternion(90);
	private static final Quaternion SOUTH_ROTATION_QUAT = Vector3f.POSITIVE_Y.getDegreesQuaternion(0);
	private static final Quaternion WEST_ROTATION_QUAT =Vector3f.POSITIVE_Y.getDegreesQuaternion(270);
	private final TreasureChestModel chestModel = new TreasureChestModel();
	
	
	public HallowedTreasureChestBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}
	
	@Override
	public void render(HallowedTreasureChestBlockEntity treasureChest, float partialTicks, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
		matrixStack.push();
		BlockState state;
		if(treasureChest.hasWorld()) {
			state = treasureChest.getCachedState();
		} else {
			state = HallowedBlocks.HALLOWED_TREASURE_CHEST.getDefaultState();
		}
		
		// initial translation to center chest -- default renders upside down.
		matrixStack.multiply(RIGHTSIDE_UP);
		matrixStack.translate(0.215, -0.57, -0.215);
		
		// rotate based on facing direction
		if (state.contains(HallowedTreasureChestBlock.FACING)) {
			switch (state.get(HallowedTreasureChestBlock.FACING)) {
				case NORTH:
					matrixStack.translate(0.57, 0, 0);
					matrixStack.multiply(SOUTH_ROTATION_QUAT);
					break;
				case EAST:
					matrixStack.translate(0.57, 0, -0.57);
					matrixStack.multiply(EAST_ROTATION_QUAT);
					break;
				case SOUTH:
					matrixStack.translate(0, 0, -0.57);
					matrixStack.multiply(NORTH_ROTATION_QUAT);
					break;
				case WEST:
					matrixStack.multiply(WEST_ROTATION_QUAT);
					break;
				default:
					break;
			}
		}
		
		// flip & scale to size
		matrixStack.multiply(NORTH_ROTATION_QUAT);
		matrixStack.scale(0.57f, 0.57f, 0.57f);
		
		// render chest
		chestModel.render(matrixStack, vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(TEXTURE)), i, j, 1f, 1f, 1f, 1f);
		
		matrixStack.pop();
	}
}
