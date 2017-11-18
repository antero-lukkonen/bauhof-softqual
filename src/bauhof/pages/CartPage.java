package bauhof.pages;

import java.net.URI;

import org.openqa.selenium.WebDriver;

import utils.Func;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver, URI uri) {
        super(driver, Func.toUri(uri + "/checkout/cart/").normalize());
    }
}
