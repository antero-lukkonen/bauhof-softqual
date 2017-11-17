package bauhof.usecases;

import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import bauhof.pages.HomePage;
import bauhof.pages.ProductListItem;
import bauhof.pages.SearchResultsPage;

@TestInstance(Lifecycle.PER_CLASS)
public class UC2_FindProductByKeyword extends BaseTemplate {

	@Test
	public void emptySearchRefreshesCurrentPage() {
		HomePage page = (HomePage) new HomePage(driver, baseUri).navigateTo();
		
		page.waitForRefreshAfter(() -> page.searchFor(""));
		
		assertThat(page.getUri().toString(), is(driver.getCurrentUrl()));
	}

	@Test
	public void searchingOpensResultsPage() throws UnsupportedEncodingException, URISyntaxException {
		String searchedItem = "Lihvmasin BO3710 Makita";

		HomePage page = (HomePage) new HomePage(driver, baseUri).navigateTo();
		page.enterSearchText(searchedItem);
		SearchResultsPage resultsPage = page.submitSearch();

		assertThat(driver.getCurrentUrl(), startsWith(resultsPage.getUriFor(searchedItem).toString()));
	}

	@Test
	public void resultsPageContainsSearchedItem() throws UnsupportedEncodingException, URISyntaxException {
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
	public void clickingOnAddToCartButtonAddsNewItemToCart() throws UnsupportedEncodingException, URISyntaxException {		
		String searchString = "Lihvmasin";

		getAnySearchResult(searchString).getAddToCartButton().click();

		assertThat(driver.getCurrentUrl(), startsWith(new SearchResultsPage(driver, baseUri).getUriFor(searchString).toString()));

	}

	private ProductListItem getAnySearchResult(String searchString) throws UnsupportedEncodingException {
		return searchFor(searchString).getFirstResult();		
	}

	private SearchResultsPage searchFor(String searchString) throws UnsupportedEncodingException {
		try {
			return new SearchResultsPage(this.driver, this.baseUri).searchFor(searchString);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
}