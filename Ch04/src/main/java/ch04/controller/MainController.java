package ch04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// annotation을 Component를 해도 되지만, 명확한 의미를 부여하기 위하여 Controller로 함.
@Controller
public class MainController {

	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/hello", method=RequestMethod.GET)
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	@GetMapping("/greeting")
	public String greeting() {
		return "greeting";
	}
	
	@GetMapping("/redirect")
	public String redirect() {
		return "redirect:/annotation/param";
	}
	
	@GetMapping("/forward")
	public String forward() {
		return "forward:/annotation/model";
	}
}