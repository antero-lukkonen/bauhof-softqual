package bauhof.pages;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage extends BasePage {
    public SearchResultsPage(WebDriver driver, URI baseUri) {
        super(driver, baseUri);
    }

    public URI getUriFor(String searchedItem)
            throws UnsupportedEncodingException, URISyntaxException {
        return new URI(this.getUri() + "/catalogsearch/result/?q="
                + URLEncoder.encode(searchedItem, "UTF-8")).normalize();
    }

    public SearchResultsPage searchFor(String searchedItem)
            throws UnsupportedEncodingException, URISyntaxException {
        driver.get(this.getUriFor(searchedItem).toString());
        return this;
    }

    public ProductListItem getResultByName(String name) {
        return getResultBy(
                x -> x.getName().toLowerCase().equals(name.toLowerCase()));
    }

    public ProductListItem getFirstResult() {
        return getResults().findFirst().orElse(null);
    }

    private ProductListItem getResultBy(Predicate<ProductListItem> predicate) {
        return this.getResults().filter(predicate).findFirst().orElse(null);
    }

    private Stream<ProductListItem> getResults() {
        return driver.findElements(By.cssSelector("div.product-item-info"))
                .stream().map(toListItem);
    }

    private static Function<? super WebElement, ? extends ProductListItem> toListItem = x -> new ProductListItem(
            x.findElement(By.className("price")).getText(),
            new Clickable(x.findElement(By.cssSelector("button.tocart"))::click,
                    null),
            x.findElement(By.cssSelector("a.product-item-link")).getText(),
            new Clickable(
                    x.findElement(By.cssSelector("a.product-item-link"))::click,
                    null),
            null);
}