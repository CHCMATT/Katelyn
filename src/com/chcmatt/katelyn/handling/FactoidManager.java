package com.chcmatt.katelyn.handling;

import java.util.HashMap;
import com.chcmatt.katelyn.utils.Config;

public class FactoidManager
{
	private static Config config = new Config("config.json");
	
	static
	{
		// Check to make sure the "factoids" map exists in the config
		if (!config.getMap().keySet().contains("factoids"))
			// If it doesn't exist, create it
			config.getMap().put("factoids", new HashMap<String, Object>());
	}
	
	public static void addFactoid(String name, String data)
	{
		config.getMap().get("factoids").put(name, name + ": " + data);
		config.updateFile();
	}
	
	public static void removeFactoid(String name)
	{
		config.getMap().get("factoids").remove(name);
		config.updateFile();	
	}
	
	public static boolean factoidExists(String name)
	{
		return config.getMap().get("factoids").keySet().contains(name);
	}
	
	public static String getFactoidData(String name)
	{
		return config.getString("factoids", name);
	}
}