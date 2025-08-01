package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
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
	
	public Integer captureIdProduct(String endpoint) {
		System.out.println("Capture id");
		response = RestAssured.given().log().body()
				.when().contentType(ContentType.JSON)
				.get(endpoint);
				response.then().statusCode(200);
		List<Integer> ids = new ArrayList<Integer>();
		ids = response.jsonPath().getList(endpoint + ".id");
		System.out.println(ids.stream().findFirst().orElse(-1));
		System.out.println("id: " + ids.stream().findFirst().orElse(-1));
		return ids.stream().findFirst().orElse(-1);
	}

	public void sendRequestGETMethodWithIdUser(String endpoint) {
		ReportUtils.logInfo("Send request for endpoint: " + endpoint);
		int id = captureIdProduct(endpoint);
		response = RestAssured.given().log().all()
			.contentType(ContentType.JSON).get(endpoint + id);
		ReportUtils.logInfo("Status code: " + response.getStatusCode());
		
	}

	public void validateResponseSpecificProduct() {
		ReportUtils.logInfo("Validate product id");
		response.then().statusCode(200).log().body()
			.body("id", Matchers.instanceOf(Integer.class))
			.body("title", Matchers.instanceOf(String.class))
			.body("description", Matchers.instanceOf(String.class));
		ReportUtils.attachEvidence(response, Hooks.getScenarioName());
	}

	public void validateResponseWithError(String statusCode) {
		ReportUtils.logInfo("Validate Error Code");
		int sc = Integer.parseInt(statusCode);
		response.then().statusCode(sc).log().body()
			.body("message", Matchers.equalTo("Product with id '9999' not found"));
		ReportUtils.attachEvidence(response, Hooks.getScenarioName());
	}

	public void sendRequestPOSTMethod(String endpoint) {
		ReportUtils.logInfo("send post request");
		RestAssured.given().log().body()
			.contentType(ContentType.JSON).body("")
			.post(endpoint);
		ReportUtils.logInfo("Status code: " + response.getStatusCode());
	}

	public void validateResponseWithCompleteLists() {
		ReportUtils.logInfo("Validate complete list");
		response.then().statusCode(200).log().body();
		List<Map<String, Object>> posts = response.jsonPath().getList("posts");
		for(Map<String, Object> post : posts) {
			Assert.assertTrue(post.get("id").getClass().equals(Integer.class));
		    Assert.assertTrue(post.get("title").getClass().equals(String.class));
		    Assert.assertTrue(post.get("body").getClass().equals(String.class));
		    List<?> tags = (List<?>) post.get("tags");
		    Assert.assertNotNull(tags);
		    Assert.assertTrue(post.get("views").getClass().equals(Integer.class));
		    Assert.assertTrue(post.get("userId").getClass().equals(Integer.class));
		}
		ReportUtils.attachEvidence(response, Hooks.getScenarioName());
	}

	public void validateResponseWithSpecificPost() {
		ReportUtils.logInfo("Validate specific post");
		response.then().statusCode(200).log().body()
			.body("id", Matchers.instanceOf(Integer.class))
			.body("title", Matchers.instanceOf(String.class))
			.body("body", Matchers.instanceOf(String.class));
		ReportUtils.attachEvidence(response, Hooks.getScenarioName());
	}

	public void validateCompleteListComments() {
		ReportUtils.logInfo("Validate complete list comments");
		response.then().statusCode(200).log().body();
		List<Map<String, Object>> comments = response.jsonPath().getList("comments");
		for(Map<String, Object> comment : comments) {
			Assert.assertTrue(comment.get("id").getClass().equals(Integer.class));
			Assert.assertTrue(comment.get("body").getClass().equals(String.class));
			Assert.assertTrue(comment.get("postId").getClass().equals(Integer.class));
			Assert.assertTrue(comment.get("likes").getClass().equals(Integer.class));
		}
		ReportUtils.attachEvidence(response, Hooks.getScenarioName());
	}

	public void validateResponseSpecificComment() {
		ReportUtils.logInfo("Validate specific comments list");
		response.then().statusCode(200).log().body()
			.body("id", Matchers.instanceOf(Integer.class))
			.body("body", Matchers.instanceOf(String.class))
			.body("postId", Matchers.instanceOf(Integer.class))
			.body("likes", Matchers.instanceOf(Integer.class));
		ReportUtils.attachEvidence(response, Hooks.getScenarioName());
	}

	public void validateResponseWithCompleteTasksList() {
		ReportUtils.logInfo("Validate complete Task list");
		response.then().statusCode(200).log().body().extract().jsonPath();
		List<Map<String, Object>> comments = response.jsonPath().getList("todos");
		for(Map<String, Object> comment : comments){
			Assert.assertTrue(comment.get("id").getClass().equals(Integer.class));
			Assert.assertTrue(comment.get("todo").getClass().equals(String.class));
			Assert.assertTrue(comment.get("completed").getClass().equals(Boolean.class));
			Assert.assertTrue(comment.get("userId").getClass().equals(Integer.class));
		}
		ReportUtils.attachEvidence(response, Hooks.getScenarioName());
	}

	public void validateResponseSpecificTask() {
		ReportUtils.logInfo("Validate specific Task list");
		response.then().statusCode(200).log().body()
			.body("id", Matchers.instanceOf(Integer.class))
			.body("todo", Matchers.instanceOf(String.class))
			.body("completed", Matchers.instanceOf(Boolean.class))
			.body("userId", Matchers.instanceOf(Integer.class));
		ReportUtils.attachEvidence(response, Hooks.getScenarioName());
	}

}
