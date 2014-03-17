package com.chcmatt.katelyn.commands;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;
import org.pircbotx.User;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="op", desc="Gives channel operator to a user.", syntax = "op [username]", opOnly=true)
public class Op extends GenericCommand
{
	
	public Op(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	public void execute()
	{
		if (event.hasNoArgs())
		{
			channel.send().op(userChannelDao.getUser(event.getUser().getNick()));
		}
		else 
		{
			String nick = event.getArgumentList().get(0);
			if (userChannelDao.userExists(nick)) // Checks if the user is in the channel.
			{
				User user = userChannelDao.getUser(nick); 
				channel.send().op(user);
			}
			else
				event.respond(Colors.setBold(user.getNick()) + ": There is no one in here with the name " + Colors.setBold(event.getArgumentList().get(0)) + ".");
			}
	}
}
