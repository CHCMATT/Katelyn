package com.chcmatt.katelyn.commands;

import org.apache.commons.lang3.StringUtils;
import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;
import com.chcmatt.katelyn.handling.FactoidManager;

@Command(name="addmessage", alias={"addmsg","amsg"}, desc="Adds a message to be printed when the command called.", syntax="addmessage <name> <data>", requiresArgs=true, adminOnly=true)
public class Messages extends GenericCommand
{
	public Messages(CommandEvent<PircBotX> event)
	{
		super(event);
	}

	public void execute()
	{
		String factoid = event.getArgumentList().get(0).toLowerCase();
		String data = event.getArgRange(1);

		if (!FactoidManager.factoidExists(factoid) && StringUtils.isNotBlank(data))
		{	
			FactoidManager.addFactoid(factoid, data);
			event.respond("Saved factoid: " + factoid);
		}
		else if (FactoidManager.factoidExists(factoid))
			event.respondToUser(Colors.setColor("Error: Message \"" + factoid + "\" already exists.", Colors.RED));
		else
			event.respondToUser(Colors.setColor("Error: Nothing to save.", Colors.RED));
	}
}

@Command(name="removemessage", alias={"rmmsg","removemsg","rmmessage"}, desc="Removes a message that has been previously added.", syntax="removemessage <MessageName>", requiresArgs=true, adminOnly=true)
class RemoveMessage extends GenericCommand
{
	public RemoveMessage(CommandEvent<PircBotX> event)
	{
		super(event);
	}

	public void execute()
	{
		String factoid = event.getArgumentList().get(0);

		if (FactoidManager.factoidExists(factoid))
		{
			FactoidManager.removeFactoid(factoid);
			event.respondToUser("Successfully removed message \"" + factoid + "\".");
		}
		else
			event.respondToUser(Colors.setColor("Error: Message \"" + factoid + "\" doesn't exist.", Colors.RED));

	}
}