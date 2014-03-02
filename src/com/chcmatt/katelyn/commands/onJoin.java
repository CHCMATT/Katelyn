package com.chcmatt.katelyn.commands;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;

public class onJoin extends ListenerAdapter<PircBotX> {
	public onJoin(final JoinEvent<PircBotX> event)
	{
		event.respond("Hi, " + event.getUser().getNick());
	}
}