/*Assignment 2:Nykaa
=============
1) Go to https://www.nykaa.com/
2) Mouseover on Brands and Search L'Oreal Paris
3) Click L'Oreal Paris
4) Check the title contains L'Oreal Paris(Hint-GetTitle)
5) Click sort By and select customer top rated
6) Click Category and click Hair->Click haircare->Shampoo
7) Click->Concern->Color Protection
8)check whether the Filter is applied with Shampoo
9) Click on L'Oreal Paris Colour Protect Shampoo
10) GO to the new window and select size as 175ml
11) Print the MRP of the product
12) Click on ADD to BAG
13) Go to Shopping Bag 
14) Print the Grand Total amount
15) Click Proceed
16) Click on Continue as Guest
17) Check if this grand total is the same in step 14
18) Close all windows*/

package online.automate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// Mouseover on Brands and Search L'Oreal Paris
		WebElement ele = driver.findElement(By.xpath("//a[text()='brands']"));// inspect brand
		// Mousehover action
		Actions action = new Actions(driver);
		action.moveToElement(ele).perform();

		// Click L'Oreal Paris
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
		driver.findElement(By.linkText("L'Oreal Paris")).click();
		Thread.sleep(3000);

		// Check the title contains L'Oreal Paris(Hint-GetTitle)
		String title= driver.getTitle();
		if(title.contains("Paris")){
				System.out.println("Title of the page contains Paris");
		}else 
		{
					System.out.println("Title of the page not contains Paris");
				}
		// Click sort By
		driver.findElement(By.xpath("//span[@class='sort-name']")).click();

		// and select customer top rated
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();

		// Click Category and click Hair->Click haircare->Shampoo
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();

		// Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();

		// check whether the Filter is applied with Shampoo
		System.out.println("Filter is applied with Shampoo : "
				+ driver.findElement(By.xpath("//span[text()='Shampoo']")).isSelected());

		// Click on L'Oreal Paris Colour Protect Shampoo
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='css-xrzmfa']")).click();

		// Window handling
		Set<String> windowHandles = driver.getWindowHandles();
		// convert set into list
		List<String> windowHandleslist = new ArrayList<String>(windowHandles);
		// switch the control to corresponding window
		driver.switchTo().window(windowHandleslist.get(1));

		// GO to the new window and select size as 175ml
		Thread.sleep(3000);
		WebElement drop = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select ml = new Select(drop);
		ml.selectByValue("0");

		// Print the MRP of the product
		WebElement MRP = driver.findElement(By.xpath("(//span[text()='₹189'])[1]"));
		String price = MRP.getText();
		System.out.println("Price of shampo  :" + price);

		// Click on ADD to BAG
		driver.findElement(By.xpath("(//span[text()='Add to Bag'])[1]")).click();

		// Go to Shopping Bag
		driver.findElement(By.xpath("//button[@class='css-g4vs13']")).click();

		// Print the Grand Total amount

		WebElement grandTotal = driver.findElement(By.xpath("//iframe[@class='css-acpm4k']"));
		driver.switchTo().frame(grandTotal);
		String gDT = driver.findElement(By.xpath("(//div[@class='value'])[4]")).getText();
		System.out.println("GrandTotal is :" + gDT);

		// Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();

		// Click on Continue as Guest
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();

		// Check if this grand total is the same in step 14
		String gDT1 = driver.findElement(By.xpath("(//div[@class='value'])[2]")).getText();
		System.out.println("GrandTotal is :" + gDT1);
		if (gDT.equals(gDT1)) {
			System.out.println("GrandTotal is Same");
		} else {
			System.out.println("GrandTotal is Differ");
		}

		// Close all windows
		driver.quit();

	}

}
