package baseTest;


import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	
	WebDriver driver;
	
	
	public  WebDriver chooseBrowser()
	{
		File f=new File(System.getProperty("user.dir"));
		String bname=utils.UserData.bname;
		if(bname.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",f+"/chromedriver");
		    driver= new ChromeDriver();
			return driver;
		}
		
		else if(bname.equalsIgnoreCase("chrome-headless"))
			{
				System.setProperty("webdriver.chrome.driver",f+"/chromedriver");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless", "--disable-gpu","--ignore-certificate-errors","window-size=1440,900");
				
				
				driver= new ChromeDriver(options);
				return driver;
			}
		
		else
		{
			return null;
		}
	}

}
