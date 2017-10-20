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
import org.openqa.selenium.chrome.ChromeDriver;

import bauhof.pages.HomePage;
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
		
		SearchResultsPage page = new SearchResultsPage(driver, this.baseUri);
		page.searchFor("Lihvmasin");
		
		assertThat(page.getResultByName(expectedProductName), is(not(nullValue())));	
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
}