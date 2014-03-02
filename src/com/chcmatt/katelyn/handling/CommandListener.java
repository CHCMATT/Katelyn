package com.chcmatt.katelyn.handling;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter<PircBotX>
{

	public void onCommand(CommandEvent<PircBotX> event)
	{
		boolean worked = event.getBot().getCommandRegistry().executeCommand(event);
		if (!worked)
			event.respondToUser("Error while executing command: " + event.getCommandName());
	}
}
