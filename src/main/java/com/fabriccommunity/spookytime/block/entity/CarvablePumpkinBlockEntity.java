package com.fabriccommunity.spookytime.block.entity;

import com.fabriccommunity.spookytime.common.SpookyBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author Indigo Amann
 */
public class CarvablePumpkinBlockEntity extends BlockEntity {
    public CarvablePumpkinBlockEntity() {
        super(SpookyBlocks.CARVABLE_PUMPKIN_BLOCK_ENTITY);
    }
    //                                      Face  X   Y
    public boolean[][][] carving = new boolean[4][16][16];

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        ListTag faces = new ListTag();
        for (boolean[][] carvingFace : carving) {
            ListTag face = new ListTag();
            for (boolean[] carvingRow: carvingFace) {
                ListTag row = new ListTag();
                for (boolean carved: carvingRow) {
                    row.add(new ByteTag((byte) (carved ? 1 : 0)));
                }
                face.add(row);
            }
            faces.add(face);
        }
        tag.put("carving", faces);
        return tag;
    }
    public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
        if (hitResult.getSide().getAxis() == Direction.Axis.Y) return false;
        Random random = new Random();
        carving[hitResult.getSide().getId() - 2][random.nextInt(16)][random.nextInt(16)] = true;
        return true;
    }
}
