package com.chcmatt.katelyn.commands;

import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;
import com.chcmatt.katelyn.handling.CommandInfo;

public abstract class GenericCommand
{
	
	protected CommandEvent<PircBotX> event;
	protected CommandInfo<GenericCommand> info;
	
	public GenericCommand(CommandEvent<PircBotX> event)
	{
		this.event = event;
		this.info = event.getBot().getCommandRegistry().getCommandInfo(event);
	}
	
	public CommandInfo<GenericCommand> getCommandInfo()
	{
		return info;
	}
	
	public abstract void execute();
}
