package com.chcmatt.katelyn.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.pircbotx.Colors;

public class WebUtils
{
	public static Map<String, String> getLocationData(String ip)
	{
		String address = Colors.removeFormattingAndColors(String.format("http://geo.liamstanley.io/json/%s", ip));
		try
		{
			HttpURLConnection conn = (HttpURLConnection) new URL(address).openConnection();
			Scanner in = new Scanner(conn.getInputStream());

			String json = "";
			while (in.hasNext())
				json += in.nextLine();

			JSONParser parser = new JSONParser();
			@SuppressWarnings("unchecked")
			Map<String, String> data = (Map<String, String>)parser.parse(json);
			in.close();
			return data;
		}
		catch (IOException | ParseException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}