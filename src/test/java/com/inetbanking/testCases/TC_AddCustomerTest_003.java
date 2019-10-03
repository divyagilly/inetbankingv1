package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.pageObjects.*;

public class TC_AddCustomerTest_003 extends BaseClass {
	
	 @Test
	public void testAddCustomer() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(username);
		logger.info("username entered");
		lp.setPassword(password);
		logger.info("password entered");
		lp.buttonClick();
		AddCustomer addcust = new AddCustomer(driver);
		addcust.clickAddNewCustomer();
		logger.info("link click");
		
		logger.info("Providing customer details");
		addcust.custName("Divya");
		addcust.custgender("male");
		addcust.custdob("01", "15", "90");
		addcust.custaddress("vellanikaran house");
		addcust.custcity("suwanee");
		addcust.custstate("Georgia");
		addcust.custpinno("300246");
		addcust.custtelephoneno("4046441661");
		
		String custemail=randomString()+"@gmail.com";
		addcust.custemailid(custemail);
		
		addcust.custpassword("daniel123");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		logger.info("Validation started");
		boolean res=driver.getPageSource().contains("Customer Registered Successfully");
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("test case passed");
		}
		else
		{
			logger.info("Test case failed");
			captureScreen(driver,"addCustomer");
			Assert.assertTrue(false);
			
		}
		
		
		

	}

}
