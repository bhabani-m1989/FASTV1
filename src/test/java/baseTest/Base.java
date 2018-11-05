package baseTest;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	
	WebDriver driver;
	
	
	public  WebDriver chooseBrowser()
	{
		String bname=utils.UserData.bname;
		if(bname.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","/Users/bhabanimishra/Documents/workspace/Practice/chromedriver");
		    driver= new ChromeDriver();
			return driver;
		}
		
		else if(bname.equalsIgnoreCase("chrome-headless"))
			{
				System.setProperty("webdriver.chrome.driver","/Users/bhabanimishra/Documents/workspace/Practice/chromedriver");
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
