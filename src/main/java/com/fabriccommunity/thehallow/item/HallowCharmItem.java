package com.fabriccommunity.thehallow.item;

import com.fabriccommunity.thehallow.block.HallowedGateBlock;
import com.fabriccommunity.thehallow.registry.HallowedBlocks;
import com.fabriccommunity.thehallow.registry.HallowedDimensions;
import com.fabriccommunity.thehallow.registry.HallowedItems;
import com.mojang.blaze3d.platform.GlStateManager;
import dev.emi.trinkets.api.ITrinket;
import net.fabricmc.fabric.api.dimension.v1.EntityPlacer;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.block.BlockState;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class HallowCharmItem extends Item implements ITrinket {
	public HallowCharmItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		PlayerEntity player = context.getPlayer();
		if (context.getWorld().isClient) return ActionResult.PASS;
		BlockState state = context.getWorld().getBlockState(context.getBlockPos());
		if (context.getWorld().getDimension().getType() == DimensionType.OVERWORLD && state.getBlock() == HallowedBlocks.HALLOWED_GATE) {
			if (HallowedGateBlock.isValid(context.getWorld(), context.getBlockPos(), state)) {
				BlockPos pos = player.getBlockPos();
				CompoundTag tag = new CompoundTag();
				tag.putInt("x", pos.getX());
				tag.putInt("y", pos.getY());
				tag.putInt("z", pos.getZ());
				context.getStack().putSubTag("PortalLoc", tag);
				FabricDimensions.teleport(player, HallowedDimensions.THE_HALLOW, DO_NOTHING);
				player.teleport(pos.getX(), pos.getY(), pos.getZ());
			} else {
				player.sendMessage(new TranslatableText("text.hallow.gate_incomplete"));
			}
		}
		return ActionResult.PASS;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if (world.getDimension().getType() == HallowedDimensions.THE_HALLOW) {
			player.setCurrentHand(hand);
			return new TypedActionResult<>(ActionResult.SUCCESS, player.getActiveItem());
		}
		return new TypedActionResult<>(ActionResult.FAIL, player.getStackInHand(hand));
	}

	@Override
	public int getMaxUseTime(ItemStack stack) {
		return 100;
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		//always happens when in The Hallow
		if (world.isClient) return stack;
		if (!(user instanceof PlayerEntity)) return stack;
		PlayerEntity player = (PlayerEntity)user;
		CompoundTag tag = stack.getOrCreateTag();
		if (tag.containsKey("PortalLoc", NbtType.COMPOUND)) {
			CompoundTag locTag = tag.getCompound("PortalLoc");
			BlockPos pos = new BlockPos(locTag.getInt("x"), locTag.getInt("y"), locTag.getInt("z"));
			tag.remove("PortalLoc");
			FabricDimensions.teleport(player, DimensionType.OVERWORLD, DO_NOTHING);
			player.teleport(pos.getX(), pos.getY(), pos.getZ());
			return stack;
		} else {
			BlockPos pos = player.getSpawnPosition();
			FabricDimensions.teleport(player, DimensionType.OVERWORLD, DO_NOTHING);
			player.teleport(pos.getX(), pos.getY(), pos.getZ());
			return stack;
		}
	}

	@Override
	public boolean hasEnchantmentGlint(ItemStack stack) {
		return stack.getOrCreateTag().containsKey("PortalLoc");
	}

	private EntityPlacer DO_NOTHING = (entity, world, dim, offsetX, offsetZ) -> new BlockPattern.TeleportTarget(new Vec3d(0, 500, 0), entity.getVelocity(), 0);

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.contains("head") && slot.contains("necklace");
	}

	@Override
	public void render(String slot, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float headYaw, float headPitch) {
		ItemRenderer renderer = MinecraftClient.getInstance().getItemRenderer();
		GlStateManager.pushMatrix();
		ITrinket.translateToChest(model, player, headYaw, headPitch);
		GlStateManager.translatef(0.0F, -0.15F, 0.0F);
		GlStateManager.scalef(0.5F, 0.5F, 0.5F);
		GlStateManager.rotatef(-180F, 0.0F, 0.0F, 1.0F);
		renderer.renderItem(new ItemStack(HallowedItems.HALLOW_CHARM), ModelTransformation.Type.FIXED);
		GlStateManager.popMatrix();
	}
}
