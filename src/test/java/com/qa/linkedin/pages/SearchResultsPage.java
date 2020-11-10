package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class SearchResultsPage extends BasePageWeb {

	private static Logger log=Logger.getLogger(SearchResultsPage.class);
	
	//create a constructor
	public SearchResultsPage() {
		PageFactory.initElements(driver, this);
	
	}
	
	@FindBy(xpath="//h3[contains(@class,'search-results__total')]")
	private WebElement searchResultText; 
	
	@FindBy(xpath="//*[contains(@class,'global-nav__primary-link')]")
	private WebElement HomeTab; 

	String searchResultsPgTitle="| Search | LinkedIn";
	
	public void validateSearchResultsTitle() {
		log.debug("waiting for the search results page title---" +searchResultsPgTitle);
		
		wait.until(ExpectedConditions.titleContains(searchResultsPgTitle));
		Assert.assertTrue(driver.getPageSource().contains(driver.getTitle()));
	
	}

	public long getResultsCount() {
		
		validateSearchResultsTitle();
		log.debug("wait for the search results text");
		wait.until(ExpectedConditions.visibilityOf(searchResultText));
		
		log.debug("getting the results text from webpage");
		String txt=searchResultText.getText();
		log.debug("results text is :" + txt);
		//txt="";
		
		String[] str=txt.split(" ");
		//str[]=["","",""]
		log.debug("results count in string format is --->" +str[1]);
		String finalStringcnt=str[1].replace(",", "").trim();
		//convert the string into long format
		
		long count=Long.parseLong(finalStringcnt);
		
		return count;
		
	}
		
		public void clickHomeTab() throws InterruptedException {
			
			log.debug("wait for the home tab");
			
			wait.until(ExpectedConditions.visibilityOf(HomeTab));
			log.debug("check whether hometab is clickable or not");
			click(HomeTab);
			
		}
		
		
		
	}
	
	


