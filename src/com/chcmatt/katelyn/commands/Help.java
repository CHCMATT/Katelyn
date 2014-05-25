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
			List<String> commandList = new ArrayList<String>();
			List<String> modCommandList = new ArrayList<String>();
			List<String> adminCommandList = new ArrayList<String>();
			List<String> ownerCommandList = new ArrayList<String>();
			
			for (CommandInfo<GenericCommand> info : bot.getCommandRegistry().getCommands())
			{
				//check if user is mod
				if (event.getUser().getGroup().getName().equals("mod"))
					modCommandList.add(info.getName());
				//checks if user is admin
				else if (event.getUser().getGroup().getName().equals("admin"))
					adminCommandList.add(info.getName());
				//checks if user is owner
				else if (event.getUser().getGroup().getName().equals("owner"))
					ownerCommandList.add(info.getName());
				else if (event.getUser().getGroup().getName().equals("DEFAULT"))
					commandList.add(info.getName());
			}
			//Organizes the commands into alphabetical order
			Collections.sort(commandList);
			Collections.sort(modCommandList);
			Collections.sort(adminCommandList);
			Collections.sort(ownerCommandList);
			
			event.respond(Colors.setBold("Commands: ") + StringUtils.join(commandList, ", "));
			if (event.getUser().getGroup().getName().equals("mod") || bot.getPermissions().getGroupNames().contains("admin") || bot.getPermissions().getGroupNames().contains("owner"))
				event.respondToUser(Colors.setBold("Moderator only Commands: ") + StringUtils.join(modCommandList, ", "));
			
			if (bot.getPermissions().getGroupNames().contains("admin") || bot.getPermissions().getGroupNames().contains("owner"))
				event.respondToUser(Colors.setBold("Admin only Commands: ") + StringUtils.join(adminCommandList, ", "));
			
			if (bot.getPermissions().getGroupNames().contains("owner"))
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