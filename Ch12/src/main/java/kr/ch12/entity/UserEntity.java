package kr.ch12.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "User")
public class UserEntity implements OAuth2User {

	@Id
	private String 		  uid;
	private String 		  pass;
	private String 		  name;
	private int 		  age;
	private String 		  hp;
	private String 		  role;
	
	@CreationTimestamp
	private LocalDateTime regDate;
	
	// 추가필드
	private String provider;
	private String nickname;
	private String email;
	
	@Override
	public Map<String, Object> getAttributes() {
		Map<String, Object> user = new HashMap<>();
        user.put("uid", uid);
        user.put("pass", pass);
        user.put("name", name);
        user.put("age", age);
        user.put("hp", hp);
        user.put("role", role);
        user.put("regDate", regDate);
        user.put("provider", provider);
        user.put("nickname", nickname);
        user.put("email", email);
        
        return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
}