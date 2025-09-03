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
import utils.MetricsUtils;
import utils.ReportUtils;

public class NasaService {

    private static final String API_KEY = ConfigLoader.get("nasa.api.key");
    private Response response;

    public void requestGETMethodWithAPIKey(String endpoint) {
        ReportUtils.logInfo("Send request for endpoint: " + endpoint);
        MetricsUtils.startSample();

        response = RestAssured.given().log().all()
                .queryParam("api_key", API_KEY)
                .contentType(ContentType.JSON)
                .get(endpoint);

        MetricsUtils.stopSample(Hooks.getScenarioName(), "passed");
    }

    public void validateResponseWithImageData() {
        ReportUtils.logInfo("Validate image data");
        MetricsUtils.startSample();

        response.then().statusCode(200).log().body()
                .body("date", Matchers.instanceOf(String.class))
                .body("explanation", Matchers.instanceOf(String.class))
                .body("media_type", Matchers.instanceOf(String.class))
                .body("service_version", Matchers.instanceOf(String.class))
                .body("title", Matchers.instanceOf(String.class));

        ReportUtils.attachEvidence(response, Hooks.getScenarioName());
        MetricsUtils.stopSample(Hooks.getScenarioName(), "passed");
    }

    public void requestGETMethodWithAPIKeyAndParam(String endpoint, String param, String data) {
        ReportUtils.logInfo("Send request with param: " + param + " for endpoint: " + endpoint);
        MetricsUtils.startSample();

        response = RestAssured.given().log().all()
                .queryParam("api_key", API_KEY)
                .queryParam(param, data)
                .contentType(ContentType.JSON)
                .get(endpoint);

        MetricsUtils.stopSample(Hooks.getScenarioName(), "passed");
    }

    public void validateResponseWithPictureData() {
        ReportUtils.logInfo("Validate picture data");
        MetricsUtils.startSample();

        response.then().statusCode(200).log().body();

        List<Map<String, Object>> pictures = response.jsonPath().getList("photos");

        for (Map<String, Object> picture : pictures) {
            Assert.assertTrue(picture.get("id").getClass().equals(Integer.class));
            Assert.assertTrue(picture.get("sol").getClass().equals(Integer.class));
            Assert.assertTrue(picture.get("img_src").getClass().equals(String.class));
            Assert.assertTrue(picture.get("earth_date").getClass().equals(String.class));
        }

        ReportUtils.attachEvidence(response, Hooks.getScenarioName());
        MetricsUtils.stopSample(Hooks.getScenarioName(), "passed");
    }

	public void requestGETMethodWithInicialDateAndFinalDate(String endpoint, String start_date, String end_date) {
		ReportUtils.logInfo("Send request with dates: " + "start-date: " + start_date + " end-date: "+ end_date);
		MetricsUtils.startSample();
		response = RestAssured.given()
				.queryParam("api_key", API_KEY)
				.contentType(ContentType.JSON)
				.get(endpoint);
		MetricsUtils.stopSample(Hooks.getScenarioName(), "passed");
	}

	@SuppressWarnings("unchecked")
	public void validateResponseWithEarthDate() {
	    ReportUtils.logInfo("Validate earth data");
	    MetricsUtils.startSample();

	    response.then().statusCode(200).log().body();

	    Map<String, List<Map<String, Object>>> nearEarthObjects = response.jsonPath().getMap("near_earth_objects");
	    Assert.assertNotNull("Campo 'near_earth_objects' está ausente!", nearEarthObjects);
	    Assert.assertFalse("Lista de objetos próximos à Terra está vazia!", nearEarthObjects.isEmpty());

	    for (Map.Entry<String, List<Map<String, Object>>> entry : nearEarthObjects.entrySet()) {
	        List<Map<String, Object>> asteroids = entry.getValue();
	        Assert.assertNotNull("Lista de asteroides está nula para a data " + entry.getKey(), asteroids);
	        Assert.assertFalse("Lista de asteroides está vazia para a data " + entry.getKey(), asteroids.isEmpty());

	        for (Map<String, Object> asteroid : asteroids) {
	            Assert.assertNotNull("Campo 'estimated_diameter' está ausente!", asteroid.get("estimated_diameter"));

	            Map<String, Object> estimatedDiameter = (Map<String, Object>) asteroid.get("estimated_diameter");
	            Assert.assertNotNull("Campo 'kilometers' está ausente!", estimatedDiameter.get("kilometers"));

	            Map<String, Object> kilometers = (Map<String, Object>) estimatedDiameter.get("kilometers");
	            Assert.assertTrue(kilometers.containsKey("estimated_diameter_min"));
	            Assert.assertTrue(kilometers.containsKey("estimated_diameter_max"));

	            Assert.assertNotNull(kilometers.get("estimated_diameter_min"));
	            Assert.assertNotNull(kilometers.get("estimated_diameter_max"));

	            Assert.assertTrue(kilometers.get("estimated_diameter_min") instanceof Number);
	            Assert.assertTrue(kilometers.get("estimated_diameter_max") instanceof Number);
	        }
	    }
	    ReportUtils.attachEvidence(response, Hooks.getScenarioName());
        MetricsUtils.stopSample(Hooks.getScenarioName(), "passed");
	}


}
