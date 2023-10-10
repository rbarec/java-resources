package rbarec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rbarec.fundationsjava8.ParentDirectoryFromJar;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping(value = "/")
	String hello() {
		ParentDirectoryFromJar parent = new ParentDirectoryFromJar();
		System.out.println(parent.getPath());
		return "Hello World!";
	}

}
