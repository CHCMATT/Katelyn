package com.chcmatt.katelyn.commands;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;
@Command(name="global", alias={"g","gl"}, desc="Sends a global command.", syntax="global <subCommand> [sumCommandParameters]", requiresArgs=true, minGroup="admin")
public class Global extends GenericCommand
{ 
	
	public Global(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	@Command.Default
	public void missingMessage()
	{
		event.respondToUser(Colors.setColor(Colors.setBold("Error:")+" This command requires arguments. "+Colors.setBold("SYNTAX:")+" global <subCommand> [sumCommandParameters]",Colors.RED));
	}
	@Command.Sub(name="channels", alias={"c","ch","chan","chans"})
	public void globalChannels()
	{
		ArrayList<String> chanNames = new ArrayList<>();
		 
		for (Channel chan : bot.getUserBot().getChannels())
		    chanNames.add(chan.getName());
		 
		String chans = StringUtils.join(chanNames, ", ");
		event.respond(user.getNick()+": "+chans);
	}
	
	@Command.Sub(name="sub2", adminOnly=true)
	public void subPing2() 
	{
		event.respond(user.getNick() + ": " + Colors.setColor("Sub2-PONG!", Colors.RED + Colors.BOLD));
	}
	
	@Command.Sub(name="sub3", requiresArgs=true)
	public void subPing3()
	{
		event.respond(event.getArguments() + ": " + Colors.setColor("Sub-PONG!", Colors.RED + Colors.BOLD));
	}
}