package week6.day0;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class ServiceNowMobile {

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

		// Click-AllEnter Service catalog in filter navigator and press enter
		Shadow shadow = new Shadow(driver);
		shadow.setImplicitWait(30);
		WebElement dom = shadow.findElementByXPath("//div[@id='all']");
		dom.click();
		shadow.findElementByXPath("//span[text()='Service Catalog']").click();

		// Click on mobiles
		WebElement frame = shadow.findElementByXPath("//iframe[@title='Main Content']");
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//h2[contains(text(),'Mobiles')]")).click();

		// Select Apple iphone6s
		driver.findElement(By.xpath("//strong[text()='iPhone 6s']")).click();

		// Update color field to rose gold and storage field to 128GB
		WebElement color = driver.findElement(By.xpath("(//div[contains(@class,'col-xs-6')]//select)[1]"));
		Select rosegold = new Select(color);
		rosegold.selectByVisibleText("Gold");
		WebElement size = driver.findElement(By.xpath("(//div[contains(@class,'col-xs-6')]//select)[2]"));
		Select gb = new Select(size);
		gb.selectByValue("onehudred_twentyeight");

		// Select Order now option
		driver.findElement(By.xpath("//button[@id='oi_order_now_button']")).click();

		// Verify order is placed and copy the request number"
		WebElement order = driver.findElement(By.xpath("(//div[@class='notification notification-success']//span)[1]"));
		String placed = order.getText();
		System.out.println("order status  " + placed);
		WebElement num = driver.findElement(By.xpath("//a[@id='requesturl']"));
		String reqid = num.getText();
		System.out.println("Request number  " + reqid);

	}

}
