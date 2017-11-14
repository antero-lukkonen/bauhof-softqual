package bauhof.pages;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.function.Function;
import java.util.stream.Stream;

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

	public SearchResultsPage searchFor(String searchedItem) throws UnsupportedEncodingException {
		driver.get(this.getUriFor(searchedItem));
		return this;
	}

	public ProductListItem getResultByName(String name) {
		return this.getResultElements()
			.map(toListItem)
			.filter(x -> x.getName().toLowerCase().equals(name.toLowerCase()))
			.findFirst()
			.orElse(null);				
	}

	public ProductListItem getFirstResult() {
		return getResultElements()
			.findFirst()
			.map(toListItem)
			.orElse(null);	
	}

	private Stream<WebElement> getResultElements() {
		return driver
			.findElements(By.cssSelector("div.product-item-info"))
			.stream();
	}
	
	private static Function<? super WebElement, ? extends ProductListItem> toListItem = x -> new ProductListItem(
			x.findElement(By.className("price")).getText(),
			x.findElement(By.cssSelector("button.tocart")),
			x.findElement(By.cssSelector("a.product-item-link")).getText());
}