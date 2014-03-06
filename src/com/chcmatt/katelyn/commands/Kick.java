package com.chcmatt.katelyn.commands;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="kick", desc="Kicks a user out of the channel.", syntax = "kick <username> [reason]", requiresArgs = true, adminOnly=true)
public class Kick extends GenericCommand
{
	
	public Kick(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	public void execute()
	{
		String nick = Colors.removeFormattingAndColors(event.getArgumentsList().get(0));
		
		if (event.getArgumentsList().get(1) == null)
		{
			channel.send().kick(nick);
		}
		else
		{
			String reason = event.getArgumentsList().get(1);
			channel.send().kick(nick, reason);
		}
	}
}
