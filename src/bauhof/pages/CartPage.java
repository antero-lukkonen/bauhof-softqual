package bauhof.pages;

import java.net.URI;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Func;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver, URI uri) {
        super(driver, Func.toUri(uri + "/checkout/cart/").normalize());
    }

    public Stream<CartItem> getCartItems() {
        return this.driver.findElements(By.cssSelector("tbody.item")).stream().map(CartPage::toItem);
    }

    private static CartItem toItem(WebElement element) {
        return new CartItem(element.findElement(By.cssSelector(".product-item-name>a")).getText());
    }
}