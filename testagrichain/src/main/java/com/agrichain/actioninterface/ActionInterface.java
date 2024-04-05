package com.agrichain.actioninterface;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ActionInterface {
	//added all user actions abstract method to achive astraction
	public void scrollByVisibilityOfElement(WebDriver driver,WebElement ele);
	public void click(WebDriver driver, WebElement ele);
	//webelement related
	public boolean findElement(WebDriver driver,WebElement ele);
	public boolean isDisplayed(WebDriver driver,WebElement ele);
	public boolean isEnabled(WebDriver driver,WebElement ele);
	public boolean type(WebElement ele,String text);
	public boolean selectBySendKeys(String value,WebElement ele);
// dropdown related values
	public boolean selectByIndex(WebElement element, int index);
	public boolean selectByValue(WebElement element,String value);
	public boolean selectByVisibleText(String visibletext, WebElement ele);
	//mouse hower realted 
	public boolean mouseHoverByJavaScript(WebElement locator,WebDriver driver);
	public boolean JSClick(WebDriver driver, WebElement ele);
	//frames realted methods
	public boolean switchToFrameByIndex(WebDriver driver,int index);
	public boolean switchToFrameById(WebDriver driver,String idValue);
	public boolean switchToFrameByName(WebDriver driver,String nameValue);
	public boolean switchToDefaultFrame(WebDriver driver);
	//acrion class related move to element and all
	public void mouseOverElement(WebDriver driver,WebElement element);
	public boolean moveToElement(WebDriver driver, WebElement ele);
	public boolean mouseover(WebDriver driver, WebElement ele);
	public boolean draggable(WebDriver driver,WebElement source, int x, int y);
	public boolean draganddrop(WebDriver driver,WebElement source, WebElement target);
	public boolean slider(WebDriver driver,WebElement ele, int x, int y);
	public boolean rightclick(WebDriver driver,WebElement ele);
	//handling multiple windows
	public boolean switchWindowByTitle(WebDriver driver,String windowTitle, int count);
	public boolean switchToNewWindow(WebDriver driver);
	public boolean switchToOldWindow(WebDriver driver);
	//handling rows and coluns menthods
	public int getColumncount(WebElement row);
	public int getRowCount(WebElement table);
//alert
    public boolean Alert(WebDriver driver);
	public boolean launchUrl(WebDriver driver,String url);
	public boolean isAlertPresent(WebDriver driver);
	
public String getCurrentURL(WebDriver driver);
	public String getTitle(WebDriver driver);
	public boolean click1(WebElement locator, String locatorName);
	//waits
	public void fluentWait(WebDriver driver,WebElement element, int timeOut);
	public void implicitWait(WebDriver driver, int timeOut);
	public void explicitWait(WebDriver driver, WebElement element, int timeOut);
	public void pageLoadTimeOut(WebDriver driver, int timeOut);
	//screenshot methods
	public String screenShot(WebDriver driver, String filename);
	public String getCurrentTime();

}