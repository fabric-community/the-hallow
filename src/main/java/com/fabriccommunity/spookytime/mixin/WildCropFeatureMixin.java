package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.WildCropFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Allows pumpkin spawning
 * @author Indigo Amann
 */
@Mixin(WildCropFeature.class)
public class WildCropFeatureMixin {
    @Redirect(method = "method_13651", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;"))
    private Block getFakeBlock(BlockState state) {
        return state.getBlock() == SpookyBlocks.DECEASED_GRASS_BLOCK ? Blocks.GRASS_BLOCK : state.getBlock();
    }
}
