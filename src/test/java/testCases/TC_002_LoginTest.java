package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass{
	
	@Test(groups={"sanity","Master"})
	public void verify_login() 
	{
		logger.info("*** Starting TC_002_LoginTest - updated  verify_login() ***");
		
		try {
		//Home Page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		
		//Login
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(prop.getProperty("email"));
		lp.setPassword(prop.getProperty("password"));
		lp.clickLogin();
		
		//MyAccountPage 
		MyAccountPage macc= new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
		//Assert.assertEquals(targetPage, true,"Login failed");
		Assert.assertTrue(targetPage);
		
		logger.info("*** Finished TC_002_LoginTest");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	}

}
