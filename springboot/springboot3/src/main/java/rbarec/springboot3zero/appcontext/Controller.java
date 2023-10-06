package rbarec.springboot3zero.appcontext;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.ServletContext;

@RestController
@RequestMapping("")
public class Controller {

	@Autowired
	private ServletContext servletContext;
	
	
	@GetMapping("/info")
	public String info() {
		Object contextObj = servletContext.getAttribute("repoName");
		String resp = contextObj==null?"no-context":contextObj.toString();
		
		
		return "info("+resp+") "+new Date();
	}
}
