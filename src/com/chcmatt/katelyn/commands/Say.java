package com.chcmatt.katelyn.commands;

import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="say", desc="Sends message to channel", adminOnly=true)
public class Say extends GenericCommand
{
	private CommandEvent<PircBotX> event;
	
	public Say(CommandEvent<PircBotX> event)
	{
		super(event);
		this.event = event;
	}
	
	@Override
	public void execute()
	{
		event.respond(event.getArgString());
	}
}