package serviceNow;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class SN1BaseClass {
	public RemoteWebDriver driver;
	public String excelFile;

	@Parameters({ "browserName", "URL", "UName", "Password" })
	@BeforeMethod
	public void preCondition(String browserName, String url, String uname, String pass) {
		if (browserName.equalsIgnoreCase("Chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("Edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys(uname);
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys(pass);
		driver.findElement(By.xpath("//button[@id='sysverb_login']")).click();

	}

	@AfterMethod
	public void postCondition() {

		driver.close();

	}

	@DataProvider(name = "TestData")
	public String[][] fetchData() throws IOException {

		String[][] readData = SN2ReadExcel.readexcel(excelFile);
		return readData;

	}
}
