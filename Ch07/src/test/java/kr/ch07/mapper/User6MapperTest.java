package kr.ch07.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.ch07.dto.User6DTO;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
@SpringBootTest
public class User6MapperTest {

	@Autowired
	private User6Mapper mapper;
	
	
	public void insertUser6() {
		User6DTO user6 = User6DTO.builder()
						.uid("A101")
						.name("홍길동")
						.birth("1999-04-04")
						.gender("F")
						.age(33)
						.addr("부산 혜도빌딩")
						.hp("010-1234-1001")
						.build();
		log.info("user6 : " + user6);
		mapper.insertUser6(user6);
		log.info("completed!");
	}
}