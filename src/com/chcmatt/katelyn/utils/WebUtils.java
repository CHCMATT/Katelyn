package com.chcmatt.katelyn.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WebUtils
{
	public static Map<String, String> getLocationData(String ip) throws IOException, ParseException
	{
		String address = "http://geo.liamstanley.io/json/" + ip;
		HttpURLConnection conn = (HttpURLConnection) new URL(address).openConnection();
		JSONObject data =
				(JSONObject)new JSONParser().parse(new InputStreamReader(conn.getInputStream()));
		
		Map<String, String> results = new HashMap<>();
		for (Object o : data.keySet())
		{
			String key = o.toString();
			String val = data.get(key).toString();
			if (val.equals("0") || StringUtils.isBlank(val))
				results.put(key, "N/A");
			else
				results.put(key, Utils.toTitleCase(val));
		}
		return results;
	}
}