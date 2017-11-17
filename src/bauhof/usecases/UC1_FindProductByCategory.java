package bauhof.usecases;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import bauhof.pages.CategoryPage;
import bauhof.pages.Clickable;
import bauhof.pages.SubcategoryPage;

@TestInstance(Lifecycle.PER_CLASS)
public class UC1_FindProductByCategory extends BaseTemplate {

    private String category = "aiakaubad";

    @Test
    public void categoryPageContainsSubcategories() {
        CategoryPage page = openCategory(category);

        assertThat(page.getSubCategories().findAny().get(),
                is(not(nullValue())));
    }

    @Test
    public void clickingOnSubCategoryOpensSubcategoryPage() {
        String subCategory = "aiakatted";

        CategoryPage page = openCategory(category);
        SubcategoryPage subPage = openSubcategory(page, subCategory);

        assertThat(driver.getCurrentUrl(),
                is(subPage.getUriFor(category, subCategory).toString()));
    }

    private SubcategoryPage openSubcategory(CategoryPage page,
            String subCategory) {

        Runnable throwNotFound = () -> {
            throw new RuntimeException("No category found. Categories: "
                    + page.getSubCategories().map(x -> x.getName())
                            .collect(Collectors.joining(", ")));
        };

        Predicate<? super Clickable> predicate = x -> x.getName()
                .equals(subCategory);

        page.getSubCategories().filter(predicate).findFirst()
                .ifPresentOrElse(x -> x.click(), throwNotFound);

        return new SubcategoryPage(driver, baseUri);
    }

    private CategoryPage openCategory(String cat) {
        CategoryPage page = (CategoryPage) new CategoryPage(driver, baseUri,
                cat).navigateTo();
        page.maximize();
        return page;
    }
}