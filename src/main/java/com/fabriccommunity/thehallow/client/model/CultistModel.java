package com.fabriccommunity.thehallow.client.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.ModelWithHat;
import net.minecraft.client.util.math.MatrixStack;

import com.fabriccommunity.thehallow.entity.CultistEntity;

public class CultistModel extends BipedEntityModel<CultistEntity> implements ModelWithHat {
	private final ModelPart hood1;
	private final ModelPart hood2;
	private final ModelPart cape;
	
	public CultistModel() {
		super(1);
		textureWidth = 128;
		textureHeight = 64;
		
		leftArmPose = BipedEntityModel.ArmPose.EMPTY;
		rightArmPose = BipedEntityModel.ArmPose.EMPTY;
		
		head = new ModelPart(this, 0, 20);
		head.setPivot(0.0F, 0.0F, 0.0F);
		head.addCuboid(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false);
		
		hood1 = new ModelPart(this, 0, 0);
		hood1.setPivot(0.0F, 1.0F, -1.0F);
		setRotationAngle(hood1, 0.1745F, 0.0F, 0.0F);
		head.addChild(hood1);
		hood1.addCuboid(-5.0F, -11.0875F, -2.0046F, 10, 11, 9, 0.0F, false);
		
		hood2 = new ModelPart(this, 32, 23);
		hood2.setPivot(0.0F, -1.0875F, 1.9954F);
		setRotationAngle(hood2, -0.2618F, 0.0F, 0.0F);
		hood1.addChild(hood2);
		hood2.addCuboid(-4.0F, -9.0F, 0.0F, 8, 7, 6, 0.0F, false);
		
		torso = new ModelPart(this, 90, 16);
		torso.setPivot(0.0F, 0.0F, 0.0F);
		torso.addCuboid(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F, false);
		
		cape = new ModelPart(this, 38, 0);
		cape.setPivot(0.0F, 1.0F, 2.5F);
		cape.addCuboid(-6.0F, -1.0F, -0.5F, 12, 22, 1, 0.0F, false);
		
		rightArm = new ModelPart(this);
		rightArm.setPivot(-5.0F, 2.0F, 0.0F);
		rightArm.addCuboid("right_arm", -3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, 64, 0);
		rightArm.addCuboid("right_garb", -4.0F, 6.0F, -3.0F, 6, 3, 7, 0.0F, 64, 16);
		rightArm.mirror = true;
		
		leftArm = new ModelPart(this);
		leftArm.setPivot(5.0F, 2.0F, 0.0F);
		leftArm.addCuboid("left_arm", -1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, 80, 0);
		leftArm.addCuboid("left_garb", -2.0F, 6.0F, -3.0F, 6, 3, 7, 0.0F, 64, 26);
		
		rightLeg = new ModelPart(this, 96, 0);
		rightLeg.setPivot(-1.9F, 12.0F, 0.0F);
		rightLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);
		rightLeg.mirror = true;
		
		leftLeg = new ModelPart(this, 112, 0);
		leftLeg.setPivot(1.9F, 12.0F, 0.0F);
		leftLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);
	}
	
	@Override
	public void render(MatrixStack matrixStack, VertexConsumer vertexConsumer, int i, int j, float r, float g, float b, float f) {
		head.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		torso.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		leftArm.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		rightArm.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		leftLeg.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		rightLeg.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
		cape.render(matrixStack, vertexConsumer, i, j, r, g, b, f);
	}
	
	public void setRotationAngle(ModelPart cuboid, float x, float y, float z) {
		cuboid.pitch = x;
		cuboid.yaw = y;
		cuboid.roll = z;
	}
	
	@Override
	public void setHatVisible(boolean visible) {
		helmet.visible = visible;
		hood2.visible = visible;
	}
	
	@Override
	public void setAngles(CultistEntity entity, float f, float f1, float f2, float f3, float f4) {
		super.setAngles(entity, f, f1, f2, f3, f4);
		if (entity.isInSneakingPose()) {
			this.cape.pivotY = 2.0F;
		} else {
			this.cape.pivotY = 0.0F;
		}
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		hood1.visible = visible;
		hood2.visible = visible;
		cape.visible = visible;
	}
}
