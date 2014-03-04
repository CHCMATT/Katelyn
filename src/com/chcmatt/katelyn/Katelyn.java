package com.chcmatt.katelyn;

import org.pircbotx.PircBotX;
import org.pircbotx.Configuration;
import org.pircbotx.cap.EnableCapHandler;
import org.pircbotx.hooks.Listener;
import org.reflections.Reflections;

import com.chcmatt.katelyn.listeners.AddListener;
import com.chcmatt.katelyn.utils.Config;

public class Katelyn
{

	public static void main(String[] args) throws Exception
	{
		Config c = new Config("config.json");
		String nsPass = c.getPassword("nickserv");
		String bncPass = c.getPassword("bouncer");
		
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
		.addAdminAccounts("ChasedSpade", "CHCMATT", "R2D2Warrior", "happyslayer")

		.addPrefix("-", "MESSAGE")
		.addPrefix("|", "NOTICE")
		
		.addAutoJoinChannels("#botspam", "#ChasedSpade",
							"#CHCMATT", "#Katelyn", "#SC-Staff",
							"Survival-Craft");
		
		// All this just adds any class with @AddListener to the listeners
		try
 		{
			Reflections reflections = new Reflections("com.r2d2warrior.c3p0j.listeners");
			for (Class<?> cls : reflections.getTypesAnnotatedWith(AddListener.class))
			{
				@SuppressWarnings("unchecked")
				Listener<PircBotX> listener = (Listener<PircBotX>) cls.newInstance();
				builder.addListener(listener);
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