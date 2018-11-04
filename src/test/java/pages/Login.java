package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



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
		driver.findElement(email).sendKeys("walin10@gmail.com");
		driver.findElement(password).sendKeys("walindia1234");
		driver.findElement(submit).click();
	}

}
