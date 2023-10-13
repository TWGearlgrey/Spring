package kr.ch10.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import kr.ch10.dto.User6DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="USER6")
@SequenceGenerator(
    name = "USER_SEQ_GENERATOR"
    , sequenceName = "SEQ_USER6"
    , initialValue = 1
    , allocationSize = 1
)
public class User6Entity {
	
	@Id
	@GeneratedValue(
        strategy = GenerationType.SEQUENCE
        , generator = "USER_SEQ_GENERATOR"
	)
	private int    seq;
	private String name;
	private String gender;
	private int    age;
	private String addr;
	
	// DTO 변환
	public User6DTO toDTO() {
		return User6DTO.builder()
						.seq(seq)
						.name(name)
						.gender(gender)
						.age(age)
						.addr(addr)
						.build();
	}
}