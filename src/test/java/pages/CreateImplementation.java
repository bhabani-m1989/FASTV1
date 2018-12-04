package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateImplementation {
	
	WebDriver driver;
	
	public CreateImplementation(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By getStarted= By.xpath("//button[text() = 'Get Started']");
	By clinicalPopup=By.xpath("//span[text()='CLINICAL']");
	By createNew= By.xpath("//span[text()='Create New']");
	By setup= By.xpath("//h2[text()='Setup']");
	
	//Objects for Filling the data in Implementation page
	
	By name=By.xpath("//form//div//div//input[@placeholder='NAME'][@type='text']");
	By siteName= By.xpath(("//form//div//div//input[@placeholder='SITE NAME'][@type='text']"));
	By nickName=By.xpath("//form//div//div//input[@formcontrolname='nick_name'][@type='text']");
	
	
	//Filling dates in outcome block
	
	By check= By.xpath("//div[@id='outcome-block']//div//label//span");
	By datePicker= By.xpath("//div[@id='outcome-block']//span[contains(@class,'date-picker-wrap')]//button");
	By today=By.xpath("//div[@id='outcome-block']//table[1]//td[2]//button");
	
	//Submit BUtton
	
	By submit=By.xpath("//form//button[text()='SUBMIT']");
	
	//DashBoard
	
	By dashBoard=By.xpath("//div[contains(@class,'header-links-wrapper')]//a//span[text()='Dashboard']");
	
	//NewImplementation button in home page
	
	By newImplementationHome=By.xpath("//button//span[text()='New Implementation']");
	
	public void chooseImplementationtypes()
	{
		List<WebElement> elm = driver.findElements(getStarted);
		if(elm.size()!=0)
		{
			clickCreateNewImplementation();
		}
		else
		{
			clicknewImplementationHome();
		}
	}
	
	public void clicknewImplementationHome()
	{
		driver.findElement(newImplementationHome).click();
		try{
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		driver.findElement(clinicalPopup).click();
		driver.findElement(createNew).click();
		
	}
	
	public void clickCreateNewImplementation()
	{
		Actions act = new Actions(driver);
		WebElement elm = driver.findElement(getStarted);
		act.moveToElement(elm).perform();
		driver.findElement(getStarted).click();
		driver.findElement(clinicalPopup).click();
		driver.findElement(createNew).click();
		
	}
	
	public void fillImplementation()
	{
		WebDriverWait wt= new WebDriverWait(driver,20);
        boolean b=	wt.until(ExpectedConditions.visibilityOfElementLocated(setup)).isDisplayed();
        System.out.println(b);
        
       driver.findElement(name).clear();
       try
       {
       Thread.sleep(1000);
       }
       catch(Exception e)
       {
    	   e.printStackTrace();
    	   e.getMessage();
    	   e.toString();
       }
       driver.findElement(name).sendKeys("testname");
       
       driver.findElement(siteName).clear();
       driver.findElement(siteName).sendKeys("test site");
       
       driver.findElement(nickName).clear();
       driver.findElement(nickName).sendKeys("test nick name");
       
       ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,180)");
       
       List<WebElement> checkBox= driver.findElements(check);
       
       for(WebElement elm:checkBox)
	   {
		   elm.click();
		   try
		   {
		   Thread.sleep(500);
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
	   }
       
       List<WebElement> dates= driver.findElements(datePicker);
       for(WebElement elm:dates)
       {
    	  elm.click();
    	  try
    	  {
      	 Thread.sleep(1200);
         }
    	  catch(Exception e)
    	  {
    		  e.printStackTrace();
    	  }
    	  
    	  driver.findElement(today).click();
    	  
       }
       
       driver.findElement(submit).click();
       try
 	  {
   	 Thread.sleep(4000);
      }
 	  catch(Exception e)
 	  {
 		  e.printStackTrace();
 	  }
       WebDriverWait wt1= new WebDriverWait(driver,20);
       boolean b1=	wt1.until(ExpectedConditions.elementToBeClickable(dashBoard)).isDisplayed();
       driver.findElement(dashBoard).click();
       
       
	}

}
