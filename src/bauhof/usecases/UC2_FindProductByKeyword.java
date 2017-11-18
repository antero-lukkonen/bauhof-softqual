package bauhof.usecases;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

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
    public void searchingOpensResultsPage() {
        String searchedItem = "Lihvmasin BO3710 Makita";

        HomePage page = (HomePage) new HomePage(driver, baseUri).navigateTo();
        page.enterSearchText(searchedItem);
        SearchResultsPage resultsPage = page.submitSearch();

        assertThat(driver.getCurrentUrl(), startsWith(resultsPage.getUriFor(searchedItem).toString()));
    }

    @Test
    public void resultsPageContainsSearchedItem() {
        String expectedProductName = "LIHVMASIN BO3710 180W MAKITA";
        String searchString = "Lihvmasin";

        SearchResultsPage page = searchFor(searchString);

        assertThat(page.getResultByName(expectedProductName), is(not(nullValue())));
    }

    @Test
    public void searchResultItemContainsProductName() {
        String searchString = "Lihvmasin";

        ProductListItem item = getAnySearchResult(searchString);

        assertThat(item.getName(), is(not(nullValue())));
    }

    @Test
    public void searchResultItemContainsPriceInEuro() {
        String searchString = "Lihvmasin";

        ProductListItem item = getAnySearchResult(searchString);

        assertThat(item.getPrice(), endsWith("€"));
    }

    @Test
    public void searchResultItemContainsAddToCartButton() {
        String searchString = "Lihvmasin";

        ProductListItem item = getAnySearchResult(searchString);

        assertThat(item.getAddToCartButton(), is(not(nullValue())));
    }

    @Test
    public void clickingOnAddToCartButtonAddsNewItemToCart() {
        String searchString = "Lihvmasin";

        getAnySearchResult(searchString).getAddToCartButton().click();

        assertThat(driver.getCurrentUrl(), startsWith(new SearchResultsPage(driver, baseUri).getUriFor(searchString).toString()));
    }

    private ProductListItem getAnySearchResult(String searchString) {
        return searchFor(searchString).getFirstResult();
    }

    private SearchResultsPage searchFor(String searchString) {
        return new SearchResultsPage(this.driver, this.baseUri).searchFor(searchString);
    }
}