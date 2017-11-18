package bauhof.usecases;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

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
    public void clickingOnAddToCartButtonAddsNewItemToCart() throws UnsupportedEncodingException, URISyntaxException {

        getAnyProduct().getAddToCartButton().click();

        assertThat(driver.getCurrentUrl(), startsWith(new SubcategoryPage(driver, baseUri).getUriFor(category, subCategory).toString()));
    }

    private ProductListItem getAnyProduct() {
        return openSubcategoryPage().getAnyProduct();
    }

    private SubcategoryPage openSubcategoryPage() {
        return (SubcategoryPage) new SubcategoryPage(driver, baseUri, category, subCategory).navigateTo();
    }
}