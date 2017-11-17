package bauhof.pages;

import java.net.URI;

import org.openqa.selenium.WebDriver;

import utils.Func;

public class SubcategoryPage extends BasePage {

    public SubcategoryPage(WebDriver driver, URI uri) {
        super(driver, uri);
    }

    public URI getUriFor(String category, String subCategory) {
        return Func.toUri(this.getUri() + "/" + category + "/" + subCategory)
                .normalize();
    }

}
