package com.chcmatt.katelyn.listeners;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;

@AddListener
public class ConnectMessage extends ListenerAdapter<PircBotX>
{
	public void onConnect(ConnectEvent<PircBotX> event)
	{
			//Waits 30 seconds before sending the message. (Enough time to /WHO all/most channels.)
			wait(30000);
			event.getBot().sendIRC().action("#Katelyn", "is now connected to the server.");
	}
	
	//Wait method (Input in milliseconds).
	public void wait(int waitTime)
	{
		try
		{
			Thread.sleep(waitTime);
		}
		catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}
	}
}