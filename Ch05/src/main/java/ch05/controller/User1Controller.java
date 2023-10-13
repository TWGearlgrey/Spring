package ch05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch05.dto.User1DTO;
import ch05.service.User1Service;

@Controller
public class User1Controller {
	
	@Autowired
	private User1Service service;

	
	
	
	@GetMapping("/user1/register")
	public String register() {
		return "user1/register";
	}
	
	@PostMapping("/user1/register")
	public String register(User1DTO dto) { // User1DTO 앞에 @ModelAttribute 가 생략되어있음.
		service.insertUser1(dto);
		return "redirect:/user1/list";
	}
	
	
	
	
	@GetMapping("/user1/list")
	public String list(Model model) {
		
		List<User1DTO> users = service.selectUser1s();
		model.addAttribute("users", users);
		
		return "user1/list";
	}
	
	
	
	
	@GetMapping("/user1/modify")
	public String modify(Model model, String uid) {
		
		User1DTO user = service.selectUser1(uid);
		model.addAttribute(user);
		// key 값을 설정하지 않은 경우 User1DTO의 소문자가 key값이 됨. user1DTO
		
		return "user1/modify";
	}
	
	@PostMapping("/user1/modify")
	public String modify(@ModelAttribute User1DTO dto) {

		service.updateUser1(dto);
		
		/* 
		 * redirect: 뒤에 /를 붙이면 현재 위치를 기준으로 redirect해줌
		 * ex) redirect:user1/list -> user1/user1/list
		 */
		return "redirect:/user1/list";
	}
	
	
	
	@GetMapping("/user1/delete")
	public String delete(String uid) {
		service.deleteUser1(uid);
		
		return "redirect:/user1/list";
	}
}
