package com.chcmatt.katelyn.listeners;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.UnknownEvent;

@AddListener
public class CAPListener extends ListenerAdapter<PircBotX>
{
	public void onUnknown(UnknownEvent<PircBotX> event)
	{
		if(event.getLine().startsWith("CAP unknown-nick LS"))
			event.getBot().sendCAP().request("account-notify", "extended-join");
	}
}
