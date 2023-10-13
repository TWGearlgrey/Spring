package kr.ch10.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.ch10.dto.User4DTO;
import kr.ch10.service.user.MyUser4Service;

@Controller
public class MyUser4Controller {
	
	@Autowired
	private MyUser4Service service;
	
	@GetMapping("/user4/list")
	public String list(Model model) {
		List<User4DTO> users = service.selectUser4s();
		model.addAttribute("users", users);
		return "user4/list";
	}
	
	@GetMapping("/user4/modify")
	public String modify(Model model, String id) {
		User4DTO user = service.selectUser4(id);
		model.addAttribute("user", user);
		return "user4/modify";
	}
	
	@PostMapping("/user4/modify")
	public String modify(User4DTO dto) {
		service.updateUser4(dto);
		return "redirect:/user4/list";
	}
	
	@GetMapping("/user4/register")
	public String register() {
		return "user4/register";
	}
	
	@PostMapping("/user4/register")
	public String register(User4DTO dto) {
		service.insertUser4(dto);
		return "redirect:/user4/list";
	}
	
	@GetMapping("/user4/delete")
	public String delete(String id) {
		service.deleteUser4(id);
		return "redirect:/user4/list";
	}
}