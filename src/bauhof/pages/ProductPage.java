package bauhof.pages;

import java.net.URI;

import org.openqa.selenium.WebDriver;

import utils.Func;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver, URI uri) {
        super(driver, uri);
    }

    public URI getUriFor(String category, String subCategory, String id) {
        return getUriFor(this.getUri(), category, subCategory, id);
    }

    private static URI getUriFor(URI uri, String category, String subCategory,
            String id) {
        return Func.toUri(uri + "/" + category + "/" + subCategory + "/" + id)
                .normalize();
    }

}
