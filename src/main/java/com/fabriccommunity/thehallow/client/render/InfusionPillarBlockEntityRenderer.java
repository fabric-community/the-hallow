package com.fabriccommunity.thehallow.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.math.MathHelper;
import com.fabriccommunity.thehallow.block.entity.InfusionPillarBlockEntity;

@Environment(EnvType.CLIENT)
public class InfusionPillarBlockEntityRenderer extends BlockEntityRenderer<InfusionPillarBlockEntity> {

	public float rotation = 0;
	
	private boolean rotationMode = false;
	
	private long nanosA = 0;
	private long nanosB = 0;
	
	public InfusionPillarBlockEntityRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher) {
		super(blockEntityRenderDispatcher);
	}
	
	@Override
	public void render(InfusionPillarBlockEntity pillar, float partialTicks, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
		ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
		
		if (!pillar.storedStack.isEmpty()) {
			matrixStack.push();
			matrixStack.translate(0.5, 1.25 + MathHelper.sin(rotation / 2) / 32, 0.5);
			matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(rotation * 2));
			matrixStack.scale(0.5f, 0.5f, 0.5f);
			itemRenderer.method_23178(pillar.storedStack, ModelTransformation.Type.FIXED, i, j, matrixStack, vertexConsumerProvider);
			matrixStack.pop();
		}
		
		nanosA = System.nanoTime();
		if ((nanosA - nanosB) / 1000000 > 16) {
			nanosB = System.nanoTime();
			if (rotationMode) {
				rotation += 0.16;
				if (rotation >= 360) {
					rotationMode = !rotationMode;
				}
			} else {
				rotation += 0.16;
				if (rotation <= 0) {
					rotationMode = !rotationMode;
				}
			}
		}
		
	}
}
