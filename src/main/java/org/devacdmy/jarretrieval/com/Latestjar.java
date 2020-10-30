package org.devacdmy.jarretrieval.com;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Latestjar {

	public static void main(String[] args) throws IOException {
		TreeMap<String, String> LinkMap = new TreeMap<String, String>();
		Map<String, String> Fmap = new TreeMap<String, String>();

		Document doc = Jsoup.connect(
				"http://repo.release.cerner.corp/nexus/content/repositories/main-repo/com/cerner/synapse/rules/").get();
		Elements trs = doc.select("table tr");
		/**
		 * Removing Unecessary Rows
		 */
		for (int i = 0; i < 2; i++) {
			trs.remove(0);
		}
		for (Element tr : trs) {

			Elements tdd = tr.getElementsByTag("a");
			LinkMap.put(tdd.text(), tdd.attr("href"));
		}
		/**
		 * Passing Url to geturl method
		 */
		for (Map.Entry<String, String> entry : LinkMap.entrySet()) {
			String add = entry.getValue();
			String clientname = entry.getKey();
			System.out.println(""+clientname);
			Jarversion obb = new Jarversion();
			Fmap.put(clientname, obb.geturtl(add));
		}
		/**
		 * Printing To a file
		 */
		File file = new File("C:\\sample.txt");
		PrintStream stream = new PrintStream(file);
		System.setOut(stream);
		for (Map.Entry<String, String> entry : Fmap.entrySet())
			System.out.println("\nName =" + entry.getKey() + "\t\tVersion = " + entry.getValue());
		
		
		

	}

}
