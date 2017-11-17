package bauhof.pages;

import java.net.URI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    private URI uri;

    public BasePage(WebDriver driver, URI uri) {
        this.uri = uri.normalize();
        this.driver = driver;
    }

    public BasePage navigateTo() {
        driver.get(this.uri.toString());
        return this;
    }

    public BasePage maximize() {
        driver.manage().window().maximize();
        return this;
    }

    public URI getUri() {
        return uri.normalize();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void waitForRefreshAfter(Runnable action) {
        WebElement element = driver.findElement(By.tagName("body"));

        action.run();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.stalenessOf(element));
    }
}