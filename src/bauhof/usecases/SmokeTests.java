package bauhof.usecases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import bauhof.pages.BasePage;
import bauhof.usecases.BaseTemplate;

@TestInstance(Lifecycle.PER_CLASS)
public class SmokeTests extends BaseTemplate {

	@Test
	public void verifyPageIsUp() {
		new BasePage(driver, baseUri).navigateTo();
	}

}
