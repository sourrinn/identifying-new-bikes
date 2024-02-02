package TestBase;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class BaseClass {
	public static WebDriver driver;
	public Properties p;
	public Logger log;
	public int choice;
	@BeforeTest(groups= {"master"}) 
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws InterruptedException, IOException {
		FileReader file= new FileReader(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		//Logger
		log=LogManager.getLogger(this.getClass());
		
	    if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
	 	{	
	    	DesiredCapabilities capabilities=new DesiredCapabilities();
	    	//os
	    	if(os.equalsIgnoreCase("windows"))
	    	{
	    		capabilities.setPlatform(Platform.WIN11);
	    	}
	    	else if(os.equalsIgnoreCase("mac"))
	    	{
	    		capabilities.setPlatform(Platform.MAC);
	    	}
	    	else
	    	{
	    		System.out.println("No matching os..");
	    		return;
	    	}
	    	//browser
	    	switch(br.toLowerCase())
	    	{
	    	case "chrome" : capabilities.setBrowserName("chrome"); break;
	    	case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
	    	default: System.out.println("No matching browser.."); return;
	    	}
	    	driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
	    }
	    
	    //If execution_env is local then run in local system
	    else if(p.getProperty("execution_env").equalsIgnoreCase("local"))
	    {
	    	//launching browser based on condition - locally
	    	switch(br.toLowerCase())
	    	{
	    	case "chrome": driver=new ChromeDriver(); break;
	    	case "edge": driver=new EdgeDriver(); break;
	    	default: System.out.println("No matching browser..");
					return;
	    	}
	    }
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		try {
			driver.manage().window().maximize();
			driver.get(p.getProperty("altUrl"));
		}catch(Exception e) {}
		driver.get(p.getProperty("Url"));
		//driver.manage().window().maximize();
	}
	@AfterTest(groups= {"master"})        
	public void teardown() {
		driver.quit();
	}
}
