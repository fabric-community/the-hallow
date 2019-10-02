package com.fabriccommunity.spookytime.mixin;

import com.fabriccommunity.spookytime.MixinHelpers;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Indigo Amann
 */
@Mixin(EndermanEntity.class)
public abstract class EndermanPumpkinMixin extends HostileEntity {
    @Shadow public abstract void setCarriedBlock(BlockState blockState_1);

    protected EndermanPumpkinMixin(EntityType<? extends HostileEntity> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    @Override
    public EntityData initialize(IWorld iWorld, LocalDifficulty localDifficulty, SpawnType spawnType, EntityData entityData, CompoundTag compoundTag) {
        if (MixinHelpers.RANDOM.nextInt(10) == 0) this.setCarriedBlock((MixinHelpers.RANDOM.nextBoolean() ? Blocks.PUMPKIN : (MixinHelpers.RANDOM.nextBoolean() ? Blocks.CARVED_PUMPKIN : Blocks.JACK_O_LANTERN)).getDefaultState());
        return super.initialize(iWorld, localDifficulty, spawnType, entityData, compoundTag);
    }
}
