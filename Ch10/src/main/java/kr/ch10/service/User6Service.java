package kr.ch10.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ch10.dto.User6DTO;
import kr.ch10.repository.User6Repository;

@Service
public class User6Service {
	
	@Autowired
	private User6Repository repo;
	
	public User6DTO insertUser6(User6DTO dto) {
		return repo.save(dto.toEntity()).toDTO();
	}
	
	public User6DTO selectUser6(int seq) {
		return repo.findById(seq).get().toDTO();
	}
	
	public List<User6DTO> selectUser6s() {
		return repo.findAll()
				.stream()
				.map(entity -> 
					User6DTO.builder()
					.seq(entity.getSeq())
					.name(entity.getName())
					.gender(entity.getGender())
					.age(entity.getAge())
					.addr(entity.getAddr())
					.build()
		).collect(Collectors.toList());
	}
	
	public User6DTO updateUser6(User6DTO dto) {
		return repo.save(dto.toEntity()).toDTO();
	}
	
	public void deleteUser6(int seq) {
		repo.deleteById(seq);
	}
}