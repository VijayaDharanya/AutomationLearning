package week6.day0;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class ServicenowProposal {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// Launch ServiceNow application
		driver.get("https://dev100201.service-now.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// Login with valid credentials
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("Malaysia@94");
		driver.findElement(By.xpath("//button[@id='sysverb_login']")).click();

		//// Click All and Enter Proposal in filter navigator and press enter
		Shadow shadow = new Shadow(driver);
		shadow.setImplicitWait(30);
		WebElement dom = shadow.findElementByXPath("//div[@id='all']");
		dom.click();
		WebElement filter = shadow.findElementByXPath("//input[@id='filter']");
		filter.click();
		filter.sendKeys("Proposal", Keys.ENTER);
		dom.click();
		Thread.sleep(5000);
		shadow.findElementByXPath("//a[contains(@class,'nested-item item-label')]").click();

		// Click- new and fill mandatory fields and click 'Submit' Button.
		WebElement frame1 = shadow.findElementByXPath("//iframe[@title='Main Content']");
		driver.switchTo().frame(frame1);
		driver.findElement(By.xpath("//button[@id='sysverb_new']")).click();
		driver.findElement(By.xpath("//a[@id='lookup.std_change_proposal.short_description']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandleslist = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandleslist.get(1));
		driver.findElement(By.xpath("//a[text()='Issue with networking']")).click();
		driver.switchTo().window(windowHandleslist.get(0));
		WebElement frame2 = shadow.findElementByXPath("//iframe[@title='Main Content']");
		driver.switchTo().frame(frame2);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@id='sysverb_insert']")).click();

		// Verify the successful creation of new Proposal"
		String text = driver.findElement(By.xpath("(//a[@class='linked formlink'])[1]")).getText();
		System.out.println(text);
		String state = driver.findElement(By.xpath("(//td[@class='vt'])[4]")).getText();
		System.out.println("State: " + state);

	}

}
