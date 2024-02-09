package demos;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

class regDemo {
	
	public WebDriver driver = null;

	@DataProvider(name = "test-data")
	public Object[][] dataProvFunc() {

		return new Object[][] {
				//first user test data
				{ "testFirstname", "testLastname", "testyoganand@gmail.com", 
					"8801636401", "testyoganand","testyoganand" },
				/*
				 * //second user test data { "testFname", "testLname", "testbheema@gmail.com",
				 * "9988776655", "testbheema", "testbheema" }, //Third user test data {
				 * "testFirstname", "testLastname", "testyoganand@gmail.com", "8801636401",
				 * "testyoganand","testyoganand" }, //Fourth user test data { "testFname",
				 * "testLname", "testbheema@gmail.com", "9988776655", "testbheema", "testbheema"
				 * }, //Fiveth user testdata { "testFirstname", "testLastname",
				 * "testyoganand@gmail.com", "8801636401", "testyoganand", "testyoganand" }
				 */

		};
	}

	@BeforeMethod
	public void setUp() {
		System.out.println("Start test");
		// System.setProperty("webdriver.http.factory", "jdk-http-client");
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver","./driversfolder/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		driver = new ChromeDriver(options);
		driver.get("https://awesomeqa.com/ui/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();

	}

	@Test(dataProvider = "test-data")
	public void search(String keyWord1, String keyWord2, String keyWord3, String keyWord4, String keyWord5,
			String keyWord6) throws InterruptedException {

		// Myaccount
		WebElement myaccount = driver.findElement(By.xpath("//div[@id=\"top-links\"]/ul/li[2]/a"));
		myaccount.click();

		// register
		WebElement register_link = driver.findElement(By.xpath("//a[contains(text(),'Register')]"));
		register_link.click();

		Thread.sleep(5000);// wait 5000 ==5sec

		// firstname
		WebElement first_name = driver.findElement(By.xpath("//input[@id=\"input-firstname\"]"));
		first_name.sendKeys(keyWord1);

		// lastname
		WebElement last_name = driver.findElement(By.xpath("//input[@id=\"input-lastname\"]"));
		last_name.sendKeys(keyWord2);

		// email
		WebElement email = driver.findElement((By.xpath("//input[@id=\"input-email\"]")));
		email.sendKeys(keyWord3);

		// mobilenumber
		WebElement mobilenumber = driver.findElement((By.xpath("//input[@id=\"input-telephone\"]")));
		mobilenumber.sendKeys(keyWord4);

		// password
		WebElement password = driver.findElement(By.xpath("//input[@id=\"input-password\"]"));
		password.sendKeys(keyWord5);

		// confirm_password
		WebElement confirm_password = driver.findElement(By.xpath("//input[@id=\"input-confirm\"]"));
		confirm_password.sendKeys(keyWord6);

		// checkbox
		WebElement privacy_check = driver.findElement(By.xpath("//input[@id=\"input-confirm\"]/following::input[3]"));
		privacy_check.click();

		// continue button
		WebElement continue_btn = driver.findElement(By.xpath("//input[@value=\"Continue\"]"));
		continue_btn.click();

	}

	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}

}
