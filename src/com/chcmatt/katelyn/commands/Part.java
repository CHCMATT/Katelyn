package com.chcmatt.katelyn.commands;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="part", desc="Parts current or specified channel", syntax="part [#channel] [reason]", adminOnly=true)
public class Part extends GenericCommand
{
	
	public Part(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	public void execute()
	{
		String chan = (event.hasChannelArg()) ? event.getArgumentList().get(0) : channel.getName();
		String defaultMsg = "Parted by " + user.getNick();
		String msg = "";
		
		if (event.hasNoArgs())
		{
			msg = defaultMsg;
		}
		else
		{
			if (event.hasChannelArg())
			{
				msg = (event.getArgumentList().size() > 1) ? event.getArgRange(1) : defaultMsg;
			}
			else
				msg = event.getArguments();
		}
		
		if (userChannelDao.channelExists(chan))
		{
			event.respondToUser("Trying to part channel " + Colors.setBold(chan) + ".");
			userChannelDao.getChannel(chan).send().part(msg);
		}
		else
			event.respondToUser("Not in channel " + Colors.setBold(chan) + ".");
	}
}
