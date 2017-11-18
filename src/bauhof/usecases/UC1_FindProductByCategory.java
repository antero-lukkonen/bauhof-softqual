package bauhof.usecases;

import static org.hamcrest.CoreMatchers.endsWith;
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

import bauhof.pages.CategoryPage;
import bauhof.pages.ProductListItem;
import bauhof.pages.ProductPage;
import bauhof.pages.SubcategoryPage;

@TestInstance(Lifecycle.PER_CLASS)
public class UC1_FindProductByCategory extends BaseTemplate {

    private String category = "aiakaubad";
    private String subCategory = "aiakatted";

    @Test
    public void categoryPageContainsSubcategories() {
        CategoryPage page = openCategory(category);

        assertThat(page.getSubCategories().findAny().get(), is(not(nullValue())));
    }

    @Test
    public void clickingOnSubCategoryOpensSubcategoryPage() {
        SubcategoryPage subCategoryPage = openCategory(category).openSubcategory(subCategory);

        assertThat(driver.getCurrentUrl(), is(urlFor(subCategoryPage)));
    }

    @Test
    public void subCategoryPageContainsProductList() {
        SubcategoryPage page = (SubcategoryPage) new SubcategoryPage(driver, baseUri, category, subCategory).navigateTo();

        assertThat(page.getAnyProduct(), is(not(nullValue())));
    }

    @Test
    public void listItemContainsPriceInEuro() throws UnsupportedEncodingException {
        ProductListItem item = getAnyProduct();

        assertThat(item.getPrice(), endsWith("€"));
    }

    @Test
    public void listItemContainsAddToCartButton() throws UnsupportedEncodingException {
        ProductListItem item = getAnyProduct();

        assertThat(item.getAddToCartButton(), is(not(nullValue())));
    }

    @Test
    public void listItemContainsProductName() {
        ProductListItem item = getAnyProduct();

        assertThat(item.getName(), is(not(nullValue())));
    }

    @Test
    public void clickingOnProductNameOpensProductPage() {
        ProductListItem item = getAnyProduct();

        item.getOpenButton().click();

        assertThat(driver.getCurrentUrl(), is(urlFor(item)));
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

    private CategoryPage openCategory(String cat) {
        CategoryPage page = (CategoryPage) new CategoryPage(driver, baseUri, cat).navigateTo();
        page.maximize();
        return page;
    }

    private String urlFor(ProductListItem item) {
        return new ProductPage(driver, baseUri).getUriFor(category, subCategory, item.getId()).toString();
    }

    private String urlFor(SubcategoryPage subPage) {
        return subPage.getUriFor(category, subCategory).toString();
    }

}