package com.chcmatt.katelyn.commands;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="time", desc="Sends the current bot time.", adminOnly=true)
public class Time extends GenericCommand
{
	
	public Time(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	@Override
	public void execute()
	{
		//TODO: Add ability to have have different timezones.
		String botTime = Colors.setBold("The current date is: ") + new SimpleDateFormat("MMMM d, yyyy").format(new Date()) + Colors.setBold(" and the time is: ") + new SimpleDateFormat("h:mm:ss").format(new Date());
		event.respond(user.getNick() + ": " + botTime);
	}
}