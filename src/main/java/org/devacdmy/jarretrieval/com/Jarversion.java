package org.devacdmy.jarretrieval.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Jarversion {
	
	/**
	 * Reads Url as an arguement and parse corresponding table in given Url
	 * @param url:Url to connect to document and parse the table
	 * @return:Return latest jar version based on date rom parsed data
	 */
	public String geturtl(String url) {
		TreeMap<Date, String> hMap = new TreeMap<Date, String>();
			try {
			Document doc = Jsoup.connect(url).get();
			Elements tablename = doc.select("table tr");
			

			for (int i = 0; i <= 1; i++) {        //Removing uneccessary rows
				tablename.remove(0);
			}
			for (int i = 0; i <= 2; i++) {
				tablename.remove(tablename.size() - 1);
			}
			for (Element tr : tablename) {
				Elements tableoftd = tr.getElementsByTag("td");
				Elements tableofa = tr.getElementsByTag("a");
				Element tableoffirst = tableoftd.first();
				tableoftd.remove(0);
				/**
				 * Converting string to Date Format
				 */

				SimpleDateFormat DateFor = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
				try {
					Date date = DateFor.parse(tableoftd.text());
					hMap.put(date, tableofa.text());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		} catch (IllegalArgumentException i) {
			return "Invalid Url";
		}
		/**
		 * Returning Latest Jar Version
		 */
		Date Modifieddate = ((TreeMap<Date, String>) hMap).lastEntry().getKey();
		String Jarversion = ((TreeMap<Date, String>) hMap).lastEntry().getValue();
		return Jarversion;
	}


}
