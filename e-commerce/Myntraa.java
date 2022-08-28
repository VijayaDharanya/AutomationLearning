/*Assignment 5:
============
Myntra
1) Open https://www.myntra.com/
2) Mouse hover on MeN 
3) Click Jackets 
4) Find the total count of item 
5) Validate the sum of categories count matches
6) Check jackets
7) Click + More option under BRAND
8) Type Duke and click checkbox
9) Close the pop-up x
10) Confirm all the Coats are of brand Duke
    Hint : use List 
11) Sort by Better Discount
12) Find the price of first displayed item
Click on the first product
13) Take a screen shot
14) Click on WishList Now
15) Close Browser*/

package online.automate;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntraa {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option =new ChromeOptions();
		option.addArguments("--disable-notifications");
		WebDriver driver=new ChromeDriver(option);
		
		//Open https://www.myntra.com/
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Mouse hover on MeN 
		WebElement ele=driver.findElement(By.xpath("(//a[text()='Men'])[1]"));
		Actions action=new Actions(driver);
		action.moveToElement(ele).perform();
		
		//Click Jackets 
		driver.findElement(By.xpath("(//a[text()='Jackets'])[1]")).click();
		
		//Find the total count of item 
		String total=driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		System.out.println("Total count of items :" +total);
		String totalJacket=total.replaceAll("\\D","");
		System.out.println(totalJacket);
		int tt=Integer.valueOf(totalJacket).intValue();
		
		//Validate the sum of categories count matches
		String jackTotal=driver.findElement(By.xpath("(//span[@class='categories-num'])")).getText();
		String jack=jackTotal.replaceAll("\\D","");
		System.out.println(jack);
		int jt=Integer.valueOf(jack).intValue();
		String raintotal =driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();
		String rain=raintotal.replaceAll("\\D","");
		System.out.println(rain);
		int rt=Integer.valueOf(rain).intValue();
		int sum=jt+rt;
		System.out.println("sum of categories count "+sum);
		if(tt==(sum)) {
			System.out.println("sum of categories count matches");
		}else {
			System.out.println("Does not match sum of categories count");
		}
		
		//Check jackets
		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[1]")).click();
		
		//Click + More option under BRAND
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		
		//Type Duke and click checkbox
		driver.findElement(By.xpath("//input[@class='FilterDirectory-searchInput']")).sendKeys("Duke");
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//label[@class=' common-customCheckbox'])[1]")).click();
		
		// Close the pop-up x
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();
		
		//Confirm all the Coats are of brand Duke  [ Hint : use List ]
		String coat=driver.findElement(By.xpath("//div[@class='product-productMetaInfo']//h3")).getText();
		List<String> duke =new ArrayList<String>(Arrays.asList(coat));	
		System.out.println("List of duke products :"+duke.addAll(duke));
		
		//Sort by Better Discount
		WebElement sort = driver.findElement(By.xpath("//div[@class='sort-sortBy']"));
		Actions action1=new Actions(driver);
		action1.moveToElement(sort).perform();
		Thread.sleep(3000);
		WebElement dis=driver.findElement(By.xpath("//label[text()='Better Discount']"));
		Actions action2=new Actions(driver);
		action2.doubleClick(dis).perform();
		
		
		//Find the price of first displayed item
		Thread.sleep(3000);
		System.out.println("First product price :"+driver.findElement(By.xpath("(//span[@class='product-discountedPrice'])[1]")).getText());
	
		//Click on the first product and Take a screen shot
		driver.findElement(By.xpath("(//img[@class='img-responsive'])[1]")).click();
		
		//WindoeHandles
		Set<String> windowHandles = driver.getWindowHandles();
		//convert set into list
		List<String> windowHandleslist=new ArrayList<String>(windowHandles);
		//switch the control to corresponding window
		driver.switchTo().window(windowHandleslist.get(1));
		
		//snapshot
		WebElement pic = driver.findElement(By.xpath("//div[@class='pdp-description-container']"));
	    File src=pic.getScreenshotAs(OutputType.FILE);
	    File trg=new File("./snaps/Myntra.png");
	    FileUtils.copyFile(src,trg);
	    
		//Click on WishList Now
	    driver.findElement(By.xpath("(//span[@class='desktop-userTitle'])[2]")).click();
		
		//Close Browser
		driver.quit();
	
	}

}
