package com.chcmatt.katelyn.commands;

import org.pircbotx.PircBotX;
import org.pircbotx.Colors;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="reload", desc="Reloads all commands from the disc.", adminOnly=true)
public class Reload extends GenericCommand
{
	
	public Reload(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	public void execute()
	{
		//bot.getCommandRegistry().parseAnnotations();
		event.respond(user.getNick() + ": " + Colors.setBold("Successfully all commands from file!"));
	}
}