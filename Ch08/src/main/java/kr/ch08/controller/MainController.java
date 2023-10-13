package kr.ch08.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.ch08.entity.User1Entity;
import kr.ch08.repository.User1Repository;

@Controller
public class MainController {
	
	@Autowired
	private User1Repository repo;
	
	@GetMapping(value = {"/", "/index"})
	public String index() {
		return "/index";
	}

	@GetMapping("/query1")
	public String query1() {
		User1Entity entity = repo.findUser1EntityByUid("a101");
		System.out.println("------------------------------------------------------------");
		System.out.println(entity);
		System.out.println("------------------------------------------------------------");
		
		return "redirect:/";
	}
	
	@GetMapping("/query2")
	public String query2() {
		List<User1Entity> entity = repo.findUser1EntityByName("김다섯");
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		
		return "redirect:/";
	}
	
	@GetMapping("/query3")
	public String query3() {
		List<User1Entity> entity = repo.findUser1EntityByNameNot("김다섯");
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		
		return "redirect:/";
	}
	
	@GetMapping("/query4")
	public String query4() {
		User1Entity entity = repo.findUser1EntityByUidAndName("a102", "김둘");
		System.out.println("------------------------------------------------------------");
		System.out.println(entity);
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query5")
	public String query5() {
		List<User1Entity> entity = repo.findUser1EntityByUidOrName("a103", "김넷");
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query6")
	public String query6() {
		List<User1Entity> entity = repo.findUser1EntityByAgeGreaterThan(3);
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query7")
	public String query7() {
		List<User1Entity> entity = repo.findUser1EntityByAgeGreaterThanEqual(3);
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query8")
	public String query8() {
		List<User1Entity> entity = repo.findUser1EntityByAgeLessThan(3);
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query9")
	public String query9() {
		List<User1Entity> entity = repo.findUser1EntityByAgeLessThanEqual(3);
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query10")
	public String query10() {
		List<User1Entity> entity = repo.findUser1EntityByAgeBetween(2, 5);
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query11")
	public String query11() {
		List<User1Entity> entity = repo.findUser1EntityByNameLike("김다섯");
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query12")
	public String query12() {
		List<User1Entity> entity = repo.findUser1EntityByNameContains("일");
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query13")
	public String query13() {
		List<User1Entity> entity = repo.findUser1EntityByNameStartsWith("김");
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query14")
	public String query14() {
		List<User1Entity> entity = repo.findUser1EntityByNameEndsWith("둘");
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query15")
	public String query15() {
		List<User1Entity> entity = repo.findUser1EntityByOrderByName();
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query16")
	public String query16() {
		List<User1Entity> entity = repo.findUser1EntityByOrderByAgeAsc();
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query17")
	public String query17() {
		List<User1Entity> entity = repo.findUser1EntityByOrderByAgeDesc();
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query18")
	public String query18() {
		List<User1Entity> entity = repo.findUser1EntityByAgeGreaterThanOrderByAgeDesc(3);
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query19")
	public String query19() {
		int count = repo.countUserEntityByUid("a105");
		System.out.println("------------------------------------------------------------");
		System.out.println("count : " + count);
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query20")
	public String query20() {
		int count = repo.countUserEntityByName("김다섯");
		System.out.println("------------------------------------------------------------");
		System.out.println("count : " + count);
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query21")
	public String query21() {
		List<User1Entity> entity = repo.selectUser1UnderAge30();
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query22")
	public String query22() {
		List<User1Entity> entity = repo.selectUser1ByName("김다섯");
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query23")
	public String query23() {
		List<User1Entity> entity = repo.selectUser1ByNameParam("김다섯");
		System.out.println("------------------------------------------------------------");
		for(User1Entity ent : entity) {
			System.out.println(ent);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
	
	@GetMapping("/query24")
	public String query24() {
		List<Object[]> object = repo.selectUser1ByUid("a101");
		System.out.println("------------------------------------------------------------");
		for(Object[] ent : object) {
			System.out.println("ent[0] : " + ent[0]);
			System.out.println("ent[1] : " + ent[1]);
			System.out.println("ent[2] : " + ent[2]);
		}
		System.out.println("------------------------------------------------------------");
		return "redirect:/";
	}
}
