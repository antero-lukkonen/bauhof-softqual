package bauhof.usecases;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import bauhof.pages.CartPage;
import bauhof.pages.CartSummary;
import bauhof.pages.SubcategoryPage;

@TestInstance(Lifecycle.PER_CLASS)
public class UC10_PriceInfoWhenFinalizingOrder extends BaseTemplate {
    private String category = "aiakaubad";
    private String subCategory = "aiakatted";

    @Test
    public void summarySectionIsDisplayed() {
        addAnyProductToCart();

        CartPage page = getCartPage();

        CartSummary summary = page.getSummary();

        assertThat(summary, is(not(nullValue())));
    }

    @Test
    public void summaryContainsPriceWithoutVat() {
        addAnyProductToCart();

        CartPage page = getCartPage();

        CartSummary summary = page.getSummary();

        assertThat(summary.getPrice(), endsWith("€"));
    }

    @Test
    public void summaryContainsPriceWithVat() {
        addAnyProductToCart();

        CartPage page = getCartPage();

        CartSummary summary = page.getSummary();

        assertThat(summary.getPriceWithVat(), endsWith("€"));
    }

    @Test
    public void summaryContainsVat() {
        addAnyProductToCart();

        CartPage page = getCartPage();

        CartSummary summary = page.getSummary();

        assertThat(summary.getVat(), endsWith("€"));
    }

    @Test
    public void summaryContainsTotalPrice() {
        addAnyProductToCart();

        CartPage page = getCartPage();

        CartSummary summary = page.getSummary();

        assertThat(summary.getTotal(), endsWith("€"));
    }

    private void addAnyProductToCart() {
        openSubcategoryPage().addAnyProductToCart();
    }

    private SubcategoryPage openSubcategoryPage() {
        return (SubcategoryPage) new SubcategoryPage(driver, baseUri, category, subCategory).navigateTo();
    }

    private CartPage getCartPage() {
        return (CartPage) new CartPage(driver, baseUri);
    }
}