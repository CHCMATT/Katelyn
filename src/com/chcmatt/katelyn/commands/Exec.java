package com.chcmatt.katelyn.commands;

import org.pircbotx.PircBotX;

import bsh.EvalError;
import bsh.Interpreter;

import com.chcmatt.katelyn.utils.Utils;
import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="exec", desc="Execute a method within PircBotX", syntax="exec <code to execute>", requiresArgs=true, minGroup="admin")
public class Exec extends GenericCommand
{
	
	public Exec(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	public void execute()
	{
		String arg = event.getArguments();
		
		try
		{
			Interpreter i = Utils.createDefaultInterpreter(event);
			
			event.respondToUser("Trying to run \"" + arg + "\"");
			i.eval(arg);
		}
		catch (EvalError e)
		{
			//Let the user know that an error occurred (for debugging purposes)
			e.printStackTrace();
			event.respondToUser("Error while running \"" + arg + "\"");
		}
	}
}
