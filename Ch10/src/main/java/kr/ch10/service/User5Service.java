package kr.ch10.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ch10.dto.User5DTO;
import kr.ch10.repository.User5Repository;

@Service
public class User5Service {
	
	@Autowired
	private User5Repository repo;
	
	public User5DTO insertUser5(User5DTO dto) {
		return repo.save(dto.toEntity()).toDTO();
	}
	
	public User5DTO selectUser5(String id) {
		return repo.findById(id).get().toDTO();
	}
	
	public List<User5DTO> selectUser5s() {
		return repo.findAll()
				.stream()
				.map(entity -> 
					User5DTO.builder()
					.id(entity.getId())
					.name(entity.getName())
					.gender(entity.getGender())
					.age(entity.getAge())
					.addr(entity.getAddr())
					.build()
		).collect(Collectors.toList());
	}
	
	public User5DTO updateUser5(User5DTO dto) {
		return repo.save(dto.toEntity()).toDTO();
	}
	
	public void deleteUser5(String id) {
		repo.deleteById(id);
	}
}