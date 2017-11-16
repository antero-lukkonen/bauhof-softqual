package bauhof.usecases;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;

import bauhof.pages.BaseContants;

/**
 * Superclass for attributes and methods that every test class should have
 */
public class BaseTemplate {

	protected WebDriver driver;
	protected String baseUri;

	@Rule
	public TestRule rule = new TestWatcher() {

		@Override
		protected void failed(Throwable e, Description description) {
			System.out.println("Failed " + description);
		}

		@Override
		protected void finished(Description description) {
			System.out.println("Finished " + description + "\n");
		}

		@Override
		protected void starting(Description description) {
			System.out.println("Starting " + description);
		}

		@Override
		protected void succeeded(Description description) {
			System.out.println("Passed " + description);
		}
	};

	@BeforeAll
	public void beforeAllTests() {
		baseUri = BaseContants.BASE_URI;
		ChromeDriverManager.getInstance().setup();
	}

	@BeforeEach
	public void beforeEachTest() {
		driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEachTest() {
		driver.close();
	}
}
