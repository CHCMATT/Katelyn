package com.chcmatt.katelyn.commands;

import org.pircbotx.PircBotX;
import org.pircbotx.Colors;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="ping", desc="Ping to test your lag.", adminOnly=false)
public class Ping extends GenericCommand
{
	private CommandEvent<PircBotX> event;
	
	public Ping(CommandEvent<PircBotX> event)
	{
		super(event);
		this.event = event;
	}
	
	public void execute()
	{
		event.respond(event.getUser().getNick() + ": " + Colors.setBold("PONG"));
	}
}
