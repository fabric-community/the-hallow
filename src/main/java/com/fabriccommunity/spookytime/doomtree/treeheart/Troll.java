package com.fabriccommunity.spookytime.doomtree.treeheart;

import com.fabriccommunity.spookytime.doomtree.DoomTree;
import com.fabriccommunity.spookytime.doomtree.DoomTreePacket;
import com.fabriccommunity.spookytime.registry.SpookyBlocks;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntHeapPriorityQueue;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.fabric.api.server.PlayerStream;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.network.Packet;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings("serial")
class Troll extends IntHeapPriorityQueue {
	private final int originX;
	private final int originY;
	private final int originZ;

	private final int maxY;

	private final IntOpenHashSet set = new IntOpenHashSet();

	int y;
	int index;

	private static final int MAX_INDEX;
	private static final int[] OFFSETS;

	static {
		final IntArrayList offsets = new IntArrayList();
		
		for (int x = -TreeDesigner.RADIUS; x <= TreeDesigner.RADIUS; x++) {
			for (int z = -TreeDesigner.RADIUS; z <= TreeDesigner.RADIUS; z++) {
				final int sqd = x * x + z * z;
				
				if(sqd > 0 && sqd <= TreeDesigner.RADIUS * TreeDesigner.RADIUS) {
					offsets.add(RelativePos.relativePos(x, 0, z));
				}
			}
		}

		offsets.sort((i0, i1) -> Integer.compare(RelativePos.squaredDistance(i0), RelativePos.squaredDistance(i1)));

		OFFSETS = offsets.toIntArray();
		MAX_INDEX = OFFSETS.length;
	}

	Troll(BlockPos origin) {
		super((i0, i1) -> Integer.compare(RelativePos.squaredDistance(i0), RelativePos.squaredDistance(i1)));

		originX = origin.getX();
		originY = origin.getY();
		originZ = origin.getZ();

		maxY = ((originY + TreeDesigner.MAX_TRUNK_HEIGHT + 2) & ~15) + 16;
	}

	void enqueue(long pos) {
		enqueue(RelativePos.relativePos(originX, originY, originZ, pos));
	}

	void enqueue(BlockPos pos) {
		enqueue(RelativePos.relativePos(originX, originY, originZ, pos));
	}

	@Override
	public void enqueue(int relativePos) {
		if (set.add(relativePos)) {
			super.enqueue(relativePos);
		}
	}

	@Override
	public int dequeueInt() {
		final int result = super.dequeueInt();
		set.remove(result);
		return result;
	}

	void troll(DoomHeartBlockEntity heart) {
		if (isEmpty()) {
			trollNext(heart);
		} else {
			trollQueue(heart);
		}
	}

	private void trollNext(DoomHeartBlockEntity heart) {
		if (y >= maxY) {
			y = 0;
			index++;
			heart.markDirty();
		}

		if(index >= MAX_INDEX) {
			index = 0;
		}
		
		final int offset = OFFSETS[index];
		final int x = RelativePos.rx(offset);
		final int z = RelativePos.rz(offset);
		
		final BlockPos.Mutable mPos = heart.mPos;
		final World world = heart.getWorld();

		for (int i = 0; i < 16; i++) {
			trollBlock(world, mPos, heart, RelativePos.relativePos(x, -originY + y + i, z));
		}

		y += 16;
	}

	private void trollQueue(DoomHeartBlockEntity heart) {
		final BlockPos.Mutable mPos = heart.mPos;
		final World world = heart.getWorld();

		boolean didUpdate = false;

		for (int i = 0; i < 32; i++) {
			if(isEmpty()) {
				return;
			}

			didUpdate |= trollBlock(world, mPos, heart, dequeueInt());
		}

		if (didUpdate) {
			heart.resetTickCounter();
		}
		heart.markDirty();
	}
	
	private boolean trollBlock(World world, BlockPos.Mutable mPos, DoomHeartBlockEntity heart, int pos) {
		RelativePos.set(mPos, originX, originY, originZ, pos);

		if (!World.isValid(mPos) || !world.isBlockLoaded(mPos)) {
			return false;
		}

		final BlockState currentState = world.getBlockState(mPos);
		final BlockState trollState = Troll.trollState(world, currentState, mPos);

		if (trollState == null || trollState == currentState) return false;
		
		final Block newBlock = trollState.getBlock();
		if (!currentState.isAir() && newBlock != SpookyBlocks.WITCH_WATER_BLOCK) {
			heart.power += 2;
		}

		if (newBlock == DoomTree.MIASMA_BLOCK) {
			placeMiasma(mPos, world);
		} else {
			world.setBlockState(mPos, trollState, 19);
		}
		
		return true;
	}

	static void placeMiasma(BlockPos pos, World world) {
		BlockState state = (HashCommon.mix(pos.asLong()) & 31) == 0 ? DoomTree.GLEAM_STATE : DoomTree.MIASMA_STATE;
		world.setBlockState(pos, state);
		final Packet<?> packet = DoomTreePacket.misama(pos);
		PlayerStream.around(world, pos, 32).forEach(p -> 
		ServerSidePacketRegistry.INSTANCE.sendToPlayer(p, packet));
	}

	int[] toIntArray() {
		int[] result = new int[size + 2];
		result[0] = y;
		result[1] = index;
		System.arraycopy(heap, 0, result, 2, size);
		return result;
	}

	void fromArray(int[] data) {
		clear();

		y = data[0];
		index = data[1];
		final int newSize = data.length;

		if (newSize > 2) {
			for (int i = 2; i < newSize; i++) {
				enqueue(data[i]);
			}
		}
	}

	/**
	 * Null means can't troll - tendril blocked
	 */
	static BlockState trollState(World world, BlockState fromState, BlockPos pos) {
		final Block block = fromState.getBlock();
	
		if (block == Blocks.BEDROCK || block.matches(DoomTree.DOOM_TREE_IGNORED)) {
			return fromState;
		}
	
		final Material material = fromState.getMaterial();

		if (material.isLiquid() && block instanceof FluidBlock) {
			final FluidState fluidState = fromState.getFluidState();
			if (fluidState != null) {
				if (fluidState.isStill()) {
					if (material == Material.LAVA) {
						return DoomTree.DOOMED_STONE_STATE;
					} else {
						return SpookyBlocks.WITCH_WATER_BLOCK.getDefaultState();
					}
				}
			} else {
				return fromState;
			}
		}
	
		if (TreeDesigner.canReplace(fromState)) {
			if (block.matches(BlockTags.LOGS) && fromState.contains(PillarBlock.AXIS)) {
				return DoomTree.DOOMED_LOG_STATE.with(PillarBlock.AXIS, fromState.get(PillarBlock.AXIS));
			} else if (block.matches(BlockTags.DIRT_LIKE)) {
				return DoomTree.DOOMED_EARTH_STATE;
			} else if (block.isFullOpaque(fromState, world, pos)) {
				 if (material == Material.STONE) {
					return DoomTree.DOOMED_STONE_STATE;
				} else {
					return DoomTree.DOOMED_DUST_STATE;
				}
			}
			return DoomTree.MIASMA_STATE;
		}
	
		return fromState;
	}
}
