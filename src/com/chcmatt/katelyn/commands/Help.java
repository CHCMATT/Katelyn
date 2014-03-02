package com.chcmatt.katelyn.commands;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.pircbotx.PircBotX;
import org.pircbotx.Colors;

import com.chcmatt.katelyn.handling.CommandEvent;
import com.chcmatt.katelyn.handling.CommandInfo;

@Command(name="help", desc="Displays command list or command information")
public class Help extends GenericCommand
{
	private CommandEvent<PircBotX> event;
	
	public Help(CommandEvent<PircBotX> event)
	{
		super(event);
		this.event = event;
	}
	
	public void execute()
	{
		String prePublic = event.getBot().getConfiguration().getPrefixes().keySet().asList().get(1);
		String prePrivate = event.getBot().getConfiguration().getPrefixes().keySet().asList().get(0);
		if (event.hasNoArgs())
		{
			event.respondToUser(Colors.BOLD + "Valid prefixes:" + Colors.BOLD + " Public message: \"" + prePublic + "\" Private notice: \"" + prePrivate +"\"");
			List<String> commandList = new ArrayList<String>();
			List<String> adminCommands = new ArrayList<String>();
			for (CommandInfo<GenericCommand> info : event.getBot().getCommandRegistry().getCommands())
			{
				if (!info.isAdminOnly())
					commandList.add(info.getName());
				else if (event.getUser().isAdmin())
					adminCommands.add(info.getName());
			}
			event.respondToUser("Commands: " + StringUtils.join(commandList, ", "));
			if (event.getUser().isAdmin())
			event.respondToUser("Admin Commands: " + StringUtils.join(adminCommands, ", "));
		}
		else
		{
			String cmd = event.getCommandArgs().get(0);
			if (event.getBot().getCommandRegistry().isCommand(cmd))
			{
				String desc = event.getBot().getCommandRegistry().getCommandInfo(cmd).getDesc();
				event.respond(cmd.toLowerCase() + " - " + desc);
			}
			else
				event.respondToUser("Sorry, I don't have a command \"" + cmd + "\".");
		}
	}
}
