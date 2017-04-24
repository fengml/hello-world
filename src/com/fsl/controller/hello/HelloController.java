package com.fsl.controller.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/hello")
public class HelloController {
	
	@RequestMapping("/time")
	public String sayHello(){
		System.out.println("======================Hello World!!!================");
		return "/hello/hello";
	}
}
