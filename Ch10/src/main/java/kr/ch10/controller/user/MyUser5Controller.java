package kr.ch10.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.ch10.dto.User5DTO;
import kr.ch10.service.user.MyUser5Service;

@Controller
public class MyUser5Controller {
	
	@Autowired
	private MyUser5Service service;
	
	@GetMapping("/user5/list")
	public String list(Model model) {
		List<User5DTO> users = service.selectUser5s();
		model.addAttribute("users", users);
		return "user5/list";
	}
	
	@GetMapping("/user5/modify")
	public String modify(Model model, String id) {
		User5DTO user = service.selectUser5(id);
		model.addAttribute("user", user);
		return "user5/modify";
	}
	
	@PostMapping("/user5/modify")
	public String modify(User5DTO dto) {
		service.updateUser5(dto);
		return "redirect:/user5/list";
	}
	
	@GetMapping("/user5/register")
	public String register() {
		return "user5/register";
	}
	
	@PostMapping("/user5/register")
	public String register(User5DTO dto) {
		service.insertUser5(dto);
		return "redirect:/user5/list";
	}
	
	@GetMapping("/user5/delete")
	public String delete(String id) {
		service.deleteUser5(id);
		return "redirect:/user5/list";
	}
}