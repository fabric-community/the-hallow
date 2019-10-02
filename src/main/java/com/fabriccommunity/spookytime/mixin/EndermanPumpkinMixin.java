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
    public EntityData initialize(IWorld iWorld_1, LocalDifficulty localDifficulty_1, SpawnType spawnType_1, EntityData entityData_1, CompoundTag compoundTag_1) {
        if (MixinHelpers.random.nextInt(3) == 0) this.setCarriedBlock((MixinHelpers.random.nextBoolean() ? Blocks.PUMPKIN : (MixinHelpers.random.nextBoolean() ? Blocks.CARVED_PUMPKIN : Blocks.JACK_O_LANTERN)).getDefaultState());
        return super.initialize(iWorld_1, localDifficulty_1, spawnType_1, entityData_1, compoundTag_1);
    }
}
