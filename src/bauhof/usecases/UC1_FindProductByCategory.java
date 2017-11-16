package bauhof.usecases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import bauhof.pages.HomePage;
import bauhof.usecases.BaseTemplate;

@TestInstance(Lifecycle.PER_CLASS)
public class UC1_FindProductByCategory extends BaseTemplate {

	@Test
	public void kkkk() {
		HomePage page = (HomePage) new HomePage(driver, baseUri).navigateTo();
		page.openMainMenu();

	}
}