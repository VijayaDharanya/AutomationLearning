package automate.test;

import java.io.IOException;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SF3 extends SF34BaseClass {
	
	@BeforeTest
	
	public void setup() {
		excelFile = "SF3";
	}
	@Test(dataProvider = "testData")

	public void sF3(String app,String question, String detail) throws InterruptedException {

		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys(app);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//p[@class='slds-truncate']")).click();
		WebElement js = driver.findElement(By.xpath("//span[text()='Chatter']"));
		((RemoteWebDriver) driver).executeScript("arguments[0].click()", js);
		String actualTitle = driver.getTitle();
		System.out.println("Actual Title is  " + actualTitle);
		String expectedTitle = "Lightning Experience";
		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Same Title");
		} else {
			System.out.println("Different Title");
		}
		
		driver.findElement(By.xpath("//span[text()='Question']")).click();
		driver.findElement(By.xpath("//textarea[@role='textbox']")).sendKeys(question);
		driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys(detail);
		driver.findElement(By.xpath("//div[contains(@class,'bottomBarRight')]")).click();
		if (driver.getPageSource().contains("coming from excel")) {
			System.out.println("question appears");
		} else {
			System.out.println("question not appears");
		}

	}

	/*@DataProvider(name = "testData")
	public String[][] testdata() throws IOException {
		String[][] readData = ReadExcel3.dynamic3(salesforce3);// Classname.methodname
		return readData;
	}*/

	/*
	 * @DataProvider public String[][] testdata() { String[][] data = new
	 * String[2][5]; data[0][0] = "vj@salesforce.com"; data[0][1] = "Malaysia@94";
	 * data[0][2] = "Content"; data[0][3] = "coming from excel"; data[0][4] =
	 * "coming from excel";
	 * 
	 * data[1][0] = "ramkumar.ramaiah@testleaf.com"; data[1][1] = "Password#123";
	 * data[1][2] = "Content"; data[1][3] = "hello"; data[1][4] = "hello"; return
	 * data;
	 */

}
