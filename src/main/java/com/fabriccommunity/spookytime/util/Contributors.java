package com.fabriccommunity.spookytime.util;

import com.fabriccommunity.spookytime.SpookyTime;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ContactInformation;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.Person;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of all contributors who helped out in the development process of
 * SpookyTime
 */
public class Contributors {
	// Please add names to the fabric mod json
	
	private static final List<Text> USER_INFO = new ArrayList<>();
	
	static {
		initContributors();
	}
	
	private static void initContributors() {
		ModMetadata metadata = FabricLoader.getInstance().getModContainer(SpookyTime.MOD_ID)
			.orElseThrow(() -> new IllegalStateException("Cannot find spooky time mod, report to fabric loader!"))
			.getMetadata();
		
		for (Person author : metadata.getAuthors()) {
			USER_INFO.add(makeDesc(author, Formatting.DARK_RED));
		}
		
		for (Person author : metadata.getContributors()) {
			USER_INFO.add(makeDesc(author, Formatting.GOLD));
		}
	}
	
	private static Text makeDesc(Person person, Formatting formatting) {
		ContactInformation contact = person.getContact();
		Text ret = new LiteralText(person.getName()).formatted(formatting);
		contact.get("github").ifPresent(gh ->
			ret.append(Texts.bracketed(new TranslatableText("social.spookytime.github")
				.styled(style -> style.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, gh))))
				.formatted(Formatting.BLACK)
			)
		);
		
		contact.get("minecraft").ifPresent(mc ->
			ret.append(Texts.bracketed(new TranslatableText("social.spookytime.minecraft")
				.styled(style -> style.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://mcuuid.net/?q=" + mc))))
				.formatted(Formatting.GREEN)
			)
		);
		
		return ret;
	}
	
	public static void sendContributorsMessage(ServerCommandSource source) {
		source.sendFeedback(Texts.bracketed(new TranslatableText("spookytime.contrib.title").formatted(Formatting.GOLD)), false);
		
		Text root = new LiteralText("");
		
		for (Text entry : USER_INFO) {
			root.append(new TranslatableText("spookytime.contrib.entry", entry.copy()));
		}
		
		source.sendFeedback(root, false);
	}
}
