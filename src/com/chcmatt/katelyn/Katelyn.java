package com.chcmatt.katelyn;

import org.pircbotx.PircBotX;
import org.pircbotx.Configuration;
import org.pircbotx.cap.EnableCapHandler;
import com.chcmatt.katelyn.utils.Config;

public class Katelyn
{

	public static void main(String[] args) throws Exception
	{
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