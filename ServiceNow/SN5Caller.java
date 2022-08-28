package serviceNow;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.WebElement;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.sukgu.Shadow;

public class SN5Caller extends SN1BaseClass {
	@BeforeTest
	public void setup() {
		excelFile = "SN6CallerExcel";
	}

	@Test(dataProvider = "TestData")
	public void caller(String fname, String lname, String email, String bnum, String mnum, String name)
			throws InterruptedException {

		Shadow shadow = new Shadow(driver);
		shadow.setImplicitWait(30);

		WebElement dom = shadow.findElementByXPath("//div[@id='all']");
		dom.click();
		WebElement filter = shadow.findElementByXPath("//input[@id='filter']");
		filter.click();
		filter.sendKeys("Callers", Keys.ENTER);
		dom.click();
		Thread.sleep(5000);
		shadow.findElementByXPath("//mark[text()='Callers']").click();

		WebElement frame = shadow.findElementByXPath("//iframe[@title='Main Content']");
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//button[text()='New']")).click();
		Thread.sleep(5000);
		WebElement fn1 = driver.findElement(By.xpath("//input[@id='sys_user.first_name']"));
		fn1.sendKeys(fname);
		driver.findElement(By.xpath("//input[@id='sys_user.last_name']")).sendKeys(lname);
		driver.findElement(By.xpath("//input[@id='sys_user.title']")).sendKeys("new caller");
		driver.findElement(By.xpath("//input[@id='sys_user.email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='sys_user.phone']")).sendKeys(bnum);
		driver.findElement(By.xpath("//input[@id='sys_user.mobile_phone']")).sendKeys(mnum);
		driver.findElement(By.xpath("//button[@id='sysverb_insert']")).click();

		WebElement firstname = driver.findElement(By.xpath("(//input[@placeholder='Search'])[3]"));
		firstname.click();
		firstname.sendKeys(name, Keys.ENTER);
		String actualresult = driver.findElement(By.xpath("(//td[@class='vt'])[2]")).getText();

		if (actualresult.contains(name)) {
			System.out.println("Search found");
		} else {
			System.out.println("Search not found");
		}

	}
}