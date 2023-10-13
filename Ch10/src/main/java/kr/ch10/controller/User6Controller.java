package kr.ch10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ch10.dto.User6DTO;
import kr.ch10.service.User6Service;

@RestController
public class User6Controller {

	@Autowired
	private User6Service service;
	
	@GetMapping("/user6")
	public List<User6DTO> selects() {
		List<User6DTO> lists = service.selectUser6s();
		return lists;
	}
	
	@GetMapping("/user6/{seq}")
	public User6DTO select(@PathVariable int seq) {
		return service.selectUser6(seq);
	}
	
	@PostMapping("/user6")
	public User6DTO register(User6DTO user6) {
		return service.updateUser6(user6);
	}
	
	@PutMapping("/user6")
	public User6DTO update(User6DTO user6) {
		return service.updateUser6(user6);
	}
	
	@DeleteMapping("/user6/{seq}")
	public void delete(@PathVariable int seq) {
		service.deleteUser6(seq);
	}
}