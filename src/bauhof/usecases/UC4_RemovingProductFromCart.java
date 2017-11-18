package bauhof.usecases;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import bauhof.pages.CartItem;
import bauhof.pages.CartPage;
import bauhof.pages.SubcategoryPage;

@TestInstance(Lifecycle.PER_CLASS)
public class UC4_RemovingProductFromCart extends BaseTemplate {

    private String category = "aiakaubad";
    private String subCategory = "aiakatted";

    @Test
    public void cartItemContainsRemoveButton() {
        addAnyProductToCart();

        CartPage page = openCartPage();

        Stream<CartItem> items = page.getCartItems();
        CartItem item = items.findAny().get();

        assertThat(item.getRemoveButton(), is(not(nullValue())));
    }

    @Test
    public void removeButtonRemovesItemFromCart() {
        addAnyProductToCart();

        CartPage page = openCartPage();

        Stream<CartItem> items = page.getCartItems();
        CartItem item = items.findAny().get();
        item.getRemoveButton().click();

        CartItem removed = page.getCartItems().filter(CartPage.itemByName(item.getName())).findAny().orElse(null);

        assertThat(removed, is(nullValue()));
    }

    private void addAnyProductToCart() {
        openSubcategoryPage().addAnyProductToCart();
    }

    private SubcategoryPage openSubcategoryPage() {
        return (SubcategoryPage) new SubcategoryPage(driver, baseUri, category, subCategory).navigateTo();
    }

    private CartPage openCartPage() {
        return (CartPage) new CartPage(driver, baseUri).navigateTo();
    }
}