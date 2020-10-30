package org.devacdmy.jarretrieval.com;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import org.junit.Test;

public class Jarversion_test {
	//Testing for valid url
	@Test
	public void good_url() {
		Jarversion ob = new Jarversion();
		String LatestVersion = "5.71.0/";
		String expected = ob.geturtl(
				"http://repo.release.cerner.corp/nexus/content/repositories/main-repo/com/cerner/synapse/rules/app-rules/");
		assertEquals(LatestVersion,expected);

	}

	@Test
	//Testing for invalid url
	public void bad_url() {
		Jarversion ob = new Jarversion();
		String Invalid = "Invalid Url";
		String expected = ob.geturtl(
				"htp://repo.release.cerner.corp/nexus/content/repositories/main-repo/com/cerner/synapse/rules/app-rules/");
		assertEquals(Invalid,expected);

	}
}
