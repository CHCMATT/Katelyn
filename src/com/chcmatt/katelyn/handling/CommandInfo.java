package com.chcmatt.katelyn.handling;

import com.chcmatt.katelyn.commands.GenericCommand;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * Class to hold all information from a single <code>@Command</code> annotation
 */
@Getter
public class CommandInfo<T extends GenericCommand>
{
	private String name;
	private String[] aliases;
	private String desc;
	private String syntax;
	private boolean isAdminOnly;
	private boolean isOpOnly;
	private boolean isVoiceOnly;
	@Getter(AccessLevel.NONE)
	private boolean requiresArgs;
	private Class<T> commandClass;
	
	@SuppressWarnings("unchecked")
	public CommandInfo(String name, String[] aliases, String desc, String syntax,
			boolean adminOnly, boolean opOnly, boolean voiceOnly, boolean requiresArgs, Class<?> commandClass)
	{
		this.name = name;
		this.aliases = aliases;
		this.desc = desc;
		this.syntax = syntax;
		this.isAdminOnly = adminOnly;
		this.isOpOnly = opOnly;
		this.isVoiceOnly = voiceOnly;
		this.requiresArgs = requiresArgs;
		this.commandClass = (Class<T>)commandClass;
	}
	
	public boolean requiresArgs()
	{
		return requiresArgs;
	}
}
