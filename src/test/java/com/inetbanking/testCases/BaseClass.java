package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;


public class BaseClass {
	ReadConfig readconfig=new ReadConfig();
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public static WebDriver driver=null;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		logger=Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");
		 if(br.equalsIgnoreCase("chrome"))
		 {
		System.setProperty("webdriver.chrome.driver",readconfig.getChromepath());
		driver=new ChromeDriver();
		 }
		 else if(br.equalsIgnoreCase("firefox"))
			 
		 {
			 System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxpath());
				driver=new FirefoxDriver();
		 }
		 else if(br.equalsIgnoreCase("ie"))
		 {
			 System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
				InternetExplorerOptions capabilities = new InternetExplorerOptions();
				capabilities.ignoreZoomSettings();
				driver = new InternetExplorerDriver(capabilities);
		 }
		
		 driver.get(baseURL);	
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomString()
	 {
		 String randomstring=RandomStringUtils.randomAlphabetic(8);
		 return randomstring;
	 }
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}

}
