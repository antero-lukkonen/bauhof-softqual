package bauhof.usecases;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import bauhof.pages.HomePage;
import io.github.bonigarcia.wdm.ChromeDriverManager;

@TestInstance(Lifecycle.PER_CLASS)
public class UC1_FindProductByCategory {	
	private WebDriver driver;
	private String baseUri = "https://www.bauhof.ee";
	
	@Test
	public void kkkk() {
		HomePage page = (HomePage) new HomePage(driver, baseUri).go().maximize();
		page.openMainMenu();

	}	
	
	@BeforeAll
	public void beforeAllTests() {
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