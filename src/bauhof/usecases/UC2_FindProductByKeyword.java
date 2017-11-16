package bauhof.usecases;

import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;

import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import bauhof.pages.HomePage;
import bauhof.pages.ProductListItem;
import bauhof.pages.SearchResultsPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;

@TestInstance(Lifecycle.PER_CLASS)
public class UC2_FindProductByKeyword {	
	private WebDriver driver;
	private String baseUri = "https://www.bauhof.ee";
	
	@Test
	public void searchingOpensResultsPage() throws UnsupportedEncodingException {
		String searchedItem = "Lihvmasin BO3710 Makita";
		
		HomePage page = (HomePage) new HomePage(driver, baseUri).go();
		page.enterSearchText(searchedItem);		
		SearchResultsPage resultsPage = page.submitSearch();
		
		assertThat(driver.getCurrentUrl(), startsWith(resultsPage.getUriFor(searchedItem)));	
	}	
	
	@Test
	public void resultsPageContainsSearchedItem() throws UnsupportedEncodingException {		
		String expectedProductName = "LIHVMASIN BO3710 180W MAKITA";			
		String searchString = "Lihvmasin";
		
		SearchResultsPage page = searchFor(searchString);
		
		assertThat(page.getResultByName(expectedProductName), is(not(nullValue())));	
	}
	
	@Test
	public void searchResultItemContainsProductName() throws UnsupportedEncodingException {		
		String searchString = "Lihvmasin";
		
		ProductListItem item = getAnySearchResult(searchString);
		
		assertThat(item.getName(), is(not(nullValue())));	
	}
	
	@Test
	public void searchResultItemContainsPriceInEuro() throws UnsupportedEncodingException {		
		String searchString = "Lihvmasin";
		
		ProductListItem item = getAnySearchResult(searchString);
		
		assertThat(item.getPrice(), endsWith("€"));	
	}
	
	@Test
	public void searchResultItemContainsAddToCartButton() throws UnsupportedEncodingException {		
		String searchString = "Lihvmasin";
		
		ProductListItem item = getAnySearchResult(searchString);
		
		assertThat(item.getAddToCartButton(), is(not(nullValue())));	
	}
	
	@Test
	public void clickingOnAddToCartButton() throws UnsupportedEncodingException {		
		String searchString = "Lihvmasin";
		
		getAnySearchResult(searchString)
			.getAddToCartButton()
			.click();

		assertThat(driver.getCurrentUrl(), startsWith(new SearchResultsPage(driver, baseUri).getUriFor(searchString)));	
	}
	
	@BeforeAll
	public void beforeAllTests() {
		ChromeDriverManager.getInstance().setup();
	}
	
	@BeforeEach
	public void beforeEachTest() {	
		driver = new ChromeDriver();
	}
	
	@AfterEach
	public void afterEachTest() {
		driver.close();
	}	
	
	private ProductListItem getAnySearchResult(String searchString) throws UnsupportedEncodingException {
		return searchFor(searchString).getFirstResult();
	}
	
	private SearchResultsPage searchFor(String searchString) throws UnsupportedEncodingException {
		return new SearchResultsPage(this.driver, this.baseUri).searchFor(searchString);
	}
}