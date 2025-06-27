package service;

import io.restassured.RestAssured;

public class DummyService {

	public void accessApi(String url) {
		System.out.println("Access API " + url);
		RestAssured.baseURI = url;
	}

}
