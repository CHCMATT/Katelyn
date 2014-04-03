package com.chcmatt.katelyn.listeners;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.InviteEvent;

@AddListener
public class InviteJoin extends ListenerAdapter<PircBotX>
{
	public void onInvite(InviteEvent<PircBotX> event)
	{
		User invitedFrom = event.getBot().getUserChannelDao().getUser(event.getUser());
		if (invitedFrom.isAdmin())
		{
			event.getBot().sendIRC().joinChannel(event.getChannel());
			event.getBot().getUserChannelDao().getChannel("#Katelyn").send().message(
					"Joined " + Colors.setBold(event.getChannel()) + " from " + Colors.setBold(invitedFrom.getNick()) + "\'s invite.");
		}
	}
}
