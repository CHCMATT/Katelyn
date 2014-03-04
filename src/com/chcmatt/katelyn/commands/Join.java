package com.chcmatt.katelyn.commands;

import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="join", desc="Joins specified channel", requiresArgs = true, adminOnly=true)
public class Join extends GenericCommand
{
	
	public Join(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	public void execute()
	{
		String chan = event.getArgumentsList().get(0);
		
		if (!userChannelDao.channelExists(chan) && event.hasChannelArg())
		{
			event.respondToUser("Trying to join channel: " + chan + " ...");
			bot.sendIRC().joinChannel(chan);
		}
		else if (userChannelDao.channelExists(chan))
			event.respondToUser("Already in channel: " + chan);
		else
			event.respondToUser("Invalid channel name: " + chan);
	}
}
