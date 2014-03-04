package com.chcmatt.katelyn.commands;

import java.util.Map;

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

		String address = event.getArgumentsList().get(0);
		Map<String, ?> results = WebUtils.getLocationData(address);
		String ip = (String) results.get("ip");
		String city = (String) results.get("city");
		String region = (String) results.get("region_name");
		String country = (String) results.get("country_name");
		String zipcode = (String) results.get("zipcode");
		double latitude = (Double) results.get("latitude");
		double longitude = (Double) results.get("longitude");
		
		//Checks if IP is Reserved, 198.0.0.1, etc...
		if (results.get("country_name").equals("Reserved"))
		{
			event.respond(Colors.setColor("That IP is blacklisted.", Colors.RED));
		}
		//Else, build and send message.
		else
		{
			event.respond(user.getNick() + ": " +
						Colors.setColor("IP: ", Colors.DARK_BLUE + Colors.BOLD) + ip + " " +
						Colors.setColor("City: ", Colors.DARK_BLUE + Colors.BOLD) + city + " " +
						Colors.setColor("Region: ", Colors.DARK_BLUE + Colors.BOLD) + region + " " +
						Colors.setColor("Country: ", Colors.DARK_BLUE + Colors.BOLD) + country + " " +
						Colors.setColor("Zip Code: ", Colors.DARK_BLUE + Colors.BOLD) + zipcode + " " +
						Colors.setColor("Latitude: ", Colors.DARK_BLUE + Colors.BOLD) + latitude + " " +
						Colors.setColor("Longitude: ", Colors.DARK_BLUE + Colors.BOLD) + longitude);
		}
	}
}
