package ch05.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;import ch05.dto.User1DTO;
import ch05.dto.User2DTO;

public class User2RowMapper implements RowMapper<User2DTO>{
	
	@Override
	public User2DTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User2DTO dto = new User2DTO();
		dto.setUid(rs.getString(1));
		dto.setName(rs.getString(2));
		dto.setHp(rs.getString(3));
		dto.setAge(rs.getString(4));
		
		return dto;
	}
}