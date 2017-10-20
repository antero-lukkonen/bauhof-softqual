package bauhof.pages;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage extends BasePage {
	public SearchResultsPage(WebDriver driver, String baseUri) {
		super(driver, baseUri);
	}

	public String getUriFor(String searchedItem) throws UnsupportedEncodingException {
		return this.uri + "/catalogsearch/result/?q=" + URLEncoder.encode(searchedItem, "UTF-8");
	}

	public void searchFor(String searchedItem) throws UnsupportedEncodingException {
		driver.get(this.getUriFor(searchedItem));
	}

	public WebElement getResultByName(String name) {
		return driver
			.findElements(By.cssSelector("a.product-item-link"))
			.stream()
			.filter(x -> x.getText().toLowerCase().equals(name.toLowerCase()))
			.findFirst()
			.orElse(null);
	}
}