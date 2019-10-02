package com.fabriccommunity.spookytime.item;

import com.mojang.blaze3d.platform.GlStateManager;
import dev.emi.trinkets.api.ITrinket;

import net.minecraft.block.DispenserBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SkirtCostume extends Item implements ITrinket {
	public SkirtCostume(Settings settings) {
		super(settings);
		DispenserBlock.registerBehavior(this, TRINKET_DISPENSER_BEHAVIOR);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity_1, Hand hand_1) {
		return ITrinket.equipTrinket(playerEntity_1, hand_1);
	}
	
	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals("legs") && slot.equals("belt");
	}
	
	@Override
	public void render(String slot, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float headYaw, float headPitch) {
		ItemRenderer renderer = MinecraftClient.getInstance().getItemRenderer();
		GlStateManager.pushMatrix();
		ITrinket.translateToChest(model, player, headYaw, headPitch);
		GlStateManager.translatef(0.25F, 0.65F, 0.0F);
		GlStateManager.scalef(0.5F, 0.5F, 0.5F);
		GlStateManager.rotatef(-45F, 0.0F, 0.0F, 1.0F);
		renderer.renderItem(new ItemStack(Items.BLAZE_ROD), ModelTransformation.Type.FIXED);
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		ITrinket.translateToChest(model, player, headYaw, headPitch);
		GlStateManager.translatef(-0.25F, 0.65F, 0.0F);
		GlStateManager.scalef(0.5F, 0.5F, 0.5F);
		GlStateManager.rotatef(-45F, 0.0F, 0.0F, 1.0F);
		renderer.renderItem(new ItemStack(Items.BLAZE_ROD), ModelTransformation.Type.FIXED);
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		ITrinket.translateToChest(model, player, headYaw, headPitch);
		GlStateManager.translatef(0.0F, 0.65F, 0.325F);
		GlStateManager.scalef(0.5F, 0.5F, 0.5F);
		GlStateManager.rotatef(-45F, 0.0F, 0.0F, 1.0F);
		renderer.renderItem(new ItemStack(Items.BLAZE_ROD), ModelTransformation.Type.FIXED);
		GlStateManager.popMatrix();
	}
}