package listeners;

import java.io.File;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import messages.BugExceptions;
import messages.MapStore;
import messages.PTupdates;
import messages.SlackPost;
import utils.CustomLog;
import utils.UserData;


public class ExtentListener implements ITestListener {
	
	MapStore m;
	BugExceptions ex;
	String s="";
	ExtentReports report;
	ExtentTest test;
	ExtentHtmlReporter reporter;
	SlackPost p;
	PTupdates pt;
	CustomLog log;
	
	static int pass_count=0,fail_count=0,skip_count;

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		System.out.println("Starting test-->"+result.getName());
		test= report.createTest(result.getName());
		
		log.startTest(result.getName());
		log.info("The name of the test is "+result.getName());
		
		pt= new PTupdates();
		String[] arr= pt.getTCName();
		for(int i=0;i<arr.length;i++)
		{
			if (arr[i].equalsIgnoreCase(result.getName()))
			{
				
				test.log(Status.SKIP, "The test "+result.getName()+" has been skipped");
				throw new SkipException("Bug is already logged for this test case");
				
			}
			
		}
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		if(result.SUCCESS==ITestResult.SUCCESS)
		{
			System.out.println("Test Passed-->"+result.getName());
			test.log(Status.PASS, "The test "+result.getName()+" has passed");
			
		}
		pass_count++;
		System.out.println(pass_count);
		
		log.info("The test case "+result.getName()+" passed successfully");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		if(result.FAILURE==ITestResult.FAILURE)
		{
		System.out.println("Test failed-->"+result.getName());
		
		
		String name=result.getName();
		Throwable th=result.getThrowable();
		if(th!=null)
		{
			s=th.getMessage();
		}
		ex=  new BugExceptions();
		ex.createBugType(UserData.trackerTool,name,s);
//		ex.createPTIssue(name,s);
		}
		test.log(Status.FAIL, "The test "+result.getName()+" has failed"+result.getThrowable().toString());
		
		fail_count++;
		System.out.println(fail_count);
		log.error("The test case "+result.getName()+" was failed");
		log.info("The test "+result.getName()+" has failed"+result.getThrowable().toString());
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		if(result.SKIP==ITestResult.SKIP)
		{
		System.out.println("Test Skipped-->"+result.getName());
		test.log(Status.SKIP, "The test "+result.getName()+" has been skipped");
		
		}
		skip_count++;
	//	System.out.println(skip_count);
		
		log.warn("The test case "+result.getName()+" was failed");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		reporter= new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/WALReport.html"));
		reporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
		report= new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Environment","QA");
		report.setSystemInfo("Platform","MAC");
		report.setSystemInfo("Browser","Chrome");
		System.out.println("Starting test Suite-->"+context.getName());
		m= new MapStore();
	//	log.startTest(context.getName());
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Finishing test Suite-->"+context.getName());
		System.out.println(m.mydata());
		report.flush();
		report.close();
		p= new SlackPost();
		p.postSlackMessage(pass_count,fail_count,skip_count);
		
	//System.out.println("The stored data is "+m.mydata());
	//	log.endTest(context.getName());
		
		
	}

}



