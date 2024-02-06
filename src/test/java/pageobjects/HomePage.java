package pageobjects;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import Utilities.ExcelUtils;
import Utilities.Screenshots;

public class HomePage extends BasePage {
    //constructor of homepage 
	public HomePage(WebDriver driver) throws IOException {
		super(driver);
	}
	
	//declaring variables
	public Screenshots ss;
	public Actions act;
	public String xlFile;

	public String title = driver.getTitle(); //storing the title of the webpage in the variable
	public String homePage_WindowId = driver.getWindowHandle(); //window id of the page stored in the variable
	
	//Elements
	@FindBy(xpath="//div[@class='col-lg-12 pl-0']/ul/li[3]/a")
	public WebElement newBikes;
	
	@FindBy(xpath="//ul[@class='h-d-nav fnt-16 ']/li[3]/ul/li[5]/span")
	public WebElement upcomingBikes;
	
	@FindBy(xpath="//div[@class='col-lg-12 pl-0']/ul/li[7]/a")
	public WebElement usedCars;
	
	@FindBy(xpath="//div[@class='h-dd-r']/ul/li/span")
	public List<WebElement> usedcar_City;
	
	@FindBy(xpath="//div[@class='h-dd-r']/ul/li[4]/span")
	public WebElement chennai_City;
	
	@FindBy(xpath="//div[@id='forum_login_title_lg']")
	public WebElement loginSignUp;
	
	@FindBy(xpath="//div[contains(@class,'googleSignIn')]")
	public WebElement googleBtn;
	
	@FindBy(xpath="//div[@class='aCsJod oJeWuf']/div/div[1]/input[@type='email']")
	public WebElement email;
	
	@FindBy(xpath="//span[@class='VfPpkd-vQzf8d' and text()='Next']")
	public WebElement nextBtn;
	
	@FindBy(xpath="//div[@class='o6cuMc Jj6Lae']")
	public WebElement warning;
	
	
	//Action methods
	
	//validating title
	public void titleValidation() throws IOException {
		ss = new Screenshots(driver);
		ss.ScreenShot("HomePage");
		Assert.assertEquals(title, "New Cars & Bikes, Prices, News, Reviews, Buy & Sell Used Cars - ZigWheels.com");
	}
	
	//clicking on login, then clicking on google button, after that need to switch in child window
	public void login() throws InterruptedException, IOException {
		ss = new Screenshots(driver);
		highlightElement(loginSignUp);
		loginSignUp.click();
		Thread.sleep(2000);
		ss.ScreenShot("LoginSignUp");
		googleBtn.click();
		Set<String> windowIds = driver.getWindowHandles();
		for(String s: windowIds) {
			if(!s.equalsIgnoreCase(homePage_WindowId)) {
				driver.switchTo().window(s);  //switching the window
				break;
			}
		}
		email.sendKeys(p.getProperty("emailid"));
		nextBtn.click();
		Thread.sleep(2000);
		ss.ScreenShot("googleSignIn");
	}
	
	//after providing the invalid mail, capturing the error message
	public void captureErrorMessage() throws IOException {
		ss = new Screenshots(driver);
		xlFile = System.getProperty("user.dir")+"\\testData\\zigWheels.xlsx";
		highlightElement(warning);
		System.out.println(warning.getText());
		ExcelUtils.setCellData(xlFile, "warningMessage", 1, 0,warning.getText());  //writing the error message in excel
	}
	
	//hover to new bikes, then clickicng on the upcoming bikes option
	public void clickingOnUpcomingBikes() throws IOException {
		ss = new Screenshots(driver);
		act = new Actions(driver);
		act.moveToElement(newBikes).perform();
		highlightElement(upcomingBikes); //highlighting the upcoming bikes option
		ss.ScreenShot("upComingBikes");
		upcomingBikes.click();
	}
	
	//hover to usedcars, then clicking on the Chennai
	public void clickingOnCityForUsedCars() throws IOException, InterruptedException {
		ss = new Screenshots(driver);
		act = new Actions(driver);
		act.moveToElement(usedCars).perform();
		for(WebElement e:usedcar_City) {
			if(e.getText().equalsIgnoreCase(p.getProperty("city"))) {	
				Thread.sleep(3000);
				highlightElement(chennai_City);  //highlighting the chennai city
				ss.ScreenShot("clickingOnChennai");
				e.click();
				break;
			}
		}
	}
}

