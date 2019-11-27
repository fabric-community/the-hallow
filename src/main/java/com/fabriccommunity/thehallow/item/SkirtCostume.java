package com.fabriccommunity.thehallow.item;

import net.minecraft.item.Item;

public class SkirtCostume extends Item /*implements ITrinket*/ {
	public SkirtCostume(Settings settings) {
		super(settings);
		//DispenserBlock.registerBehavior(this, TRINKET_DISPENSER_BEHAVIOR);
	}
	
	/*@Override
	public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity, Hand hand) {
		return ITrinket.equipTrinket(playerEntity, hand);
	}
	
	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.LEGS) && slot.equals(Slots.BELT);
	}
	
	@Override
	@Environment(EnvType.CLIENT)
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
	}*/
}
