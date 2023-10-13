package kr.ch10.dto;

import kr.ch10.entity.User6Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User6DTO {
	
	private int    seq;
	private String name;
	private String gender;
	private int    age;
	private String addr;
	
	// entity 변환
	public User6Entity toEntity() {
		return User6Entity.builder()
							.seq(seq)
							.name(name)
							.gender(gender)
							.age(age)
							.addr(addr)
							.build();
	}
}