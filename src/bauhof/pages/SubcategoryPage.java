package bauhof.pages;

import java.net.URI;
import java.util.function.Function;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Func;

public class SubcategoryPage extends BasePage {

    public SubcategoryPage(WebDriver driver, URI uri) {
        super(driver, uri);
    }

    public SubcategoryPage(WebDriver driver, URI baseUri, String category,
            String subCategory) {
        super(driver, getUriFor(baseUri, category, subCategory));
    }

    public URI getUriFor(String category, String subCategory) {
        return getUriFor(this.getUri(), category, subCategory);
    }

    private static URI getUriFor(URI uri, String category, String subCategory) {
        return Func.toUri(uri + "/" + category + "/" + subCategory).normalize();
    }

    public Stream<ProductListItem> getProducts() {
        return driver.findElements(By.cssSelector(".product-item")).stream()
                .map(toListItem);
    }

    public ProductListItem getAnyProduct() {
        return getProducts().findAny().get();
    }

    private static Function<? super WebElement, ? extends ProductListItem> toListItem = x -> new ProductListItem(
            x.findElement(By.className("price")).getText(),
            new Clickable(x.findElement(By.cssSelector("button.tocart"))::click,
                    null),
            x.findElement(By.cssSelector("a.product-item-link")).getText());
}
