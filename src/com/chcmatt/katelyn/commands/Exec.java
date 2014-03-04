package com.chcmatt.katelyn.commands;

import org.pircbotx.PircBotX;

import bsh.EvalError;
import bsh.Interpreter;

import com.chcmatt.katelyn.utils.Utils;
import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="exec", desc="Execute a method within PircBotX", adminOnly=true)
public class Exec extends GenericCommand
{
	private CommandEvent<PircBotX> event;
	
	public Exec(CommandEvent<PircBotX> event)
	{
		super(event);
		this.event = event;
	}
	
	public void execute()
	{
		String arg = event.getArgString();
		
		try
		{
			Interpreter i = Utils.createDefaultInterpreter(event);
			
			event.respondToUser("Trying to run \"" + arg + "\"");
			i.eval(arg);
		}
		catch (EvalError e)
		{
			e.printStackTrace();
			event.respondToUser("Error while running \"" + arg + "\"");
		}
	}
}
