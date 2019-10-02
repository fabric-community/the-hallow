package com.fabriccommunity.spookytime.command;

import com.mojang.brigadier.context.CommandContext;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.ClickEvent.Action;
import net.minecraft.text.Texts;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

public class SpookyCommand {
	public static int run(CommandContext<ServerCommandSource> ctx) {
		ctx.getSource().sendFeedback(Texts.bracketed(new TranslatableText("spookytime.about.name").formatted(Formatting.GOLD)), false);
		ctx.getSource().sendFeedback(new TranslatableText("spookytime.about.description").formatted(Formatting.LIGHT_PURPLE), false);
		ctx.getSource().sendFeedback(new TranslatableText("spookytime.about.github").formatted(Formatting.YELLOW)
						.append(new TranslatableText("spookytime.github").formatted(Formatting.GREEN)
								.styled(style -> style.setClickEvent(new ClickEvent(Action.OPEN_URL, "https://github.com/fabric-community/spooky-time")))),
				false);
		return 1;
	}
}
