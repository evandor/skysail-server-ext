package de.twenty11.skysail.server.ext.notes.atests;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class JsonTests {
	
	private static final String urlPrefix = "http://localhost:8181";

	@Test
	public void bundleRequestShouldReturnSuccess() {
		expect().body("success", equalTo("true")).when().get(urlPrefix + "/rest/osgi/bundles/?mode=json");
	}

}
