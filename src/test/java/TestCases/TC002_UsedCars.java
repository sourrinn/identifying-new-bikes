package TestCases;

import java.io.IOException;

import org.testng.annotations.Test;

import pageobjects.UsedCarsPage;

public class TC002_UsedCars extends TC001_HomePage {
	
	public UsedCarsPage up;
	
	@Test(priority=3,groups= {"master","regression"})
	void test3() throws IOException, InterruptedException {
		up = new UsedCarsPage(driver);
		up.displayPopularModels();
	}
	
	@Test(priority=4, dependsOnMethods= {"test3"},groups= {"master","regression"})
	void test4() throws IOException {
		up = new UsedCarsPage(driver);
		up.returnToHomePage();

	}

}
