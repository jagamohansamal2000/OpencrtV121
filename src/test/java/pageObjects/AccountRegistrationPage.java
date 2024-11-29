package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	
	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
	}
	
	
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTeliPhone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement checkBox;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	public void setFirstName(String fname) 
	{
		txtFirstname.sendKeys(fname);
	}
	
	
	public void setLastName(String lname) 
	{
		txtLastName.sendKeys(lname);
	}
	
	
	public void setEmail(String email) 

	{
		txtEmail.sendKeys(email);
	}
	
	
	public void setTeliphone(String number) 
	{
		txtTeliPhone.sendKeys(number);
	}

	
 	public void setPassword(String password) 
	{
		txtPassword.sendKeys(password);
	}
	
	
	public void setConfirmPassword(String confirmpassword) 
	{
		txtConfirmPassword.sendKeys(confirmpassword);
	}
	
	
	public void clickCheckBox() 
	{
		checkBox.click();
	}
	
	
	public void clickontinueButton() 
	{
		btnContinue.click();
	}
	
	
	
	public String getConfirmationMessege() 
	{
		try 
		{
			return(msgConfirmation.getText());
		}catch(Exception e) 
		{
			return(e.getMessage());
		}
		
	}

}
