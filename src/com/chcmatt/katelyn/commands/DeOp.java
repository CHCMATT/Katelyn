package com.chcmatt.katelyn.commands;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;
import org.pircbotx.User;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="deop", desc="Removes channel operator from a user.", syntax = "deop [username]", alias="do", opOnly=true)
public class DeOp extends GenericCommand
{
	
	public DeOp(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	public void execute()
	{
		if (event.hasNoArgs())
		{
			channel.send().deOp(userChannelDao.getUser(event.getUser().getNick()));
		}
		else 
		{
			String nick = event.getArgumentList().get(0);
			if (userChannelDao.userExists(nick))
			{
				User user = userChannelDao.getUser(nick); 
				channel.send().deOp(user);
			}
			else
			{
				event.respond(Colors.setBold(user.getNick()) + ": There is no one in "+ Colors.setBold(event.getChannel().getName()) +" with the name " + Colors.setBold(event.getArgumentList().get(0)) + ".");
			}
		}
	}
}
