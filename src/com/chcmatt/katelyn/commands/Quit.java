package com.chcmatt.katelyn.commands;

import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="quit", desc="Quits the server the command is sent from.", adminOnly=true)
public class Quit extends GenericCommand
{
	
	public Quit(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	public void execute()
	{		
		if (event.hasNoArgs())
		{
			event.respond("Quitting IRC (No Reason)");
			event.getBot().sendIRC().quitServer();
		}
		else
		{
			event.respond("Quitting IRC (" + event.getArguments() + ")");
			event.getBot().sendIRC().quitServer(event.getArguments());
		}
	}
}
