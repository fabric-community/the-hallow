package com.fabriccommunity.spookytime.entity;

import com.fabriccommunity.spookytime.registry.SpookyEntities;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StemBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class PumpcownEntity extends CowEntity {
	public static final BlockState STEM_FEATURE = Blocks.PUMPKIN_STEM.getDefaultState().with(StemBlock.AGE, 7);
	
	public PumpcownEntity(EntityType<? extends CowEntity> entity, World world) {
		super(entity, world);
	}
	
	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new EscapeDangerGoal(this, 2.0D));
		this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
		this.goalSelector.add(3, new TemptGoal(this, 1.25D, Ingredient.ofItems(Items.PUMPKIN_PIE), false));
		this.goalSelector.add(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
		this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.add(7, new LookAroundGoal(this));
	}
	
	@Override
	public boolean isBreedingItem(ItemStack itemStack_1) {
		return itemStack_1.getItem() == Items.PUMPKIN_PIE;
	}
	
	@Override
	public boolean interactMob(PlayerEntity player, Hand hand) {
		ItemStack stack = player.getStackInHand(hand);
		if (stack.getItem() == Items.SHEARS && this.getBreedingAge() >= 0) {
			this.world.addParticle(ParticleTypes.EXPLOSION, this.x, this.y + (double) (this.getHeight() / 2.0F), this.z, 0.0D, 0.0D, 0.0D);
			if (!this.world.isClient) {
				this.remove();
				CowEntity cow = EntityType.COW.create(this.world);
				cow.setPositionAndAngles(this.x, this.y, this.z, this.yaw, this.pitch);
				cow.setHealth(this.getHealth());
				cow.field_6283 = this.field_6283;
				if (this.hasCustomName()) {
					cow.setCustomName(this.getCustomName());
				}
				
				this.world.spawnEntity(cow);
				
				for (int i = 0; i < 5; ++i) {
					this.world.spawnEntity(new ItemEntity(this.world, this.x, this.y + (double) this.getHeight(), this.z, new ItemStack(STEM_FEATURE.getBlock())));
				}
				
				stack.damage(1, player, ((player_1) -> {
					player_1.sendToolBreakStatus(hand);
				}));
				this.playSound(SoundEvents.ENTITY_MOOSHROOM_SHEAR, 1.0F, 1.0F);
			}
			return true;
		} else {
			return super.interactMob(player, hand);
		}
	}
	
	@Override
	public PumpcownEntity createChild(PassiveEntity entity) {
		return SpookyEntities.PUMPCOWN.create(this.world);
	}
}
