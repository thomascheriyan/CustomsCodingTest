Feature: Trade Me Property Search UI Test

Background: 
    Given the TradeMe website "https://www.tmsandbox.co.nz" is up and running
		
  Scenario: Search for a house in Wellington and verify the listing details
    Given I navigate to the TradeMe website "https://www.tmsandbox.co.nz"
    When I enter "house" in the search field and click on Search
    And I select the category as "Property"
    And I set the location region as "Wellington"
    Then I verify the number of listings displayed is 283
    When I select the first listing and verify the key details