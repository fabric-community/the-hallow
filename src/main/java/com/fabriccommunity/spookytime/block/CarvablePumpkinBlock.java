package com.fabriccommunity.spookytime.block;

import com.fabriccommunity.spookytime.block.entity.CarvablePumpkinBlockEntity;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.BlockView;

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
}
