package com.fabriccommunity.spookytime.block;

import com.fabriccommunity.spookytime.block.entity.CarvablePumpkinBlockEntity;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

/**
 * @author Indigo Amann
 */
public class CarvablePumpkinBlock extends BlockWithEntity {
    public CarvablePumpkinBlock() {
        super(FabricBlockSettings.of(Material.PUMPKIN, MaterialColor.ORANGE).strength(1.0F, 1.0F).sounds(BlockSoundGroup.WOOD).build());
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new CarvablePumpkinBlockEntity();
    }
    public boolean isOpaque(BlockState blockState_1) {
        return false;
    }

    public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
        BlockEntity blockEntity_1 = world_1.getBlockEntity(blockPos_1);
        if (blockEntity_1 instanceof CarvablePumpkinBlockEntity) {
            return ((CarvablePumpkinBlockEntity) blockEntity_1).activate(blockState_1, world_1, blockPos_1, playerEntity_1, hand_1, blockHitResult_1);
        }
        return false;
    }
}
