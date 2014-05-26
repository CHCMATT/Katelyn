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
			//Create ArrayList for each set of commands
			List<String> commandList = 		bot.getCommandRegistry().getCommandsMinGroup("default");
			List<String> modCommandList = 	bot.getCommandRegistry().getCommandsMinGroup("mod");
			List<String> adminCommandList = bot.getCommandRegistry().getCommandsMinGroup("admin");
			List<String> ownerCommandList = bot.getCommandRegistry().getCommandsMinGroup("owner");
			
			//Organizes the commands into alphabetical order
			Collections.sort(commandList);
			Collections.sort(modCommandList);
			Collections.sort(adminCommandList);
			Collections.sort(ownerCommandList);
			
			event.respond(Colors.setBold("Commands: ") + StringUtils.join(commandList, ", "));
			if (user.getGroup().getName().equals("mod") || user.getGroup().getName().equals("admin") || user.getGroup().getName().equals("owner"))
				event.respondToUser(Colors.setBold("Moderator only Commands: ") + StringUtils.join(modCommandList, ", "));
			
			if (user.getGroup().getName().equals("admin") || user.getGroup().getName().equals("owner"))
				event.respondToUser(Colors.setBold("Admin only Commands: ") + StringUtils.join(adminCommandList, ", "));
			
			if (user.getGroup().getName().equals("owner"))
				event.respondToUser(Colors.setBold("Owner only Commands: ") + StringUtils.join(ownerCommandList, ", "));
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
				event.respondToUser("Sorry, I don't have a command called" + cmd + ".");
		}
	}
}