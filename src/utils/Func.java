package utils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.function.Supplier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Optional;

import bauhof.pages.Clickable;
import bauhof.pages.ProductListItem;

public class Func {
    public static URI toUri(String x) {
        try {
            return new URI(x);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toUtf8UriPart(String searchedItem) {
        try {
            return URLEncoder.encode(searchedItem, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static ProductListItem toProductListItem(WebElement x, Supplier<String> getId) {
        WebElement btnToCart = x.findElement(By.cssSelector(".tocart"));
        WebElement link = x.findElement(By.cssSelector("a.product-item-link"));
        return new ProductListItem(getId.get(), link.getText(), x.findElement(By.className("price")).getText(), toClickable(btnToCart), toClickable(link));
    }

    public static Clickable toClickable(WebElement element) {
        return Optional.of(element).transform(y -> new Clickable(y::click, null)).get();
    }

    public static void waitForVisible(WebElement x, WebDriver driver) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(x));
    }
}