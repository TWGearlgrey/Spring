package kr.ch10.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.ch10.dto.User3DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="USER3")
public class User3Entity {
	
	@Id
	private String id;
	private String name;
	private String hp;
	private int    age;
	
	// DTO 변환
	public User3DTO toDTO() {
		return User3DTO.builder()
						.id(id)
						.name(name)
						.hp(hp)
						.age(age)
						.build();
	}
}
