package com.fabriccommunity.spookytime.entity;

import com.fabriccommunity.spookytime.block.SpookyCactusBlock;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal.Control;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

import java.util.List;

@SuppressWarnings("unchecked")
public class SpookyCactusEntity extends MobEntityWithAi {
	public static final TrackedData<Integer> CACTUS_HEIGHT = DataTracker.registerData(SpookyCactusEntity.class, TrackedDataHandlerRegistry.INTEGER);
	public BlockPos landPos;
	public int age;
	public int hop;

	public SpookyCactusEntity(EntityType<?> type, World world) {
		super((EntityType<? extends MobEntityWithAi>) type, world);
		setVelocity(0.0F, 0.5F, 0.0F);
		calculateDimensions();
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new WanderAroundFarGoal(this, 1.0F));
	}

	@Override
	protected void initAttributes() {
		super.initAttributes();
		this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(CACTUS_HEIGHT, 1);
	}

	@Override
	public void tick() {
		super.tick();
		if (!world.isClient) {
			List<Entity> collidingEntities = this.world.getEntities(Entity.class, this.getBoundingBox(), EntityPredicates.canBePushedBy(this));
			for (Entity entity : collidingEntities) {
				entity.damage(DamageSource.CACTUS, 1);
			}

			if (hop > 0) {
				double moveX = (x - landPos.getX() - 0.5F) / hop;
				double moveZ = (z - landPos.getZ() - 0.5F) / hop;
				float moveAngle = ((yaw - 90) % 360.0F) / hop;
				setPositionAndAngles(x - moveX, y, z - moveZ, yaw - moveAngle, this.pitch);

				if (hop == 1) {
					int i;
					for (i = 0; i < getCactusHeight(); i++) {
						world.setBlockState(landPos.up(i), SpookyBlocks.SPOOKY_CACTUS.getDefaultState().with(SpookyCactusBlock.AGE, age));
					}
					this.remove();
				}
				hop--;
			} else if (this.random.nextInt(256) == 0 && world.getBlockState(getBlockPos().down()).getBlock() == SpookyBlocks.TAINTED_SAND) {
				if (world.getBlockState(getBlockPos().north()).getMaterial().isSolid()) return;
				if (world.getBlockState(getBlockPos().south()).getMaterial().isSolid()) return;
				if (world.getBlockState(getBlockPos().east()).getMaterial().isSolid()) return;
				if (world.getBlockState(getBlockPos().west()).getMaterial().isSolid()) return;

				for (int i = 0; i < getCactusHeight(); i++) {
					if (world.getBlockState(getBlockPos().up(i).north()).getMaterial().isSolid())
						return;
					if (world.getBlockState(getBlockPos().up(i).south()).getMaterial().isSolid())
						return;
					if (world.getBlockState(getBlockPos().up(i).east()).getMaterial().isSolid())
						return;
					if (world.getBlockState(getBlockPos().up(i).west()).getMaterial().isSolid())
						return;
				}

				setVelocity(0.0F, 0.5F, 0.0F);
				landPos = getBlockPos();
				hop = 20;
				this.goalSelector.disableControl(Control.MOVE);
				this.goalSelector.disableControl(Control.LOOK);
				this.goalSelector.disableControl(Control.TARGET);
			}
		}
	}

	@Override
	public boolean damage(DamageSource source, float damage) {
		if (source == DamageSource.CACTUS) return false;
		return super.damage(source, damage);
	}

	@Override
	public void writeCustomDataToTag(CompoundTag tag) {
		super.writeCustomDataToTag(tag);
		tag.putInt("CactusHeight", getCactusHeight());
		tag.putInt("CactusAge", age);
	}

	@Override
	public void readCustomDataFromTag(CompoundTag tag) {
		super.readCustomDataFromTag(tag);
		this.setCactusHeight(tag.getInt("CactusHeight"));
		age = tag.getInt("CactusAge");
	}

	public int getCactusHeight() {
		return this.dataTracker.get(CACTUS_HEIGHT);
	}

	public void setCactusHeight(int height) {
		this.dataTracker.set(CACTUS_HEIGHT, height);
		calculateDimensions();
	}

	@Override
	public EntityDimensions getDimensions(EntityPose pose) {
		return EntityDimensions.changing(0.9F, 1.0F * getCactusHeight());
	}

	@Override
	public float getPathfindingFavor(BlockPos pos, ViewableWorld world) {
		return world.getBlockState(pos.down()).getBlock() == SpookyBlocks.TAINTED_SAND ? 10.0F : 1.0F;
	}
}
