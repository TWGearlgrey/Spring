package kr.ch11.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import kr.ch11.jwt.JwtAuthenticationFilter;
import kr.ch11.jwt.JwtProvider;

@Configuration
public class SecurityConfiguration {

	@Autowired
	private SecurityUserService service;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// :: <- 메서드 참조 연산자로 람다식을 간결하게 표현.
		http
			// 사이트 위변조 방지 비활성
			.csrf(CsrfConfigurer::disable)
			// 기본 HTTP 인증방식 비활성
			.httpBasic(HttpBasicConfigurer::disable)
			// 폼 방식 로그인 비활성(토큰 방식으로 로그인 처리하기 때문)
			.formLogin(FormLoginConfigurer::disable)
			// 세션 사용 비활성(토큰기반 인증 방식이기 때문)
			.sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilter(corsFilter())
			// 토큰 검사 필터 설정
			.addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
			// 인가 권한 설정
			.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
									.requestMatchers("/admin/**").hasAuthority("ADMIN")
									.requestMatchers("/manager/**").hasAnyAuthority("ADMIN", "MANAGER")
									.requestMatchers("/user/**").permitAll()
									.anyRequest().permitAll());
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// 같은 1234도 다른 문자열로 생성하는 메서드
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	// Spring Security에서 CORS 해제 필터
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("http://localhost:5173"); // <-- 반드시 허용 도메인 주소를 작성, *(전체) 허용하면 안됨
		config.addAllowedMethod("*");
		config.addAllowedHeader("*");
		config.setAllowCredentials(true);
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}