package com.agrichain.base;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeTest;

import com.agrichain.actionDriver.Action;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static Properties prop;
	public static WebDriver driver;
	static Action action;
	@BeforeTest
	public void loadcofig() {
		
		prop=new Properties();//create object of properties class to invoked all config parameters
		System.out.println("Super constructor invoked");
		try {
			FileInputStream ip=new FileInputStream(
		 System.getProperty("user.dir") + "\\configuration\\config.properties");//to read the data from data from config file thats why we have used and give path
			prop.load(ip);//load the data
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//launch the browser
	public static void launchApp()
	{
		WebDriverManager .chromedriver().setup();//decalre webdrivermanagare it takes care the drivers on upper selenium version
		
		String browserName=prop.getProperty("browser");//read the valued from config properties it will pick browser
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager .chromedriver().setup();
			driver=new ChromeDriver();//create object of that so we can access 
		}
		else if( browserName.equalsIgnoreCase("FireFox"))
		{
			WebDriverManager .firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("IE"))
		{
			WebDriverManager .iedriver().setup();
			driver=new InternetExplorerDriver();
	}
	//now open browser then maxmize window and 
	driver.manage().window().maximize();
	action=new Action();
	action.implicitWait(driver,30);
	action.pageLoadTimeOut(driver, 40);
	driver.get(prop.getProperty("url"));
	}
}

