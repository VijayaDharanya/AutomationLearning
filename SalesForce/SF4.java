package automate.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SF4 extends SF34BaseClass{
@BeforeTest
	
	public void setup() {
		excelFile = "SF4";
	}
	@Test(dataProvider = "testData")
	public void sF4(String app1,String lname,String app2,String name) throws InterruptedException {
			
			driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys(app1);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//p[@class='slds-truncate']")).click();
			driver.findElement(By.xpath("//div[text()='New']")).click();
			driver.findElement(By.xpath("(//div[@class='uiPopupTrigger'])[1]")).click();
			driver.findElement(By.xpath("//a[@title='Mrs.']")).click();
			WebElement lastname = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
			lastname.sendKeys(lname);
			driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
			driver.findElement(By.xpath("//button[text()='View All']")).click();
			driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys(app2);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//p[@class='slds-truncate']")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//div[text()='New'])[1]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@role='combobox']")).sendKeys(name);
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//div[contains(@class,'primaryLabel')])[1]")).click();
			if (driver.getPageSource().contains("N")) {
				System.out.println("Same name appears");
			} else {
				System.out.println("Same name not appears");
			}
	}
		/*	@DataProvider
			public String[][] testdata() {
			String[][] data=new String[2][6];
			
			data[0][0] = "vj@salesforce.com";
			data[0][1] = "Malaysia@94";
			data[0][2] = "Individuals";
			data[0][3] = "N";
			data[0][4] = "Customers";
			data[0][5] = "N";
			
			data[1][0] = "ramkumar.ramaiah@testleaf.com";
			data[1][1] = "Password#123";
			data[1][2] = "Individuals";
			data[1][3] = "R";
			data[1][4] = "Customers";
			data[1][5] = "R";
			return data;*/
		}
			
	

