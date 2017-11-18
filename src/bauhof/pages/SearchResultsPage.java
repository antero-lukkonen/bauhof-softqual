package bauhof.pages;

import java.net.URI;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Func;

public class SearchResultsPage extends BasePage {
    public SearchResultsPage(WebDriver driver, URI baseUri) {
        super(driver, baseUri);
    }

    public URI getUriFor(String searchedItem) {
        return Func.toUri(this.getUri() + "/catalogsearch/result/?q=" + Func.toUtf8UriPart(searchedItem)).normalize();
    }

    public SearchResultsPage searchFor(String searchedItem) {
        driver.get(this.getUriFor(searchedItem).toString());
        return this;
    }

    public ProductListItem getResultByName(String name) {
        return getResultBy(x -> x.getName().toLowerCase().equals(name.toLowerCase()));
    }

    public ProductListItem getFirstResult() {
        return getResults().findFirst().orElse(null);
    }

    private ProductListItem getResultBy(Predicate<ProductListItem> predicate) {
        return this.getResults().filter(predicate).findFirst().orElse(null);
    }

    private Stream<ProductListItem> getResults() {
        return driver.findElements(By.cssSelector("div.product-item-info")).stream().map(x -> Func.toProductListItem(x, () -> null));
    }
}