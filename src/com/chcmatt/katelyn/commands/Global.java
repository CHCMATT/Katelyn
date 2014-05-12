package com.chcmatt.katelyn.commands;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;
@Command(name="global", alias={"g","gl"}, desc="Sends a global command.", syntax="global <subCommand> [sumcommandParameters]", requiresArgs=true, adminOnly=true)
public class Global extends GenericCommand
{
	
	public Global(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	@Command.Sub(name="channels", alias={"c","ch","chan","chans"})
	public void globalChannels()
	{
		event.respond(user.getNick() + ": " + bot.getUserBot().getChannels().toString());
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