package com.fabriccommunity.spookytime.block;

import com.fabriccommunity.spookytime.block.entity.InfusionAltarBlockEntity;
import com.fabriccommunity.spookytime.block.entity.InfusionPillarBlockEntity;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.data.server.RecipesProvider;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeInputProvider;
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

	private int delay = 200;

	public InfusionAltarBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new InfusionAltarBlockEntity();
	}

	@Override
	public boolean activate(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult) {
		InfusionAltarBlockEntity altarEntity = (InfusionAltarBlockEntity) world.getBlockEntity(blockPos);
		altarEntity.linkedPillars = hasPillars(world, blockPos);
		if (altarEntity != null) {
			List<ItemStack> pillarStacks = new ArrayList<ItemStack>();
			altarEntity.linkedPillars.forEach((pos, entity) -> {
				if (entity.storedStack != null) {
					pillarStacks.add(entity.storedStack.copy());
				}
			});
			if (InfusionAltarBlockEntity.craftRecipe(pillarStacks) != ItemStack.EMPTY) {
				if (this.delay > 0) {
					altarEntity.stacksToDraw = new ArrayList<>(pillarStacks);
					Block.dropStack(world, blockPos, InfusionAltarBlockEntity.craftRecipe(pillarStacks).copy());
					altarEntity.linkedPillars.forEach((pos, entity) -> {
						entity.storedStack = null;
					});
					if (world.isClient) {
						world.playSound(MinecraftClient.getInstance().player, blockPos, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
					}
				} else {
					return true;
				}
			}
		}
		return true;
	}

	@Override
	public void onPlaced(World world, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
		super.onPlaced(world, blockPos, blockState, livingEntity, itemStack);
		InfusionAltarBlockEntity altarEntity = (InfusionAltarBlockEntity) world.getBlockEntity(blockPos);
		if (altarEntity != null) {
			altarEntity.linkedPillars = hasPillars(world, blockPos);
		}
	}

	public Map<BlockPos, InfusionPillarBlockEntity> hasPillars(World world, BlockPos blockPos) {
		Map<BlockPos, InfusionPillarBlockEntity> pillars = new HashMap<>();
		for (Direction direction : HorizontalFacingBlock.FACING.getValues()) {
			BlockPos offsetPos = blockPos.offset(direction, 3);
			if (world.getBlockState(offsetPos).getBlock() == SpookyBlocks.INFUSION_PILLAR_BLOCK) {
				InfusionPillarBlockEntity pillarEntity = (InfusionPillarBlockEntity)world.getBlockEntity(offsetPos);
				if (pillarEntity != null) {
					pillars.put(offsetPos, pillarEntity);
				}
			}
		}
		return pillars;
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
