package bauhof.usecases;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import bauhof.pages.CartItem;
import bauhof.pages.CartPage;
import bauhof.pages.Clickable;
import bauhof.pages.SubcategoryPage;

@TestInstance(Lifecycle.PER_CLASS)
public class UC9_BuyingMultipleUnitsOfSameProduct extends BaseTemplate {
    private String category = "aiakaubad";
    private String subCategory = "aiakatted";

    @Test
    public void cartItemContainsAddOneMoreButton() {
        addAnyProductToCart();

        CartPage page = getCartPage();

        Stream<CartItem> items = page.getCartItems();
        CartItem item = items.findAny().get();

        assertThat(item.getAddOneMoreButton(), is(not(nullValue())));
    }

    @Test
    public void clickingOnAddOneMoreButtonIncreasesQuantity() {
        addAnyProductToCart();

        CartPage page = getCartPage();

        Stream<CartItem> items = page.getCartItems();
        CartItem item = items.findAny().get();

        int qty = item.getQty();

        Clickable addOneMoreButton = item.getAddOneMoreButton();
        addOneMoreButton.click();

        int qtyAfterIncrease = page.getCartItems().filter(CartPage.itemByName(item.getName())).findFirst().get().getQty();

        assertThat(qtyAfterIncrease, is(greaterThan(qty)));
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
