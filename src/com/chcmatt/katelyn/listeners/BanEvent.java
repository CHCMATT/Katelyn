package com.chcmatt.katelyn.listeners;
 
import java.util.List;
import java.util.regex.Pattern;
 
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.SetChannelBanEvent;
 
import com.chcmatt.katelyn.utils.Config;
 
@AddListener
public class BanEvent extends ListenerAdapter<PircBotX>
{
	public void onSetChannelBan(SetChannelBanEvent<PircBotX> event)
	{
		// Send the debug(?) message
		//event.getBot().sendIRC().action("#Katelyn","Channel ban was set on: "+Colors.setBold(event.getHostmask())+" at "+new SimpleDateFormat(Colors.setBold("h:mm:ss a")).format(new Date())+" in channel "+event.getChannel().getName());
		
		// Get the list of ban exempts from the config (hacky way)
		@SuppressWarnings("unchecked")
		List<String> banExempts = (List<String>) new Config("config.json").getMap().get("no-ban");
		
		// Set up regex to check if the banned hostmask (including wildcards) matches any of the exempts
		String banMask = event.getHostmask();
		String regexMask = banMask.replace("*", ".+");
		Pattern pattern = Pattern.compile(regexMask, Pattern.CASE_INSENSITIVE);
		
		for (String banExempt : banExempts)
		{
			if (pattern.matcher(banExempt).matches())
			{
				bannedExempt(event);
				break;
			}
		}
	}
	
	// Custom method to handle when an exempt is banned
	private void bannedExempt(SetChannelBanEvent<PircBotX> event)
	{
		// Remove the ban
		event.getChannel().send().unBan(event.getHostmask());
		
		// Kick and Ban the banner (*!*@banner-host)
		//event.getChannel().send().ban(event.getUser().getHostmask());
		//event.getChannel().send().kick(event.getUser(), "Please don\'t try to ban "+Colors.setBold(event.getHostmask())+"!");
	}
}