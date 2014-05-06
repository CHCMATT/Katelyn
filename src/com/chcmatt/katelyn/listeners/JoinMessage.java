package com.chcmatt.katelyn.listeners;

import java.io.IOException;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.pircbotx.Colors;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;

import com.chcmatt.katelyn.utils.WebUtils;

@AddListener
public class JoinMessage extends ListenerAdapter<PircBotX>
{
	public void onJoin(JoinEvent<PircBotX> event) throws ParseException
	{
		if (event.getUser().getNick() != event.getBot().getNick())
		{
			try
			{
				String address = event.getUser().getHostmask();
				Map<String, String> results = WebUtils.getLocationData(address);
				String region =		results.get("region_name");
				String country =	results.get("country_name");

				if (results.get("country_name").equals("N/A"))
					event.getChannel().send().message(Colors.setColor("User is connecting from "+Colors.setBold(country)+".",Colors.DARK_GREEN));
				else
					event.getChannel().send().message(Colors.setColor("User is connecting from "+Colors.setBold(region+", "+country)+".",Colors.DARK_GREEN));
			}

			catch (IOException e)
			{
				//e.printStackTrace();
			}
		}
		else
			event.getBot().sendIRC().notice(event.getBot().getNick(), "");

	}
}