package kr.ch10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ch10.dto.User4DTO;
import kr.ch10.service.User4Service;

@RestController
public class User4Controller {

	@Autowired
	private User4Service service;
	
	@GetMapping("/user4")
	public List<User4DTO> selects() {
		List<User4DTO> lists = service.selectUser4s();
		return lists;
	}
	
	@GetMapping("/user4/{id}")
	public User4DTO select(@PathVariable String id) {
		return service.selectUser4(id);
	}
	
	@PostMapping("/user4")
	public User4DTO register(User4DTO user4) {
		return service.updateUser4(user4);
	}
	
	@PutMapping("/user4")
	public User4DTO update(User4DTO user4) {
		return service.updateUser4(user4);
	}
	
	@DeleteMapping("/user4/{id}")
	public void delete(@PathVariable String id) {
		service.deleteUser4(id);
	}
}