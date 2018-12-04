package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.UserData;



public class Login {
	
	WebDriver driver;
	
	public Login(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	By email= By.xpath("//form//input[@id='email']");
	By password=By.xpath("//input[@id='password']");
	By submit=By.xpath("//input[@name='commit']");
	
	public void socioLogin()
	{
		driver.findElement(email).sendKeys(utils.UserData.emailId);
		driver.findElement(password).sendKeys(UserData.password);
		driver.findElement(submit).click();
	}

}
