package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver; 
	public Logger logger;     //log4j
	public Properties prop;
	
	
	
	@BeforeClass(groups={"Sanity","Regression","Master","Datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os ,String br) throws IOException 
	{
		logger = LogManager.getLogger(this.getClass());   //log4j2
		
		//Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		 prop=new Properties();
		 prop.load(file);
	
		 switch(br.toLowerCase()) 
		 {
		 case"chrome" : driver=new ChromeDriver(); break;
		 case"edge" : driver = new EdgeDriver(); break; 
		 default :System.out.println("Invalisd browser name");
		 return;
		 }
		 
		
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups={"Sanity","Regression","Master","Datadriven"})
	public void tearDown() 
	{
		driver.quit();
	}
	
	
	public String randomString() 
	{
		String generetedString = RandomStringUtils.randomAlphabetic(5);
		return generetedString;
	}
	
	
	public String randonNumber() 
	{
		String randomnumber =RandomStringUtils.randomNumeric(10);
		return randomnumber;
	}
	
	public String randomPassword() 
	{
		String generetedString = RandomStringUtils.randomAlphabetic(3);
		String randomnumber =RandomStringUtils.randomNumeric(3);
		return(generetedString+randomnumber);
		
	}
	
	
	public String captureScreen(String tname) throws IOException
	{
		String timeStampp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourceFile =  takeScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+ tname + "_" + timeStampp + ".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
		 
	}

}
