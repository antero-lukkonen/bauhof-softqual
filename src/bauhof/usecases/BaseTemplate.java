package bauhof.usecases;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import bauhof.pages.BaseContants;
import io.github.bonigarcia.wdm.ChromeDriverManager;

/**
 * Superclass for attributes and methods that every test class should have
 */
public class BaseTemplate {

    protected WebDriver driver;
    protected URI baseUri;

    @BeforeAll
    public void beforeAllTests() throws URISyntaxException {
        baseUri = new URI(BaseContants.BASE_URI);
        ChromeDriverManager.getInstance().setup();
    }

    @BeforeEach
    public void beforeEachTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void afterEachTest() {
        driver.close();
    }
}
