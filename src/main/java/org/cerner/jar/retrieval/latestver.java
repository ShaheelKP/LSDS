package org.cerner.jar.retrieval;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class latestver {
	private TreeMap<Date, String> hrefMap = new TreeMap<Date, String>();

	/**
	 * 
	 * Create Treemap to store latest Version and Modified date of the version
	 */
	public TreeMap<Date, String> getPeopleMap() {
		return hrefMap;
	}

	public static void main(String[] args) {
		latestver obj = new latestver();
		TreeMap<Date, String> hrefMap = obj.getPeopleMap();
		try {
			Document doc = Jsoup.connect(
					"http://repo.release.cerner.corp/nexus/content/repositories/main-repo/com/cerner/synapse/rules/app-rules/")
					.get();
			Elements trs = doc.select("table tr");

			/**
			 * Deleting unnecessary rows
			 */
			for (int i = 0; i <= 2; i++) {
				trs.remove(0);
			}
			for (int i = 0; i <= 3; i++) {
				trs.remove(trs.size() - 1);
				;
			}

			/**
			 * Retrieving Based on tags
			 */

			for (Element tr : trs) {
				Elements tds = tr.getElementsByTag("td");
				Elements tdd = tr.getElementsByTag("a");
				Element td = tds.first();
				tds.remove(0);
				/**
				 * Converting string to Date Format
				 */

				SimpleDateFormat DateFor = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
				/**
				 * Storing data into treemap
				 */
				try {
					Date date = DateFor.parse(tds.text());
					hrefMap.put(date, tdd.text());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
		Date Modifieddate = ((TreeMap<Date, String>) hrefMap).lastEntry().getKey();
		String Jarversion = ((TreeMap<Date, String>) hrefMap).lastEntry().getValue();
		System.out.println("Latest Jar Version : " + Jarversion + "  Modified Date : " + Modifieddate);
	}
}
