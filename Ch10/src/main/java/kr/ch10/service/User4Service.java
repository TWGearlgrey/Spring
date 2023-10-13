package kr.ch10.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ch10.dto.User4DTO;
import kr.ch10.repository.User4Repository;

@Service
public class User4Service {
	
	@Autowired
	private User4Repository repo;
	
	public User4DTO insertUser4(User4DTO dto) {
		return repo.save(dto.toEntity()).toDTO();
	}
	
	public User4DTO selectUser4(String id) {
		return repo.findById(id).get().toDTO();
	}
	
	public List<User4DTO> selectUser4s() {
		return repo.findAll()
				.stream()
				.map(entity -> 
					User4DTO.builder()
					.id(entity.getId())
					.name(entity.getName())
					.gender(entity.getGender())
					.age(entity.getAge())
					.addr(entity.getAddr())
					.build()
		).collect(Collectors.toList());
	}
	
	public User4DTO updateUser4(User4DTO dto) {
		return repo.save(dto.toEntity()).toDTO();
	}
	
	public void deleteUser4(String id) {
		repo.deleteById(id);
	}
}