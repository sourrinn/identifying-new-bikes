package TestCases;

import java.io.IOException;

import org.testng.annotations.Test;

import pageobjects.UsedCarsPage;

public class TC002_UsedCars extends TC001_HomePage {
	//variable of usedCarsPage
	public UsedCarsPage up;
	
	//test3()--> priting the popular models in console and writing the name of models in excel
	@Test(priority=3,groups= {"master","regression"})
	void test3() throws IOException, InterruptedException {
		up = new UsedCarsPage(driver); //object of usedcarspage
		up.displayPopularModels();
		log.info("---popular models are displayed---");
	}
	
	//test4()--> returing to homepage
	@Test(priority=4, dependsOnMethods= {"test3"},groups= {"master","regression"})
	void test4() throws IOException {
		up = new UsedCarsPage(driver);  //object of usedcarspage
		up.returnToHomePage();
		log.info("---returned homepage sucessfully---");
	}

}
