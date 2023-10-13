package kr.co.sboard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {

	@Autowired
	private SecurityUserService service;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// :: <- 메서드 참조 연산자로 람다식을 간결하게 표현.
		http
			// 사이트 위변조 방지 비활성
			.csrf(CsrfConfigurer::disable)
			// 폼 로그인
			.formLogin(config -> config
					.loginPage("/user/login")
					.defaultSuccessUrl("/", true)
					.failureUrl("/user/login?success=100")
					.usernameParameter("uid")
					.passwordParameter("pass"))
			// 로그아웃 설정
			.logout(config -> config
					.logoutUrl("/user/logout")
					.invalidateHttpSession(true)
					.clearAuthentication(true)
					.logoutSuccessUrl("/user/login?success=200"))
			// 인가 권한 설정
			.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
					.requestMatchers("/article/**").hasAnyRole("ADMIN", "MANAGER", "USER")
					.requestMatchers("/user/**").permitAll()
					.requestMatchers("/").authenticated()
					.requestMatchers("/vendor/**", "/js/**", "/dist/**", "/data/**", "/less/**").permitAll());
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

	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
}