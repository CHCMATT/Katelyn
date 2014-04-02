package com.chcmatt.katelyn.commands;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;
import com.chcmatt.katelyn.utils.WebUtils;

@Command(name="info", alias="information", desc="Lists current information about the bot.")
public class Info extends GenericCommand
{

	public Info(CommandEvent<PircBotX> event)
	{
		super(event);
	}

	public void execute()
	{
		try
		{
			String latestTag = WebUtils.getGithubTags().get(0).get("name").toString();
			String response = Colors.setBold(event.getUser().getNick()) + ": Hi, I'm " + bot.getNick() + " and I'm a bot! I'm running " + Colors.setBold("PircBotX 2.0") + " on GitHub Release version " + Colors.setBold(latestTag) + ". " + Colors.setBold(bot.getConfiguration().getOwnerAccount()) + " is my owner!";
			event.respond(response);
		}
		catch (IOException | ParseException | IllegalArgumentException e)
		{
			event.respondToUser(Colors.setColor("An error occurred while getting tags.", Colors.RED));
			e.printStackTrace();
		}
	}
}
