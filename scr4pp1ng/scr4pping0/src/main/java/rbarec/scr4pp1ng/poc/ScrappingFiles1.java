package rbarec.scr4pp1ng.poc;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * NGROK https://51aa-2800-bf0-8140-1465-4c47-f854-d571-ff63.ngrok-free.app/  <br>
 * Sitio Local C:0Z/nginx   localhost:80/index.html <br>
 * <br>
 * <br>
 * @author Ronald
 *
 */
public class ScrappingFiles1 {

	public static void main(String[] args) {
		ScrappingFiles1.search();
	}

	public static void search() {
		
		final String URL_SITE ="https://51aa-2800-bf0-8140-1465-4c47-f854-d571-ff63.ngrok-free.app/";

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

		//full xpath COPIADO a boton-input de DOWNLOAD archivo
		// igual que registroCIVIL scrapping
		final String xpathBoton = "/html/body/table/tbody/tr[3]/td[1]/input[2]";
		
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get( URL_SITE );
		
		WebElement we = driver.findElement(By.xpath(xpathBoton));
		we.click();
		System.out.println("BAJADO ....");
	

//		try {
//			Writer writer = Files.newBufferedWriter(Paths.get("https://www.guiacultural.com/guia_regional/regional/uruguay/letr_uy/mario_benedetti_-_la_tregua.pdf"));
//			File output = new File("C:\\TEMP\\000archivos\\aaa.pdf");
//			Scanner sc = new Scanner(writer);
//            PrintWriter printer = new PrintWriter(output);
//            while (sc.hasNextLine()) {
//                String s = sc.nextLine();
//                printer.write(s);
//            }
//			writer.flush();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
