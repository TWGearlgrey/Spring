package kr.ch11.jwt;

import java.io.IOException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import kr.ch11.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtProvider jwtProvider;

	public static final String AUTH_HEADER  = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.info("JwtAuthenticationFilter...1");

		String uri = request.getRequestURI();
		int i = uri.lastIndexOf("/");
		String path = uri.substring(i); // htt.... /Ch11/refreshToken에서 refreshToken만 가져옴.
		
		String header = request.getHeader(AUTH_HEADER);
		log.info("JwtAuthenticationFilter...2 : " + header);
		
		String token = getTokenFromHeader(header);
		log.info("JwtAuthenticationFilter...3 : " + token);
		
		if(token != null) {
			log.info("JwtAuthenticationFilter...4");
			try{
				jwtProvider.validateToken(token);

				if(path.equals("/refreshToken")) {
					Claims claims = jwtProvider.getClaims(token);
					String uid  = (String) claims.get("uid");
					String role = (String) claims.get("role");

					UserEntity user = UserEntity.builder()
												.uid("")
												.role("").build();

					String accessToken = jwtProvider.createToken(user, 3);

					response.setStatus(HttpServletResponse.SC_CREATED);
					response.getWriter().print(accessToken);
					return;
				}



			} catch (SecurityException | MalformedJwtException e) {
				log.debug("잘못된 JWT 서명 입니다.");
				response.setStatus(401);
				response.getWriter().print("잘못된 JWT 서명입니다.");
				return;
			} catch (ExpiredJwtException e) {
				log.debug("만료된 JWT 서명 입니다.");
				response.setStatus(402);
				response.getWriter().print("만료된 JWT 서명입니다.");
				return;
			} catch (UnsupportedJwtException e) {
				log.debug("지원되지 않는 JWT 서명 입니다.");
				response.setStatus(403);
				response.getWriter().print("지원되지 않는 JWT 서명입니다.");
				return;
			} catch (IllegalArgumentException e) {
				log.debug("JWT 토큰이 잘 못 되었습니다.");
				response.setStatus(404);
				response.getWriter().print("JWT 토큰이 잘 못 되었습니다.");
				return;
			}
			// Security 인증처리
			Authentication authentication = jwtProvider.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		// 다음 필터 이동
		filterChain.doFilter(request, response);
		
		// 인증필터(userpasswordauthentication fillter), 권한검증필터(scrurity interceptor)
	}
	
	public String getTokenFromHeader(String header) {
		
		if(header != null && header.startsWith(TOKEN_PREFIX)) {
			return header.substring(TOKEN_PREFIX.length());
		}
		
		return null;
	}
}