package serviceNow;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class SN3Mobile extends SN1BaseClass {
	@Test
	public void mobile() throws InterruptedException {

		Shadow shadow = new Shadow(driver);
		shadow.setImplicitWait(30);
		WebElement dom = shadow.findElementByXPath("//div[@id='all']");
		dom.click();
		shadow.findElementByXPath("//span[text()='Service Catalog']").click();

		WebElement frame = shadow.findElementByXPath("//iframe[@title='Main Content']");
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//h2[contains(text(),'Mobiles')]")).click();

		driver.findElement(By.xpath("//strong[text()='iPhone 6s']")).click();

		WebElement color = driver.findElement(By.xpath("(//div[contains(@class,'col-xs-6')]//select)[1]"));
		Select rosegold = new Select(color);
		rosegold.selectByVisibleText("Gold");
		WebElement size = driver.findElement(By.xpath("(//div[contains(@class,'col-xs-6')]//select)[2]"));
		Select gb = new Select(size);
		gb.selectByValue("onehudred_twentyeight");

		driver.findElement(By.xpath("//button[@id='oi_order_now_button']")).click();

		WebElement order = driver.findElement(By.xpath("(//div[@class='notification notification-success']//span)[1]"));
		String placed = order.getText();
		System.out.println("order status  " + placed);
		WebElement num = driver.findElement(By.xpath("//a[@id='requesturl']"));
		String reqid = num.getText();
		System.out.println("Request number  " + reqid);

	}

}
