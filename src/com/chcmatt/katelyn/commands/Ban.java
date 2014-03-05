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
		if (event.getArguments().startsWith("*!*") && event.getArguments().contains("@") && event.getArguments().contains("."))
		{
			String hostmask = Colors.removeFormattingAndColors(event.getArguments());
			channel.send().ban(hostmask);
		}
		else if (event.getArguments().contains("@") && event.getArguments().contains("."))
		{
			String hostmask = "*!*" + Colors.removeFormattingAndColors(event.getArguments());
			channel.send().ban(hostmask);
		}
		else if (event.getArguments().contains("."))
		{
			String hostmask = "*!*@" + Colors.removeFormattingAndColors(event.getArguments());
			channel.send().ban(hostmask);
		}
		else
		{
			if (userChannelDao.userExists(Colors.removeFormattingAndColors(event.getArguments())))
			{
				String user = Colors.removeFormattingAndColors(event.getArguments());
				channel.send().ban("*!*@" + userChannelDao.getUser(user).getHostmask());
			}
			else
			{
				String hostmask = Colors.removeFormattingAndColors(event.getArguments()) + "!*@*";
				channel.send().ban(hostmask);
			}
		}
	}
}
