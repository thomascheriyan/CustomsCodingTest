package API.stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

public class TradeMeAPISteps {

    private static final String BASE_URL = "https://api.trademe.co.nz/v1/Categories/UsedCars.json";
    private Response response;
    private List<String> expectedCarBrands = Arrays.asList("Toyota", "Ford", "Nissan", "Honda", "Mazda"); // Example expected brands
    private int expectedCarBrandCount = expectedCarBrands.size();

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

    
    @Then("the response should contain a count of car brands that matches the expected count")
    public void theResponseShouldContainACountOfCarBrandsThatMatchesTheExpectedCount() {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JSONArray carBrands = jsonResponse.getJSONArray("Subcategories");
        Assert.assertEquals("The count of car brands does not match the expected count", expectedCarBrandCount, carBrands.length());
    }

    @Then("the response time should be less than {int} milliseconds")
    public void theResponseTimeShouldBeLessThanMilliseconds(int maxResponseTime) {
        long responseTime = response.getTime();
        Assert.assertTrue("Response time exceeded limit: " + responseTime + "ms", responseTime < maxResponseTime);
    }
}
