package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DeleteImplementation 
{
	
	WebDriver driver;
	
	public DeleteImplementation(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By del_btn= By.xpath("//div[contains(@class,'home-implementation-info')]//button[text()='Delete']");
	By del_popup= By.xpath("//div[@class='popup-content']//div/div//button//span[text()='Continue']");
	By implementationText=By.xpath("//div[@class='home-implementation-wrap']//span[contains(text(),'A Clinic-Based AIDS')]");
	
	public void deleteImplementation()
	{
		List<WebElement> del=driver.findElements(del_btn);
		System.out.println(del.size());
		for(WebElement el:del)
		{
	//	driver.findElement(del_btn).click();
		el.click();
		try
		{
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		driver.findElement(del_popup).click();
	   List<WebElement> textmsg=driver.findElements(implementationText);
	   WebDriverWait wt= new WebDriverWait(driver,15);
	   wt.until(ExpectedConditions.visibilityOfAllElements(textmsg));
		
	}
	}
	

}
