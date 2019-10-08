package com.fabriccommunity.spookytime.block;

import com.fabriccommunity.spookytime.block.entity.InfusionAltarBlockEntity;
import com.fabriccommunity.spookytime.block.entity.InfusionPillarBlockEntity;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.google.common.collect.Iterables;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfusionAltarBlock extends Block implements BlockEntityProvider {
	private static final VoxelShape shapeA = Block.createCuboidShape(2, 0, 2, 14, 1, 14);
	private static final VoxelShape shapeB = Block.createCuboidShape(3, 1, 3, 13, 2, 13);

	private static final VoxelShape SHAPE = VoxelShapes.union(shapeA, shapeB);

	public InfusionAltarBlock(Block.Settings settings) {
		super(settings);
	}

	public List<ItemStack> getPillarStacks(InfusionAltarBlockEntity altarEntity) {
		List<ItemStack> pillarStacks = new ArrayList<>();
		altarEntity.linkedPillars.forEach((pos, entity) -> {
			if (entity.storedStack != null) {
				pillarStacks.add(entity.storedStack.copy());
			}
		});
		return pillarStacks;
	}

	public void clearStacks(InfusionAltarBlockEntity altarEntity) {
		altarEntity.linkedPillars.forEach((pos, entity) -> {
			entity.storedStack = null;
		});
	}

	public void getLinkedPillars(InfusionAltarBlockEntity altarEntity) {
		Map<BlockPos, InfusionPillarBlockEntity> pillars = new HashMap<>();
		for (Direction direction : HorizontalFacingBlock.FACING.getValues()) {
			BlockPos offsetPos = altarEntity.getPos().offset(direction, 3);
			if (altarEntity.getWorld().getBlockState(offsetPos).getBlock() == SpookyBlocks.INFUSION_PILLAR_BLOCK) {
				InfusionPillarBlockEntity pillarEntity = (InfusionPillarBlockEntity)altarEntity.getWorld().getBlockEntity(offsetPos);
				if (pillarEntity != null) {
					pillars.put(offsetPos, pillarEntity);
				}
			}
		}
		altarEntity.linkedPillars = pillars;
	}

	public Ingredient getInput(InfusionAltarBlockEntity altarEntity) {
		return Ingredient.ofStacks(Iterables.toArray(getPillarStacks(altarEntity), ItemStack.class));
	}

	public ItemStack getOutput(InfusionAltarBlockEntity altarEntity) {
		return InfusionAltarBlockEntity.craftRecipe(getInput(altarEntity));
	}

	public void createParticles(InfusionAltarBlockEntity altarEntity) {
		altarEntity.linkedPillars.forEach((pos, entity) -> {
			altarEntity.getWorld().addParticle(ParticleTypes.EXPLOSION, entity.getPos().getX(), entity.getPos().getY(), entity.getPos().getZ(), 0.0D, 0.0D, 0.0D);
		});
	}

	public void createSound(InfusionAltarBlockEntity altarEntity) {
		altarEntity.getWorld().playSound(MinecraftClient.getInstance().player, altarEntity.getPos(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0F, 1.0F);
	}

	public void createDrop(InfusionAltarBlockEntity alterEntity, ItemStack outputStack) {
		Block.dropStack(alterEntity.getWorld(), alterEntity.getPos(), outputStack.copy());
	}

	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new InfusionAltarBlockEntity();
	}

	@Override
	public boolean activate(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult) {
		InfusionAltarBlockEntity altarEntity = (InfusionAltarBlockEntity) world.getBlockEntity(blockPos);
		if (altarEntity != null) {
			getLinkedPillars(altarEntity);
			if (!getOutput(altarEntity).isEmpty()) {
				if (world.isClient()) {
					createParticles(altarEntity);
					createSound(altarEntity);
				}
				createDrop(altarEntity, getOutput(altarEntity));
				clearStacks(altarEntity);
			}
		}
		return true;
	}

	@Override
	public void onPlaced(World world, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
		super.onPlaced(world, blockPos, blockState, livingEntity, itemStack);
		InfusionAltarBlockEntity altarEntity = (InfusionAltarBlockEntity) world.getBlockEntity(blockPos);
		getLinkedPillars(altarEntity);
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPosition, EntityContext entityContext) {
		return SHAPE;
	}
}
