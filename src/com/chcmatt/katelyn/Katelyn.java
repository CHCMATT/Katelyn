package com.chcmatt.katelyn;

import java.io.File;
import java.nio.charset.Charset;

import org.pircbotx.PircBotX;
import org.pircbotx.Configuration;

import com.chcmatt.katelyn.handling.CommandListener;
import com.google.common.io.Files;

public class Katelyn
{

	public static void main(String[] args) throws Exception
	{
		String nsPass = Files.toString(new File("ns-pass.txt"), Charset.defaultCharset());
		String bncPass = Files.toString(new File("bnc-pass.txt"), Charset.defaultCharset());
		
		Configuration<PircBotX> config = new Configuration.Builder<PircBotX>()

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
		
		//Listeners
		.addListener(new CommandListener())
		
		//Command management
		.addAdminAccount("ChasedSpade")
		.addAdminAccount("CHCMATT")
		.addAdminAccount("happyslayer")
		.addAdminAccount("R2D2Warrior")

		.addPrefix("-", "MESSAGE")
		.addPrefix("|", "NOTICE")
		
		.addAutoJoinChannel("#botspam")
		.addAutoJoinChannel("#ChasedSpade")
		.addAutoJoinChannel("#CHCMATT")
		.addAutoJoinChannel("#Katelyn")
		.addAutoJoinChannel("#SC-Staff")
		.addAutoJoinChannel("#Survival-Craft")
		
		.buildConfiguration();

		PircBotX bot = new PircBotX(config);

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