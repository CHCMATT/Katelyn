package com.chcmatt.katelyn.commands;

import org.pircbotx.PircBotX;
import bsh.EvalError;
import bsh.Interpreter;

import com.chcmatt.katelyn.Utils; //i'l get you this in a sec
import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="eval", desc="Evaluate a method within PircBotX", adminOnly=true)
public class Eval extends GenericCommand
{
	private CommandEvent<PircBotX> event;
	
	public Eval(CommandEvent<PircBotX> event)
	{
		super(event);
		this.event = event;
	}
	
	@Override
	public void execute()
	{
		String eval = event.getArgString();
			
		String result = "";
		try
		{
			Interpreter i = Utils.createDefaultInterpreter(event);
			
			i.eval("item = " + eval);
			result = i.get("item").toString();
		}
		catch (EvalError e)
		{
			event.respondToUser("Error occurred while evaluating: \"" + eval + "\"");
			e.printStackTrace();
		}
		
		// TODO cut off anything that overflows maximum line length (for Exec.java too)
		event.respond(result);
	}
}
