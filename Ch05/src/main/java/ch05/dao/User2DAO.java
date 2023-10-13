package ch05.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ch05.dto.User2DTO;

@Repository
public class User2DAO {

	@Autowired
	private JdbcTemplate jdbc;
	
	public void insertUser2(User2DTO dto) {
		String sql = "INSERT INTO `User2` VALUES (?, ?, ?, ?)";
		
		Object[] params = {
				dto.getUid(), 
				dto.getName(), 
				dto.getHp(), 
				dto.getAge()
		};
		
		jdbc.update(sql, params);
	}
	
	public User2DTO selectUser2(String uid) {
		String sql = "SELECT * FROM `User2` WHERE `uid`=?";
		User2DTO dto = jdbc.queryForObject(sql, new User2RowMapper(), uid);
		return dto;
	}
	
	public List<User2DTO> selectUser2s() {
		String sql = "SELECT * FROM `User2`";
		List<User2DTO> users = jdbc.query(sql, new User2RowMapper());
		return users;
	}
	
	public void updateUser2(User2DTO dto) {
		
	}
	
	public void deleteUser2(String uid) {
		
	}
}
