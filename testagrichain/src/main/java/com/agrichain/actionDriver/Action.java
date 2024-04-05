package com.agrichain.actionDriver;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.agrichain.actioninterface.ActionInterface;
import com.agrichain.base.BaseClass;



public class Action extends BaseClass implements ActionInterface{
	////inheritance oop principal
	public void scrollByVisibilityOfElement(WebDriver driver,WebElement ele)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;//down casting
		js.executeAsyncScript("arguments[0].scrollIntoView();", ele);
	}
	public void click(WebDriver driver, WebElement ele)
	{
		Actions act=new Actions(driver);
		//act.click();
		act.moveToElement(ele).click().build().perform();
	}
	@Override
	public boolean findElement(WebDriver driver, WebElement ele) {
		boolean flag=false;
		try {
			ele.isDisplayed();
			flag=true;
		}catch(Exception e)
		{
			flag=false;
		}
		finally
		{
			if(flag)
			{
				System.out.println("Successfully find an element");
			}
			else
			{
				System.out.println("unable to locate element");
			}
		}
		return flag;
	}
	@Override
	public boolean isDisplayed(WebDriver driver, WebElement ele) {
		boolean flag=false;

		flag=findElement(driver, ele);//element is able to find and then displayed or not check 
		if(flag)
		{
			flag=ele.isDisplayed();//here check is displayed
			if(flag) {
				System.out.println("the element is displayed");
			}
			else
			{
				System.out.println("the element is not displayed");
			}
		}
		else
		{
			System.out.println("not displayed");
		}
		return flag;
	}
	@Override
	public boolean isEnabled(WebDriver driver, WebElement ele) {
		boolean flag=false;

		flag=findElement(driver, ele);//element is able to find and then displayed or not check 
		if(flag)
		{
			flag=ele.isEnabled();//here check is displayed
			if(flag) {
				System.out.println("the element is enabled");
			}
			else
			{
				System.out.println("the element is not enabled");
			}
		}
		else
		{
			System.out.println("not eneabled");
		}
		return flag;
	}
	/**
	 * Type text at location
	 * 
	 * @param locatorName
	 * @param text
	 * @return - true/false
	 */

	@Override
	public boolean type(WebElement ele, String text) {
		//text type
		boolean flag=false;
		
		try {
			flag=ele.isDisplayed();
			ele.clear();//clear any text
			ele.sendKeys(text);
			flag=true;
		}catch(Exception e)
		{
			System.out.println("Location not found");
			flag=false;
		}
		finally
		{
			if(flag)
			{
				System.out.println("Successfully entered value");
			}
			else
			{
				System.out.println("unable to enter value");
			}
		}

		return flag;

	}
	@Override
	public boolean selectBySendKeys(String value, WebElement ele) {
		boolean flag = false;
		try {
			ele.sendKeys(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Select value from the DropDown");		
			} else {
				System.out.println("Not Selected value from the DropDown");
				// throw new ElementNotFoundException("", "", "")
			}
		}
	}


	/**
	 * select value from DropDown by using selectByIndex
	 * 
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param index       : Index of value wish to select from dropdown list.
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
	 *                    Listbox etc..)
	 * 
	 */
	@Override
	public boolean selectByIndex(WebElement element, int index) {

		boolean flag=false;
		try {
			Select s=new Select(element);
			s.selectByIndex(index);
			flag=true;
			return true;
		}catch(Exception e)
		{
			return false;
		}finally {
			if(flag)
			{
				System.out.println("option selcted by index");
			}
			else
			{
				System.out.println("option not selected by index");
			}
		}

	}
	@Override
	public boolean selectByValue(WebElement element, String value) {
		boolean flag=false;
		try {
			Select s=new Select(element);
			s.selectByValue(value);
			flag=true;
			return true;
		}catch(Exception e)
		{
			return false;
		}
		finally
		{
			if(flag)
			{
				System.out.println("option selected by value");
			}
			else
			{
				System.out.println("option is not selected by value");
			}
		}
	}
	/**
	 * select value from DropDown by using selectByVisibleText
	 * 
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param visibletext : VisibleText wish to select from dropdown list.
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
	 *                    Listbox etc..)
	 */

	@Override
	public boolean selectByVisibleText(String visibletext, WebElement ele) {
		boolean flag = false;
		try {
			Select s = new Select(ele);
			s.selectByVisibleText(visibletext);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by VisibleText");
			} else {
				System.out.println("Option not selected by VisibleText");
			}
		}
	}
	@Override
	public boolean mouseHoverByJavaScript(WebElement locator,WebDriver driver) {
		boolean flag=false;
		try {
			WebElement mo=locator;
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js=(JavascriptExecutor) driver;
			js.executeScript(javaScript, mo);
			flag = true;
			return true;

		}catch(Exception e)
		{
			return false;
		}
		finally {
			if(flag)
			{
				System.out.println("mouseover action is performed");
			}
			else
			{
				System.out.println("mouseOver action is not performed");
			}
		}
	}
	@Override
	public boolean JSClick(WebDriver driver, WebElement ele) {

		boolean flag=false;
		try {
			JavascriptExecutor js=(JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", ele);
			flag=true;
		}
		catch(Exception e)
		{
			return false;
		}
		finally
		{
			if(flag)
			{
				System.out.println("click action is perfomed");
			}
			else {
				System.out.println("click action is not present");
			}
		}
		return flag;
	}
	@Override
	public boolean switchToFrameByIndex(WebDriver driver, int index) {
		boolean flag=false;

		try {
			new WebDriverWait(driver,15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//iframe")));
			driver.switchTo().frame(index);
			flag=true;
			return true;
		}catch(Exception e)
		{
			return false;
		}finally {
			if(flag)
			{
				System.out.println("frame with index \""+index+ "\" is selected");
			}
		}
	}
	@Override
	public boolean switchToFrameById(WebDriver driver, String idValue) {
		boolean flag=false;
		try {
			new WebDriverWait(driver,15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("\\iframe")));
			driver.switchTo().frame(idValue);
			flag=true;
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			if(flag)
			{
				System.out.println("frame with id \""+ idValue + "\"is selected");
			}
			else
			{
				System.out.println("frame with id \""+ idValue + "\"is not selected");
			}
		}
	}
	@Override
	public boolean switchToFrameByName(WebDriver driver, String nameValue) {
		boolean flag=false;
		try {
			new WebDriverWait(driver,15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("\\iframe")));
			driver.switchTo().frame(nameValue);
			flag=true;
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			if(flag)
			{
				System.out.println("frame with id \""+ nameValue + "\"is selected");
			}
			else
			{
				System.out.println("frame with id \""+ nameValue + "\"is not selected");
			}
		}
	}
	@Override
	public boolean switchToDefaultFrame(WebDriver driver) {

		boolean flag=false;
		try {
			driver.switchTo().defaultContent();
			flag=true;
			return true;
		}catch(Exception e)
		{
			return false;
		}
		finally{
			if(flag)
			{
				System.out.println("frame with default content");
			}
			else
			{
				System.out.println("not to default content");
			}
		}

	}
	@Override
	public void mouseOverElement(WebDriver driver, WebElement element) {

		boolean flag=false;
		try {
			Actions actions=new Actions(driver);
			actions.moveToElement(element).build().perform();
			flag=true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(flag)
			{
				System.out.println("MouseOver action is performed on");
			}
			else
			{
				System.out.println("MouseOver action is not performed on");
			}
		}
	}
	@Override
	public boolean moveToElement(WebDriver driver, WebElement ele) {
		boolean flag=false;
		try {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);", ele);
			Actions actions=new Actions(driver);
			actions.moveToElement(ele).build().perform();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean mouseover(WebDriver driver, WebElement ele) {
		boolean flag=false;
		try {

			Actions actions=new Actions(driver);
			actions.moveToElement(ele).build().perform();
			flag=true;
			return true;
		}catch(Exception e)
		{
			return false;
		}

	}
	@Override
	public boolean draggable(WebDriver driver, WebElement source, int x, int y) {
		boolean flag=false;
		try {
			Actions actions=new Actions(driver);
			actions.dragAndDropBy(source, x, y);
			flag=true;
			return true;
		}catch(Exception e)
		{
			return false;
		}
		finally {
			if (flag) {
				System.out.println("Draggable Action is performed on \""+source+"\"");			
			} else if(!flag) {
				System.out.println("Draggable action is not performed on \""+source+"\"");
			}
		}
	}
	@Override
	public boolean draganddrop(WebDriver driver, WebElement source, WebElement target) {
		boolean flag=false;
		try {
			Actions actions=new Actions(driver);
			actions.dragAndDrop(source, target);
			flag=true;
			return true;
		}catch(Exception e)
		{
			return false;
		}
		finally {
			if (flag) {
				System.out.println("DragAndDrop Action is performed");
			} else if(!flag) {
				System.out.println("DragAndDrop Action is not performed");
			}
		}
	}

	@Override
	public boolean slider(WebDriver driver, WebElement ele, int x, int y) {
		boolean flag=false;
		try {
			Actions actions=new Actions(driver);
			actions.dragAndDropBy(ele, x, y).build().perform();
			//Thread.sleep(5000);
			flag=true;
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}

	}
	@Override
	public boolean rightclick(WebDriver driver, WebElement ele) {
		boolean flag=false;
		try {
			Actions actions=new Actions(driver);
			actions.contextClick(ele).perform();
			flag=true;
			return true;
		}catch(Exception e)
		{
			return false;
		}
		finally {
			if (flag) {
				System.out.println("right click is happened on ");
			} else if(!flag) {
				System.out.println("Dra");
			}
		}
	}
	@Override
	public boolean switchWindowByTitle(WebDriver driver, String windowTitle, int count) {
		boolean flag=false;
		try
		{
			Set<String> windowhandle=driver.getWindowHandles();
			String[] array=windowhandle.toArray(new String[0]);
			driver.switchTo().window(array[count-1]);

			if(driver.getTitle().contains(windowTitle))
			{
				return true;
			}
			else
			{
				return false;
			}
		}catch(Exception e)
		{
			return false;
		}finally {
			if(flag)
			{
				System.out.println("Navigated to the window with title");
			}
			else
			{
				System.out.println("the window with title is not selected");
			}
		}	
	}
	@Override
	public boolean switchToNewWindow(WebDriver driver) {
		//new window opens
		boolean flag=true;
		try
		{
			Set<String> s=driver.getWindowHandles();
			Object popup[]=s.toArray();
			driver.switchTo().window(popup[1].toString());
			flag=true;
			return true;
		}catch(Exception e)
		{
			flag=false;
			return flag;
		}finally
		{
			if(flag)
			{
				System.out.println("window is navigated to new");
			}
			else
			{
				System.out.println("window is title is not selected");
			}
		}
	}
	@Override
	public boolean switchToOldWindow(WebDriver driver) {
		boolean flag=true;
		try
		{
			Set<String> s=driver.getWindowHandles();
			Object popup[]=s.toArray();
			driver.switchTo().window(popup[0].toString());
			flag=true;
			return true;
		}catch(Exception e)
		{
			flag=false;
			return flag;
		}finally
		{
			if (flag) {
				System.out.println("Focus navigated to the window with title");			
			} else {
				System.out.println("The Window with title: is not Selected");
			}
		}
	}

	@Override
	public int getColumncount(WebElement row) {

		List<WebElement> columns=row.findElements(By.tagName("td"));
		int a=columns.size();
		System.out.println(a);
		for(WebElement colum:columns)
		{
			System.out.print(colum.getText());
			System.out.print(" | ");
		}
		return a;

	}
	@Override
	public int getRowCount(WebElement table) {
		List<WebElement> a=table.findElements(By.tagName("tr"));
		int size=a.size()-1;
		return size ;
	}
	//verify alert is present or not
	//if alert is present true marks selenium prove alert class to handle alert
	@Override
	public boolean Alert(WebDriver driver) {

		boolean presentflag=false;
		org.openqa.selenium.Alert alert=null;
		try {
			alert=driver.switchTo().alert();
			alert.accept();
			presentflag=true;
			return true;
		}catch(Exception e)
		{
			return false;
		}finally
		{
			if(presentflag)
			{
				System.out.println("alert is handle successfully");
			}else
			{
				System.out.println("no alert to handle");
			}
		}


	}
	@Override
	public boolean launchUrl(WebDriver driver, String url) {
		boolean flag=false;

		try
		{
			driver.navigate().to(url);
			flag=true;
			return true;
		}catch(Exception e)
		{
			return false;
		}
		finally
		{
			if(flag)
			{
				System.out.println("url is successfully opned");
			}
			else
			{
				System.out.println("url is not opened");
			}
		}
	}
	@Override
	public boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	@Override
	public String getCurrentURL(WebDriver driver) {
		String text=driver.getCurrentUrl();
		return text;
	}
	@Override
	public String getTitle(WebDriver driver) {
		String title=driver.getTitle();		
		return title;
	}
	@Override
	public boolean click1(WebElement locator, String locatorName) {
		boolean flag = false;
		try {
			locator.click();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Able to click on \""+locatorName+"\"");
			} else {
				System.out.println("Click Unable to click on \""+locatorName+"\"");
			}
		}

	}
	@Override
	public void fluentWait(WebDriver driver, WebElement element, int timeOut) {
		Wait<WebDriver> wait = null;
	    try {
	        wait = new FluentWait<WebDriver>((WebDriver) driver)
	        		.withTimeout(Duration.ofSeconds(20))
	        	    .pollingEvery(Duration.ofSeconds(2))
	        	    .ignoring(Exception.class);
	        wait.until(ExpectedConditions.visibilityOf(element));
	        element.click();
	    }catch(Exception e) {
	    }
	}

	@Override
	public void implicitWait(WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS)	;	
	}
	@Override
	public void explicitWait(WebDriver driver, WebElement element, int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	@Override
	public void pageLoadTimeOut(WebDriver driver, int timeOut) {
		driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);	

	}
	@Override
	public String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + dateName + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		// This new path for jenkins
		String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename + "_"
				+ dateName + ".png";
		return newImageString;
	}
	@Override
	public String getCurrentTime() {

		String currentDate=new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
		return currentDate;
	}



}




