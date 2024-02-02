package pageobjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utilities.ExcelUtils;
import Utilities.Screenshots;

public class UsedCarsPage extends BasePage  {

	public UsedCarsPage(WebDriver driver) throws IOException {
		super(driver);
	}
	
	public Screenshots ss;
	public String xlFile;
	public JavascriptExecutor js;
	
	//Elements
	@FindBy(xpath="//div[@class='row qlc']//div[@class='col-lg-2']")
	public WebElement zigWheelsLogo;
	
	@FindBy(xpath="//div[@class='gsc_thin_scroll']/ul/li/span/input")
	public List<WebElement> checkBoxes;
	
	@FindBy(xpath="//div[@class='gsc_thin_scroll']/ul/li/label")
	public List<WebElement> popularModels;

	//Action Methods
	public void returnToHomePage() {
		zigWheelsLogo.click();
	}
	
	public void displayPopularModels() throws InterruptedException, IOException {
		ss = new Screenshots(driver);
		xlFile = System.getProperty("user.dir")+"\\testData\\zigWheels.xlsx";
		js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,700);", "");
		Thread.sleep(2000);
		ss.ScreenShot("popularModels");
		Thread.sleep(2000);
		for(int i=0;i<popularModels.size();i++) {
			js.executeScript("arguments[0].click();", checkBoxes.get(i));
			Thread.sleep(2000);
			System.out.println(popularModels.get(i).getText());
			ExcelUtils.setCellData(xlFile, "popularModels", i+1, 0,popularModels.get(i).getText());
		}
	}
}
