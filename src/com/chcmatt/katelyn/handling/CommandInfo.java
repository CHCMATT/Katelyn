package com.chcmatt.katelyn.handling;

import com.chcmatt.katelyn.commands.GenericCommand;

import lombok.Getter;

@Getter
public class CommandInfo<T extends GenericCommand>
{
	private String name;
	private String desc;
	private boolean isAdminOnly;
	private Class<T> commandClass;
	
	@SuppressWarnings("unchecked")
	public CommandInfo(String name, String desc, boolean adminOnly, Class<?> commandClass)
	{
		this.name = name;
		this.desc = desc;
		this.isAdminOnly = adminOnly;
		this.commandClass = (Class<T>)commandClass;
	}
}
