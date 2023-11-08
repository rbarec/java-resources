package rbarec.scr4pp1ng.poc;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ScrappingDownloadDirFile {

	public static void main(String[] args) {
		ScrappingDownloadDirFile.search();
	}

	public static void search() {

		Map<String, Object> preferences = new HashMap<>();
		preferences.put("profile.default_content_settings.popups", 0);
		preferences.put("download.default_directory", "C:\\TEMP\\000archivos");

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
		options.setExperimentalOption("prefs", preferences);

		WebDriver driver = new ChromeDriver(options);

		final String url = "https://www.labibliotecadejuanjo.com/2017/01/la-tregua-mario-benedetti-pdf.html";
		 
		final String xpathBoton = "/html/body/div[3]/div/div[4]/div[3]/div[2]/div/div/div[1]/main/div[1]/div/div[1]/div/div/div[1]/div/div[2]/div/div/div[3]/span[3]/a";
		
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get( url );
		
		WebElement we = driver.findElement(By.xpath(xpathBoton));
		we.click();
		System.out.println("BAJADO ....");
	

		try {
			Writer writer = Files.newBufferedWriter(Paths.get("https://www.guiacultural.com/guia_regional/regional/uruguay/letr_uy/mario_benedetti_-_la_tregua.pdf"));
			  writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
