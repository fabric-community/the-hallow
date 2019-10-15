package com.fabriccommunity.spookytime.doomtree;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.fabricmc.fabric.api.rendering.data.v1.RenderAttachmentBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;

public class AlchemicalBasinBlockEntity extends BlockEntity implements Tickable, BlockEntityClientSerializable, RenderAttachmentBlockEntity {

	public static final int MODE_EMPTY = 0;
	public static final int MODE_PRIMED_WATER = 1;
	public static final int MODE_PRIMED_WITCHWATER = 2;
	public static final int MODE_BURNING = 3;
	public static final int MODE_INFUSING = 4;
	public static final int MAX_LEVEL = 32;
	
	protected static final String TAG_MODE = "mode";
	protected static final String TAG_LEVEL = "level";
	
	protected int mode = MODE_EMPTY;
	
	protected int level = 0;
	
	public AlchemicalBasinBlockEntity(BlockEntityType<?> entityType) {
		super(entityType);
	}

	public AlchemicalBasinBlockEntity() {
		this(DoomTree.ALCHEMICAL_BASIN);
	}
	
	public int mode() {
		return mode;
	}
	
	public int level() {
		return level;
	}
 
	public boolean setState(int mode, int level) {
		if (world != null && !world.isClient) {
			this.mode = mode;
			this.level = level;
			this.sync();
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void setPos(BlockPos pos) {
		super.setPos(pos);
	}

	@Override
	public void validate() {
		super.validate();
	}
	

	@Override
	public void invalidate() {
		super.invalidate();
	}

	@Override
	public void tick() {
		if (world == null || world.isClient) {
			return;
		}
	}

	protected CompoundTag writeTag(CompoundTag tag) {
		tag.putInt(TAG_MODE, mode);
		tag.putInt(TAG_LEVEL, level);
		return tag;
	}
	
	protected void readTag(CompoundTag tag) {
		mode = tag.getInt(TAG_MODE);
		level = tag.getInt(TAG_LEVEL);
	}
	
	@Override
	public void fromTag(CompoundTag tag) {
		super.fromTag(tag);
		readTag(tag);
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		return writeTag(super.toTag(tag));
	}

	@Override
	public void fromClientTag(CompoundTag tag) {
		readTag(tag);
		final BlockPos pos = this.pos;
		
		if (pos != null) {
			DoomTree.RENDER_REFRESH_HANDLER.accept(pos);
		}
	}

	@Override
	public CompoundTag toClientTag(CompoundTag tag) {
		if (tag == null) {
			tag = new CompoundTag();
		}
		return writeTag(tag);
	}

	@Override
	public Object getRenderAttachmentData() {
		return this;
	}
}
