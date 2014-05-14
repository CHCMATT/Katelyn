package com.chcmatt.katelyn;

import org.pircbotx.PircBotX;
import org.pircbotx.Configuration;
import org.pircbotx.cap.EnableCapHandler;
import com.chcmatt.katelyn.utils.Config;

public class Katelyn
{

	public static void main(String[] args) throws Exception
	{
		// You can remove all of this commented code once you get your information from it into the config file
		
		
		
/*		String nsPass  = c.getMap().get("passwords").get("nickserv");
		String bncPass = c.getMap().get("passwords").get("bouncer");
		
		Configuration.Builder<PircBotX> builder = new Configuration.Builder<PircBotX>()

		//Login info
		.setName("Katelyn")
		.setLogin("Katelyn")
		.setRealName("Katelyn - CHCMATT's Personal Java Bot (PircBotX)")
		.setNickservPassword(nsPass)

		//Server info
		//.setServerHostname("irc.esper.net")
		.setServerHostname("irc.bouncer.ml")
		.setServerPassword(bncPass)
		.setChannelPrefixes("#")
		
		//Booleans
		.setAutoNickChange(true)
		.setCapEnabled(true)
		
		.addCapHandler(new EnableCapHandler("extended-join", true))
		.addCapHandler(new EnableCapHandler("account-notify", true))
		
		//Command management
		.addAdminAccounts("ChasedSpade", "CHCMATT", "happyslayer", "R2D2Warrior")
		.setOwnerAccount("CHCMATT")

		.addPrefix(".", "MESSAGE")
		.addPrefix("|", "NOTICE")
		
		.addAutoJoinChannels("#botspam", "#ChasedSpade",
							"#CHCMATT", "#Katelyn", "#SC-Staff",
							"#Survival-Craft");
		
		//.setAdminChannels("#Katelyn");*/
		
		Config c = new Config("config.json");
		
 		Configuration.Builder<PircBotX> builder =
 				new Configuration.Builder<PircBotX>(c.buildBotConfiguration())
 				
 				.addCapHandler(new EnableCapHandler("extended-join", true))
 				.addCapHandler(new EnableCapHandler("account-notify", true));

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