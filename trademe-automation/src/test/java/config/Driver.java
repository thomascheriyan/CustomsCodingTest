package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
	WebDriver driver;
	
	public WebDriver initialize() {
		driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
	}

}
