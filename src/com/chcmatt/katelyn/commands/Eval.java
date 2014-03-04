package com.chcmatt.katelyn.commands;

import org.pircbotx.PircBotX;
import bsh.EvalError;
import bsh.Interpreter;


import com.chcmatt.katelyn.utils.Utils;
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
			result = event.getUser().getNick() + ": "  + i.get("item").toString();
		}
		catch (EvalError e)
		{
			event.respondToUser("Error occurred while evaluating: \"" + eval + "\"");
			e.printStackTrace();
		}
		
		if (!result.toLowerCase().contains("pass") && !eval.contains("pass"))
			event.respond(result);
	}
}
