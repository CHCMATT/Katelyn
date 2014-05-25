package com.chcmatt.katelyn.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.pircbotx.PircBotX;
import org.pircbotx.Colors;

import com.chcmatt.katelyn.handling.CommandEvent;
import com.chcmatt.katelyn.handling.CommandInfo;

@Command(name="help", desc="Displays command list or command information", syntax="help [command]")
public class Help extends GenericCommand
{
	
	public Help(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	public void execute()
	{
		String prePublic = config.getPrefixes().inverse().get("MESSAGE");
		String prePrivate = config.getPrefixes().inverse().get("NOTICE");
		
		if (event.hasNoArgs())
		{
			event.respond(Colors.setBold("Valid prefixes: ") + "\"" + prePublic + "\" to public message, \"" + prePrivate +"\" to private notice.");
			List<String> commandList = new ArrayList<String>();
			for (CommandInfo<GenericCommand> info : bot.getCommandRegistry().getCommands())
				commandList.add(info.getName());

			Collections.sort(commandList);
			event.respond(Colors.setBold("Commands: ") + StringUtils.join(commandList, ", "));
		}
		else
		{
			String cmd = event.getArgumentList().get(0);
			if (bot.getCommandRegistry().isCommand(cmd))
			{
				String desc = bot.getCommandRegistry().getCommandInfo(cmd).getDesc();
				event.respond(cmd.toLowerCase() + " - " + desc);
			}
			else
				event.respondToUser("Sorry, I don't have a command \"" + cmd + "\".");
		}
	}
}
