package com.chcmatt.katelyn.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.SetChannelBanEvent;

@AddListener
public class BanEvent extends ListenerAdapter<PircBotX>
{
	@Override
	public void onSetChannelBan(SetChannelBanEvent<PircBotX> event)
	{
		event.getBot().sendIRC().action("#Katelyn","Channel ban was set on: "+Colors.setBold(event.getHostmask())+" at "+new SimpleDateFormat(Colors.setBold("h:mm:ss a")).format(new Date())+" in channel "+Colors.setBold(event.getChannel().getName()));
	}
}
