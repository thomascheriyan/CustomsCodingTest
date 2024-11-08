package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;

import java.util.Arrays;
import java.util.List;

public class TradeMeAPISteps {

	private static Response response;
	private static int expectedCarBrandCount = 0;

	@Before
	public static void setupExpectedCarCount() {
		RequestSpecification request = RestAssured.given();
		response = request.get();
		// JsonPath responseJsonPath = response.jsonPath();

		// Extract Subcategories from response and filter out entries where Name is
		// "Other"
		List<String> carNames = response.jsonPath()
				.getList("Subcategories.findAll { item -> item.Name != 'Other' }.Name");
		int namedCarCount = carNames.size(); // Get the count of cars where Name is not "Other"
		System.out.println("Number of named cars (excluding 'Other'): " + namedCarCount);
		expectedCarBrandCount = namedCarCount;
	}

	@Given("the Trade Me API endpoint {string} is available")
	public void theTradeMeApiEndpointIsAvailable(String endpoint) {
		RestAssured.baseURI = endpoint;
	}

	@When("I send a GET request to the Used Cars Categories endpoint")
	public void iSendAGETRequestToTheUsedCarsCategoriesEndpoint() {
		RequestSpecification request = RestAssured.given();
		response = request.get();
	}

	@Then("the response status code should be {int}")
	public void theResponseStatusCodeShouldBe(int statusCode) {
		Assert.assertEquals(statusCode, response.getStatusCode());
	}

	@Given("I have a count of expected named car brands")
	public void iHaveCountOfExpectedNamedCarBrands() {
		// This method remains empty; 
		// the expected count is already set in
		// @Before method setupExpectedCarCount()

	}

	@Then("the response should contain a count of named car brands that matches the expected count")
	public void theResponseShouldContainACountOfNamedCarBrandsThatMatchesTheExpectedCount() {
		JSONObject jsonResponse = new JSONObject(response.getBody().asString());
		JSONArray carBrands = jsonResponse.getJSONArray("Subcategories");
		int actualCarBrandCount = 0;

		// Count entries where "Name" is not equal to "Other"
		for (int i = 0; i < carBrands.length(); i++) {
			JSONObject car = carBrands.getJSONObject(i);
			if (!"Other".equals(car.getString("Name"))) {
				actualCarBrandCount++;
			}
		}

		Assert.assertEquals("The count of named car brands does not match the expected count", expectedCarBrandCount,
				actualCarBrandCount);
	}

	@Then("the response time should be less than {int} milliseconds")
	public void theResponseTimeShouldBeLessThanMilliseconds(int maxResponseTime) {
		long responseTime = response.getTime();
		Assert.assertTrue("Response time exceeded limit: " + responseTime + "ms", responseTime < maxResponseTime);
	}
}
