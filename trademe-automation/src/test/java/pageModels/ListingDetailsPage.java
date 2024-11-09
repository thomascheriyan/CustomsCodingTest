package pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListingDetailsPage {

	private WebDriver driver;

	public ListingDetailsPage(WebDriver driver) {
		this.driver = driver;
	}

	private By address = By.cssSelector(".listing-address");
	private By beds = By.cssSelector(".listing-beds");
	private By agentName = By.cssSelector(".agent-name");

	public String getAddressDetails() {
		System.out.println("Address: " + driver.findElement(address).getText()); 
		return driver.findElement(address).getText();
	}
	
	public String getBedsDetails() {
		System.out.println("Beds: " + driver.findElement(beds).getText());
		return driver.findElement(beds).getText();
	}
	
	public String getAgentName() {
		System.out.println("Agent Name: " + driver.findElement(agentName).getText());
		return driver.findElement(agentName).getText();
	}
}
