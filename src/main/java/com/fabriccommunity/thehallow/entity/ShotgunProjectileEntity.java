package com.fabriccommunity.thehallow.entity;

import com.fabriccommunity.thehallow.TheHallow;
import com.fabriccommunity.thehallow.registry.HallowedEntities;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;

import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ProjectileUtil;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;

public class ShotgunProjectileEntity extends Entity {
	public static final Identifier ENTITY_ID = TheHallow.id("shotgun_projectile");

	public Entity owner;

	public static final int DEFAULT_LIFETIME = 40; // ticks to live before despawn, in this case 2 seconds til despawn
	public static final float DEFAULT_DAMAGE = 5.0f;
	
	public static final TrackedData<Float> DAMAGE = DataTracker.registerData(ShotgunProjectileEntity.class, TrackedDataHandlerRegistry.FLOAT);
	public static final TrackedData<Integer> AGE = DataTracker.registerData(ShotgunProjectileEntity.class, TrackedDataHandlerRegistry.INTEGER);
	public static final TrackedData<Integer> LIFETIME = DataTracker.registerData(ShotgunProjectileEntity.class, TrackedDataHandlerRegistry.INTEGER);

	public ShotgunProjectileEntity(EntityType<? extends ShotgunProjectileEntity> entityType, World world) {
		super(entityType, world);
		setNoGravity(true);
	}

	public ShotgunProjectileEntity(World world, Entity entity, Vec3d vel) {
		this(world, entity, entity.getX(), entity.getY() + entity.getEyeHeight(entity.getPose()), entity.getZ(),
			entity.yaw, entity.pitch,
			vel.x, vel.y, vel.z);
	}

	public ShotgunProjectileEntity(World world, Entity owner, double x, double y, double z,
								   float yaw, float pitch,
								   double velX, double velY, double velZ) {
		super(HallowedEntities.SHOTGUN_PROJECTILE, world);

		this.setOwner(owner);
		setPositionAndAngles(x, y, z, yaw, pitch);
		setVelocity(velX + random.nextGaussian() * 0.05f, velY + random.nextGaussian() * 0.05f, velZ + random.nextGaussian() * 0.05f);

		if (world.isClient()) {
			updateTrackedPositionAndAngles(x, y, z, yaw, pitch, 0, false);
		}
	}

	@Override
	public void tick() {
		super.tick();
		this.prevRenderX = getX();
		this.prevRenderY = getY();
		this.prevRenderZ = getZ();

		this.age();

		HitResult hitResult = this.getCollision();

		HitResult.Type type = hitResult.getType();
		if (type != HitResult.Type.MISS) {
			if (type == HitResult.Type.ENTITY) {
				Entity hitEntity = ((EntityHitResult) hitResult).getEntity();
				hitEntity.damage(DamageSource.GENERIC, getDamage());
			} else if(type == HitResult.Type.BLOCK) {
				BlockPos hitPos = ((BlockHitResult) hitResult).getBlockPos();
				if (world.getBlockState(hitPos).getMaterial() == Material.GLASS) {
					world.breakBlock(hitPos, false);
				}
			}
			remove();
		}

		Vec3d vel = getVelocity();
		setPosition(getX() + vel.x, getY() + vel.y, getZ() + vel.z);

		if (world.isClient()) {
			updateTrackedPosition(getX(), getY(), getZ());
		}

		super.tick();
	}

	@Override
	protected void initDataTracker() {
		this.dataTracker.startTracking(DAMAGE, DEFAULT_DAMAGE);
		this.dataTracker.startTracking(AGE, 0);
		this.dataTracker.startTracking(LIFETIME, DEFAULT_LIFETIME);
	}

	@Override
	protected void readCustomDataFromTag(CompoundTag compoundTag) {
		if(compoundTag.contains("damage")) {
			this.setDamage(compoundTag.getFloat("damage"));
		}
		if(compoundTag.contains("age")) {
			this.setAge(compoundTag.getInt("age"));
		}
		if(compoundTag.contains("lifetime")) {
			this.setLifetime(compoundTag.getInt("lifetime"));
		}
	}

	@Override
	protected void writeCustomDataToTag(CompoundTag compoundTag) {
		compoundTag.putFloat("damage", this.getDamage());
		compoundTag.putInt("age", this.getAge());
		compoundTag.putInt("lifetime", this.getLifetime());
	}

	@Override
	public Packet<?> createSpawnPacket() {
		PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer());

		packet.writeDouble(getX());
		packet.writeDouble(getY());
		packet.writeDouble(getZ());

		packet.writeFloat(yaw);
		packet.writeFloat(pitch);

		Vec3d vel = getVelocity();
		packet.writeDouble(vel.x);
		packet.writeDouble(vel.y);
		packet.writeDouble(vel.z);

		packet.writeInt(getOwner().getEntityId());
		packet.writeInt(getEntityId());

		return ServerSidePacketRegistry.INSTANCE.toPacket(ENTITY_ID, packet);
	}
	
	public void age() {
		this.setAge(this.getAge()+1);
		if(getAge() > getLifetime()) {
			remove();
		}
	}
	
	public int getAge() {
		return this.dataTracker.get(AGE);
	}
	
	public Entity getOwner() {
		return owner;
	}
	
	public int getLifetime() {
		return this.dataTracker.get(LIFETIME);
	}
	
	public float getDamage() {
		return this.dataTracker.get(DAMAGE);
	}
	
	public ShotgunProjectileEntity setAge(int age) {
		this.dataTracker.set(AGE, age);
		return this;
	}
	
	public ShotgunProjectileEntity setDamage(float damage) {
		this.dataTracker.set(DAMAGE, damage);
		return this;
	}
	
	public ShotgunProjectileEntity setOwner(Entity owner) {
		this.owner = owner;
		return this;
	}
	
	public ShotgunProjectileEntity setLifetime(int lifetime) {
		this.dataTracker.set(LIFETIME, lifetime);
		return this;
	}
	
	public HitResult getCollision() {
		return ProjectileUtil.getCollision(this, this.getBoundingBox().stretch(this.getVelocity()).expand(1.0D), (entity) -> !entity.isSpectator() && entity.isAlive() && entity.collides() && entity != this.getOwner(), RayTraceContext.ShapeType.OUTLINE, true);
	}
	
	@Override
	public boolean isAttackable() {
		return false;
	}
	
	@Override
	protected boolean canClimb() {
		return false;
	}
}
