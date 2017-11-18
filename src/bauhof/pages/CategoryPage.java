package bauhof.pages;

import java.net.URI;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Func;

public class CategoryPage extends BasePage {

    public CategoryPage(WebDriver driver, URI uri, String category) {
        super(driver, Func.toUri(uri + "/" + category));
    }

    public Stream<Clickable> getSubCategories() {
        Function<WebElement, String> toCategoryName = x -> Func.toUri(x.getAttribute("href")).getPath().split("/")[2];

        Function<? super WebElement, ? extends Clickable> toClickable = x -> {
            return new Clickable(() -> {
                Func.waitForVisible(x, driver);
                x.click();
            }, toCategoryName.apply(x));
        };

        return driver.findElements(By.cssSelector(".sub_categories_no_images__item a")).stream().map(toClickable);
    }

    public SubcategoryPage openSubcategory(String subCategory) {
        Runnable throwNotFound = () -> {
            throw new RuntimeException("No category found. Categories: " + this.getSubCategories().map(x -> x.getName()).collect(Collectors.joining(", ")));
        };

        Predicate<? super Clickable> predicate = x -> x.getName().equals(subCategory);

        this.getSubCategories().filter(predicate).findFirst().ifPresentOrElse(x -> x.click(), throwNotFound);

        URI uri = this.getUri();
        return new SubcategoryPage(driver, Func.toUri((uri.getScheme() + "://" + uri.getHost())));
    }
}
