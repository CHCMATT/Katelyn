package com.chcmatt.katelyn.commands;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;
import com.chcmatt.katelyn.utils.WebUtils;

@Command(name="calc", desc="Calculates a given math expression", syntax = "calc <expression>", requiresArgs=true, alias={"c","ca"})
public class Calc extends GenericCommand
{
	public Calc(CommandEvent<PircBotX> event)
	{
		super(event);
	}

	public void execute()
	{ 
		try
		{
			String response = WebUtils.getCalculation(event.getArguments());
			event.respond(response);
		}
		catch (IOException | ParseException | IllegalArgumentException e)
		{
			//Let the user know that an error occurred (for debugging purposes)
			event.respondToUser("An error occurred while calculating: " + event.getArguments());
			e.printStackTrace();
		}
	}
}