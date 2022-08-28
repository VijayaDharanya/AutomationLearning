package serviceNow;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.github.sukgu.Shadow;

public class SN4Proposal extends SN1BaseClass {
	@Test
	public void proposal() throws InterruptedException {
		Shadow shadow = new Shadow(driver);
		shadow.setImplicitWait(30);
		WebElement dom = shadow.findElementByXPath("//div[@id='all']");
		dom.click();
		Thread.sleep(5000);
		WebElement filter = shadow.findElementByXPath("//input[@id='filter']");
		filter.click();
		filter.sendKeys("Proposal", Keys.ENTER);
		dom.click();
		Thread.sleep(5000);
		shadow.findElementByXPath("//a[contains(@class,'nested-item item-label')]").click();

		// Click- new and fill mandatory fields and click 'Submit' Button.
		WebElement frame = shadow.findElementByXPath("//iframe[@title='Main Content']");
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//button[@id='sysverb_new']")).click();
		driver.findElement(By.xpath("//a[@id='lookup.std_change_proposal.short_description']")).click();

		Set<String> windowHandles = driver.getWindowHandles();

		List<String> windowHandleslist = new ArrayList<String>(windowHandles);

		driver.switchTo().window(windowHandleslist.get(1));

		driver.findElement(By.xpath("//a[text()='Issue with networking']")).click();
		driver.switchTo().window(windowHandleslist.get(0));

		WebElement frame2 = shadow.findElementByXPath("//iframe[@title='Main Content']");
		driver.switchTo().frame(frame2);
		driver.findElement(By.xpath("//button[@id='sysverb_insert']")).click();
		

		String text = driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).getText();
		System.out.println(text);

		String state = driver.findElement(By.xpath("(//td[@class='vt'])[4]")).getText();
		System.out.println("State: " + state);

	}

}
