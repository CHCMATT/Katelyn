package com.chcmatt.katelyn.handling;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.commons.lang3.ArrayUtils;

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
	private String minGroup;
	private boolean isOpOnly;
	private boolean isVoiceOnly;
	@Getter(AccessLevel.NONE)
	private boolean requiresArgs;
	private HashMap<String, Method> methods;
	private Class<T> commandClass;
	
	@SuppressWarnings("unchecked")
	public CommandInfo(String name, String[] aliases, String desc, String syntax,
			String minGroup, boolean opOnly, boolean voiceOnly, boolean requiresArgs,
			HashMap<String, Method> methods, Class<? extends GenericCommand> commandClass)
	{
		this.name = name;
		this.aliases = aliases;
		this.desc = desc;
		this.syntax = syntax;
		this.minGroup = minGroup;
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
	
	public boolean hasSub(String name)
	{
		return getSub(name) != null;
	}
	
	protected HashMap<String, Sub> getSubs()
	{
		HashMap<String, Sub> subs = new HashMap<>();
		for (String key : methods.keySet())
		{
			if (!key.equals("DEFAULT"))
			{
				Command.Sub sub = methods.get(key).getAnnotation(Command.Sub.class);
				subs.put(key, new Sub(sub.name(), sub.alias(), sub.minGroup(), sub.requiresArgs()));
			}
		}
		return subs;
	}
	
	public Sub getSub(String name)
	{
		if (name.equals("DEFAULT"))
			return null;
		else if (methods.containsKey(name))
			return getSubs().get(name);
		else
		{
			for (Sub sub : getSubs().values())
			{
				if (ArrayUtils.contains(sub.getAliases(), name))
					return sub;
			}
			return null;
		}
	}
	
	@AllArgsConstructor
	protected class Sub
	{
		@Getter
		private String name;
		@Getter
		private String[] aliases;
		@Getter
		private String minGroup;
		private boolean requiresArgs;
		
		public boolean requiresArgs()
		{
			return requiresArgs;
		}
	}
}
