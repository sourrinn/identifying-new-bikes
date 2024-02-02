package Utilities;

import java.io.File;
import java.io.IOException;
import TestBase.BaseClass;
import pageobjects.BasePage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Screenshots extends BasePage{
	public Screenshots(WebDriver driver) throws IOException {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	//Whole Page Screenshot
	public String ScreenShot(String path) throws IOException {
		String p = System.getProperty("user.dir")+"\\screenShots\\";
		p+=path+".png";
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(p);
		FileUtils.copyFile(src, trg);
		return p;
	}
	//Element wise Screenshot
	public void ScreenShotElementwise(String path, WebElement e) throws IOException {
		String p = System.getProperty("user.dir")+"\\screenShots\\";
		p+=path;
		File src = e.getScreenshotAs(OutputType.FILE);
		File trg = new File(p);
		FileUtils.copyFile(src, trg);
	}
}
