package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import hooks.Hooks;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ReportUtils;

public class DummyService {
	
	private Response response;

	public void accessApi(String url) {
		System.out.println("Access API " + url);
		RestAssured.baseURI = url;
	}

	public void sendRequestGETMethod(String endpoint) {
		ReportUtils.logInfo("Send request for endpoint: " + endpoint);
		response = RestAssured.given().log().all()
			.contentType(ContentType.JSON).get(endpoint);
		ReportUtils.logInfo("Status code: " + response.getStatusCode());
	}

	public void validateCompleteProductsList() {
		ReportUtils.logInfo("Validate List products");
		response.then().statusCode(200).log().all().extract().jsonPath();
		List<Map<String, Object>> products = response.jsonPath().getList("products");
		for (Map<String, Object> item : products) {
		    Assert.assertTrue(item.get("id").getClass().equals(Integer.class));
		    Assert.assertTrue(item.get("title").getClass().equals(String.class));
		    Assert.assertTrue(item.get("description").getClass().equals(String.class));
		    Assert.assertTrue(item.get("category").getClass().equals(String.class));
		    Assert.assertTrue(item.get("price").getClass().equals(Double.class) || item.get("price").getClass().equals(Float.class));
		    Assert.assertTrue(item.get("stock").getClass().equals(Integer.class));
		    Assert.assertTrue(item.get("tags").getClass().equals(ArrayList.class));
		}
		ReportUtils.attachEvidence(response, Hooks.getScenarioName());
	}

}
