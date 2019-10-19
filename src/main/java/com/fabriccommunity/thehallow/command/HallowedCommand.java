package com.fabriccommunity.thehallow.command;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.ClickEvent.Action;
import net.minecraft.text.Texts;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import com.mojang.brigadier.context.CommandContext;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class HallowedCommand {
	public static int run(CommandContext<ServerCommandSource> ctx) {
		ctx.getSource().sendFeedback(Texts.bracketed(new TranslatableText("thehallow.about.name").formatted(Formatting.GOLD)), false);
		ctx.getSource().sendFeedback(new TranslatableText("thehallow.about.description").formatted(Formatting.LIGHT_PURPLE), false);
		ctx.getSource().sendFeedback(new TranslatableText("thehallow.about.github").formatted(Formatting.YELLOW)
				.append(new TranslatableText("thehallow.github").formatted(Formatting.GREEN)
					.styled(style -> style.setClickEvent(new ClickEvent(Action.OPEN_URL, "https://github.com/fabric-community/the-hallow")))),
			false);
		return SINGLE_SUCCESS;
	}
}
