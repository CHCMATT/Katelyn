package com.chcmatt.katelyn.commands;

import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="admins", desc="Lists the current bot admins.", adminOnly=false)
public class listAdmins extends GenericCommand
{
	private CommandEvent<PircBotX> event;
	
	public listAdmins(CommandEvent<PircBotX> event)
	{
		super(event);
		this.event = event;
	}
	
	public void execute()
	{
		event.respond(event.getBot().getConfiguration().getAdminAccounts().toString());
	}
}
