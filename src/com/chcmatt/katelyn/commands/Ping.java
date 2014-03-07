package com.chcmatt.katelyn.commands;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;
					//Added as example
@Command(name="ping", alias="p", desc="Sends a ping reply message back to you.")
public class Ping extends GenericCommand
{
	
	public Ping(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	@Override
	public void execute()
	{
		event.respond(user.getNick() + ": " + Colors.setBold("PONG!"));
	}
}