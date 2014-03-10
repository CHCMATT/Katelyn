package com.chcmatt.katelyn.commands;

import org.pircbotx.PircBotX;
import org.pircbotx.Colors;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="calc", desc="Calculates a given arguement.", syntax="calc <term>", requiresArgs=true, alias={".","-","c"})
public class Calc extends GenericCommand
{
	
	public Calc(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	@Override
	public void execute()
	{
		event.respond(Colors.setBold(user.getNick()) + ": " + event.getArguments());
		//Math.addExact(x, y)
	}
}