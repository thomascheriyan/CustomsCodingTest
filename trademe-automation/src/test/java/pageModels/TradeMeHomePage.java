package pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class TradeMeHomePage {

	private WebDriver driver;

	public TradeMeHomePage(WebDriver driver) {
		this.driver = driver;
	}

	private By searchBox = By.id("searchString");
	private By searchButton = By.id("SearchTabs1_SearchButton");
	private By categoryDropdown = By.id("CategorySearch");
	private By regionDropdown = By.id("LocationSelect");

	public void enterSearchQuery(String query) {
		driver.findElement(searchBox).sendKeys(query);
	}

	public void clickSearchButton() {
		driver.findElement(searchButton).click();
	}

	public void selectCategory(String category) {
		new Select(driver.findElement(categoryDropdown)).selectByVisibleText(category);
	}

	public void selectRegion(String region) {
		new Select(driver.findElement(regionDropdown)).selectByVisibleText(region);
	}

}