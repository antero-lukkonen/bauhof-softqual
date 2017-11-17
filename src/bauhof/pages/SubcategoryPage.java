package bauhof.pages;

import java.net.URI;
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
        URI uri2 = Func.toUri(uri + "/" + category + "/" + subCategory);
        return uri2.normalize();
    }

    public Stream<ProductListItem> getProducts() {
        return driver.findElements(By.cssSelector(".product-item")).stream()
                .map(SubcategoryPage::toListItem);
    }

    public ProductListItem getAnyProduct() {
        return getProducts().findAny().get();
    }

    private static ProductListItem toListItem(WebElement x) {
        WebElement btnToCart = x.findElement(By.cssSelector("button.tocart"));
        WebElement link = x.findElement(By.cssSelector("a.product-item-link"));
        String id = Func.toUri(link.getAttribute("href")).getPath()
                .split("/")[3];

        return new ProductListItem(
                x.findElement(By.className("price")).getText(),
                new Clickable(btnToCart::click, null), btnToCart.getText(),
                new Clickable(link::click, null), id);
    }
}
