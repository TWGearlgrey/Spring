package kr.ch12.oauth2;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.ch12.entity.UserEntity;
import kr.ch12.repository.UserRepository;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Service
public class OAuth2UserService extends DefaultOAuth2UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		// kakao에서 발행 된 jwt accessToken (그림의 4번 과정)
		String accessToken = userRequest.getAccessToken().getTokenValue();
		log.info("OAuth2UserService...1 : " + accessToken);
		
		// 제공자(provider) = 카카오 (or 네이버, 구글 에 따라 조건을 나눔)
		String provider = userRequest.getClientRegistration().getRegistrationId();
		log.info("OAuth2UserService...2 : " + provider);
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		log.info("OAuth2UserService...3 : " + oAuth2User);
		
		Map<String, Object> attributes = oAuth2User.getAttributes();
		KakaoInfo kakaoInfo = new KakaoInfo(attributes);
		log.info("OAuth2UserService...4 : " + kakaoInfo);
		
		
		// 회원가입 처리(여기서 id는 카카오에 있는 고유 식별번호.)
		String id       = kakaoInfo.getId();
		String email    = kakaoInfo.getEmail();
		String nickname = kakaoInfo.getNickname();
		
		Optional<UserEntity> result = userRepository.findById(provider+"_"+id);
		
		UserEntity user = null;
		
		log.info("result : " + result);
		
		if(result.isEmpty()) {
			// 최초  소셜 로그인 동의 후 가입
			user = UserEntity.builder()
					.uid(provider+"_"+id)
					.name(nickname)
					.nickname(nickname)
					.email(email)
					.provider(provider)
					.role("USER")
					.build();
			
			userRepository.save(user);
			
		}else {
			// 회원가입이 된 사용자 조회
			user = result.get();
		}
		
		// 세션으로 저장.
		return user;
	}
}