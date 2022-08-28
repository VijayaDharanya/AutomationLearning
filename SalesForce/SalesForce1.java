package week6.day0;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForce1 {

	public static void main(String[] args) {
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver(options);
		
		//Launch https://login.salesforce.com/ 
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//Login with valid credentials username and password
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("vj@salesforce.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Malaysia@94");
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		
		//Click on the App Launcher (dots)
		driver.findElement(By.xpath("//div[contains(@class,'slds-icon-waffle')]")).click();
		
		//Click View All
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		
		//Type Content on the Search box
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Content");
		
		//Click Content Link
		driver.findElement(By.xpath("//p[@class='slds-truncate']")).click();
		
		//Click on Chatter Tab
		WebElement js=driver.findElement(By.xpath("//span[text()='Chatter']"));
		((RemoteWebDriver) driver).executeScript("arguments[0].click()",js);
		
		//Verify Chatter title on the page
		String actualTitle=driver.getTitle();
		System.out.println("Actual Title is  "+ actualTitle);
		String expectedTitle="Lightning Experience";
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Same Title");
		}
			else {
				System.out.println("Different Title");
			}
		
		//Click Question tab
		driver.findElement(By.xpath("//span[text()='Question']")).click();
		
		//Type Question with data (coming from excel)
		driver.findElement(By.xpath("//textarea[@role='textbox']")).sendKeys("coming from excel");
		
		//Type Details with data (coming from excel)
		driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys("coming from excel");
		
		//Click Ask
		driver.findElement(By.xpath("//div[contains(@class,'bottomBarRight')]")).click();
		
		//Confirm the question appears
		if(driver.getPageSource().contains("coming from excel")) {
			System.out.println("question appears");
			}else {
				System.out.println("question not appears");
			}
		
		//Close the browser
		driver.close();

}
}
