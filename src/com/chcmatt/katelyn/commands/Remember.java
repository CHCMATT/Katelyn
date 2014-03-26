package com.chcmatt.katelyn.commands;

import org.apache.commons.lang3.StringUtils;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;
import com.chcmatt.katelyn.handling.FactoidManager;

@Command(name="remember", alias="r", desc="Saves, or \"remembers\", a factoid. Call with ?<factoidName>", syntax="remember <name> <data>", requiresArgs=true, adminOnly=true)
public class Remember extends GenericCommand
{
	public Remember(CommandEvent<PircBotX> event)
	{
		super(event);
	}

	public void execute()
	{
		String factoid = event.getArgumentList().get(0);
		String data = event.getArgRange(1);

		if (!FactoidManager.factoidExists(factoid) && StringUtils.isNotBlank(data))
		{	
			FactoidManager.addFactoid(factoid, data);
			event.respond("Saved factoid: " + factoid);
		}
		else if (FactoidManager.factoidExists(factoid))
			event.respondToUser("Error: Factoid \"" + factoid + "\" already exists.");
		else
			event.respondToUser("Error: Nothing to save.");
	}
}

@Command(name="forget", alias="f", desc="Removes, or \"forgets\", a factoid", syntax="forget <factoidName>", requiresArgs=true, adminOnly=true)
class Forget extends GenericCommand
{
	public Forget(CommandEvent<PircBotX> event)
	{
		super(event);
	}

	public void execute()
	{
		String factoid = event.getArgumentList().get(0);

		if (FactoidManager.factoidExists(factoid))
		{
			FactoidManager.removeFactoid(factoid);
			event.respond("Removed factoid: " + factoid);
		}
		else
			event.respondToUser("Error: Factoid \"" + factoid + "\" doesn't exist.");

	}
}