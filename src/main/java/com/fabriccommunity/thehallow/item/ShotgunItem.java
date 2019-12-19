package com.fabriccommunity.thehallow.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import com.fabriccommunity.thehallow.entity.ShotgunProjectileEntity;
import com.fabriccommunity.thehallow.registry.HallowedItems;
import com.fabriccommunity.thehallow.registry.HallowedSounds;

public class ShotgunItem extends Item {
	public static final int MAX_PELLETS = 6;

	public ShotgunItem(Settings settings) {
		super(settings);
	}

	private void shootGun(World world, PlayerEntity playerEntity) {
		for (int i = 0; i < MAX_PELLETS; i++) {
			// Code below derived from pWn3d1337
			float velMul = 4.0f;
			double velX = -MathHelper.sin(playerEntity.headYaw / 180.0F * (float) Math.PI)
				* MathHelper.cos(playerEntity.pitch / 180.0F * (float) Math.PI) * velMul;
			double velY = -MathHelper.sin((playerEntity.pitch) / 180.0F * (float) Math.PI) * velMul;
			double velZ = MathHelper.cos(playerEntity.headYaw / 180.0F * (float) Math.PI)
				* MathHelper.cos(playerEntity.pitch / 180.0F * (float) Math.PI) * velMul;

			float normalizer = MathHelper.sqrt(velX * velX + velY * velY + velZ * velZ);

			velX /= normalizer;
			velY /= normalizer;
			velZ /= normalizer;

			ShotgunProjectileEntity pellet = new ShotgunProjectileEntity(
				world,
				playerEntity,
				new Vec3d(velX, velY, velZ)
			);


			world.spawnEntity(pellet);
		}
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
		ItemStack stack = playerEntity.getStackInHand(hand);
		ItemStack offhand = playerEntity.getOffHandStack();

		if (playerEntity.isSneaking()) {
			if (stack.getDamage() != 0 && offhand.getItem().equals(HallowedItems.SHOTGUN_SHELL)) {
				playerEntity.getItemCooldownManager().set(this, 5);
				offhand.decrement(1);
				stack.damage(-1, playerEntity, p -> {
				});
				playerEntity.playSound(HallowedSounds.SHOTGUN_SHELL_RELOAD, 0.8f, 1.0f);
			}

			return new TypedActionResult<>(ActionResult.SUCCESS, stack);

		}

		if (offhand.isEmpty()) {
			if (stack.getDamage() > 7) {
				playerEntity.playSound(HallowedSounds.SHOTGUN_NO_AMMO, 0.8f, 1.0f);
				return new TypedActionResult<>(ActionResult.SUCCESS, stack);
			}

			playerEntity.playSound(HallowedSounds.SHOTGUN_SHOT, 0.8f, 1.0f);
			playerEntity.getItemCooldownManager().set(this, 20);
			stack.damage(1, playerEntity, p -> {
			});

			if (!world.isClient()) {
				shootGun(world, playerEntity);
			}
		}

		return new TypedActionResult<>(ActionResult.SUCCESS, stack);
	}
}
