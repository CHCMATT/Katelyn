package com.chcmatt.katelyn;

import org.pircbotx.PircBotX;
import org.pircbotx.Configuration;
import org.pircbotx.cap.EnableCapHandler;
import org.pircbotx.hooks.Listener;
import org.reflections.Reflections;

import com.chcmatt.katelyn.listeners.AddListener;
import com.chcmatt.katelyn.utils.Config;
import com.chcmatt.katelyn.utils.Utils;

public class Katelyn
{

	public static void main(String[] args) throws Exception
	{
		Config c = new Config("config.json");
		String nsPass = c.getMap().get("passwords").get("nickserv");
		String bncPass = c.getMap().get("passwords").get("bouncer");
		
		Configuration.Builder<PircBotX> builder = new Configuration.Builder<PircBotX>()

		//Login info
		.setName("Katelyn")
		.setLogin("Katelyn")
		.setRealName("Katelyn - CHCMATT's Personal Bot (PircBotX 2.0)")
		.setNickservPassword(nsPass)

		//Server info
		//.setServerHostname("irc.esper.net")
		.setServerHostname("bnc.liamstanley.io")
		.setServerPassword(bncPass)
		.setChannelPrefixes("#")
		
		//Booleans
		.setAutoNickChange(true)
		.setCapEnabled(true)
		
		.addCapHandler(new EnableCapHandler("extended-join", true))
		.addCapHandler(new EnableCapHandler("account-notify", true))
		
		//Command management
		.addAdminAccounts("ChasedSpade", "CHCMATT", "happyslayer", "R2D2Warrior")

		.addPrefix("-", "MESSAGE")
		.addPrefix("|", "NOTICE")
		.setFactoidPrefix("?")
		
		.addAutoJoinChannels("#botspam", "#ChasedSpade",
							"#CHCMATT", "#Katelyn", "#SC-Staff",
							"Survival-Craft");
		
		// All this just adds any class with @AddListener to the listeners
		try
 		{
			Reflections reflections = new Reflections(Utils.getPackageName(AddListener.class));
			for (Class<?> cls : reflections.getTypesAnnotatedWith(AddListener.class))
			{
				if (cls.getAnnotation(AddListener.class).value())
				{
					@SuppressWarnings("unchecked")
					Listener<PircBotX> listener = (Listener<PircBotX>) cls.newInstance();
					builder.addListener(listener);
				}
			}
 		}
 		catch (IllegalAccessException | InstantiationException e)
 		{
 			e.printStackTrace();
 		}
		
		// Need to call buildConfiguration() down here since adding listeners was part of the Builder
		PircBotX bot = new PircBotX(builder.buildConfiguration());

		try
		{
			bot.startBot();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

}