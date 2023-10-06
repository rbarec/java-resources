package rbarec.springboot3zero.appcontext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.ServletContext;

@Configuration
public class Config {

	@Autowired
	private ServletContext servletContext;
	
	@Bean
	public void setContextData() {
		System.out.println("Set Conext Data repoName:JAVA_RESORUCES");
		servletContext.setAttribute("repoName" , "JAVA_RESOURCES");
	}

}
