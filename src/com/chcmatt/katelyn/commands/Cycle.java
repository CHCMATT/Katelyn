package com.chcmatt.katelyn.commands;

import org.pircbotx.PircBotX;

import com.chcmatt.katelyn.handling.CommandEvent;

@Command(name="cycle", desc="Parts and rejoins current or specified channel", adminOnly=true)
public class Cycle extends GenericCommand
{
	private CommandEvent<PircBotX> event;
	
	public Cycle(CommandEvent<PircBotX> event)
	{
		super(event);
		this.event = event;
	}
	
	public void execute()
	{
		if (event.hasNoArgs())
		{
			String chan = event.getChannel().getName();
			event.respond("Cycling channel: " + chan);
			event.getChannel().send().part("Cycling...");
			event.getBot().sendIRC().joinChannel(chan);
		}
		else
		{
			String chan = event.getCommandArgs().get(0);
			if (event.getBot().getUserChannelDao().channelExists(chan))
			{
				event.respond("Cycling channel: " + chan);
				event.getBot().getUserChannelDao().getChannel(chan).send().part("Cycling...");
				event.getBot().sendIRC().joinChannel(chan);
			}
			else
				event.respondToUser("Channel not found: " + chan);
		}
	}
}
