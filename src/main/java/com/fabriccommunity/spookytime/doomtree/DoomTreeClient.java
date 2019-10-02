package com.fabriccommunity.spookytime.doomtree;

import com.fabriccommunity.spookytime.client.model.SimpleRandomModel;
import com.fabriccommunity.spookytime.client.model.SimpleUnbakedModel;
import com.fabriccommunity.spookytime.client.model.SpookyModels;
import com.fabriccommunity.spookytime.doomtree.model.AlchemicalBasin;
import com.fabriccommunity.spookytime.doomtree.model.DoomLog;
import com.fabriccommunity.spookytime.doomtree.model.DoomLogChannel;
import com.fabriccommunity.spookytime.doomtree.model.DoomLogTerminal;
import com.fabriccommunity.spookytime.doomtree.model.DoomTreeHeart;

import net.fabricmc.fabric.api.client.render.InvalidateRenderStateCallback;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;

public class DoomTreeClient {
	public static void init() {
		SimpleUnbakedModel model = new SimpleUnbakedModel(DoomLog::create, DoomLog.TEXTURES);
		SpookyModels.register("doom_log", model);
		SpookyModels.register("doom_log_p", model);

		model = new SimpleUnbakedModel(DoomLogChannel::create, DoomLogChannel.CHANNEL_TEXTURES);
		SpookyModels.register("doom_log_channel", model);
		SpookyModels.register("doom_log_channel_p", model);

		model = new SimpleUnbakedModel(DoomLogTerminal::create, DoomLogTerminal.TERMINAL_TEXTURES);
		SpookyModels.register("doom_log_terminal", model);
		SpookyModels.register("doom_log_terminal_p", model);


		SpookyModels.register("alchemical_basin", new SimpleUnbakedModel(AlchemicalBasin::create, AlchemicalBasin.TEXTURES));
		SpookyModels.register("alchemical_basin_frame", new SimpleUnbakedModel(AlchemicalBasin::createFrame, AlchemicalBasin.TEXTURES));

		SpookyModels.register("doom_tree_heart", new SimpleUnbakedModel(DoomTreeHeart::create, DoomTreeHeart.TERMINAL_TEXTURES));
		
		SimpleRandomModel.register("doom_leaves", "block/doom_leaves_0_0", "block/doom_leaves_0_1", "block/doom_leaves_0_2", "block/doom_leaves_0_3");
		SimpleRandomModel.register("doomed_residue_block", "block/doomed_residue_block");
		SimpleRandomModel.register("warding_essence_block", "block/warding_essence_block");
		
		ClientSidePacketRegistry.INSTANCE.register(DoomTreePacket.IDENTIFIER, DoomTreePacketHandler::accept);
		
		InvalidateRenderStateCallback.EVENT.register(() -> {
			DoomTree.RENDER_REFRESH_HANDLER = p -> {
				final MinecraftClient client = MinecraftClient.getInstance();
				System.out.println("boop");
				client.worldRenderer.updateBlock(client.world, p, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), 8);
			};
		});
	}
}
