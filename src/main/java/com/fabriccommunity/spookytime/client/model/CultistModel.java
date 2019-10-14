package com.fabriccommunity.spookytime.client.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.ModelWithHat;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.entity.Entity;
import net.minecraft.util.Arm;

import com.fabriccommunity.spookytime.entity.CultistEntity;

public class CultistModel extends BipedEntityModel<CultistEntity> implements ModelWithHat {
	private final Cuboid hood2;
	private final Cuboid cape;

	public CultistModel() {
		textureWidth = 128;
		textureHeight = 64;
		
		leftArmPose = BipedEntityModel.ArmPose.EMPTY;
		rightArmPose = BipedEntityModel.ArmPose.EMPTY;

		head = new Cuboid(this, 0, 20);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false);

		headwear = new Cuboid(this, 0, 0);
		headwear.setRotationPoint(0.0F, 1.0F, -1.0F);
		setRotationAngle(headwear, 0.1745F, 0.0F, 0.0F);
		head.addChild(headwear);
		headwear.addBox(-5.0F, -11.0875F, -2.0046F, 10, 11, 9, 0.0F, false);

		hood2 = new Cuboid(this, 32, 23);
		hood2.setRotationPoint(0.0F, -1.0875F, 1.9954F);
		setRotationAngle(hood2, -0.2618F, 0.0F, 0.0F);
		headwear.addChild(hood2);
		hood2.addBox(-4.0F, -9.0F, 0.0F, 8, 7, 6, 0.0F, false);

		body = new Cuboid(this, 90, 16);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F, false);

		cape = new Cuboid(this, 38, 0);
		cape.setRotationPoint(0.0F, 1.0F, 2.5F);
		body.addChild(cape);
		cape.addBox(-6.0F, -1.0F, -0.5F, 12, 22, 1, 0.0F, false);

		leftArm = new Cuboid(this);
		leftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		leftArm.addBox("left_arm", -3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, 64, 0);
		leftArm.addBox("left_garb", -4.0F, 6.0F, -3.0F, 6, 3, 7, 0.0F, 64, 16);
		leftArm.mirror = true;

		rightArm = new Cuboid(this);
		rightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		rightArm.addBox("right_arm", -1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, 80, 0);
		rightArm.addBox("right_garb", -2.0F, 6.0F, -3.0F, 6, 3, 7, 0.0F, 64, 26);

		leftLeg = new Cuboid(this, 96, 0);
		leftLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);
		leftLeg.mirror = true;

		rightLeg = new Cuboid(this, 112, 0);
		rightLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);
	}

	@Override
	public void render(CultistEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		body.render(f5);
		leftArm.render(f5);
		rightArm.render(f5);
		leftLeg.render(f5);
		rightLeg.render(f5);
	}
	
	public void setRotationAngle(Cuboid cuboid, float x, float y, float z) {
		cuboid.pitch = x;
		cuboid.yaw = y;
		cuboid.roll = z;
	}
	
	@Override
	public void setHatVisible(boolean visible) {
		headwear.visible = visible;
		hood2.visible = visible;
	}
}
