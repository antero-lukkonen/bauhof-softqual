package bauhof.pages;

import java.net.URI;
import java.util.function.Predicate;
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
        Func.waitForVisible(this.driver.findElement(By.cssSelector("table.cart")), driver);
        return this.driver.findElements(By.cssSelector("tbody.item")).stream().map(CartPage::toItem);
    }

    private static CartItem toItem(WebElement element) {
        return new CartItem(element.findElement(By.cssSelector(".product-item-name>a")).getText(),
                Func.toClickable(element.findElement(By.className("action-delete"))), Func.toClickable(element.findElement(By.cssSelector(".qty .plus"))),
                Integer.parseInt(element.findElement(By.cssSelector("input.qty")).getAttribute("value")));
    }

    public static Predicate<? super CartItem> itemByName(String name) {
        return (Predicate<? super CartItem>) x -> x.getName().toLowerCase().equals(name.toLowerCase());
    }
}