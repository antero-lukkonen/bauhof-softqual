package bauhof.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Func;

public class ModalWindow {

    private WebElement root;
    private WebDriver driver;

    public ModalWindow(WebElement root, WebDriver driver) {
        this.root = root;
        this.driver = driver;
    }

    public void waitUntilVisible() {
        Func.waitForVisible(this.root, this.driver);
    }

    public void clickPrimary() {
        WebElement primaryButton = this.root.findElement(By.className("primary"));
        Func.waitForVisible(primaryButton, driver);
        primaryButton.click();
    }
}
