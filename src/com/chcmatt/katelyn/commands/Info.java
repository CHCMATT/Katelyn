package com.chcmatt.katelyn.commands;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;
import com.chcmatt.katelyn.utils.WebUtils;

@Command(name="info", desc="Lists information.", adminOnly=false)
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
			String response = WebUtils.getGithubTags();
			event.respond(response);
		}
		catch (IOException | ParseException | IllegalArgumentException e)
		{
			event.respondToUser(Colors.setColor("An error occurred while getting tags", Colors.RED));
			e.printStackTrace();
		}
	}
}
