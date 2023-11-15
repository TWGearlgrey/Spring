package kr.ch11.controller;

import java.util.List;
import java.util.Map;

import kr.ch11.repository.UserRepository;
import kr.ch11.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import kr.ch11.dto.UserRequestDTO;
import kr.ch11.entity.UserEntity;
import kr.ch11.jwt.JwtProvider;
import kr.ch11.security.MyUserDetails;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Log4j2
public class UserController {

	// 생성자 방식으로 주입 받음. -> @RequiredArgsConstructor, final 선언
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	private final UserService userService;
	private final UserRepository userRepository;

	@CrossOrigin("*")
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody UserRequestDTO dto) {
		
		try {
			// Security 인증처리()
			// -> SecurityUserService.loadUserByUsername이 실행 됨
			UsernamePasswordAuthenticationToken authenticationToken
				= new UsernamePasswordAuthenticationToken(dto.getUid(), dto.getPass());
			
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
			
			UserEntity user = userDetails.getUser();
			user.setPass("비밀번호 암호화");
			log.info("user : "  + user.toString());
			//////////////////////////////////////////////////////////////////////// ^^^ 여기까지 db조회해서 토큰 생성함.
			
			// 토큰 발행
			String accessToken  = jwtProvider.createToken(user, 3); // 1일
			String refreshToken = jwtProvider.createToken(user, 10); // 7일
			
			Map<String, Object> resultMap = Map.of("grantType", "Bearer",
												   "accessToken", accessToken,
												   "refreshToken", refreshToken,
											       "user", 		user);
			return resultMap;
			
		} catch (Exception e) {
			Map<String, Object> resultMap = Map.of("grantType", "None",
												   "message", e.getMessage());
			return resultMap;
		}
	}

	@CrossOrigin("*")
	@GetMapping("/users")
	public ResponseEntity<Object> getUserss(Authentication authentication) {
		if(authentication != null && authentication.isAuthenticated()) {
			log.info("OK");
			return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
		}
		log.info("UNAUTHORIZED");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
	}

	@CrossOrigin("http://localhost:5173")
	@GetMapping("/user")
	public List<UserEntity> getUsers() {
		return userService.getUsers();
	}

	@CrossOrigin("http://localhost:5173")
	@GetMapping("/user/{id}")
	public UserEntity getUser(@PathVariable String id) {
		return userService.getUser(id);
	}

	@CrossOrigin("http://localhost:5173")
	@PostMapping("/user")
	public void inputUser(@RequestBody UserEntity entity) {
		log.info("Inserting");
		userService.insertUser(entity);
	}
	@CrossOrigin("http://localhost:5173")
	@PutMapping("/user")
	public void modifyUser(@RequestBody UserEntity entity) {
		log.info("Modifying");
		userService.insertUser(entity);
	}

	@CrossOrigin("http://localhost:5173")
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
	}

}