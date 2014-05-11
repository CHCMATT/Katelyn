package com.chcmatt.katelyn.commands;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import bsh.EvalError;
import bsh.Interpreter;



import com.chcmatt.katelyn.utils.Utils;
import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="eval", desc="Evaluate a method within PircBotX", syntax="eval <code to evaluate>", requiresArgs = true, adminOnly=true)
public class Eval extends GenericCommand
{

	public Eval(CommandEvent<PircBotX> event)
	{
		super(event);
	}

	public void execute()
	{
		String eval = event.getArguments();

		String result = "";
		try
		{
			Interpreter i = Utils.createDefaultInterpreter(event);

			i.eval("item = " + eval);
			result = Colors.setBold(user.getNick()) + ": "  + i.get("item").toString();
		}
		catch (EvalError e)
		{
			event.respondToUser(Colors.setColor("An error occurred during evaluation.", Colors.RED));
			e.printStackTrace();
		}

		if (!result.toLowerCase().contains("pass") && !eval.toLowerCase().contains("pass"))
			event.respond(result);
		else
			event.respondToUser(Colors.setColor("That evaluation returned a password, this has been automatically rejected. Try again with a new evaluation statement.", Colors.RED));
	}
}
