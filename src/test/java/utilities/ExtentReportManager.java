package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;   // UI of the report
	public ExtentReports extent;                //populate common info on the report
	public ExtentTest test;                     // creating test case entries in the report and update status of the test methods
	
	
	String repName;
	
	public void onStart(ITestContext testContext)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyy.mm.dd.hh.mm.ss");
		Date dt = new Date();
		String currentdatetimestamp=df.format(dt);
		
		//String timeStamp = new SimpleDateFormat("\"yyy.mm.dd.hh.mm.ss\"").format(new Date());
		
		
		repName = "Testy-Report-"+ currentdatetimestamp +".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName); // specify location of the report
		
		sparkReporter.config().setDocumentTitle(currentdatetimestamp);  //Titel of the report
		sparkReporter.config().setReportName("Opencart Functional Testing");    //Name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application","opencart");
		extent.setSystemInfo("Module","Admin");
		extent.setSystemInfo("Sub Module", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");
		
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		
		List<String> includGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includGroups.isEmpty()) 
		{
			extent.setSystemInfo("Groups",includGroups.toString());
		}	
	}
	
	
	public void onTestSuccess(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());    // create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());          // to display groups in report
		test.log(Status.PASS,result.getName()+"got Successfully executed");   // update status
		
	}
	
	
	public void onTestFailure(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+"got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
		}
		
		catch(IOException e1) 
		{
			e1.printStackTrace();
		}
	}
	
	
	public void onTestSkiped(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"got skiped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	}
	
	
	public void onFinish(ITestContext testContext) 
	{
		extent.flush();
		
//		String pathofExtentReport = System.getProperty("user.dir")+"\\reports\\" + repName;
//		File extentReport = new File(pathofExtentReport);
//		
//		try 
//		{
//			Desktop.getDesktop().browse(extentReport.toURI());
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
	}
	
	

}
