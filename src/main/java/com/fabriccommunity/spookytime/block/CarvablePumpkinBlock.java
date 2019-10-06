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
    public BlockEntity createBlockEntity(BlockView var) {
        return new CarvablePumpkinBlockEntity();
    }
    @Override
    public boolean isOpaque(BlockState blockState) {
        return false;
    }
    @Override
    public boolean activate(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult) {
        BlockEntity blockEntity = world.getBlockEntity(blockPos);
        if (blockEntity instanceof CarvablePumpkinBlockEntity) {
            return ((CarvablePumpkinBlockEntity) blockEntity).activate(blockState, world, blockPos, playerEntity, hand, blockHitResult);
        }
        return false;
    }
}
