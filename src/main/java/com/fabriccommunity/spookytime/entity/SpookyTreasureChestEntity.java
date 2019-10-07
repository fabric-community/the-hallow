package com.fabriccommunity.spookytime.entity;

import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.loot.LootSupplier;
import net.minecraft.world.loot.context.LootContext;
import net.minecraft.world.loot.context.LootContextParameters;
import net.minecraft.world.loot.context.LootContextTypes;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.registry.SpookyEntities;
import io.netty.buffer.Unpooled;

import java.util.List;

public class SpookyTreasureChestEntity extends Entity {
	public static final Identifier ENTITY_ID = new Identifier(SpookyTime.MOD_ID, "spooky_treasure_chest");
	private static final int TICKS_PER_SECOND = 20;
	public static final int MAX_HINGE_PROGRESS = TICKS_PER_SECOND;
	// entity constants
	// todo: add config options for relevant fields
	private static final int MAX_SPIN_PROGRESS = 4 * TICKS_PER_SECOND;
	private static final int MAX_END_PROGRESS = 2 * TICKS_PER_SECOND;
	private static final float ROTATION_PER_TICK = 365f / MAX_SPIN_PROGRESS;
	public float rotation = 0;
	public float previousRotation = 0;
	// stored entity data
	private boolean shouldReplace = false;
	private boolean hasReplaced = false;
	private int spinProgress = 0;
	private int endProgress = 0;
	private int hingeProgress = 0;
	private boolean isSpinningUp = true;
	private boolean isEnding = false;
	private boolean isOpeningHinge = false;
	
	public SpookyTreasureChestEntity(EntityType<?> type, World world) {
		super(type, world);
	}
	
	public SpookyTreasureChestEntity(World world, double x, double y, double z, boolean shouldReplace, float initialRotation) {
		super(SpookyEntities.SPOOKY_TREASURE_CHEST, world);
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.rotation = initialRotation;
		this.shouldReplace = shouldReplace;
		
		this.updateTrackedPosition(this.x, this.y, this.z);
	}
	
	@Override
	public void tick() {
		if (shouldReplace && !hasReplaced) {
			world.setBlockState(getBlockPos(), Blocks.AIR.getDefaultState(), 3);
			hasReplaced = true;
		}
		
		if (isSpinningUp) {
			spinProgress++;
			
			previousRotation = rotation;
			rotation += ROTATION_PER_TICK;
			this.move(MovementType.SELF, new Vec3d(0, 0.01f, 0));
			
			if (spinProgress >= MAX_SPIN_PROGRESS) {
				isSpinningUp = false;
				isEnding = true;
			}
		}
		
		
		// stationary pause + wiggle after spin
		if (isEnding) {
			endProgress++;
			
			if (endProgress >= MAX_END_PROGRESS) {
				isEnding = false;
				isOpeningHinge = true;
			}
		}
		
		// hinge opening
		if (isOpeningHinge) {
			hingeProgress++;
			
			if (hingeProgress >= MAX_HINGE_PROGRESS * .8) {
				spawnDeathParticles();
			}
			
			if (hingeProgress >= MAX_HINGE_PROGRESS) {
				this.kill();
				
				if (!world.isClient) {
					dropLoot((ServerWorld) world);
				}
			}
		}
		
		// particles to ground
		world.addParticle(ParticleTypes.SMOKE, x, y, z, 0, -.1, 0);
	}
	
	// todo: add spooky treasure chest custom loot table
	private void dropLoot(ServerWorld serverWorld) {
		// set up loot objects
		LootSupplier supplier = serverWorld.getServer().getLootManager().getSupplier(new Identifier(SpookyTime.MOD_ID, "gameplay/treasure_chest_common"));
		LootContext.Builder builder =
			new LootContext.Builder(serverWorld)
				.setRandom(serverWorld.random)
				.put(LootContextParameters.POSITION, getBlockPos());
		
		// build & add loot to output
		List<ItemStack> stacks = supplier.getDrops(builder.build(LootContextTypes.CHEST));
		stacks.forEach(stack -> ItemScatterer.spawn(world, getBlockPos(), new BasicInventory(stack)));
	}
	
	private void spawnDeathParticles() {
		ParticleEffect particle = ParticleTypes.WITCH;
		double velX = 0;
		double velY = 1;
		double velZ = 0;
		
		double startX = x - .275f;
		double startY = y;
		double startZ = z - .275f;
		
		for (int i = 0; i < 10; i++) {
			double frontX = .5f * random.nextDouble();
			world.addParticle(particle, startX + frontX, startY + random.nextDouble() * .5, startZ + .5f, velX, velY, velZ);
			
			double backX = .5f * random.nextDouble();
			world.addParticle(particle, startX + backX, startY + random.nextDouble() * .5, startZ, velX, velY, velZ);
			
			double leftZ = .5f * random.nextDouble();
			world.addParticle(particle, startX, startY + random.nextDouble() * .5, startZ + leftZ, velX, velY, velZ);
			
			double rightZ = .5f * random.nextDouble();
			world.addParticle(particle, startX + .5f, startY + random.nextDouble() * .5, startZ + rightZ, velX, velY, velZ);
		}
	}
	
	@Override
	protected void initDataTracker() {
		
	}
	
	@Override
	protected void readCustomDataFromTag(CompoundTag compoundTag) {
		this.shouldReplace = compoundTag.getBoolean("shouldReplace");
		this.hasReplaced = compoundTag.getBoolean("hasReplaced");
		
		this.spinProgress = compoundTag.getInt("spinProgress");
		this.endProgress = compoundTag.getInt("endProgress");
		this.hingeProgress = compoundTag.getInt("hingeProgress");
		
		this.isSpinningUp = compoundTag.getBoolean("isSpinningUp");
		this.isEnding = compoundTag.getBoolean("isEnding");
		this.isOpeningHinge = compoundTag.getBoolean("isOpeningHinge");
		
		this.rotation = compoundTag.getFloat("rotation");
		this.previousRotation = compoundTag.getFloat("previousRotation");
	}
	
	@Override
	protected void writeCustomDataToTag(CompoundTag compoundTag) {
		compoundTag.putBoolean("shouldReplace", shouldReplace);
		compoundTag.putBoolean("hasReplaced", hasReplaced);
		
		compoundTag.putInt("spinProgress", spinProgress);
		compoundTag.putInt("endProgress", endProgress);
		compoundTag.putInt("hingeProgress", hingeProgress);
		
		compoundTag.putBoolean("isSpinningUp", isSpinningUp);
		compoundTag.putBoolean("isEnding", isEnding);
		compoundTag.putBoolean("isOpeningHinge", isOpeningHinge);
		
		compoundTag.putFloat("rotation", rotation);
		compoundTag.putFloat("previousRotation", previousRotation);
	}
	
	@Override
	public Packet<?> createSpawnPacket() {
		PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer());
		
		packet.writeDouble(x);
		packet.writeDouble(y);
		packet.writeDouble(z);
		
		packet.writeBoolean(shouldReplace);
		packet.writeFloat(rotation);
		
		packet.writeInt(getEntityId());
		
		return ServerSidePacketRegistry.INSTANCE.toPacket(ENTITY_ID, packet);
	}
	
	@Override
	public boolean isInvulnerable() {
		return true;
	}
	
	@Override
	public boolean damage(DamageSource damageSource_1, float float_1) {
		return false;
	}
	
	public int getEndProgress() {
		return endProgress;
	}
	
	public int getHingeProgress() {
		return hingeProgress;
	}
}
