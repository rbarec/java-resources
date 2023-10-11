package rbarec.scr4pp1ng;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class P1ratRecentBook_v2 {

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

		int limitPage = 28;
		List<PirateRow> prows = new ArrayList<>();
		for (int i = 0; i < limitPage; i++) {
			System.out.println("rows size=" + prows.size());
			System.out.println(url + i);
			driver.get(url + i);
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2200));
			WebElement tableElement = driver.findElement(By.id("searchResult")); // By.tagName("table")
			List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
			for (WebElement rowElement : rows) {
				List<WebElement> cells = rowElement.findElements(By.tagName("td"));
				int index = 1;
				PirateRow prow = new PirateRow();
				boolean esPorn = false;
				for (WebElement cellElement : cells) {

					String cellData = cellElement.getText();
					cellData = cellData == null ? null : cellData.replace("\n", " ");
					if (index == 1) {
						if (cellData.contains("Po"+"rn"))
							esPorn = true;
						prow.setTypep(cellData);
						index++;
						continue;
					}
					if (index == 2) {
						prow.setName(cellData);
						List<WebElement> links = cellElement.findElements(By.tagName("a"));
						extraeMagnentLink(links, prow);
						index++;
						continue;
					}
					if (index == 3) {
						prow.setSeeders(cellData);
						index++;
						continue;
					}
					if (index == 4) {
						prow.setLeechers(cellData);
						index++;
						continue;
					}
				}
				if (!esPorn) {
					System.out.println("" + prow.getTypep() + "    ---   " + prow.getName());
					prows.add(prow);
				}

			} // for ROWS

		} // for PAGES
		try {
			driver.quit();
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
			// e.printStackTrace();
		}
		System.out.println("size all " + prows.size());
		List<String> excludes = Arrays.asList("Po"+"rn", "Video", "Games", "Audio", "1 2 3 4");

		// solo book
//		List<PirateRow> ebooks = prows.stream().filter(u -> u.typep.contains("book")).collect(Collectors.toList());
//		System.out.println("stream SIZE=" + ebooks.size());
		int i = 1;
		for (PirateRow row : prows) {
			System.out.println(i + "\t" + row.getTypep() + "\t" + row.getName());
//			if (row.getTypep()!=null && row.getTypep().contains("book")) {
//				System.out.println(i + "\t\t" + row.getName());
//				i++;
//			}
		}

		String html = "";

		int j = 1;
		for (PirateRow row : prows) {
			if (row.getTypep() != null && row.getTypep().contains("book")) {
				html += "<tr><td>" + j + " - - " + row.name + "<a href=\"" + row.url + "\">link</a> </td><td>"
						+ row.getSeeders() + "--" + row.getLeechers() + "</td></tr>";
				j++;
			}
		}
		System.out.println(html);
		System.out.println("paste here: https://www.w3schools.com/html/tryit.asp?filename=tryhtml_basic_document");
	}

	private static void extraeMagnentLink(List<WebElement> links, PirateRow prow) {
		String a = "";
		if (links != null) {
			for (WebElement we : links) {
				String strhref = we.getAttribute("href");
				if (strhref != null && strhref.contains("magnet:?")) {
					prow.setUrl(strhref);
				}

			}
		}
		prow.setUrl(a);
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonInclude(Include.NON_NULL)
	public static class PirateRow implements Serializable {
		private static final long serialVersionUID = 1713143222180L;

		private String typep;
		private String name;
		private String url;
		private String seeders;
		private String leechers;

	}

}
