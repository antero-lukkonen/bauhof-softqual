package bauhof.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

	protected WebDriver driver;
	protected String uri;

	public BasePage(WebDriver driver, String uri) {
		this.uri = uri;
		this.driver = driver;
	}

	public BasePage go() {
		driver.get(this.uri);
		return this;
	}

	public BasePage maximize() {
		driver.manage().window().maximize();
		return this;
	}

}