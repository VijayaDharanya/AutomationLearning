package week6.day0;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class ServiceNowKnowledge {

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

		// Enter Knowledge in filter navigator and press enter
		Shadow shadow = new Shadow(driver);
		shadow.setImplicitWait(30);
		WebElement dom = shadow.findElementByXPath("//div[@id='all']");
		dom.click();
		WebElement know = shadow.findElementByXPath("//span[text()='Knowledge']");
		know.click();

		// Create new Article
		WebElement frame = shadow.findElementByXPath("//iframe[@title='Main Content']");
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//button[@type='button']")).click();

		// Select knowledge base as IT and category as IT- java and Click Ok
		driver.findElement(By.xpath("//input[@id='sys_display.kb_knowledge.kb_knowledge_base']")).sendKeys("IT",
				Keys.ENTER);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@id='lookup.kb_knowledge.kb_category']")).click();
		driver.findElement(By.xpath("//span[text()='IT']")).click();
		driver.findElement(By.xpath("//span[text()='Java']")).click();
		driver.findElement(By.xpath("//button[@id='ok_button']")).click();

		// Update the other mandatory fields
		driver.findElement(By.xpath("//input[@id='kb_knowledge.short_description']")).sendKeys("Course");

		// Select the submit option
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@id='sysverb_insert']")).click();
	}

}
