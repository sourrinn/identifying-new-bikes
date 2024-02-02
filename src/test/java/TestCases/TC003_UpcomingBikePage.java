package TestCases;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageobjects.UpcomingBikes;
//@Listeners(Utilities.ExtentReportManager.class)
public class TC003_UpcomingBikePage extends TC002_UsedCars {
	
	public UpcomingBikes up;
	
	@Test(priority=6,groups= {"master","regression"})
	void test6() throws IOException, InterruptedException {
		up=new UpcomingBikes(driver);
		up.filtermanufacturer();
	}
	@Test(priority=7,groups= {"master","regression"})
	void test7() throws IOException, InterruptedException {
		up=new UpcomingBikes(driver);
		up.scrollToViewMore();
		up.displaybikedetails();
	}
	@Test(priority=8, dependsOnMethods= {"test7"},groups= {"master","regression"})
	void test8() throws IOException {
		up=new UpcomingBikes(driver);
		up.returntohomepage();
	}


}
