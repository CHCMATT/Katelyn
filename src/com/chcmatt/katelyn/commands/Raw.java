package com.chcmatt.katelyn.commands;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="raw", desc="Sends a specified command to the IRC server", syntax="raw <command>", adminOnly=true)
public class Raw extends GenericCommand
{

	public Raw(CommandEvent<PircBotX> event)
	{
		super(event);
	}

	@Override
	public void execute()
	{
		if (event.getArguments().startsWith("me")||event.getArguments().startsWith("action"))
		{
			String channel = event.getChannel().toString();
			String args = event.getArguments();
			bot.sendIRC().action(channel, args);
			event.respondToUser("Successfully sent command: " + Colors.setBold(event.getArguments()));
		}
		else
		{
			event.respondToUser(Colors.setColor("Error:", Colors.RED) + " Unable to run command: " + event.getArguments());
		}
	}
}