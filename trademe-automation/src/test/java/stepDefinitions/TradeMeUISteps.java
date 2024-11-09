package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import java.util.List;

public class TradeMeUISteps {

    WebDriver driver;
    
    @Given("the TradeMe website {string} is up and running")
    public void theTradeMeWebsiteIsUpAndRunning(String url) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Given("I navigate to the Trade Me website {string}")
    public void iNavigateToTheTradeMeWebsite(String url) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @When("I enter {string} in the search field and click on Search")
    public void iEnterInTheSearchFieldAndClickOnSearch(String searchTerm) {
        WebElement searchField = driver.findElement(By.id("searchString")); // ID may vary
        searchField.sendKeys(searchTerm);
        driver.findElement(By.id("searchButton")).click(); // Button ID may vary
    }

    @When("I select the category as {string}")
    public void iSelectTheCategoryAs(String category) {
        WebElement categoryDropdown = driver.findElement(By.id("categoryDropdown")); // ID may vary
        categoryDropdown.click();
        driver.findElement(By.xpath("//option[text()='" + category + "']")).click();
    }

    @When("I set the location region as {string}")
    public void iSetTheLocationRegionAs(String region) {
        WebElement regionDropdown = driver.findElement(By.id("regionDropdown")); // ID may vary
        regionDropdown.click();
        driver.findElement(By.xpath("//option[text()='" + region + "']")).click();
    }

    @Then("I verify the number of listings displayed")
    public void iVerifyTheNumberOfListingsDisplayed() {
        List<WebElement> listings = driver.findElements(By.cssSelector(".listing-card")); // CSS selector may vary
        System.out.println("Number of listings displayed: " + listings.size());
        Assert.assertTrue("Listings are not displayed", listings.size() > 0);
    }

    @When("I select the first listing")
    public void iSelectTheFirstListing() {
        WebElement firstListing = driver.findElement(By.cssSelector(".listing-card")).findElement(By.tagName("a"));
        firstListing.click();
    }

    @Then("I verify the key details of the listing")
    public void iVerifyTheKeyDetailsOfTheListing() {
        WebElement address = driver.findElement(By.cssSelector(".listing-address")); // CSS selector may vary
        WebElement beds = driver.findElement(By.cssSelector(".listing-beds")); // CSS selector may vary
        WebElement agentName = driver.findElement(By.cssSelector(".agent-name")); // CSS selector may vary

        Assert.assertNotNull("Address not displayed", address);
        Assert.assertNotNull("Beds information not displayed", beds);
        Assert.assertNotNull("Agent's name not displayed", agentName);

        System.out.println("Address: " + address.getText());
        System.out.println("Beds: " + beds.getText());
        System.out.println("Agent Name: " + agentName.getText());
    }
}
