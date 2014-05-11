package com.chcmatt.katelyn.commands;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="say", desc="Sends message to channel", syntax="say <message>", adminOnly=true)
public class Say extends GenericCommand
{
	
	public Say(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	public void execute()
	{
		event.respond(Colors.setBold(user.getNick()) + ": " + event.getArguments());
	}
}