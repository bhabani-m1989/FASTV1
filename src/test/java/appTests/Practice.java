package appTests;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import baseTest.Base;
import pages.CreateImplementation;
import pages.Login;
import utils.CustomLog;

public class Practice extends Base {
	
	WebDriver driver;
	Login login;
	CreateImplementation ci;
	CustomLog log;
	
@BeforeTest
public void init()
{
	driver= Base.chooseBrowser();
	login = new Login(driver);
	ci= new CreateImplementation(driver);
	
	driver.manage().window().fullscreen();
	log.info("Browser opening in full screen");
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	driver.get("http://staging.sociopremium.s3-website-us-east-1.amazonaws.com/");
	log.info("URL entered and the browser is navigated to the page");
}

@Test(priority=0)
public void loginToSocio()
{
	log.info("attempting log in for socio");
	login.socioLogin();
	log.info("successfully loggedin to socio");
}

@Test(priority=1)
public void createNewImplementation()
{
	ci.clickCreateNewImplementation();
	ci.fillImplementation();
}


	

}
