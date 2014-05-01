package com.chcmatt.katelyn.listeners;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.SetChannelBanEvent;

@AddListener
public class BanEvent extends ListenerAdapter<PircBotX>
{
	public void onSetChannelBan(SetChannelBanEvent<PircBotX> event)
	{
		event.getBot().sendIRC().action("#Katelyn","Channel ban was set on: "+Colors.setBold(event.getHostmask())+" at "+event.getTimestamp()+" in channel "+event.getChannel());
	}
}
