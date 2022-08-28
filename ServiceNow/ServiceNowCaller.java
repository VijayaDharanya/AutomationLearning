package week6.day0;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class ServiceNowCaller {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// Launch ServiceNow application
		driver.get("https://dev100201.service-now.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// Login with valid credentials 
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("Malaysia@94");
		driver.findElement(By.xpath("//button[@id='sysverb_login']")).click();

		// Click-All and Enter Callers in filter navigator and press enter
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

		// Create new Caller by filling all the fields and click 'Submit
		WebElement frame = shadow.findElementByXPath("//iframe[@title='Main Content']");
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//button[text()='New']")).click();
		Thread.sleep(5000);
		WebElement fn1 = driver.findElement(By.xpath("//input[@id='sys_user.first_name']"));
		fn1.sendKeys("VijayaDharanya");
		driver.findElement(By.xpath("//input[@id='sys_user.last_name']")).sendKeys("N");
		driver.findElement(By.xpath("//input[@id='sys_user.title']")).sendKeys("new caller");
		driver.findElement(By.xpath("//input[@id='sys_user.email']")).sendKeys("vj@gmail.com");
		driver.findElement(By.xpath("//input[@id='sys_user.phone']")).sendKeys("9085674890");
		driver.findElement(By.xpath("//input[@id='sys_user.mobile_phone']")).sendKeys("9984765809");
		driver.findElement(By.xpath("//button[@id='sysverb_insert']")).click();

		// Search and verify the newly created Caller"
		WebElement fname = driver.findElement(By.xpath("(//input[@placeholder='Search'])[3]"));
		fname.click();
		fname.sendKeys("VijayaDharanya", Keys.ENTER);
		String actualresult = driver.findElement(By.xpath("(//td[@class='vt'])[2]")).getText();

		if (actualresult.contains("VijayaDharanya")) {
			System.out.println("Search found");
		} else {
			System.out.println("Search not found");
		}

	}
}