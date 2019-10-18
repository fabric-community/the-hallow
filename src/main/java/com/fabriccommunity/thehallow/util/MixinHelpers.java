package com.fabriccommunity.thehallow.util;

import com.fabriccommunity.thehallow.api.SnowGolemEntityModifiers;
import com.fabriccommunity.thehallow.block.ColoredCarvedPumpkinBlock;
import com.fabriccommunity.thehallow.block.ColoredPumpkinBlock;
import com.fabriccommunity.thehallow.mixin.CarvedPumpkinBlockAccessor;
import com.fabriccommunity.thehallow.registry.HallowTags;
import net.minecraft.advancement.criterion.Criterions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.Random;
import java.util.function.Predicate;

/**
 * @author Indigo Amann
 */
public class MixinHelpers {
	public static final Random RANDOM = new Random();

	public static ItemStack getEquippedOrPumpkin(LivingEntity entity, EquipmentSlot slot) {
		if (slot != EquipmentSlot.HEAD) {
			return entity.getEquippedStack(slot);
		}
		ItemStack stack = entity.getEquippedStack(EquipmentSlot.HEAD);
		if (stack.isEmpty()) {
			if (RANDOM.nextInt(10) == 0) {
				stack = new ItemStack(RANDOM.nextBoolean() ? Items.CARVED_PUMPKIN : Items.JACK_O_LANTERN);
				entity.setEquippedStack(EquipmentSlot.HEAD, stack);
			}
		}
		return stack;
	}

	public static final Predicate<BlockState> IS_PUMPKIN = (blockState) -> {
		return blockState != null && (HallowTags.CARVED_PUMPKIN_BLOCKS.contains(blockState.getBlock()) || (HallowTags.JACK_O_LANTERN_BLOCKS.contains(blockState.getBlock())));
	};

	public static void trySpawnEntity(World world, BlockPos blockPos, CarvedPumpkinBlock carvedPumpkinBlock) {
		CarvedPumpkinBlockAccessor accessor = (CarvedPumpkinBlockAccessor) carvedPumpkinBlock;

		BlockPattern.Result blockPatternResult = accessor.callGetSnowGolemPattern().searchAround(world, blockPos);

		BlockState pumpkinState;

		if (blockPatternResult != null) {
			pumpkinState = blockPatternResult.translate(0, 0, 0).getBlockState();

			for (int yTranslation = 0; yTranslation < accessor.callGetSnowGolemPattern().getHeight(); ++yTranslation) {
				CachedBlockPosition forLoopPosition = blockPatternResult.translate(0, yTranslation, 0);

				world.setBlockState(forLoopPosition.getBlockPos(), Blocks.AIR.getDefaultState(), 2);
				world.playLevelEvent(2001, forLoopPosition.getBlockPos(), Block.getRawIdFromState(forLoopPosition.getBlockState()));
			}

			SnowGolemEntity snowGolemEntity = (SnowGolemEntity) EntityType.SNOW_GOLEM.create(world);
			BlockPos floor = blockPatternResult.translate(0, 2, 0).getBlockPos();

			snowGolemEntity.setPositionAndAngles((double) floor.getX() + 0.5D, (double) floor.getY() + 0.05D, (double) floor.getZ() + 0.5D, 0.0F, 0.0F);

			SnowGolemEntityModifiers modifier = (SnowGolemEntityModifiers) snowGolemEntity;
			modifier.setHeadState(pumpkinState);

			strikeLightningIfWitched(pumpkinState, world, floor);

			world.spawnEntity(snowGolemEntity);
			Iterator<ServerPlayerEntity> iterator = world.getEntities(ServerPlayerEntity.class, snowGolemEntity.getBoundingBox().expand(5.0D)).iterator();

			while (iterator.hasNext()) {
				ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) iterator.next();
				Criterions.SUMMONED_ENTITY.handle(serverPlayerEntity, snowGolemEntity);
			}

			for (int yTranslation = 0; yTranslation < accessor.callGetSnowGolemPattern().getHeight(); ++yTranslation) {
				CachedBlockPosition forLoopBlockPos = blockPatternResult.translate(0, yTranslation, 0);

				world.updateNeighbors(forLoopBlockPos.getBlockPos(), Blocks.AIR);
			}
		} else if ((blockPatternResult = accessor.callGetIronGolemPattern().searchAround(world, blockPos)) != null) {
			// Grab the state of the pumpkin ploped down
			pumpkinState = blockPatternResult.translate(1, 0, 0).getBlockState();

			for (int xTranslation = 0; xTranslation < accessor.callGetIronGolemPattern().getWidth(); ++xTranslation) {
				for (int yTranslation = 0; yTranslation < accessor.callGetIronGolemPattern().getHeight(); ++yTranslation) {
					CachedBlockPosition forLoopBlockPos = blockPatternResult.translate(xTranslation, yTranslation, 0);

					world.setBlockState(forLoopBlockPos.getBlockPos(), Blocks.AIR.getDefaultState(), 2);
					world.playLevelEvent(2001, forLoopBlockPos.getBlockPos(), Block.getRawIdFromState(forLoopBlockPos.getBlockState()));
				}
			}

			BlockPos floor = blockPatternResult.translate(1, 2, 0).getBlockPos();
			IronGolemEntity ironGolemEntity = (IronGolemEntity) EntityType.IRON_GOLEM.create(world);

			ironGolemEntity.setPlayerCreated(true);
			ironGolemEntity.setPositionAndAngles(floor.getX() + 0.5D, floor.getY() + 0.05D, floor.getZ() + 0.5D, 0.0F, 0.0F);

			strikeLightningIfWitched(pumpkinState, world, floor);

			world.spawnEntity(ironGolemEntity);
			Iterator<ServerPlayerEntity> iterator = world.getEntities(ServerPlayerEntity.class, ironGolemEntity.getBoundingBox().expand(5.0D)).iterator();

			while (iterator.hasNext()) {
				ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) iterator.next();
				Criterions.SUMMONED_ENTITY.handle(serverPlayerEntity, ironGolemEntity);
			}

			for (int xTranslation = 0; xTranslation < accessor.callGetIronGolemPattern().getWidth(); ++xTranslation) {
				for (int yTranslation = 0; yTranslation < accessor.callGetIronGolemPattern().getHeight(); ++yTranslation) {
					CachedBlockPosition forLoopCurrentPos = blockPatternResult.translate(xTranslation, yTranslation, 0);

					world.updateNeighbors(forLoopCurrentPos.getBlockPos(), Blocks.AIR);
				}
			}
		}
	}

	private static void strikeLightningIfWitched(BlockState pumpkinState, World world, BlockPos floor) {
		if(pumpkinState.getBlock() instanceof ColoredCarvedPumpkinBlock) {
			ColoredCarvedPumpkinBlock cblock = (ColoredCarvedPumpkinBlock) pumpkinState.getBlock();
			if (cblock.getColor() == ColoredPumpkinBlock.PumpkinColor.WITCHED) {
				if(!world.isClient) {
					LightningEntity lightningEntity = new LightningEntity(world, floor.getX(), floor.getY(), floor.getZ(), true);
					world.playSound(floor.getX(), floor.getY(), floor.getZ(), SoundEvents.ENTITY_WITCH_AMBIENT, SoundCategory.HOSTILE, 1.0F, 1.0F, true);
					((ServerWorld) world).addLightning(lightningEntity);
				}
			}
		}
	}
}
