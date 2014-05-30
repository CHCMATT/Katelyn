package com.chcmatt.katelyn.commands;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.pircbotx.Colors;
import org.pircbotx.PircBotX;

import bsh.EvalError;
import bsh.Interpreter;

import com.chcmatt.katelyn.handling.CommandEvent;
import com.chcmatt.katelyn.utils.Utils;
import com.chcmatt.katelyn.utils.WebUtils;

@Command(name="info", alias="information", desc="Lists current information about the bot.")
public class Info extends GenericCommand
{

	public Info(CommandEvent<PircBotX> event)
	{
		super(event);
	}

	public void execute()
	{
		try
		{
			String noticePrefix = config.getPrefixes().inverse().get("NOTICE");
			String helpCommand 	= new com.chcmatt.katelyn.utils.Config("config.json").getString("general-info", "help-command");
			String latestTag 	= WebUtils.getGithubTags().get(0).get("name").toString();
			String botVersion 	= PircBotX.VERSION;
			String response = Colors.setBold(event.getUser().getNick()) + ": Hi, I'm " + bot.getNick() + " and I'm a bot! I'm running " + Colors.setBold("PircBotX "+botVersion) + " on GitHub Release " + Colors.setBold(latestTag) + ". " + Colors.setBold(config .getOwnerAccount()) + " is my owner! Use "+Colors.setBold(noticePrefix+helpCommand)+" to get a notice of a list of all of my commands that are available to you.";
			event.respond(response);
		}
		catch (IOException | ParseException | IllegalArgumentException e)
		{
			//Let the user know that an error occurred (for debugging purposes)
			event.respondToUser(Colors.setColor("An error occurred while getting general info.", Colors.RED));
			e.printStackTrace();
		}
	}
}

@Command(name="tag", alias="tags", desc="Lists current release tag from GitHub.")
class Tag extends GenericCommand
{

	public Tag(CommandEvent<PircBotX> event)
	{
		super(event);
	}

	public void execute()
	{
		try
		{
			String latestTag = WebUtils.getGithubTags().get(0).get("name").toString();

			String eval = "WebUtils.getGithubTags().get(0).get(\"commit\").get(\"sha\")";
			Interpreter i = Utils.createDefaultInterpreter(event);
			i.eval("item = " + eval);
			String sha = i.get("item").toString();

			String latestTagLink = "https://github.com/CHCMATT/Katelyn/commit/" + sha;
			String response = Colors.setBold(event.getUser().getNick()) + ": I'm running on GitHub Release " + Colors.setBold(latestTag) + ". More info about this Release Tag: " + Colors.setBold(latestTagLink);
			event.respond(response);
		}
		catch (IOException | ParseException | IllegalArgumentException | EvalError e)
		{
			event.respondToUser(Colors.setColor("An error occurred while getting release tags.", Colors.RED));
			e.printStackTrace();
		}
	}
}

@Command(name="github", alias={"git","source","src","code"}, desc="Lists current release link and GitHub link.")
class Github extends GenericCommand
{

	public Github(CommandEvent<PircBotX> event)
	{
		super(event);
	}

	public void execute()
	{
		event.respond(Colors.setBold(event.getUser().getNick()) + ": " + bot.getNick() + "\'s GitHub source code: " + Colors.setBold("https://github.com/CHCMATT/Katelyn/") + ". The latest commit can be found here: " + Colors.setBold("https://github.com/CHCMATT/Katelyn/commit/"));
	}
}

@Command(name="admins", desc="Lists the current bot admins.")
 class Admins extends GenericCommand
{
	
	public Admins(CommandEvent<PircBotX> event)
	{
		super(event);
	}
	
	public void execute()
	{
		event.respond(Colors.BOLD + StringUtils.join(config.getAdminAccounts(), Colors.NORMAL + ", " + Colors.BOLD));
	}
}

