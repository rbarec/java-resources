package rbarec.scr4pp1ng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	
	@RequestMapping(value = "/")
	String hello() {
		System.out.println("ejecutando search recent");
		try {
			P1ratRecent.search();
		} catch (Exception e) {
			e.printStackTrace();
			return "mal";
		}
		return "Hello World!";
	}
}
