package kr.ch10.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ch10.dto.User2DTO;
import kr.ch10.entity.User2Entity;
import kr.ch10.repository.User2Repository;

@Service
public class User2Service {

	@Autowired
	private User2Repository repo;
	
	public User2DTO insertUser2(User2DTO user2) {
		return repo.save(user2.toEntity()).toDTO();
	}
	
	public User2DTO selectUser2(String id) {
		Optional<User2Entity> result = repo.findById(id);
		return result.get().toDTO();
	}
	
	public List<User2DTO> selectUser2s() {
		return repo.findAll()
				.stream()
				.map(entity ->
					User2DTO.builder()
					.id(entity.getId())
					.name(entity.getName())
					.hp(entity.getHp())
					.age(entity.getAge())
					.build()
		).collect(Collectors.toList());
	}
	
	public User2DTO updateUser2(User2DTO dto) {
		return repo.save(dto.toEntity()).toDTO();
	}
	
	public void deleteUser2(String id) {
		repo.deleteById(id);
	}
}