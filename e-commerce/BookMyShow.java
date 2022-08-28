package online.automate;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BookMyShow {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.edgedriver().setup();
		WebDriver driver=new EdgeDriver();
		
		//Load the uRL https://in.bookmyshow.com/
		driver.get("https://in.bookmyshow.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Type the city as "Hyderabad" in Select City
		driver.findElement(By.xpath("//input[@placeholder='Search for your city']")).sendKeys("Hyderabad",Keys.ENTER);
		
		//Confirm the URL has got loaded with Hyderabad
		String url=driver.getCurrentUrl();
		if(url.contains("hyderabad")) {
			System.out.println("URL has got loaded with Hyderabad");
		}
		else {
			System.out.println("URL has not loaded with Hyderabad");
		}
		
		//Search for your favorite movie 
		driver.findElement(By.xpath("//span[contains(text(),'Search')]")).click();
		WebElement ele=driver.findElement(By.xpath("//input[contains(@placeholder,'Search')]"));
		ele.sendKeys("Sita Ramam");
		driver.findElement(By.xpath("//div[@class='sc-bOCYYb eRsEwz']")).click();
		
        //Click Book Tickets
		driver.findElement(By.xpath("(//span[text()='Book tickets'])[1]")).click();
		driver.findElement(By.xpath("//button[@id='wzrk-confirm']")).click();
		
	 //Print the name of the theater displayed first
		String the=driver.findElement(By.xpath("(//div[@class='__title']//a)[1]")).getText();
		System.out.println("First Theatre name "+the);
		
		//Click on the info of the theater
		driver.findElement(By.xpath("(//div[text()='INFO'])[1]")).click();
		
		//Confirm if there is a parking facility in the theater
		System.out.println(" Parking facility available " +driver.findElement(By.xpath("(//div[@class='facility-text'])[1]")).isDisplayed());
		
		
		//Close the theater popup
		driver.findElement(By.xpath("//div[@class='cross-container']")).click();
		
		//Click on the first green (available) timing
		driver.findElement(By.xpath("(//div[@class='__details'])[1]")).click();
		
		//Click Accept
		driver.findElement(By.xpath("//div[@id='btnPopupAccept']")).click();
		
		//Choose 1 Seat and Click Select Seats
		driver.findElement(By.xpath("//ul[@id='popQty']//li[1]")).click();
		driver.findElement(By.xpath("//div[@id='proceed-Qty']")).click();
		
		//Choose any available ticket and Click Pay
		driver.findElement(By.xpath("(//table//tr//td//div/a[@class='_available'])[1]")).click();
		////table[@class='setmain']//tr//td//a[@class='_available']
		driver.findElement(By.xpath("//a[@id='btmcntbook']")).click();
		
		//Print the sub total
		Thread.sleep(3000);		
		String sub=driver.findElement(By.xpath("(//li[@class='_total-section']//div)[2]")).getText();
		System.out.println("SubTotal "+sub);

		//Take screenshot and close browser
		WebElement ele1=driver.findElement(By.xpath("//section[@id='bksmile']"));
		File src=ele1.getScreenshotAs(OutputType.FILE);
		File trg=new File("./snap/tot.png");
		FileUtils.copyFile(src, trg);
		driver.quit();
	}

}
