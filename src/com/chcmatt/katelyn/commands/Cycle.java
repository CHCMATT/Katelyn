package com.chcmatt.katelyn.commands;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="cycle", desc="Parts and rejoins current or specified channel", syntax="cycle [#channel]", minGroup="admin")
public class Cycle extends GenericCommand
{
	
	public Cycle(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	public void execute()
	{
		String chan = (event.hasChannelArg()) ? event.getArgumentList().get(0) : channel.getName();
		
		if (event.getBot().getUserChannelDao().channelExists(chan))
		{
			userChannelDao.getChannel(chan).send().cycle();
		}
		else
			event.respondToUser("Not in channel " + Colors.setBold(chan) + ".");
	}
}
