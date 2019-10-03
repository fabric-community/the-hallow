package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Makes deceased soil count as "natural" for tree spawning
 * @author Indigo Amann
 */
@Mixin(Block.class)
public class BlockMixin {

    @Inject(method = "isNaturalDirt", at = @At("HEAD"), cancellable = true)
    private static void isNaturalDeceased(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (block == SpookyBlocks.DECEASED_GRASS_BLOCK || block == SpookyBlocks.DECEASED_DIRT) cir.setReturnValue(true);
    }
}
