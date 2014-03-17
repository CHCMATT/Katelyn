package com.chcmatt.katelyn.commands;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;
import org.pircbotx.User;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="voice", desc="Gives channel voice to a user.", syntax = "voice [username]", alias="v", opOnly=true)
public class Voice extends GenericCommand
{
	
	public Voice(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	public void execute()
	{
		if (event.hasNoArgs())
		{
			channel.send().voice(userChannelDao.getUser(event.getUser().getNick()));
		}
		else 
		{
			String nick = event.getArgumentList().get(0);
			if (userChannelDao.userExists(nick)) // Checks if the user is in the channel.
			{
				User user = userChannelDao.getUser(nick); 
				channel.send().voice(user);
			}
			else
				event.respond(Colors.setBold(user.getNick()) + ": There is no one in "+ Colors.setBold(event.getChannel().getName()) +" with the name " + Colors.setBold(event.getArgumentList().get(0)) + ".");
			}
	}
}
