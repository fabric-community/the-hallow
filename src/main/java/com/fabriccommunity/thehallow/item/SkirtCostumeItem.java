package com.fabriccommunity.thehallow.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.block.DispenserBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Quaternion;
import net.minecraft.world.World;

import dev.emi.trinkets.api.ITrinket;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;

public class SkirtCostumeItem extends Item implements ITrinket {
	
	private static final Quaternion ROTATION_CONSTANT = new Quaternion(Vector3f.POSITIVE_Z, -45f, true);
	
	public SkirtCostumeItem(Settings settings) {
		super(settings);
		DispenserBlock.registerBehavior(this, TRINKET_DISPENSER_BEHAVIOR);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity, Hand hand) {
		return ITrinket.equipTrinket(playerEntity, hand);
	}
	
	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.LEGS) && slot.equals(Slots.BELT);
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public void render(String slot, MatrixStack matrix, VertexConsumerProvider vertexConsumer, int light, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float headYaw, float headPitch) {
		ItemRenderer renderer = MinecraftClient.getInstance().getItemRenderer();
		matrix.push();
		translateToChest(model, player, headYaw, headPitch, matrix); //TODO switch back to trinkets version once it's fixed
		matrix.push();
		matrix.translate(0.25, 0.65, 0);
		matrix.scale(0.5F, 0.5F, 0.5F);
		matrix.multiply(ROTATION_CONSTANT);
		renderer.renderItem(new ItemStack(Items.BLAZE_ROD), ModelTransformation.Mode.FIXED, light, OverlayTexture.DEFAULT_UV, matrix, vertexConsumer);
		matrix.pop();
		matrix.push();
		matrix.translate(-0.25, 0.65, 0);
		matrix.scale(0.5F, 0.5F, 0.5F);
		matrix.multiply(ROTATION_CONSTANT);
		renderer.renderItem(new ItemStack(Items.BLAZE_ROD), ModelTransformation.Mode.FIXED, light, OverlayTexture.DEFAULT_UV, matrix, vertexConsumer);
		matrix.pop();
		matrix.push();
		matrix.translate(0, 0.65, 0.325);
		matrix.scale(0.5F, 0.5F, 0.5F);
		matrix.multiply(ROTATION_CONSTANT);
		renderer.renderItem(new ItemStack(Items.BLAZE_ROD), ModelTransformation.Mode.FIXED, light, OverlayTexture.DEFAULT_UV, matrix, vertexConsumer);
		matrix.pop();
		matrix.pop();
	}
	
	public static void translateToChest(PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float headYaw, float headPitch, MatrixStack matrix) {
		if (player.isInSneakingPose() && !model.riding && !player.isSwimming()) {
			matrix.translate(0, 0.2, 0);
			matrix.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(model.torso.pitch * 57.5f));
		}
		matrix.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(model.torso.yaw * 57.5f));
		matrix.translate(0, 0.4, -0.16);
	}
}
