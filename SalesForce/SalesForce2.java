package week6.day0;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForce2 {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver(options);
		
		//Launch https://login.salesforce.com/ 
		driver.get("https://login.salesforce.com/ ");
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
		
		//Type Individuals on the Search box
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Individuals");
		
		//Click Individuals Link
		Thread.sleep(3000);
		driver.findElement(By.xpath("//p[@class='slds-truncate']")).click();
		
		//Click New
		driver.findElement(By.xpath("//div[text()='New']")).click();
		
		//Select Salutation with data (coming from excel) 
		driver.findElement(By.xpath("(//div[@class='uiPopupTrigger'])[1]")).click();
		driver.findElement(By.xpath("//a[@title='Mrs.']")).click();
		
		//Type Last Name 
		WebElement lastname=driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
		lastname.sendKeys("N");
		
		//Click Save
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		
		//Click on the App Launcher (dots)
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		
		//Click View All
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		
		//Type Customers on the Search box
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Customers");
		
		//Click Customers Link
		driver.findElement(By.xpath("//p[@class='slds-truncate']")).click();
		
		//Click New
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//div[text()='New'])[1]")).click();
		
		//Type the same name provided in step 8 and confirm it appears
		driver.findElement(By.xpath("//input[@role='combobox']")).sendKeys("N");
		driver.findElement(By.xpath("(//div[contains(@class,'primaryLabel')])[1]")).click();
		if(driver.getPageSource().contains("N")) {
			System.out.println("Same name appears");
		}else {
			System.out.println("Same name not appears");
		}
		
		//Close the browser
		driver.close();
		
		
		
		

	}

}
