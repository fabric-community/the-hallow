package com.fabriccommunity.spookytime.common;

import com.fabriccommunity.spookytime.SpookyTime;
import com.fabriccommunity.spookytime.block.CarvablePumpkinBlock;
import com.fabriccommunity.spookytime.block.DeceasedGrassBlock;
import com.fabriccommunity.spookytime.block.TinyPumpkinBlock;
import com.fabriccommunity.spookytime.block.entity.CarvablePumpkinBlockEntity;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpookyBlocks {
    public static BlockEntityType<CarvablePumpkinBlockEntity> CARVABLE_PUMPKIN_BLOCK_ENTITY;
    public static Block CARVABLE_PUMPKIN;
	public static Block TINY_PUMPKIN;
	
	public static Block DECEASED_DIRT;
	public static Block DECEASED_GRASS_BLOCK;
	public static Block TAINTED_SAND;
	public static Block TAINTED_GRAVEL;
	
	private SpookyBlocks() {
		// NO-OP
	}
	
	public static void init() {
        CARVABLE_PUMPKIN = register("carvable_pumpkin", new CarvablePumpkinBlock());
        CARVABLE_PUMPKIN_BLOCK_ENTITY = BlockEntityType.Builder.create(CarvablePumpkinBlockEntity::new, CARVABLE_PUMPKIN).build(null);
        Registry.register(Registry.BLOCK_ENTITY, new Identifier(SpookyTime.MODID, "carvable_pumpkin"), CARVABLE_PUMPKIN_BLOCK_ENTITY);
		TINY_PUMPKIN = register("tiny_pumpkin", new TinyPumpkinBlock(FabricBlockSettings.of(Material.PUMPKIN).strength(1.0F, 1.0F).sounds(BlockSoundGroup.LANTERN).build()), new Item.Settings().group(ItemGroup.MISC));
		
		DECEASED_DIRT = register("deceased_dirt", new Block(FabricBlockSettings.copy(Blocks.DIRT).materialColor(MaterialColor.PURPLE).build()));
		DECEASED_GRASS_BLOCK = register("deceased_grass_block", new DeceasedGrassBlock(FabricBlockSettings.copy(Blocks.GRASS_BLOCK).materialColor(MaterialColor.PURPLE).build()));
		TAINTED_SAND = register("tainted_sand", new FallingBlock(FabricBlockSettings.copy(Blocks.SAND).materialColor(MaterialColor.PURPLE).build()));
		TAINTED_GRAVEL = register("tainted_gravel", new FallingBlock(FabricBlockSettings.copy(Blocks.GRAVEL).materialColor(MaterialColor.PURPLE).build()));
	}
	
	static <T extends Block> T register(String name, T block, Item.Settings settings) {
		return register(name, block, new BlockItem(block, settings));
	}
	
	static <T extends Block> T register(String name, T block) {
		return register(name, block, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	}
	
	static <T extends Block> T register(String name, T block, BlockItem item) {
		T b = Registry.register(Registry.BLOCK, SpookyTime.id(name), block);
		BlockItem bi = SpookyItems.register(name, item);
		bi.appendBlocks(BlockItem.BLOCK_ITEMS, bi);
		return b;
	}
        
}
