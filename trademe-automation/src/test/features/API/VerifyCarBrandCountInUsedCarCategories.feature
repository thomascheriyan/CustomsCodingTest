Feature: Verify the number of named car brands available in Trade Me Used Cars API

  Background: 
    Given the Trade Me API endpoint "https://api.trademe.co.nz/v1/Categories/UsedCars.json" is available
		
  Scenario: Verify the API returns a list of car brands
    When I send a GET request to the Used Cars Categories endpoint
    Then the response status code should be 200
    And the response time should be less than 2500 milliseconds

  Scenario: Verify the count of named car brands in the response
  	Given I have a count of expected named car brands
    When I send a GET request to the Used Cars Categories endpoint
    Then the response should contain a count of named car brands that matches the expected count
