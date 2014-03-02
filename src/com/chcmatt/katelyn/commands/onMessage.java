package com.chcmatt.katelyn.commands;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class onMessage extends ListenerAdapter<PircBotX> {
	public onMessage(MessageEvent<PircBotX> event)
	{
		event.respond("Hi, " + event.getUser().getNick());
	}
}