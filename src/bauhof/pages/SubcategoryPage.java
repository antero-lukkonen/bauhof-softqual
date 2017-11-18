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

    public SubcategoryPage(WebDriver driver, URI baseUri, String category, String subCategory) {
        super(driver, getUriFor(baseUri, category, subCategory));
    }

    public URI getUriFor(String category, String subCategory) {
        return getUriFor(this.getUri(), category, subCategory);
    }

    private static URI getUriFor(URI uri, String category, String subCategory) {
        return Func.toUri(uri + "/" + category + "/" + subCategory).normalize();
    }

    public Stream<ProductListItem> getProducts() {
        Function<WebElement, String> toId = x -> Func.toUri(x.getAttribute("href")).getPath().split("/")[3];
        return driver.findElements(By.cssSelector(".product-item")).stream()
                .map(x -> Func.toProductListItem(x, () -> toId.apply(x.findElement(By.cssSelector("a.product-item-link")))));
    }

    public ProductListItem getAnyProduct() {
        return getProducts().findAny().get();
    }

    public ModalWindow getModalWindow() {
        ModalWindow modal = new ModalWindow(driver.findElement(By.className("modal-popup")), this.driver);
        modal.waitUntilVisible();
        return modal;
    }

    public ProductListItem addAnyProductToCart() {
        ProductListItem product = getAnyProduct();
        product.addToCart();
    
        ModalWindow modal = getModalWindow();
        modal.clickPrimary();
    
        return product;
    }
}
