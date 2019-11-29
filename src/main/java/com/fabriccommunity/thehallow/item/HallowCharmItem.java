package com.fabriccommunity.thehallow.item;

import com.fabriccommunity.thehallow.block.HallowedGateBlock;
import com.fabriccommunity.thehallow.registry.HallowedBlocks;
import com.fabriccommunity.thehallow.registry.HallowedDimensions;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.Random;

//TODO commented-out trinket
public class HallowCharmItem extends Item /*implements ITrinket*/ {
	public HallowCharmItem(Settings settings) {
		super(settings);
		//DispenserBlock.registerBehavior(this, TRINKET_DISPENSER_BEHAVIOR);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		PlayerEntity player = context.getPlayer();
		if (context.getWorld().isClient) return ActionResult.PASS;
		BlockState state = context.getWorld().getBlockState(context.getBlockPos());
		if(state.getBlock() == HallowedBlocks.HALLOWED_GATE) {
			if (context.getWorld().getDimension().getType() == DimensionType.OVERWORLD) {
				if (HallowedGateBlock.isValid(context.getWorld(), context.getBlockPos(), state)) {
					BlockPos pos = player.getBlockPos();
					CompoundTag tag = new CompoundTag();
					tag.putInt("x", pos.getX());
					tag.putInt("y", pos.getY());
					tag.putInt("z", pos.getZ());
					context.getStack().putSubTag("PortalLoc", tag);
					FabricDimensions.teleport(player, HallowedDimensions.THE_HALLOW);
					return ActionResult.SUCCESS;
				} else {
					player.addChatMessage(new TranslatableText("text.thehallow.gate_incomplete"), true);
				}
			} else {
				player.addChatMessage(new TranslatableText("text.thehallow.gate_in_wrong_dimension"), true);
			}
		}
		return ActionResult.PASS;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if (world.getDimension().getType() == HallowedDimensions.THE_HALLOW) {
			player.setCurrentHand(hand);
			player.playSound(SoundEvents.BLOCK_PORTAL_TRIGGER, 1F, 1F);
			return new TypedActionResult<>(ActionResult.SUCCESS, player.getActiveItem());
		} else {
			return new TypedActionResult<>(ActionResult.PASS, player.getActiveItem());
			//return ITrinket.equipTrinket(player, hand);
		}
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
		if (tag.contains("PortalLoc", NbtType.COMPOUND)) {
			CompoundTag locTag = tag.getCompound("PortalLoc");
			BlockPos pos = new BlockPos(locTag.getInt("x"), locTag.getInt("y"), locTag.getInt("z"));
			tag.remove("PortalLoc");
			FabricDimensions.teleport(player, DimensionType.OVERWORLD, HallowedDimensions.FIND_SURFACE);
			player.teleport(pos.getX(), pos.getY(), pos.getZ());
			return stack;
		} else {
			FabricDimensions.teleport(player, DimensionType.OVERWORLD, HallowedDimensions.FIND_SURFACE);
			BlockPos pos = player.getEntityWorld().getSpawnPos();
			player.teleport(pos.getX(), pos.getY(), pos.getZ());
			return stack;
		}
	}

	@Override
	public boolean hasEnchantmentGlint(ItemStack stack) {
		return stack.getOrCreateTag().contains("PortalLoc");
	}

	/*@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.contains(SlotGroups.HEAD) && slot.contains(Slots.NECKLACE);
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
	}*/

	@Override
	public void usageTick(World world, LivingEntity user, ItemStack stack, int ticksLeft) {
		Random random = new Random();
		BlockPos pos = user.getBlockPos();
		double x = pos.getX() + random.nextFloat();
		double y = pos.getY() + random.nextFloat();
		double z = pos.getZ() + random.nextFloat();
		double velX = (random.nextFloat() - 0.5D) * 0.5D;
		double velY = (random.nextFloat() - 0.5D) * 0.5D;
		double velZ = (random.nextFloat() - 0.5D) * 0.5D;
		world.addParticle(ParticleTypes.PORTAL, x, y, z, velX, velY, velZ);
	}
}
