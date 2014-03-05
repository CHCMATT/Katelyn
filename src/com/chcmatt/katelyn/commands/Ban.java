package com.chcmatt.katelyn.commands;

import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="ban", desc="Bans a hostmask of a user.", syntax = "ban <hostmask or username>", requiresArgs = true, adminOnly=true)
public class Ban extends GenericCommand
{
	
	public Ban(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	public void execute()
	{
		Channel chan = event.getChannel();
		
		if (event.getArguments().startsWith("*!*") && event.getArguments().contains("@") && event.getArguments().contains("."))
		{
			String hostmask = Colors.removeFormattingAndColors(event.getArguments());
			bot.sendIRC().mode(chan, "+b " + hostmask);
		}
		else if (event.getArguments().contains("@") && event.getArguments().contains("."))
		{
			String hostmask = "*!*" + Colors.removeFormattingAndColors(event.getArguments());
			bot.sendIRC().mode(chan, "+b " + hostmask);
		}
		/*
		 *  else
			event.respondToUser(Colors.setBold("Invalid hostmask: ") + event.getArguments());
			*/
	}
}
