package kr.ch10.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ch10.dto.User3DTO;
import kr.ch10.repository.User3Repository;

@Service
public class User3Service {
	
	@Autowired
	private User3Repository repo;
	
	public User3DTO insertUser3(User3DTO dto) {
		return repo.save(dto.toEntity()).toDTO();
	}
	
	public User3DTO selectUser3(String id) {
		return repo.findById(id).get().toDTO();
	}
	
	public List<User3DTO> selectUser3s() {
		return repo.findAll()
				.stream()
				.map(entity -> 
					User3DTO.builder()
					.id(entity.getId())
					.name(entity.getName())
					.hp(entity.getHp())
					.age(entity.getAge())
					.build()
		).collect(Collectors.toList());
	}
	
	public User3DTO updateUser3(User3DTO dto) {
		return repo.save(dto.toEntity()).toDTO();
	}
	
	public void deleteUser3(String id) {
		repo.deleteById(id);
	}
}