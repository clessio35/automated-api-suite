package service;

import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Assert;

import hooks.Hooks;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigLoader;
import utils.ReportUtils;

public class NasaService {
	
	private static final String API_KEY = ConfigLoader.get("nasa.api.key");
	private Response response;

	public void requestGETMethodWithAPIKey(String endpoint) {
		ReportUtils.logInfo("Send request for endpoint: " + endpoint);
		response = RestAssured.given().log().all().queryParam("api_key", API_KEY)
				.contentType(ContentType.JSON).get(endpoint);
	}

	public void validateResponseWithImageData() {
		ReportUtils.logInfo("validate image data");
		response.then().log().body()
			.body("date", Matchers.instanceOf(String.class))
			.body("explanation", Matchers.instanceOf(String.class))
			.body("media_type", Matchers.instanceOf(String.class))
			.body("service_version", Matchers.instanceOf(String.class))
			.body("title", Matchers.is("Closest Ever Images Near the Sun"));
		ReportUtils.attachEvidence(response, Hooks.getScenarioName());
	}

	public void requestGETMethodWithAPIKeyAndParam(String endpoint, String param, String data) {
		ReportUtils.logInfo("Send request with param: " + param + " for endpoint: " + endpoint);
		response = RestAssured.given().log().all()
				.queryParam("api_key", API_KEY)
				.queryParam(param, data)
				.contentType(ContentType.JSON).get(endpoint);
	}

	public void validateResponseWithPictureData() {
		ReportUtils.logInfo("validate picture data");
		response.then().log().body();
		List<Map<String, Object>> pictures = response.jsonPath().getList("photos");
		for(Map<String, Object> picture : pictures) {
			Assert.assertTrue(picture.get("id").getClass().equals(Integer.class));
		    Assert.assertTrue(picture.get("sol").getClass().equals(Integer.class));
		    Assert.assertTrue(picture.get("img_src").getClass().equals(String.class));
		    Assert.assertTrue(picture.get("earth_date").getClass().equals(String.class));
		}
		ReportUtils.attachEvidence(response, Hooks.getScenarioName());
	}
	
	
	

}
