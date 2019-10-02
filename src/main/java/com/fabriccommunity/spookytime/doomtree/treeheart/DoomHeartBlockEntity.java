package com.fabriccommunity.spookytime.doomtree.treeheart;

import java.util.Comparator;

import com.fabriccommunity.spookytime.doomtree.DoomTree;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

public class DoomHeartBlockEntity extends BlockEntity implements Tickable {
   private static final ChunkTicketType<ChunkPos> DOOM_TREE_TICKET = ChunkTicketType.create("doom_tree", Comparator.comparingLong(ChunkPos::toLong));

	   
	int tickCounter = 0;
	long power = 1000;
	boolean leafTick = false;
	boolean itMe = false;
	
	LogTracker logs = null;
	TrunkBuilder builds = null;
	BranchBuilder branches = null;
	Troll troll = null;

	Job job = null;

	final BlockPos.Mutable mPos = new BlockPos.Mutable();

	public DoomHeartBlockEntity(BlockEntityType<?> entityType) {
		super(entityType);
	}

	public DoomHeartBlockEntity() {
		this(DoomTree.DOOM_HEART);
	}

	@Override
	public void setPos(BlockPos pos) {
		super.setPos(pos);
		
		// NBT deserialization happens before this
		if (logs == null) logs = new LogTracker(pos);
		if (builds == null) builds = new TrunkBuilder(pos);
		if (branches == null) branches = new BranchBuilder(pos);
		if (troll == null) troll = new Troll(pos);
	}

	@Override
	public void validate() {
		super.validate();

		if (world !=null  && !world.isClient) {
			DoomTreeTracker.track(world, pos);
			forceChunks((ServerWorld) world, new ChunkPos(this.pos), true);
		}
	}
	
	static void forceChunks(ServerWorld world, ChunkPos chunkPos, boolean enable) {
		final ServerChunkManager scm = world.method_14178();
		
		if (enable) {
			scm.addTicket(DOOM_TREE_TICKET, chunkPos, 4, chunkPos);
		} else {
			scm.removeTicket(DOOM_TREE_TICKET, chunkPos, 4, chunkPos);
		}
	}

	@Override
	public void invalidate() {
		if (world !=null  && !world.isClient) {
			DoomTreeTracker.untrack(world, pos);
			forceChunks((ServerWorld) world, new ChunkPos(this.pos), false);
		}

		super.invalidate();
	}

	@Override
	public void tick() {
		if (world == null || world.isClient) {
			return;
		}

		++power;
		--tickCounter;

		itMe = true;
		
		if (job == null) {
			idle();
		} else {
			job = job.apply(this);
		}
		
		itMe = false;
	}

	void idle() {
		if (tickCounter <= 0 && power >= 2000) {
			final boolean noBuilds = builds.isEmpty() && branches.isEmpty();
			if((leafTick || noBuilds) && logs.hasBranches()) {
				LeafGrower.growLeaves(this);
				leafTick = false;
				return;
			} else if (!noBuilds) {
				if (builds.buildDistanceSquared() > branches.buildDistanceSquared()) {
					branches.build(this);	
				} else {
					builds.build(this);
				}
				leafTick = true;
				return;
			}
		}
		
		troll.troll(this);
	}

	void resetTickCounter() {
		tickCounter = 10;
	}

	public void setTemplate(long[] blocks) {
		final LogTracker logs = this.logs;
		for (long p : blocks) {
			logs.add(p);
		}

		job = new BuildPopulator(this);

		this.markDirty();
	}

	static final String LOG_KEY = "logs";
	static final String BRANCH_KEY = "branches";
	static final String POWER_KEY = "power";
	static final String TROLL_KEY = "troll";

	@Override
	public void fromTag(CompoundTag tag) {
		super.fromTag(tag);

		power = tag.getLong(POWER_KEY);

		if (logs == null) {
			logs = new LogTracker(this.getPos());
		}

		if  (tag.containsKey(LOG_KEY)) {
			logs.fromArray(tag.getIntArray(LOG_KEY));
		}

		if (builds == null) {
			builds = new TrunkBuilder(this.getPos());
		}

		if (branches == null) {
			branches = new BranchBuilder(this.getPos());
		}

		if (tag.containsKey(BRANCH_KEY)) {
			branches.fromArray(tag.getIntArray(BRANCH_KEY));
		}
		
		if (troll == null) {
			troll = new Troll(this.getPos());
		}

		if (tag.containsKey(TROLL_KEY)) {
			troll.fromArray(tag.getIntArray(TROLL_KEY));
		}

		job = new BuildPopulator(this);
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		tag = super.toTag(tag);
		tag.putLong(POWER_KEY, power);

		if (logs != null) {
			tag.putIntArray(LOG_KEY, logs.toIntArray());
		}
		
		if (branches != null) {
			tag.putIntArray(BRANCH_KEY, branches.toIntArray());
		}

		if (troll != null) {
			tag.putIntArray(TROLL_KEY, troll.toIntArray());
		}
		
		return tag;
	}

	public void reportBreak(BlockPos pos, boolean isLog) {
		if (itMe) return;
		
		if (isLog && logs.contains(pos)) {
			builds.enqueue(pos.asLong());
		} else {
			troll.enqueue(pos.asLong());
		}
	}
}
