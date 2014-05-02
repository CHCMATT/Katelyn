package com.chcmatt.katelyn.commands;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="ban", desc="Bans a hostmask or a user.", syntax = "ban <hostmask or username>", requiresArgs = true, opOnly=true)
public class Ban extends GenericCommand
{
	
	public Ban(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	public void execute()
	{
		// Strip the argument up here for simplicity and .get(0) because you only want the first argument
		String arg = Colors.removeFormattingAndColors(event.getArgumentList().get(0));
		
		if (arg.startsWith("*!*") && arg.contains("@") && arg.contains("."))
		{
			channel.send().ban(arg);
		}
		else if (arg.contains("@") && arg.contains("."))
		{
			channel.send().ban("*!*" + arg);
		}
		else if (arg.contains("."))
		{
			channel.send().ban("*!*@" + arg);
		}
		else
		{
			if (userChannelDao.userExists(arg)) // Someone's nick was entered; use their hostmask
			{
				// You don't need to add *!*@, so just use "arg"
				channel.send().ban(userChannelDao.getUser(arg).getHostmask());
			}
			else // Someone's nick was entered but is not sharing a channel with the bot; ban them by nick 
			{
				// You don't need to add !*@*, so just ban "arg"
				channel.send().ban(arg);
			}
		}
	}
}
