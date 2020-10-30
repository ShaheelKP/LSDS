package org.cerner.jar.retrieval;

import static org.junit.Assert.*;
import org.junit.Test;

import org.hamcrest.collection.IsMapContaining;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class latestver_Test {
	/**
	 * Testing for latestver class
	 */
	@Test
	public void getPeopleMap() {
		latestver hmap = new latestver();
		TreeMap<Date, String> hrefMap = hmap.getPeopleMap();
		final Calendar cal = Calendar.getInstance();
		final Calendar cal2 = Calendar.getInstance();
		final Calendar cal3 = Calendar.getInstance();
		final Calendar cal4 = Calendar.getInstance();
		final Calendar cal5 = Calendar.getInstance();
		cal.set(2014, 0, 1, 0, 0, 0);
		cal2.set(2013, 11, 31, 0, 0, 0);
		cal3.set(2011, 11, 31, 0, 0, 0);
		cal4.set(2012, 11, 31, 0, 0, 0);
		cal5.set(2010, 11, 31, 0, 0, 0);
		hrefMap.put(cal.getTime(), "java");
		hrefMap.put(cal2.getTime(), "c++");
		hrefMap.put(cal3.getTime(), "python");
		hrefMap.put(cal4.getTime(), "node");

		Map<Date, String> expected = new TreeMap<Date, String>();
		expected.put(cal.getTime(), "java");
		expected.put(cal2.getTime(), "c++");
		expected.put(cal3.getTime(), "python");
		expected.put(cal4.getTime(), "node");

		// Test equal, ignore order
		assertThat(hrefMap, is(expected));

		// Test size
		assertThat(hrefMap.size(), is(4));

		// Test map entry, best!
		assertThat(hrefMap, IsMapContaining.hasEntry(cal.getTime(), "java"));
		assertThat(hrefMap, not(IsMapContaining.hasEntry(cal5.getTime(), "ruby")));

		// Test map key
		assertThat(hrefMap, IsMapContaining.hasKey(cal4.getTime()));

		// Test map value
		assertThat(hrefMap, IsMapContaining.hasValue("node"));
	}

}
