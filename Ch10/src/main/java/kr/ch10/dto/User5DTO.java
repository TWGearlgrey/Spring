package kr.ch10.dto;

import kr.ch10.entity.User5Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User5DTO {
	
	private String id;
	private String name;
	private String gender;
	private int    age;
	private String addr;
	
	// entity 변환
	public User5Entity toEntity() {
		return User5Entity.builder()
							.id(id)
							.name(name)
							.gender(gender)
							.age(age)
							.addr(addr)
							.build();
	}
}