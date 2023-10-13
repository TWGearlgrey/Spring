package kr.ch09.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.ch09.entity.UserEntity;
import kr.ch09.repository.UserRepository;

@Service
public class SecurityUserService implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// - AuthenticationProvider에서 비밀번호를 체크 후 UserDetailService가 생성.
		// - 이후 UserDetailService에서 UserDetails 객체를 생성한다. 이 내용에 대해 아래서 정리함.
		
		// 패스워드에 대한 검사는 이전 컴포넌트(AuthenticationProvider)에서 처리되어 사용자 아이디만 넘어온다.
		UserEntity user =repo.findById(username).get();
		
		// 사용자 인증 객체 생성(세션에 저장)
		UserDetails userDetails = MyUserDetails.builder()
			.uid(user.getUid())
			.pass(user.getPass())
			.name(user.getName())
			.age(user.getAge())
			.hp(user.getHp())
			.role(user.getRole())
			.regDate(user.getRegDate())
			.build();
		
		return userDetails;
	}
}