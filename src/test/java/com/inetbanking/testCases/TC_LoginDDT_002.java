package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass 
{
	@Test(dataProvider="LoginData")
	public void loginDDT(String user,String pwd) throws InterruptedException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUsername(user);
		logger.info("username provided");
		lp.setPassword(pwd);
		logger.info("password provided");
		lp.buttonClick();
		logger.info("button clicked");
		Thread.sleep(3000);
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//close the alert window
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("login failed");
		}
		else
		{
			Assert.assertTrue(true);
	        lp.clickLogout();
	        Thread.sleep(3000);
	        driver.switchTo().alert().accept();
	        driver.switchTo().defaultContent();
		}
	}
	
	public boolean isAlertPresent()//userdefined method to check alert is present or not
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
		
	}
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		 int rowcount=XLUtils.getRowCount(path, "sheet1");
		 int colcount=XLUtils.getCellCount(path, "sheet1",1);
		 
		 String logindata[][]=new String[rowcount][colcount];
		 
		 for(int i=1;i<=rowcount;i++)
		 {
			 for(int j=0;j<colcount;j++)
			 {
				 logindata[i-1][j]=XLUtils.getCellData(path, "sheet1",i,j);
			 }
		 }
		 return logindata;
		
	}

}
