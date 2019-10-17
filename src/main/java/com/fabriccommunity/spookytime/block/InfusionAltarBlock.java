package com.fabriccommunity.spookytime.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import com.fabriccommunity.spookytime.block.entity.InfusionAltarBlockEntity;
import com.fabriccommunity.spookytime.block.entity.InfusionPillarBlockEntity;
import com.fabriccommunity.spookytime.inventory.InfusionInventory;
import com.fabriccommunity.spookytime.recipe.InfusionRecipe;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;

import com.google.common.collect.Iterables;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InfusionAltarBlock extends Block implements BlockEntityProvider {
	private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 12, 16);
	
	private InfusionInventory combinedInventory;
	
	public InfusionAltarBlock(Block.Settings settings) {
		super(settings);
	}
	
	public List<ItemStack> getPillarStacks(InfusionAltarBlockEntity altarEntity) {
		List<ItemStack> pillarStacks = new ArrayList<ItemStack>();
		altarEntity.linkedPillars.forEach((pos, entity) -> {
			if (!entity.storedStack.isEmpty()) {
				pillarStacks.add(entity.storedStack.copy());
			}
		});
		return pillarStacks;
	}
	
	public void clearAllStacks(InfusionAltarBlockEntity altarEntity) {
		altarEntity.storedStack = ItemStack.EMPTY;
		clearPillarStacks(altarEntity);
	}
	
	public void clearPillarStacks(InfusionAltarBlockEntity altarEntity) {
		altarEntity.linkedPillars.forEach((pos, entity) -> {
			entity.storedStack = ItemStack.EMPTY;
		});
	}
	
	public void getLinkedPillars(InfusionAltarBlockEntity altarEntity) {
		Map<BlockPos, InfusionPillarBlockEntity> pillars = new HashMap<BlockPos, InfusionPillarBlockEntity>();
		for (Direction direction : HorizontalFacingBlock.FACING.getValues()) {
			BlockPos offsetPos = altarEntity.getPos().offset(direction, 3);
			if (altarEntity.getWorld().getBlockState(offsetPos).getBlock() == SpookyBlocks.INFUSION_PILLAR_BLOCK) {
				InfusionPillarBlockEntity pillarEntity = (InfusionPillarBlockEntity) altarEntity.getWorld().getBlockEntity(offsetPos);
				if (pillarEntity != null) {
					pillars.put(offsetPos, pillarEntity);
				}
			}
		}
		altarEntity.linkedPillars = pillars;
	}
	
	public void getCombinedInventory(InfusionAltarBlockEntity altarEntity) {
		List<ItemStack> input = new ArrayList<ItemStack>();
		altarEntity.linkedPillars.forEach((pos, entity) -> {
			if (!entity.storedStack.isEmpty()) {
				input.add(entity.storedStack.copy());
			}
		});
		combinedInventory = new InfusionInventory(altarEntity.storedStack, Iterables.toArray(input, ItemStack.class));
	}
	
	public void createParticles(InfusionAltarBlockEntity altarEntity) {
		altarEntity.linkedPillars.forEach((pos, entity) -> {
			altarEntity.getWorld().addParticle(ParticleTypes.EXPLOSION, entity.getPos().getX(), entity.getPos().getY(), entity.getPos().getZ(), 0.0D, 0.0D, 0.0D);
		});
	}
	
	@Environment(EnvType.CLIENT)
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
		if (playerEntity.isSneaking()) {
			if (altarEntity != null) {
				getLinkedPillars(altarEntity);
				getCombinedInventory(altarEntity);
				Optional<InfusionRecipe> recipe = world.getRecipeManager().getFirstMatch(InfusionRecipe.Type.INSTANCE, combinedInventory, world);
				if (recipe.isPresent()) {
					if (world.isClient()) {
						createParticles(altarEntity);
						createSound(altarEntity);
					}
					if (!altarEntity.storedStack.isEmpty()) {
						createDrop(altarEntity, recipe.get().getOutput());
						clearAllStacks(altarEntity);
					} else {
						altarEntity.storedStack = recipe.get().getOutput().copy();
						clearPillarStacks(altarEntity);
					}
				}
			}
		} else {
			if (altarEntity != null) {
				if (playerEntity.getStackInHand(hand).isEmpty()) {
					playerEntity.inventory.offerOrDrop(world, altarEntity.takeStack());
				} else {
					playerEntity.setStackInHand(hand, altarEntity.putStack(playerEntity.getStackInHand(hand)));
				}
			}
		}
		
		return true;
	}
	
	@Override
	public void afterBreak(World world, PlayerEntity playerEntity, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity, ItemStack itemStack) {
		Block.dropStack(world, blockPos, ((InfusionAltarBlockEntity) blockEntity).storedStack);
		super.afterBreak(world, playerEntity, blockPos, blockState, blockEntity, itemStack);
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
