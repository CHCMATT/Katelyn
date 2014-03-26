package com.chcmatt.katelyn.handling;

import java.util.Map;

import com.chcmatt.katelyn.utils.Config;

public class FactoidManager
{

	private static Config config = new Config("config.json");
	private static Map<String, String> factoids = config.getMap().get("factoids");

	public static void addFactoid(String name, String data)
	{
		factoids.put(name, data);
		config.update();
	}

	public static void removeFactoid(String name)
	{
		factoids.remove(name);
		config.update();
	}

	public static boolean factoidExists(String name)
	{
		return factoids.keySet().contains(name);
	}

	public static String getFactoidData(String name)
	{
		return factoids.get(name);
	}
}