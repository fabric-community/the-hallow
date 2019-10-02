package com.fabriccommunity.spookytime.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.ImmutableList;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

/**
 * A list of all contributors who helped out in the development process of
 * SpookyTime
 */
public class Contributors {
	// Please leave adding names to this when we release so we can have a complete
	// list

	private static final Map<String, Formatting> USERS_AND_FORMATTING = new HashMap<>();

	private static final List<Formatting> DISALLOWED_FORMATTING = ImmutableList.of(Formatting.BOLD,
			Formatting.OBFUSCATED, Formatting.STRIKETHROUGH, Formatting.UNDERLINE, Formatting.ITALIC);

	static {
		initContributors();
	}

	private static void initContributors() {
		/*
		 * Here is an example of how to add a user to the list
		 * 
		 * USERS_AND_FORMATTING.put("Github-Username/MC Username", Formatting.DARK_RED);
		 * 
		 * Note that Magic, Underline and bold formattings are striped to make sure
		 * someone doesn't mess up.
		 */
	}

	public static void sendContributorsMessage(ServerCommandSource source) {
		source.sendFeedback(
				Texts.bracketed(new TranslatableText("spookytime.contrib.title").formatted(Formatting.GOLD)), false);

		Text root = new LiteralText("");

		for (Entry<String, Formatting> entry : USERS_AND_FORMATTING.entrySet()) {
			if (DISALLOWED_FORMATTING.contains(entry.getValue())) {
				root.append(new TranslatableText("spookytime.contrib.entry", entry.getKey()));
				continue;
			}

			root.append(new TranslatableText("spookytime.contrib.entry", entry.getKey()).formatted(entry.getValue()));
		}

		source.sendFeedback(root, false);
	}
}
