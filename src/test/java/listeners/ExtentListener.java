package listeners;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import messages.MapStore;
import messages.PTExceptions;
import messages.PTupdates;
import messages.SlackPost;

public class ExtentListener implements ITestListener {
	
	MapStore m;
	PTExceptions ex;
	String s="";
	ExtentReports report;
	ExtentTest test;
	ExtentHtmlReporter reporter;
	SlackPost p;
	PTupdates pt;
	
	static int pass_count=0,fail_count=0,skip_count;

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		System.out.println("Starting test-->"+result.getName());
		test= report.createTest(result.getName());
		
		pt= new PTupdates();
		String[] arr= pt.getTCName();
		for(int i=0;i<arr.length;i++)
		{
			if (arr[i].equalsIgnoreCase(result.getName()))
			{
				
				test.log(Status.SKIP, "The test "+result.getName()+" has been skipped");
				throw new SkipException("Bug is logged for this test case");
				
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
		ex=  new PTExceptions();
		ex.createPTIssue(name,s);
		}
		test.log(Status.FAIL, "The test "+result.getName()+" has failed"+result.getThrowable().toString());
		
		fail_count++;
		System.out.println(fail_count);
		
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
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		reporter= new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/WALReport.html"));
		reporter.loadXMLConfig(new File("/Users/bhabanimishra/Documents/workspace/FAST/extent-config.xml"));
		report= new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Environment","QA");
		report.setSystemInfo("Platform","MAC");
		report.setSystemInfo("Browser","Chrome");
		System.out.println("Starting test Suite-->"+context.getName());
		m= new MapStore();
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Finishing test Suite-->"+context.getName());
		System.out.println(m.mydata());
		report.flush();
		report.close();
		p= new SlackPost();
		p.postSlackMessage(pass_count,fail_count,skip_count);
		
		System.out.println("The stored data is "+m.mydata());
		
	}

}



