package com.app.resources;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin(origins="*")
public class IndexResource {
	
	@RequestMapping( method=RequestMethod.GET)
	public String index(){
		return "Sistema Rodando na porta 8080";
	}

}
