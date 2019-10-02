package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.block.TinyPumpkinBlock;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpookyBlocks
{
	public static final Block SPOOKY_LANTERN = register(
		"spooky_lantern",
		new Block(
			FabricBlockSettings.of(Material.REDSTONE_LAMP)
				.breakByHand(true)
				.lightLevel(5)
				.ticksRandomly()
				.build()
		)
	);
	public static Block TINY_PUMPKIN;
    
	public static void init() {
        // NO-OP
		TINY_PUMPKIN = register("tiny_pumpkin", new TinyPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).strength(1.0F, 1.0F).sounds(BlockSoundGroup.LANTERN).build()));
    }

    private static Block register(String name, Block item) {
        return Registry.register(Registry.BLOCK, new Identifier(SpookyTime.MODID, name), item);
    }

    private SpookyBlocks() {
        // NO-OP
    }
}
