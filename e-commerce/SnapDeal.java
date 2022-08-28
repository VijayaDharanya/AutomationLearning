/*
Assignment 3:SnapDeal
============
1. Launch https://www.snapdeal.com/
2. Go to Mens Fashion
3. Go to Sports Shoes
4. Get the count of the sports shoes
5. Click Training shoes
6. Sort by Low to High
7. Check if the items displayed are sorted correctly
8.Select the price range (900-1200)
9.Filter with color Navy 
10 verify the all applied filters 
11. Mouse Hover on first resulting Training shoes
12. click QuickView button
13. Print the cost and the discount percentage
14. Take the snapshot of the shoes.
15. Close the current window
16. Close the main window*/

package online.automate;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// Go to https://www.snapdeal.com/
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// Go to Mens Fashion
		WebElement ele = driver.findElement(By.xpath("(//span[@class='catText'])[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(ele).perform();

		// Go to Sports Shoes
		driver.findElement(By.xpath("(//span[text()='Sports Shoes'])[1]")).click();

		// Get the count of the sports shoes
		System.out.println("No of count :"
				+ driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText());

		// Click Training shoes
		WebElement ts = driver.findElement(By.xpath("//div[text()='Training Shoes']"));
		ts.click();

		// Sort by Low to High
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class='sort-label']")).click();
		WebElement sort = driver.findElement(By.xpath("(//li[@class='search-li'])[1]"));
		sort.click();

		// Select the price range (500-1200)
		WebElement from = driver.findElement(By.xpath("//input[@name='fromVal']"));
		from.clear();
		from.sendKeys("500");
		WebElement to = driver.findElement(By.xpath("//input[@name='toVal']"));
		to.clear();
		to.sendKeys("1200");
		driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		// Filter with color Navy
		driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();

		// Mouse Hover on first resulting Training shoes
		WebElement shoes = driver.findElement(By.xpath("(//img[@class='product-image wooble'])[1]"));
		Actions shoe = new Actions(driver);
		shoe.moveToElement(shoes).perform();

		// click QuickView button
		driver.findElement(By.xpath("(//div[@class='clearfix row-disc']//div)[1]")).click();

		// Print the cost and the discount percentage
		System.out.println("Price :" + driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText());
		System.out.println("Discount : " + driver
				.findElement(By.xpath("(//div[@class='product-price pdp-e-i-PAY-l clearfix']//span)[2]")).getText());

		// Take the snapshot of the shoes.
		WebElement pic = driver.findElement(By.xpath("(//div[contains(@class,'modal-wrapper')])[1]"));
		File src = pic.getScreenshotAs(OutputType.FILE);
		File trg = new File("./snaps/blue.png");
		FileUtils.copyFile(src, trg);

		// Close the current window
		driver.findElement(By.xpath("//div[@class='close close1 marR10']")).click();

		// Close the main window
		driver.close();

	}

}
