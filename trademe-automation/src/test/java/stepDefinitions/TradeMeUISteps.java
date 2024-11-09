package stepDefinitions;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.Driver;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageModels.ListingDetailsPage;
import pageModels.SearchResultsPage;
import pageModels.TradeMeHomePage;

public class TradeMeUISteps {
	private WebDriver _driver = Driver.getDriver();
	private TradeMeHomePage _tradeMeHomePage = new TradeMeHomePage(_driver);
	private SearchResultsPage _searchResultsPage = new SearchResultsPage(_driver);
	private ListingDetailsPage _listingDetailsPage = new ListingDetailsPage(_driver);

	@Given("the TradeMe website {string} is up and running")
	public void theTradeMeWebsiteIsUpAndRunning(String url) {
		int attempts = 0;
	    int maxRetries = 3;
	    boolean isPageLoaded = false;

	    while (attempts < maxRetries && !isPageLoaded) {
	        try {
	            if (!_driver.getCurrentUrl().equals(url)) {
	                _driver.get(url);
	                WebDriverWait wait = new WebDriverWait(_driver, Duration.ofSeconds(10));
	        	    wait.until(ExpectedConditions.urlToBe(url)); // Wait until the URL is fully loaded
	        	  
	        	    //Check page title
	        	    String pageTitle = _driver.getTitle();
	        	    Assert.assertTrue("TradeMe webpage is not reachable!", pageTitle.contains("Trade Me"));
	            }
	            isPageLoaded = true; // Exit loop if page is loaded
	        } 
	        catch (Exception e) {
	            attempts++;
	            if (attempts == maxRetries) {
	                throw new RuntimeException("Failed to load the TradeMe sandbox after " + maxRetries + " attempts", e);
	            }
	        }
	    }	    
	}

	@Given("I navigate to the TradeMe website {string}")
	public void iNavigateToTheTradeMeWebsite(String url) {
	    if (!_driver.getCurrentUrl().equals(url)) {
	        _driver.get(url);
	    }
	}

	@When("I enter {string} in the search field and click on Search")
	public void iEnterInTheSearchFieldAndClickOnSearch(String query) {
		_tradeMeHomePage.enterSearchQuery(query);
		_tradeMeHomePage.clickSearchButton();
	}

	@And("I select the category as {string}")
	public void iSelectTheCategoryAs(String category) {
		_tradeMeHomePage.selectCategory(category);
	}

	@And("I set the location region as {string}")
	public void iSetTheLocationRegionAs(String region) {
		_tradeMeHomePage.selectRegion(region);
	}

	@Then("I verify the number of listings displayed is {int}")
	public void iVerifyTheNumberOfListingsDisplayed(int numberOfListings) {
		int searchResultsCount = _searchResultsPage.getSearchResultListingsCount();
		Assert.assertEquals("Listings are not displayed or does not match the expected count", searchResultsCount,
				numberOfListings);

	}

	@Then("I select the first listing and verify the key details")
	public void iSelectTheFirstListingAndVerifyTheKeyDetails() {
		_searchResultsPage.selectFirstListing();
		
//		String address = _listingDetailsPage.getAddressDetails();
//		String beds = _listingDetailsPage.getBedsDetails();
//		String agentName = _listingDetailsPage.getAgentName();
		
		Assert.assertNotNull("Address not displayed", _listingDetailsPage.getAddressDetails());
		Assert.assertNotNull("Beds information not displayed", _listingDetailsPage.getBedsDetails());
		Assert.assertNotNull("Agent's name not displayed", _listingDetailsPage.getAgentName());
	}

	@After
	public void tearDown() {
		Driver.quitDriver();
	}
}