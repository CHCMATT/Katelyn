package com.chcmatt.katelyn.handling;

import java.lang.reflect.Method;
import java.util.HashMap;

import com.chcmatt.katelyn.commands.Command;
import com.chcmatt.katelyn.commands.GenericCommand;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
	private HashMap<String, Method> methods;
	private Class<T> commandClass;
	
	@SuppressWarnings("unchecked")
	public CommandInfo(String name, String[] aliases, String desc, String syntax,
			boolean adminOnly, boolean opOnly, boolean voiceOnly, boolean requiresArgs,
			HashMap<String, Method> methods, Class<? extends GenericCommand> commandClass)
	{
		this.name = name;
		this.aliases = aliases;
		this.desc = desc;
		this.syntax = syntax;
		this.isAdminOnly = adminOnly;
		this.isOpOnly = opOnly;
		this.isVoiceOnly = voiceOnly;
		this.requiresArgs = requiresArgs;
		this.methods = methods;
		this.commandClass = (Class<T>)commandClass;
	}
	
	public boolean requiresArgs()
	{
		return requiresArgs;
	}
	
	public boolean hasSubCommands()
	{
		return methods.size() > 1;
	}
	
	protected Sub getSub(String name)
	{
		Command.Sub sub = methods.get(name).getAnnotation(Command.Sub.class);
		return new Sub(sub.adminOnly(), sub.requiresArgs());
	}
	
	@AllArgsConstructor
	protected class Sub
	{
		@Getter
		private boolean isAdminOnly;
		@Getter(AccessLevel.NONE)
		private boolean requiresArgs;
		
		public boolean requiresArgs()
		{
			return requiresArgs;
		}
	}
}
