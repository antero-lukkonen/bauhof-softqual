package bauhof.usecases;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;

import bauhof.pages.BasePage;

@TestInstance(Lifecycle.PER_CLASS)
public class SmokeTests extends BaseTemplate {

    @Test
    public void verifyPageIsUp() {
        new BasePage(driver, baseUri).navigateTo();
    }

    @Test
    public void verifyCriticalUxElementsArePresent() {

        @SuppressWarnings("serial")
        HashMap<String, String> elements = new HashMap<String, String>() {
            {
                put("AddToCartButton", "button.tocart");
                put("ProductItemLink", "a.product-item-link");
                put("SearchProductsEntryField", "input#search.input-text");
                put("SearchButton", "button.action.search");
                put("ShowCartButton", "a.action.showcart");
            }
        };
        new BasePage(driver, baseUri).navigateTo();
        elements.values()
                .forEach(value -> driver.findElement(By.cssSelector(value)));

    }
}
