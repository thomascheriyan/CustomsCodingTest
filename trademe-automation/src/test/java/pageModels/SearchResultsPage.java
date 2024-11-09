package pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage {
	private WebDriver driver;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
	}

	private By listings = By.cssSelector(".listing-card");


	public int getSearchResultListingsCount() {
		java.util.List<org.openqa.selenium.WebElement> results = driver.findElements(listings);
		System.out.println("Number of search listings displayed: " + results.size());
		return results.size();
	}
	
	public void selectFirstListing() {
		WebElement firstListing = listings.findElement(driver).findElement(By.tagName(
				"a"));
		firstListing.click();
	}
}
