package com.chcmatt.katelyn.commands;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;
					//Added as example
@Command(name="ping", alias="p", desc="Sends a ping reply message back to you.")
public class Ping extends GenericCommand
{
	
	public Ping(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	@Command.Default
	public void ping()
	{
		event.respond(user.getNick() + ": " + Colors.setBold("PONG!"));
	}
	
	@Command.Sub(name="sub")
	public void subPing()
	{
		event.respond(user.getNick() + ": " + Colors.setColor("Sub-PONG!", Colors.RED + Colors.BOLD));
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