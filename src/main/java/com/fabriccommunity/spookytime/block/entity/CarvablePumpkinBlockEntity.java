package com.fabriccommunity.spookytime.block.entity;

import com.fabriccommunity.spookytime.common.SpookyBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.LongTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import java.util.BitSet;
/**
 * @author Indigo Amann
 */
public class CarvablePumpkinBlockEntity extends BlockEntity {
    public CarvablePumpkinBlockEntity() {
        super(SpookyBlocks.CARVABLE_PUMPKIN_BLOCK_ENTITY);
    }
    //                               4 Faces
    public BitSet[] carved = new BitSet[4];

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        ListTag faces = new ListTag();
        for (BitSet carvedFace : carved) {
        	ListTag face = new ListTag();
        	
        	if(carvedFace == null) {
        		face = new ListTag();
        	} else {
	            for (long twoRows: carvedFace.toLongArray()) {
	                face.add(new LongTag(twoRows));
	            }
        	}
            
        	faces.add(face);
        }
        tag.put("carving", faces);
        return tag;
    }
    
    @Override
    public void fromTag(CompoundTag tag) {
    	super.fromTag(tag);
    	this.carved = new BitSet[4];
    	ListTag facesTag = tag.getList("carving", new LongTag(0).getType()); 
    	for (int i = 0; i < 4; i++) {
    		Tag regularFaceTag = facesTag.get(i);
    		ListTag faceTag = (ListTag) regularFaceTag;
    		if (faceTag.isEmpty()) {
    			this.carved[i] = null;
    		} else {
    			long[] face = new long[faceTag.size()];
    			// maybe emit a warning if the face is not 256 bits long?
    			for (int j = 0; j < face.length; j++) {
    				LongTag longTag = (LongTag) faceTag.get(j);
    				face[j] = longTag.getLong();
    			}
    			this.carved[i] = BitSet.valueOf(face);
    		}
    	}
    }
    
    public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
        if (hitResult.getSide().getAxis() == Direction.Axis.Y) return false;
        Vec3d onBlock = hitResult.getPos().subtract(new Vec3d(hitResult.getBlockPos()));
        
        int x = (int) (onBlock.x * 16f);
        int y = (int) (onBlock.y * 16f);
        int z = (int) (onBlock.z * 16f);
        
        int face = hitResult.getSide().getId() - 2;
        int faceX = hitResult.getSide().getAxis() == Direction.Axis.Z ? x : z;
        
        if (carved[face] != null && carved[face].get(faceX + 16 * y)) {
        	return false;
        }
        
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem() instanceof ShearsItem) {
            if (!player.abilities.creativeMode) {
                stack.damage(1, player, it -> {});
            }
            if (carved[face] == null) {
            	// Initialize uncarved face
            	carved[face] = new BitSet(256);
            }
            carved[face].set(faceX + 16 * y, true);
            return true;
        }
        return false;
    }
}
