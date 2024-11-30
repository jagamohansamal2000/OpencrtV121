package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass{
	
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Datadriven")//getting dataprovider from different class
	public void verify_loginDDT(String email, String pwd, String exp) 
	{
		
	logger.info("***  starting TC_003_LoginDDT ***");	
		
	
	try 
	{
	//HomePage
	HomePage hp = new HomePage(driver);
	hp.clickMyAccount();
	hp.clickLogin();
	
	
	//Login
	LoginPage lp = new LoginPage(driver);
	lp.setEmail(email);
	lp.setPassword(pwd);
	lp.clickLogin();
	
	//MyAccountPage 
	MyAccountPage macc= new MyAccountPage(driver);
	boolean targetPage = macc.isMyAccountPageExists();
	
	
	/*Data is Valid  - login success - test pass - logout
	                   login failed  - test fail
	   
	  Data is invalid  -  login success - test fail  - logout 
	                   -  login failed  -  test pass              
	*/
	
	if(exp.equalsIgnoreCase("Valid")) 
	{
		if(targetPage==true) 
		{
			macc.clickLogout();
			Assert.assertTrue(true);	
		}
		else
		{
			Assert.assertTrue(false);
			
		}
	}
	
	
	if(exp.equalsIgnoreCase("invalid")) 
	{
		if(targetPage==true)
	    Assert.assertTrue(false);		
	}
	else 
	{
		Assert.assertTrue(true);
	}
	}
	catch(Exception e)
	{
		Assert.fail(exp);
	}
	
	logger.info("*** Finished TC_003_LoginDDT ***");
	}

}
