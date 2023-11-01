package rbarec.unityka.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rbarec.unityka.dto.CreateKnowledgeFileRequest;
import rbarec.unityka.services.CreateKnowledgeFileService;

@CrossOrigin("*")
@RestController
@RequestMapping("")
public class ApiController {

	@Autowired
	private CreateKnowledgeFileService service;

	@RequestMapping(value = "/createKnowledgeFile", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	String hello(@RequestBody CreateKnowledgeFileRequest req) {
		System.out.println("aaa");
		service.create(req);
		return "Hello World!";
	}
}
