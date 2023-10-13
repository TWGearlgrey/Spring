package kr.ch10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ch10.dto.User3DTO;
import kr.ch10.service.User3Service;

@RestController
public class User3Controller {

	@Autowired
	private User3Service service;
	
	@GetMapping("/user3")
	public List<User3DTO> selects() {
		List<User3DTO> lists = service.selectUser3s();
		return lists;
	}
	
	@GetMapping("/user3/{id}")
	public User3DTO select(@PathVariable String id) {
		return service.selectUser3(id);
	}
	
	@PostMapping("/user3")
	public User3DTO register(User3DTO user3) {
		return service.updateUser3(user3);
	}
	
	@PutMapping("/user3")
	public User3DTO update(User3DTO user3) {
		return service.updateUser3(user3);
	}
	
	@DeleteMapping("/user3/{id}")
	public void delete(@PathVariable String id) {
		service.deleteUser3(id);
	}
}