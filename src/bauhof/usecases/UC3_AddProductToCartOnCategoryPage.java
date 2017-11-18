package bauhof.usecases;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import bauhof.pages.CartPage;
import bauhof.pages.ModalWindow;
import bauhof.pages.ProductListItem;
import bauhof.pages.SubcategoryPage;

@TestInstance(Lifecycle.PER_CLASS)
public class UC3_AddProductToCartOnCategoryPage extends BaseTemplate {

    private String category = "aiakaubad";
    private String subCategory = "aiakatted";

    @Test
    public void listItemContainsAddToCartButton() throws UnsupportedEncodingException {
        ProductListItem item = getAnyProduct();

        assertThat(item.getAddToCartButton(), is(not(nullValue())));
    }

    @Test
    public void clickingOnAddToCartButtonOpensModalWindow() throws UnsupportedEncodingException, URISyntaxException {
        SubcategoryPage page = openSubcategoryPage();
        ProductListItem product = page.getAnyProduct();

        product.addToCart();

        assertThat(page.getModalWindow(), is(not(nullValue())));
        assertThat(driver.getCurrentUrl(), isSubcategoryPageUrl());
    }

    @Test
    public void clickingOnPrimaryButtonInModalWindowAddsProductToCart() {
        SubcategoryPage page = openSubcategoryPage();
        ProductListItem product = page.getAnyProduct();
        product.addToCart();

        ModalWindow modal = page.getModalWindow();
        modal.clickPrimary();

        assertThat(driver.getCurrentUrl(), isCartUrl());
    }

    private Matcher<String> isCartUrl() {
        return is(new CartPage(driver, baseUri).getUri().toString());
    }

    private Matcher<String> isSubcategoryPageUrl() {
        return startsWith(new SubcategoryPage(driver, baseUri).getUriFor(category, subCategory).toString());
    }

    private ProductListItem getAnyProduct() {
        return openSubcategoryPage().getAnyProduct();
    }

    private SubcategoryPage openSubcategoryPage() {
        return (SubcategoryPage) new SubcategoryPage(driver, baseUri, category, subCategory).navigateTo();
    }
}