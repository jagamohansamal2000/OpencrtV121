package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistration_Test extends BaseClass {
	
	public Logger logger = LogManager.getLogger(this.getClass());
	
	
	@Test(groups={"Regression","Master"})
	void verify_account_registration()  
	{
		
		logger.info("Starting TC001 AccountRegistrationTest ");
		//logger = LogManager.getLogger(this.getClass());
		try 
		{
			
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link {}", hp);
		
		hp.clickRegister();
		logger.info("Clicked On Register");
		
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		/*
		 * logger.info("Providing Customer Details...");
		 * regpage.setFirstName(randomString().toUpperCase());
		 * regpage.setLastName(randomString().toUpperCase());
		 * regpage.setEmail(randomString()+"@gmail.com"); // randomly generated the
		 * email regpage.setTeliphone(randonNumber());
		 */
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");  // randomly generated the email
		regpage.setTeliphone(randonNumber());
		
		//String Password = randomAlphaNumeric();
		
		String password = randomPassword();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.clickCheckBox();
		regpage.clickontinueButton();
		
		 
		logger.info("Validating expected message {} " ,password);
		String confmsg = regpage.getConfirmationMessege();	
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else 
		{
			 logger.error("Test Failed..."); 
			 logger.debug("Debug logs..");
			 Assert.assertTrue(false);
		}
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e) 
		 {
		    logger.error("Test Failed..."); 
		    logger.debug("Debug logs..");
		    Assert.fail();
		}
	}
	
	
	
	
	

}
