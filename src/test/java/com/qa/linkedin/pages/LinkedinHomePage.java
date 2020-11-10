package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinHomePage extends BasePageWeb {
	
	private static Logger log=Logger.getLogger(LinkedinHomePage.class);
	
	//create a constructor
	public LinkedinHomePage() {
		PageFactory.initElements(driver, this);
	
	}
	
	
	@FindBy(css="a[class='nav__logo-link'")
	private WebElement linkedinLogo; 
	
	@FindBy(linkText="Sign in")
	private WebElement SigninLink; 

	@FindBy(xpath="//h1[@class='header__content__heading '][contains(.,'Welcome Back')]")
	private WebElement welcomebackHeaderTxt; 

	@FindBy(id="username")
	private WebElement emailEditbox; 

	@FindBy(name="session_password")
	private WebElement passwordEditbox; 
	
	
	@FindBy(xpath="//button[@type='submit' and @aria-label='Sign in']")
	private WebElement SigninBtn;
	
	
	String homepgtitle="LinkedIn: Log In or Sign Up";
	String Loginpgtitle="LinkedIn Login, Sign in | LinkedIn";
	
	
	
	public void verifyLinkedinHomePageTitle() {
		log.debug("verify the Linkedin home page title" + homepgtitle);
		
		wait.until(ExpectedConditions.titleIs(homepgtitle));
		Assert.assertEquals(driver.getTitle(), homepgtitle);
	}
	
	
	public void verifyLinkedinLogoPresence() throws InterruptedException {
		
		log.debug("Verify the linkedin logo is present or not");
		Assert.assertTrue(isDisplayedElement(linkedinLogo), "linkedinLogo is not present");
		
	}
	
	public void clickSignInLink() throws InterruptedException {
		click(SigninLink);
	}
	
    	
	
	public void verifyLinkedinLoginPageTitle() {
		log.debug("verify the Linkedin Loginpage title" + Loginpgtitle);
		
		wait.until(ExpectedConditions.titleIs(Loginpgtitle));
		Assert.assertEquals(driver.getTitle(), Loginpgtitle);
		

	}
	
  public void verifyWelcomeBackheadingPresence() throws InterruptedException {
		
		log.debug("Verify the linkedin logo is present or not");
		Assert.assertTrue(isDisplayedElement(welcomebackHeaderTxt), "welcomebackHeaderTxt is not present");
}


    public void clickSignInButton() throws InterruptedException {
	click(SigninBtn);
	
	
}

public LinkedinLoggedinPage doLogin(String uname,String pwd) throws InterruptedException {
	
	log.debug("clear the content in the emailEditbox ");
	clearText(emailEditbox);
	
	log.debug("type the content in the emailEditbox "+uname);
	sendKey(emailEditbox, uname);
	
	
	log.debug("clear the content in the passwordEditbox ");
	clearText(passwordEditbox);
	
	log.debug("type the content in the passwordEditbox "+pwd);
	sendKey(passwordEditbox, pwd);
	
	clickSignInButton();
	
	return new LinkedinLoggedinPage();
	
	
	
	
	
}




}