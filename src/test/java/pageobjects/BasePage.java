package pageobjects;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	public WebDriver driver;
	public Properties p;
	public BasePage(WebDriver driver) throws IOException {
	this.driver=driver;
	PageFactory.initElements(driver,this);
	FileReader file= new FileReader(".//src//test//resources//config.properties");
	p=new Properties();
	p.load(file);
}
	public void highlightElement(WebElement element) {
		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
		}catch(Exception e) {}
	}

}
