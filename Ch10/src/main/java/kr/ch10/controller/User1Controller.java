package kr.ch10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ch10.entity.User1Entity;
import kr.ch10.service.User1Service;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin("http://localhost:5173")
@Controller
public class User1Controller {
	
	/*
	 * @ResponseBody를 선언해줌으로서 
	 * GSON을 사용하지 않고 json 데이터를 전달.
	 */

	/*
	 * CORS(Cross-Origin Resource Sharing Policy)
	 *  - HTTP 웹 서비스 보안정책 중 하나로 동일 출처의 리소스만 공유하는 원칙(SOP)
	 *  - @CrossOrigin 어노테이션으로 특정 Origin 주소를 설정하면 해당 Origin에서만 응답처리
	 */
	
	@Autowired
	private User1Service service;

	@ResponseBody
	@GetMapping("/user1")
	public List<User1Entity> list() {
		List<User1Entity> user1s = service.selectUser1s();
		return user1s;
	}
	
	@ResponseBody
	@GetMapping("/user1/{id}")
	public User1Entity user1(@PathVariable("id") String id) {
		return service.selectUser1(id);
	}
	
	@ResponseBody
	@PostMapping("/user1")
	public User1Entity user1(User1Entity user1) {
		return service.insertUser1(user1);
	}
	
	@ResponseBody
	@PutMapping("/user1")
	public User1Entity modify(User1Entity user1) {
		return service.updateUser1(user1);
	}
	
	@ResponseBody
	@DeleteMapping("/user1/{id}")
	public void delete(@PathVariable("id") String id) {
		service.deleteUser1(id);
	}
}