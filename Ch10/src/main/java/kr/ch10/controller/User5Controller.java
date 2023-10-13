package kr.ch10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ch10.dto.User5DTO;
import kr.ch10.service.User5Service;

@RestController
public class User5Controller {

	@Autowired
	private User5Service service;
	
	@GetMapping("/user5")
	public List<User5DTO> selects() {
		List<User5DTO> lists = service.selectUser5s();
		return lists;
	}
	
	@GetMapping("/user5/{id}")
	public User5DTO select(@PathVariable String id) {
		return service.selectUser5(id);
	}
	
	@PostMapping("/user5")
	public User5DTO register(User5DTO user5) {
		return service.updateUser5(user5);
	}
	
	@PutMapping("/user5")
	public User5DTO update(User5DTO user5) {
		return service.updateUser5(user5);
	}
	
	@DeleteMapping("/user5/{id}")
	public void delete(@PathVariable String id) {
		service.deleteUser5(id);
	}
}