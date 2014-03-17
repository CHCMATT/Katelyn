package com.chcmatt.katelyn.commands;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;
import org.pircbotx.User;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="devoice", desc="Removes channel voice from a user.", syntax = "devoice [username]", alias="dv", opOnly=true)
public class DeVoice extends GenericCommand
{
	
	public DeVoice(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	public void execute()
	{
		if (event.hasNoArgs())
		{
			channel.send().deVoice(userChannelDao.getUser(event.getUser().getNick()));
		}
		else 
		{
			String nick = event.getArgumentList().get(0);
			if (userChannelDao.userExists(nick))
			{
				User user = userChannelDao.getUser(nick); 
				channel.send().deVoice(user);
			}
			else
			{
				event.respond(Colors.setBold(user.getNick()) + ": There is no one in "+ Colors.setBold(event.getChannel().getName()) +" with the name " + Colors.setBold(event.getArgumentList().get(0)) + ".");
			}
		}
	}
}
