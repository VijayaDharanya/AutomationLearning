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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// Load the uRL https://www.amazon.in/
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// Type "Bags" in the Search box
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Bags");

		// Choose the third displayed item in the result (bags for boys)
		driver.findElement(By.xpath("//span[text()=' for boys']/parent::div")).click();

		// Print the total number of results (like 30000)
		String total = driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"))
				.getText();
		System.out.println("total number of results : " + total);

		// Select the first 2 brands in the left menu
		driver.findElement(By.xpath("(//i[@class='a-icon a-icon-checkbox'])[3]")).click();
		driver.findElement(By.xpath("(//i[@class='a-icon a-icon-checkbox'])[2]")).click();

		// Confirm the results have got reduced (use step 05 for compare)
		String BTotal = driver.findElement(By.xpath("(//div[@class='sg-col-inner']//div)[1]")).getText();
		if (total.equals(BTotal)) {
			System.out.println("result not reduced in total");
		} else {
			System.out.println("results have got reduced in total");
		}

		// Choose New Arrivals (Sort)
		driver.findElement(By.xpath("//span[text()='Sort by:']")).click();
		driver.findElement(By.xpath("//a[text()='Newest Arrivals']")).click();

		// Print the first resulting bag info (name, discounted price)
		WebElement name = driver.findElement(By.xpath("(//h2[contains(@class,'a-size')])[1]"));
		String na = name.getText();
		System.out.println("first resulting bag info name " + na);
		String price = driver.findElement(By.xpath("(//span[@class='a-price'])[1]")).getText();
		System.out.println("first resulting bag info discounted price " + price);
		driver.findElement(By.xpath("(//div[@class='a-section aok-relative s-image-tall-aspect'])[1]")).click();

		// Take screenshot and close
		Set<String> windhand = driver.getWindowHandles();
		List<String> wd = new ArrayList(windhand);
		driver.switchTo().window(wd.get(1));
		WebElement pic = driver.findElement(By.xpath("//div[@id='imgTagWrapperId']"));
		File src = pic.getScreenshotAs(OutputType.FILE);
		File trg = new File("./snap/img.png");
		FileUtils.copyFile(src, trg);
		driver.quit();
	}

}
