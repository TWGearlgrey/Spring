package kr.co.sboard.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kr.co.sboard.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class MyUserDetails implements UserDetails {
	private static final long serialVersionUID = 159306062704865977L;

	private UserEntity user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 계정이 갖는 권한 목록 (리스트인 이유? -> 권한을 여러개 갖는 경우가 있기 때문)
		List<GrantedAuthority> authorities = new ArrayList<>();

		// 반드시 접두어로 ROLE_ 입력해야 됨. 그래야 hasRole(), hasAnyRole() 메서드가 처리 됨.
		// 만약 ROLE_ 접두어를 안 쓰면 hasAuthority(), hasAnyAuthority() 메서드로 해야 됨.
		// hasAuthority(), hasAnyAuthority()를 사용할 시엔 hasAuthority("ROLE_ADMIN")과 같이 할 것
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
		return authorities;
	}

	@Override
	public String getPassword() {
		// 계정이 갖는 비밀번호
		return user.getPass();
	}

	@Override
	public String getUsername() {
		// 계정이 갖는 아이디
		return user.getUid();
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정 만료 여부(true:만료 안 됨, false:만료)
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정 잠김 여부(true:잠김 안 됨, false:잠김)
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 계정 비밀번호 만료 여부(true:만료 안 됨, false:만료)
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 계정 활성화 여부(true:활성화, false:비활성화)
		return true;
	}
}