package TestCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import Utilities.Screenshots;
import pageobjects.HomePage;

public class TC001_HomePage extends BaseClass {
	
	public HomePage hp;
	
	@Test(priority=1,groups= {"master","regression"})
	void test1() throws IOException {
		hp = new HomePage(driver);
		hp.titleValidation();
	}
	
	@Test(priority=2, dependsOnMethods= {"test1"},groups= {"master","regression"})
	void test2() throws IOException, InterruptedException {
		hp = new HomePage(driver);
		hp.clickingOnCityForUsedCars();
	}
	
	@Test(priority=5,groups= {"master","regression"})
	void Test5() throws IOException {
		hp = new HomePage(driver);
		hp.clickingOnUpcomingBikes();
	}
	
	@Test(priority=9,groups= {"master","sanity"})
	void Test9() throws IOException, InterruptedException {
		hp = new HomePage(driver);
		hp.login();
		hp.captureErrorMessage();
	}
}
