package com.chcmatt.katelyn.commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="time", alias="date", desc="Sends the current bot time.", syntax="time [timezone]")
public class Time extends GenericCommand
{

	public Time(CommandEvent<PircBotX> event)
	{
		super(event);
	}

	public void execute()
	{
		if (event.hasNoArgs())
		{
			String botTime = "The current date is: " + Colors.setBold(new SimpleDateFormat("MMMM d, yyyy").format(new Date())) + " and the time is: " + Colors.setBold(new SimpleDateFormat("h:mm:ss a").format(new Date()));
			event.respond(Colors.setBold(user.getNick()) + ": " + botTime);
		}

		else if (event.getArguments().toLowerCase().equals("est") || event.getArguments().toLowerCase().equals("edt")) {
			TimeZone zone = TimeZone.getTimeZone("America/New_York");

			SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
			SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a");
			SimpleDateFormat zoneFormat = new SimpleDateFormat("z");

			dateFormat.setTimeZone(zone);
			timeFormat.setTimeZone(zone);
			zoneFormat.setTimeZone(zone);

			String date = dateFormat.format(new Date());
			String time = timeFormat.format(new Date());
			String timezone = zoneFormat.format(new Date());

			event.respond("The current "+timezone+" date is: "+Colors.setBold(date)+" and the time is: "+Colors.setBold(time));
		}
		else {
			TimeZone zone = TimeZone.getTimeZone(event.getArguments());

			SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
			SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a");
			SimpleDateFormat zoneFormat = new SimpleDateFormat("z");

			dateFormat.setTimeZone(zone);
			timeFormat.setTimeZone(zone);
			zoneFormat.setTimeZone(zone);

			String date = dateFormat.format(new Date());
			String time = timeFormat.format(new Date());
			String timezone = zoneFormat.format(new Date());

			event.respond("The current "+timezone+" date is: "+Colors.setBold(date)+" and the time is: "+Colors.setBold(time));
		}


	}
}