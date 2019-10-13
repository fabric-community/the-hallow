package com.fabriccommunity.spookytime.block;

import com.fabriccommunity.spookytime.registry.SpookyBlocks;
import com.fabriccommunity.spookytime.registry.SpookyTags;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;

public class BloodBlock extends CraftingFluidBlock {
	public BloodBlock(BaseFluid fluid, Settings settings) {
		super(fluid, settings, SoundEvents.ENTITY_PLAYER_SPLASH);
		addRecipe(Items.RED_BANNER, Items.WHITE_BANNER);
		addRecipe(Items.RED_BED, Items.WHITE_BED);
		addRecipe(Items.RED_CARPET, Items.WHITE_CARPET);
		addRecipe(Items.RED_CONCRETE, Items.WHITE_CONCRETE);
		addRecipe(Items.RED_CONCRETE_POWDER, Items.WHITE_CONCRETE_POWDER);
		addRecipe(Items.RED_SHULKER_BOX, Items.SHULKER_BOX);
		addRecipe(Items.RED_STAINED_GLASS, Items.GLASS);
		addRecipe(Items.RED_STAINED_GLASS_PANE, Items.GLASS_PANE);
		addRecipe(Items.RED_WOOL, Items.WHITE_WOOL);
		addRecipe(Items.RED_SAND, Items.SAND);
		addRecipe(Items.RED_SANDSTONE, Items.SANDSTONE);
		addRecipe(Items.SMOOTH_RED_SANDSTONE, Items.SMOOTH_SANDSTONE);
		addRecipe(Items.CUT_RED_SANDSTONE, Items.CUT_SANDSTONE);
		addRecipe(Items.CHISELED_RED_SANDSTONE, Items.CHISELED_SANDSTONE);
		addRecipe(Items.RED_SANDSTONE_STAIRS, Items.SANDSTONE_STAIRS);
		addRecipe(Items.SMOOTH_RED_SANDSTONE_STAIRS, Items.SMOOTH_SANDSTONE_STAIRS);
		addRecipe(Items.RED_SANDSTONE_SLAB, Items.SANDSTONE_SLAB);
		addRecipe(Items.SMOOTH_RED_SANDSTONE_SLAB, Items.SMOOTH_SANDSTONE_SLAB);
		addRecipe(Items.CUT_RED_SANDSTONE_SLAB, Items.CUT_SANDSTONE_SLAB);
		addRecipe(Items.RED_SANDSTONE_WALL, Items.SANDSTONE_WALL);
		addRecipe(Items.RED_TULIP, Items.WHITE_TULIP);
		addRecipe(Items.RED_NETHER_BRICKS, Items.NETHER_BRICKS);
		addRecipe(Items.RED_NETHER_BRICK_SLAB, Items.NETHER_BRICK_SLAB);
		addRecipe(Items.RED_NETHER_BRICK_STAIRS, Items.NETHER_BRICK_STAIRS);
		addRecipe(Items.RED_NETHER_BRICK_WALL, Items.NETHER_BRICK_WALL);

		addRecipe(SpookyBlocks.RED_PUMPKIN, SpookyBlocks.BLUE_PUMPKIN);
		addRecipe(SpookyBlocks.RED_PUMPKIN, SpookyBlocks.YELLOW_PUMPKIN);
		addRecipe(SpookyBlocks.RED_PUMPKIN, SpookyBlocks.TAN_PUMPKIN);
		addRecipe(SpookyBlocks.RED_PUMPKIN, SpookyBlocks.RAINBOW_PUMPKIN);
		addRecipe(SpookyBlocks.RED_PUMPKIN, SpookyBlocks.WHITE_PUMPKIN);
		addRecipe(SpookyBlocks.RED_PUMPKIN, Items.PUMPKIN);

		addRecipe(SpookyBlocks.RED_CARVED_PUMPKIN, SpookyBlocks.BLUE_CARVED_PUMPKIN);
		addRecipe(SpookyBlocks.RED_CARVED_PUMPKIN, SpookyBlocks.YELLOW_CARVED_PUMPKIN);
		addRecipe(SpookyBlocks.RED_CARVED_PUMPKIN, SpookyBlocks.TAN_CARVED_PUMPKIN);
		addRecipe(SpookyBlocks.RED_CARVED_PUMPKIN, SpookyBlocks.RAINBOW_CARVED_PUMPKIN);
		addRecipe(SpookyBlocks.RED_CARVED_PUMPKIN, SpookyBlocks.WHITE_CARVED_PUMPKIN);
		addRecipe(SpookyBlocks.RED_CARVED_PUMPKIN, Items.CARVED_PUMPKIN);

		addRecipe(SpookyBlocks.RED_JACK_O_LANTERN, SpookyBlocks.BLUE_JACK_O_LANTERN);
		addRecipe(SpookyBlocks.RED_JACK_O_LANTERN, SpookyBlocks.YELLOW_JACK_O_LANTERN);
		addRecipe(SpookyBlocks.RED_JACK_O_LANTERN, SpookyBlocks.TAN_JACK_O_LANTERN);
		addRecipe(SpookyBlocks.RED_JACK_O_LANTERN, SpookyBlocks.RAINBOW_JACK_O_LANTERN);
		addRecipe(SpookyBlocks.RED_JACK_O_LANTERN, SpookyBlocks.WHITE_JACK_O_LANTERN);
		addRecipe(SpookyBlocks.RED_JACK_O_LANTERN, Items.JACK_O_LANTERN);
	}
}
