package baseTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	
	
	public static WebDriver chooseBrowser()
	{
		String bname=utils.UserData.bname;
		if(bname.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","/Users/bhabanimishra/Documents/workspace/Practice/chromedriver");
			WebDriver driver= new ChromeDriver();
			return driver;
		}
		else
		{
			return null;
		}
	}

}
