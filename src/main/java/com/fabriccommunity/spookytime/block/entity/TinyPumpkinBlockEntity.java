package com.fabriccommunity.spookytime.block.entity;

import com.fabriccommunity.spookytime.registry.SpookyBlockEntities;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;

import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;

import javax.annotation.Nonnull;

public class TinyPumpkinBlockEntity extends BlockEntity implements BlockEntityClientSerializable {
	@Nonnull
	private ItemStack leftItem = ItemStack.EMPTY;
	@Nonnull
	private ItemStack rightItem = ItemStack.EMPTY;
	
	public TinyPumpkinBlockEntity() {
		super(SpookyBlockEntities.TINY_PUMPKIN_BLOCK_ENTITY);
	}
	
	public boolean use(PlayerEntity player, Hand hand, BlockHitResult hit) {
		Direction facing = getCachedState().get(HorizontalFacingBlock.FACING);
		Direction hitSide = hit.getSide();
		if (hitSide != facing.rotateYClockwise() && hitSide != facing.rotateYCounterclockwise()) {
			return false;
		}
		
		if (!world.isClient) {
			ItemStack handStack = player.getStackInHand(hand);
			boolean isLeft = hitSide == facing.rotateYCounterclockwise();
			ItemStack heldItem = isLeft ? leftItem : rightItem;
			if (!heldItem.isEmpty()) {
				ItemScatterer.spawn(world, pos, DefaultedList.copyOf(ItemStack.EMPTY, heldItem));
				if (isLeft) {
					leftItem = ItemStack.EMPTY;
				} else {
					rightItem = ItemStack.EMPTY;
				}
				sync();
				markDirty();
			} else if (!handStack.isEmpty()) {
				if (isLeft) {
					leftItem = handStack.copy();
					leftItem.setCount(1);
				} else {
					rightItem = handStack.copy();
					rightItem.setCount(1);
				}
				handStack.decrement(1);
				sync();
				markDirty();
			}
		}
		
		return true;
	}
	
	@Nonnull
	public ItemStack getLeftItem() {
		return leftItem;
	}
	
	@Nonnull
	public ItemStack getRightItem() {
		return rightItem;
	}
	
	public DefaultedList<ItemStack> getAllItems() {
		return DefaultedList.copyOf(ItemStack.EMPTY, leftItem, rightItem);
	}
	
	@Override
	public void fromTag(CompoundTag tag) {
		super.fromTag(tag);
		leftItem = ItemStack.fromTag(tag.getCompound("Left"));
		rightItem = ItemStack.fromTag(tag.getCompound("Right"));
	}
	
	@Override
	public CompoundTag toTag(CompoundTag t) {
		CompoundTag tag = super.toTag(t);
		tag.put("Left", leftItem.toTag(new CompoundTag()));
		tag.put("Right", rightItem.toTag(new CompoundTag()));
		return tag;
	}
	
	@Override
	public void fromClientTag(CompoundTag tag) {
		leftItem = ItemStack.fromTag(tag.getCompound("Left"));
		rightItem = ItemStack.fromTag(tag.getCompound("Right"));
	}
	
	@Override
	public CompoundTag toClientTag(CompoundTag tag) {
		tag.put("Left", leftItem.toTag(new CompoundTag()));
		tag.put("Right", rightItem.toTag(new CompoundTag()));
		return tag;
	}
}
