package bauhof.pages;

import java.net.URI;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {
	public HomePage(WebDriver driver, URI uri) {
		super(driver, uri);
	}

	public void searchFor(String searchString) {
		enterSearchText(searchString);
		submitSearch();
	}

	public void enterSearchText(String searchString) {
		WebElement search = driver.findElement(By.id("search"));
		search.click();
		search.sendKeys(searchString);
	}

	public SearchResultsPage submitSearch() {
		WebElement btn = driver.findElement(By.cssSelector("button.action.search"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btn);
		return new SearchResultsPage(this.driver, this.getUri());
	}

	/** FIXME: Not working properly due to JS callback hell on client side */
	public void openMainMenu() {
		Actions hover = new Actions(this.driver);

		WebElement element = this.driver.findElement(By.xpath("//*[@id=\"store.menu\"]/div[1]/ul[2]/li[1]"));
		hover.moveToElement(element).build().perform();
	}
}