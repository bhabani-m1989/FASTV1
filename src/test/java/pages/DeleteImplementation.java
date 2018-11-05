package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeleteImplementation 
{
	
	WebDriver driver;
	
	public DeleteImplementation(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By del_btn= By.xpath("//div[contains(@class,'home-implementation-info')]//button[text()='Delete']");
	By del_popup= By.xpath("//div[@class='popup-content']//div/div//button//span[text()='Continue']");
	
	public void deleteImplementation()
	{
		driver.findElement(del_btn).click();
		try
		{
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		driver.findElement(del_popup).click();
		
	}
	

}
