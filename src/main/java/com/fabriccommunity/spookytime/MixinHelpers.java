package com.fabriccommunity.spookytime;

import java.util.Iterator;
import java.util.Random;
import java.util.function.Predicate;

import com.fabriccommunity.spookytime.api.SnowGolemEntityModifiers;
import com.fabriccommunity.spookytime.mixin.CarvedPumpkinBlockAccessor;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;

import net.minecraft.advancement.criterion.Criterions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
		return blockState != null &&
			(blockState.getBlock() == Blocks.CARVED_PUMPKIN ||
				blockState.getBlock() == Blocks.JACK_O_LANTERN ||
				blockState.getBlock() == SpookyBlocks.RAINBOW_CARVED_PUMPKIN ||
				blockState.getBlock() == SpookyBlocks.RAINBOW_JACK_O_LANTERN ||
				blockState.getBlock() == SpookyBlocks.RED_JACK_O_LANTERN ||
				blockState.getBlock() == SpookyBlocks.RED_CARVED_PUMPKIN ||
				blockState.getBlock() == SpookyBlocks.BLUE_JACK_O_LANTERN ||
				blockState.getBlock() == SpookyBlocks.BLUE_CARVED_PUMPKIN ||
				blockState.getBlock() == SpookyBlocks.YELLOW_JACK_O_LANTERN ||
				blockState.getBlock() == SpookyBlocks.YELLOW_CARVED_PUMPKIN ||
				blockState.getBlock() == SpookyBlocks.TAN_JACK_O_LANTERN ||
				blockState.getBlock() == SpookyBlocks.TAN_CARVED_PUMPKIN ||
				blockState.getBlock() == SpookyBlocks.WHITE_JACK_O_LANTERN ||
				blockState.getBlock() == SpookyBlocks.WHITE_CARVED_PUMPKIN ||
				blockState.getBlock() == SpookyBlocks.WITCHED_JACK_O_LANTERN ||
				blockState.getBlock() == SpookyBlocks.WITCHED_CARVED_PUMPKIN ||
				blockState.getBlock() == SpookyBlocks.TINY_PUMPKIN);
	};

	public static void trySpawnEntity(World world, BlockPos blockPos, CarvedPumpkinBlock carvedPumpkinBlock) {
		CarvedPumpkinBlockAccessor accessor = (CarvedPumpkinBlockAccessor) carvedPumpkinBlock;

		BlockPattern.Result blockPatternResult = accessor.getGetSnowGolemPattern().searchAround(world, blockPos);

		BlockState pumpkinState;

		if (blockPatternResult != null) {
			pumpkinState = blockPatternResult.translate(0, 0, 0).getBlockState();

			for (int int_3 = 0; int_3 < accessor.getGetSnowGolemPattern().getHeight(); ++int_3) {
				CachedBlockPosition forLoopPosition = blockPatternResult.translate(0, int_3, 0);

				world.setBlockState(forLoopPosition.getBlockPos(), Blocks.AIR.getDefaultState(), 2);
				world.playLevelEvent(2001, forLoopPosition.getBlockPos(), Block.getRawIdFromState(forLoopPosition.getBlockState()));
			}

			SnowGolemEntity snowGolemEntity = (SnowGolemEntity) EntityType.SNOW_GOLEM.create(world);
			BlockPos floor = blockPatternResult.translate(0, 2, 0).getBlockPos();

			snowGolemEntity.setPositionAndAngles((double) floor.getX() + 0.5D, (double) floor.getY() + 0.05D, (double) floor.getZ() + 0.5D, 0.0F, 0.0F);

			SnowGolemEntityModifiers modifier = (SnowGolemEntityModifiers) snowGolemEntity;
			modifier.setHeadState(pumpkinState);

			world.spawnEntity(snowGolemEntity);
			Iterator<ServerPlayerEntity> iterator = world.getEntities(ServerPlayerEntity.class, snowGolemEntity.getBoundingBox().expand(5.0D)).iterator();

			while (iterator.hasNext()) {
				ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) iterator.next();
				Criterions.SUMMONED_ENTITY.handle(serverPlayerEntity, snowGolemEntity);
			}

			for (int int_5 = 0; int_5 < accessor.getGetSnowGolemPattern().getHeight(); ++int_5) {
				CachedBlockPosition forLoopBlockPos = blockPatternResult.translate(0, int_5, 0);

				world.updateNeighbors(forLoopBlockPos.getBlockPos(), Blocks.AIR);
			}
		} else if ((blockPatternResult = accessor.getGetIronGolemPattern().searchAround(world, blockPos)) != null) {
			// Grab the state of the pumpkin ploped down
			pumpkinState = blockPatternResult.translate(0, 0, 0).getBlockState();

			for (int int_3 = 0; int_3 < accessor.getGetIronGolemPattern().getWidth(); ++int_3) {
				for (int int_4 = 0; int_4 < accessor.getGetIronGolemPattern().getHeight(); ++int_4) {
					CachedBlockPosition forLoopBlockPos = blockPatternResult.translate(int_3, int_4, 0);

					world.setBlockState(forLoopBlockPos.getBlockPos(), Blocks.AIR.getDefaultState(), 2);
					world.playLevelEvent(2001, forLoopBlockPos.getBlockPos(), Block.getRawIdFromState(forLoopBlockPos.getBlockState()));
				}
			}

			BlockPos floor = blockPatternResult.translate(1, 2, 0).getBlockPos();
			IronGolemEntity ironGolemEntity = (IronGolemEntity) EntityType.IRON_GOLEM.create(world);

			ironGolemEntity.setPlayerCreated(true);
			ironGolemEntity.setPositionAndAngles((double) floor.getX() + 0.5D, (double) floor.getY() + 0.05D, (double) floor.getZ() + 0.5D, 0.0F, 0.0F);
			world.spawnEntity(ironGolemEntity);
			Iterator<ServerPlayerEntity> iterator = world.getEntities(ServerPlayerEntity.class, ironGolemEntity.getBoundingBox().expand(5.0D)).iterator();

			while (iterator.hasNext()) {
				ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) iterator.next();
				Criterions.SUMMONED_ENTITY.handle(serverPlayerEntity, ironGolemEntity);
			}

			for (int int_5 = 0; int_5 < accessor.getGetIronGolemPattern().getWidth(); ++int_5) {
				for (int int_6 = 0; int_6 < accessor.getGetIronGolemPattern().getHeight(); ++int_6) {
					CachedBlockPosition forLoopCurrentPos = blockPatternResult.translate(int_5, int_6, 0);

					world.updateNeighbors(forLoopCurrentPos.getBlockPos(), Blocks.AIR);
				}
			}
		}
	}
}
