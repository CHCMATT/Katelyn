package com.chcmatt.katelyn.commands;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;
@Command(name="ping", alias="p", desc="Sends a ping reply message back to you.")
public class Ping extends GenericCommand
{
	
	public Ping(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	@Command.Default
	public void ping()
	{
		
		if (event.hasNoArgs())
			event.respond(Colors.setBold(user.getNick()) + ": PONG!");
		else if (userChannelDao.userExists(event.getArguments()))
			event.respond(Colors.setBold(event.getArguments())+": PONG!");
		else
			event.respondToUser("Sorry, The user "+Colors.setBold(event.getArguments())+" does not exist in this channel.");
	}
}