package com.fabriccommunity.thehallow.block;

import com.fabriccommunity.thehallow.registry.HallowedItems;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlacementEnvironment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class PumpkinPieBlock extends Block {
	public static final IntProperty BITES = IntProperty.of("bites", 1, 4);
	private static final VoxelShape SHAPE_1_BITE = VoxelShapes.union(Block.createCuboidShape(13, 0, 3, 15, 5.5, 8), Block.createCuboidShape(8, 0, 1, 15, 5.5, 3), Block.createCuboidShape(8, 0, 3, 13, 4.9, 8));
	private static final VoxelShape SHAPE_2_BITES = VoxelShapes.union(Block.createCuboidShape(13, 0, 3, 15, 5.5, 13), Block.createCuboidShape(8, 0, 1, 15, 5.5, 3), Block.createCuboidShape(8, 0, 13, 15, 5.5, 15), Block.createCuboidShape(8, 0, 3, 13, 4.9, 13));
	private static final VoxelShape SHAPE_3_BITES = VoxelShapes.union(Block.createCuboidShape(13, 0, 3, 15, 5.5, 13), Block.createCuboidShape(1, 0, 8, 3, 5.5, 13), Block.createCuboidShape(8, 0, 1, 15, 5.5, 3), Block.createCuboidShape(1, 0, 13, 15, 5.5, 15), Block.createCuboidShape(8, 0, 3, 13, 4.9, 13), Block.createCuboidShape(3, 0, 8, 8, 4.9, 13));
	private static final VoxelShape SHAPE_4_BITES = VoxelShapes.union(Block.createCuboidShape(13, 0, 3, 15, 5.5, 13), Block.createCuboidShape(1, 0, 3, 3, 5.5, 13), Block.createCuboidShape(1, 0, 1, 15, 5.5, 3), Block.createCuboidShape(1, 0, 13, 15, 5.5, 15), Block.createCuboidShape(3, 0, 3, 13, 4.9, 13));
	protected static final VoxelShape[] SHAPE = new VoxelShape[] {SHAPE_1_BITE, SHAPE_2_BITES, SHAPE_3_BITES, SHAPE_4_BITES};
	
	public PumpkinPieBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(BITES, 4));
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView blockView, BlockPos pos, EntityContext entityContext) {
		return SHAPE[state.get(BITES) - 1];
	}
	
	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
		if (!world.isClient) {
			return this.tryEat(world, pos, state, player);
		}
		ItemStack stack = player.getStackInHand(hand);
		if(stack.isEmpty()) {
			return ActionResult.PASS;
		}
		return this.tryEat(world, pos, state, player);
	}
	
	private ActionResult tryEat(IWorld iWorld, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!player.canConsume(false)) {
			return ActionResult.PASS;
		}
		float saturation = 0.1F;
		TrinketComponent trinketPlayer = TrinketsApi.getTrinketComponent(player);
		ItemStack mainHandStack = trinketPlayer.getStack("hand:ring");
		ItemStack offHandStack = trinketPlayer.getStack("offhand:ring");
		if (mainHandStack.getItem().equals(HallowedItems.PUMPKIN_RING) || offHandStack.getItem().equals(HallowedItems.PUMPKIN_RING)) {
			saturation = 0.3F;
		}
		player.getHungerManager().add(2, saturation);
		int bites = state.get(BITES);
		if (bites > 1) {
			iWorld.setBlockState(pos, state.with(BITES, bites - 1), 3);
		} else {
			iWorld.removeBlock(pos, false);
		}
		return ActionResult.SUCCESS;
	}
	
	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState state2, IWorld iWorld, BlockPos pos, BlockPos pos2) {
		return direction == Direction.DOWN && !state.canPlaceAt(iWorld, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, state2, iWorld, pos, pos2);
	}
	
	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return world.getBlockState(pos.down()).getMaterial().isSolid();
	}
	
	@Override
	public boolean canPlaceAtSide(BlockState state, BlockView blockView, BlockPos pos, BlockPlacementEnvironment placementEnv) {
		return false;
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateFactoryBuilder) {
		stateFactoryBuilder.add(BITES);
	}
	
	@Override
	public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return (state.get(BITES) * 4) - 1;
	}
	
	@Override
	public boolean hasComparatorOutput(BlockState state) {
		return true;
	}
	
	@Override
	public String getTranslationKey() {
		return Items.PUMPKIN_PIE.getTranslationKey();
	}
}
