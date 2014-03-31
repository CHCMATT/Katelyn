package com.chcmatt.katelyn.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.pircbotx.Colors;

public class WebUtils
{

	protected final static JSONParser PARSER = new JSONParser();

	/**
	 * Reads a URL and trys to parse JSON from it
	 * @param formattedUrl The URL, to be used in String.format(...)
	 * @param input The argument to be used in String.format(...)
	 * @param encodeInput Set to <code>true</code> to encode the input using URLEncoder.encode(...)
	 * @return a JSONObject representing all of the JSON data on the URL
	 * @throws IOException If fails to connect to or read the URL
	 * @throws ParseException If fails to parse the URL for JSON
	 */
	public static JSONObject getJSON(String formattedUrl, String input, boolean encodeInput) throws IOException, ParseException
	{
		if (encodeInput)
			input = URLEncoder.encode(input, "UTF-8");
		String fullUrl = String.format(formattedUrl, input);
		HttpURLConnection conn = (HttpURLConnection) new URL(fullUrl).openConnection();
		JSONObject json = (JSONObject) PARSER.parse(new InputStreamReader(conn.getInputStream()));
		return json;
	}

	/**
	 * Tries to calculate a mathematical expression using <a href="http://www.duckduckgo.com/">www.duckduckgo.com</a>
	 * @param input The math expression, with or without spaces
	 * @return The math expression, followed by an equals sign, then the answer. Example: <i>"1 + 1 = 2"</i>
	 * @throws IOException
	 * @throws ParseException
	 * @throws IllegalArgumentException If the search for <code>input</code> isn't a calculation. (Tried to define it)
	 * @see #getJSON(String, String)
	 */
	public static String getCalculation(String input) throws IOException, ParseException, IllegalArgumentException
	{

		JSONObject data = getJSON("http://api.duckduckgo.com/?q=%s&format=json", input, true);

		if (!data.get("AnswerType").equals("calc"))
			throw new IllegalArgumentException("Result not a calculation: " + input + ".");

		String htmlResult = (String) data.get("Answer");

		if (htmlResult.contains("<sup>") && htmlResult.contains("</sup>"))
			htmlResult = htmlResult.replaceAll("<sup>", " ^ ").replaceAll("</sup>", "");

		String strippedResult = new HtmlToPlainText().getPlainText(Jsoup.parse(htmlResult));

		if (strippedResult.endsWith("<>"))
			strippedResult = strippedResult.replace("<>", "");

		return strippedResult;
	}
	
	public static Map<String, String> getLocationData(String ip) throws IOException, ParseException
	{
		String address = Colors.removeFormattingAndColors("http://geo.liamstanley.io/json/" + ip);
		HttpURLConnection conn = (HttpURLConnection) new URL(address).openConnection();
		JSONObject data = (JSONObject)new JSONParser().parse(new InputStreamReader(conn.getInputStream()));

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
	public static String getGithubTags() throws IOException, ParseException, IllegalArgumentException
	{
		String address = "https://api.github.com/repos/CHCMATT/Katelyn/tags";
		HttpURLConnection conn = (HttpURLConnection) new URL(address).openConnection();
		String data = (String)new JSONParser().parse(new InputStreamReader(conn.getInputStream()));
		
		return data;
	}
}