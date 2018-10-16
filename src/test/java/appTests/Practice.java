package appTests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import messages.MapStore;

public class Practice {
	

	@Test(priority=0)
	public  void LoginTest()
	{
		System.out.println("MyName is testone");
		
		
	}
	@Test(priority=1)
	public  void CreateUser()
	{
		System.out.println("MyName is testtwo");
		
//		throw new SkipException("skip it");
		
	}
	@Test(priority=2)
	public  void CreatePackage()
	{
		System.out.println("MyName is testthree");
	}
	@Test(priority=3)
	public  void AddPeople()
	{
		System.out.println("MyName is testfour");
		
	}
	@Test(priority=4)
	public static void LogOutTest()
	{
		System.out.println("MyName is testfive");
		Assert.assertTrue(false);
	}
	

}
