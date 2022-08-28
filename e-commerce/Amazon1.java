/*Assignment:4
===========
Amazon:
1.Load the uRL https://www.amazon.in/
2.search as oneplus 9 pro 
3.Get the price of the first product
4. Print the number of customer ratings for the first displayed product
5. Mouse Hover on the stars
6. Get the percentage of ratings for the 5 star.
7. Click the first text link of the first image
8. Take a screen shot of the product displayed
9. Click 'Add to Cart' button
10. Get the cart subtotal and verify if it is correct.*/
package online.automate;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon1 {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// Load the uRL https://www.amazon.in/
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// search as oneplus 9 pro
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).click();
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("oneplus 9 pro", Keys.ENTER);

		// Get the price of the first product
		WebElement price = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]"));
		String total = price.getText();
		System.out.println("Price of 1st product : " + total);

		// Print the number of customer ratings for the first displayed product
		System.out.println("No of customer ratings for 1st displayed product : "
				+ driver.findElement(By.xpath("(//span[@class='a-size-base s-underline-text'])[1]")).getText());

		// Click the first text link of the first image
		driver.findElement(By.xpath("(//span[contains(text(),'OnePlus 9 Pro 5G')])[1]")).click();

		// WindowHandle
		Set<String> windowHandles = driver.getWindowHandles();
		// convert set into list
		List<String> windowHandleslist = new ArrayList<String>(windowHandles);
		// switch the control to corresponding window
		driver.switchTo().window(windowHandleslist.get(1));

		// Take a screen shot of the product displayed
		WebElement pic = driver.findElement(By.xpath("//div[@id='ppd']"));
		File src = pic.getScreenshotAs(OutputType.FILE);
		File trg = new File("./snap/oneplus.png");
		FileUtils.copyFile(src, trg);

		// Click 'Add to Cart' button
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();

		// Get the cart subtotal and verify if it is correct.
		WebElement cart = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']"));
		String carttotal = cart.getText();
		if (total.equals(carttotal)) {
			System.out.println("Same Value");
		} else {
			System.out.println("Not Same Value");
		}
		driver.findElement(By.xpath("//a[@id='attach-close_sideSheet-link']")).click();
		driver.quit();
	}

}
