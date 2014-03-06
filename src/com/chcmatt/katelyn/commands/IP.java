package com.chcmatt.katelyn.commands;

import java.io.IOException;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.pircbotx.PircBotX;
import org.pircbotx.Colors;

import com.chcmatt.katelyn.handling.CommandEvent;
import com.chcmatt.katelyn.utils.WebUtils;

@Command(name="ip", desc="Gets information about the specified IP address.", requiresArgs = true, adminOnly=false)
public class IP extends GenericCommand
{
	
	public IP(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	public void execute()
	{
		try
		{
			String address = event.getArgumentList().get(0);
			Map<String, String> results = WebUtils.getLocationData(address);
			String ip =			results.get("ip");
			String city = 		results.get("city");
			String region =		results.get("region_name");
			String country =	results.get("country_name");
			String zipcode =  	results.get("zipcode");
			String latitude = 	results.get("latitude");
			String longitude = 	results.get("longitude");
			
			//Checks if IP is Reserved, 198.0.0.1, etc...
			if (results.get("country_name").equals("Reserved"))
			{
				event.respond(Colors.setColor("That IP is blacklisted.", Colors.RED));
			}
			//Else, build and send message.
			else
			{
				event.respond(user.getNick() + ": " +
							Colors.setColor("IP: ", Colors.DARK_BLUE + Colors.BOLD) + ip + " | " +
							Colors.setColor("City: ", Colors.DARK_BLUE + Colors.BOLD) + city + " | " +
							Colors.setColor("Region: ", Colors.DARK_BLUE + Colors.BOLD) + region + " | " +
							Colors.setColor("Country: ", Colors.DARK_BLUE + Colors.BOLD) + country + " | " +
							Colors.setColor("Zip Code: ", Colors.DARK_BLUE + Colors.BOLD) + zipcode + " | " +
							Colors.setColor("Latitude: ", Colors.DARK_BLUE + Colors.BOLD) + latitude + " | " +
							Colors.setColor("Longitude: ", Colors.DARK_BLUE + Colors.BOLD) + longitude);
			}
		}
		catch (IOException e)
		{
			event.respondToUser("Error while connecting to URL.");
			e.printStackTrace();
		}
		catch (ParseException e)
		{
			event.respondToUser("Error while parsing results.");
			e.printStackTrace();
		}
	}
}
