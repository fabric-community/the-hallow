package com.fabriccommunity.thehallow.entity;


import com.fabriccommunity.thehallow.registry.HallowedEntities;
import net.minecraft.client.network.packet.EntitySpawnS2CPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ProjectileUtil;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;

public class ShotgunProjectileEntity extends Entity {
	public Entity owner;

	public float damage = 1.0f; // Half a heart per projectile
	public int lifetime = 40; // ticks to live before despawn, in this case 2 seconds til despawn

	public ShotgunProjectileEntity(EntityType<? extends ShotgunProjectileEntity> entityType, World world) {
		super(entityType, world);
		setNoGravity(true);
	}

	public ShotgunProjectileEntity(World world, Entity entity, Vec3d vel) {
		this(world, entity, entity.x, entity.y + entity.getEyeHeight(entity.getPose()), entity.z,
			entity.yaw, entity.pitch,
			vel.x, vel.y, vel.z);
	}

	public ShotgunProjectileEntity(World world, Entity owner, double x, double y, double z,
								   float yaw, float pitch,
								   double velX, double velY, double velZ) {
		super(HallowedEntities.SHOTGUN_PROJECTILE, world);
		this.owner = owner;
		setPositionAndAngles(x, y, z, yaw, pitch);
		setVelocity(velX + random.nextGaussian() * 0.05f, velY + random.nextGaussian() * 0.05f, velZ + random.nextGaussian() * 0.05f);

		if (world.isClient()) {
			updateTrackedPositionAndAngles(x, y, z, yaw, pitch, 0, false);
		}
	}

	@Override
	public void tick() {
		super.tick();
		this.prevRenderX = x;
		this.prevRenderY = y;
		this.prevRenderZ = z;

		if (lifetime > 0) {
			lifetime--;
		} else {
			remove();
		}

		Box box = getBoundingBox().stretch(getVelocity()).expand(1.0d);

		HitResult hitResult = ProjectileUtil.getCollision(this, box, entity -> !entity.isSpectator() && entity.collides(), RayTraceContext.ShapeType.OUTLINE, true);

		HitResult.Type type = hitResult.getType();
		if (type != HitResult.Type.MISS) {
			if (type == HitResult.Type.BLOCK) {
				remove();
			} else if (type == HitResult.Type.ENTITY) {
				Entity hitEntity = ((EntityHitResult) hitResult).getEntity();
				hitEntity.damage(DamageSource.GENERIC, 5.0f);
				remove();
			}
		}

		Vec3d vel = getVelocity();
		x += vel.x;
		y += vel.y;
		z += vel.z;

		if (world.isClient()) {
			updateTrackedPosition(x, y, z);
		}

		setPosition(x, y, z);
	}

	@Override
	protected void initDataTracker() {
	}

	@Override
	protected void readCustomDataFromTag(CompoundTag compoundTag) {
	}

	@Override
	protected void writeCustomDataToTag(CompoundTag compoundTag) {
	}

	@Override
	public Packet<?> createSpawnPacket() {
		return new EntitySpawnS2CPacket(this, owner.getEntityId());
	}
}
