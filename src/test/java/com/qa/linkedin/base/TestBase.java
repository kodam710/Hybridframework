package com.qa.linkedin.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase 
{
private Logger log=Logger.getLogger(TestBase.class);
public static WebDriver driver=null;
public WebDriverWait wait=null;
public Properties prop=null;


public String readPropertyValue(String key) throws IOException {
	
	log.info("create object for Properties");
	prop=new Properties();
	
	log.debug("read the properties file");
	try {
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\config\\config.properties");
		
		log.info("load all properties");
		//Load all the properties
		prop.load(fis);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prop.getProperty(key);
	
	
}


@BeforeSuite

public void launchApplication() throws IOException
{
	log.info("get the browser name from the properties");
	String browsername= readPropertyValue("browser");
	
	if(browsername.equalsIgnoreCase("firefox"))
	{
		log.debug("set the webdrivermanager setup for firefox");
		WebDriverManager.firefoxdriver().setup();
		//interface refvar=new implementingclass();
		
		driver=new FirefoxDriver();
		log.debug("firefox browser is launched");
	
	}else if(browsername.equalsIgnoreCase("chrome"))
	{
		log.debug("set the webdrivermanager setup for chrome");
		WebDriverManager.chromedriver().setup();
		//interface refvar=new implementingclass();
		
		driver=new ChromeDriver();
		log.debug("chrome browser is launched");
		
	
	}else if(browsername.equalsIgnoreCase("edge"))
	{
		log.debug("set the webdrivermanager setup for edge");
		WebDriverManager.edgedriver().setup();
		//interface refvar=new implementingclass();
		
		driver=new EdgeDriver();
		
		log.debug("edge browser is launched");
	}
	//maximize the window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		//create object for webdriverwait class
		
		wait=new WebDriverWait(driver,30);
		driver.get(readPropertyValue("url"));
	
	log.debug("my application url is launched");
	
	
	


}


@AfterSuite
public void quitBrowser()
{
if(driver!=null)
{
	driver.close();
}
	
	
	
	
}
}
