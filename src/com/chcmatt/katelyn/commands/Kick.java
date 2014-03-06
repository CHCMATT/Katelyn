package com.chcmatt.katelyn.commands;

import org.pircbotx.PircBotX;
import org.pircbotx.User;

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
		String nick = event.getArgumentList().get(0);
		
		if (userChannelDao.userExists(nick)) // .kick(...) takes a User parameter, not a nick, so make sure the user exists
		{
			User user = userChannelDao.getUser(nick); 
			if (event.getArgumentList().size() == 1) // Cannot use .get(1) with only 1 argument, so check the size instead
			{
				channel.send().kick(user); 
			}
			else //if event.getArgumentList().size() > 1
			{
				String reason = event.getArgRange(1); // .get(1) gets the 2nd argument, .getArgRange(1) gets 2nd to the end
				channel.send().kick(user, reason);
			}
		}
		else
			event.respondToUser("User doesn't exist: " + nick);
	}
}
