package kr.ch10.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.ch10.dto.User5DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="USER5")
public class User5Entity {
	
	@Id
	private String id;
	private String name;
	private String gender;
	private int    age;
	private String addr;
	
	// DTO 변환
	public User5DTO toDTO() {
		return User5DTO.builder()
						.id(id)
						.name(name)
						.gender(gender)
						.age(age)
						.addr(addr)
						.build();
	}
}