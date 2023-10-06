package rbarec.scr4pp1ng;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class P1ratRecent {

	public static void search() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(false);
		options.addArguments("start-maximized"); // open Browser in maximized mode
		options.addArguments("disable-infobars"); // disabling infobars
		options.addArguments("--disable-extensions"); // disabling extensions
		options.addArguments("--disable-gpu"); // applicable to Windows os only
		options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		options.addArguments("--no-sandbox"); // Bypass OS security model
		options.addArguments("--disable-in-process-stack-traces");
		options.addArguments("--disable-logging");
		options.addArguments("--log-level=3");
		options.addArguments("--remote-allow-origins=*");

		WebDriver driver = new ChromeDriver(options);

		final String a1 = "the";
		final String a2 = "pir";
		final String a3 = "teba";
		final String a4 = "0.org";
		final String url = "https://" + a1 + a2 + "a" + a3 + "y1" + a4 + "/recent/";

		int limitPage = 20;
		for (int i = 0; i < limitPage; i++) {
			driver.get(url + i);
			System.out.println(url);
			driver.getTitle();
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2200));
			// Find the table element
			WebElement tableElement = driver.findElement(By.id("searchResult")); // By.tagName("table")
			List<WebElement> rows = tableElement.findElements(By.tagName("tr"));

			// Iterate through each row

			for (WebElement rowElement : rows) {
				
				if (rowElement.getText().contains("Porn")) {
					continue;
				}
				if (rowElement.getText().contains("Video")) {
					continue;
				}
				System.out.println(rowElement.getText());
				List<WebElement> cells = rowElement.findElements(By.tagName("td"));

				// Iterate through each cell in the row
				int index = 0;
				for (WebElement cellElement : cells) {
					String cellData = cellElement.getText();
					if (!(index == 0 && cellData.contains("book"))) {
						break;
					}
					if (index == 1) {
						System.out.print(cellData + "\t");
						WebElement wa = cellElement.findElement(By.tagName("a"));
						System.out.println(wa.getAttribute("href"));
						index++;
						continue;
					}
					if (index == 2) {
						System.out.print(cellData + "\t");
						index++;
						continue;
					}
					if (index == 3) {
						System.out.print(cellData + "\t");
						index++;
						continue;
					}
				}
				System.out.println();
			}

		}

		driver.quit();
	}

//	
//	public static void execute() throws Exception {
//		WebDriver webdriver = null;
//
//		try {
//			System.setProperty("webdriver.chrome.logfile", "C:/TEMP/");
//			System.setProperty("webdriver.chrome.verboseLogging", "true");
//			System.setProperty("webdriver.http.factory", "jdk-http-client");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		ChromeOptions chromeOptions = new ChromeOptions();
//		System.setProperty("webdriver.http.factory", "jdk-http-client");
//		try {
//			WebDriverManager.chromedriver().setup();
//			chromeOptions.setExperimentalOption("debuggerAddress", "localhost:9014");
//			chromeOptions.addArguments("--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors",
//					"--verbose", "--silent");
//			chromeOptions.addArguments("--remote-allow-origins=*");
//			chromeOptions.addArguments("--enable-javascript");
//			DesiredCapabilities caps = new DesiredCapabilities();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new Exception("Error al crear las chromeOptions" + e.getMessage());
//		}
//		try {
//			webdriver = new ChromeDriver(chromeOptions);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new Exception("Error al crear el WebDriver getDriver() " + e.getMessage());
//		}
//		final String a1 = "the";
//		final String a2 = "pir";
//		final String a3 = "teba";
//		final String a4 = "0.org";
//
//		webdriver.get("https://" + a1 + a2 + "a" + a3 + "y1" + a4 + "/recent/2");
//
//	}
}
