package com.oceanLife.security;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.oceanLife.model.req.LoginRequest;
import com.oceanLife.model.res.LoginResponse;
import com.oceanLife.utils.exception.CustomAuthenticationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class TokenService {

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.access.expiration}")
	private long accessExpiration;
	
	@Value("${jwt.refresh.expiration}")
	private long refreshExpiration;
	
	public LoginResponse createToken(LoginRequest request) {

		try {
			// 封裝帳密
			Authentication authToken = new UsernamePasswordAuthenticationToken(request.getUserAccount(),
					request.getUserPass());

			// 執行帳密認證
			authToken = authenticationProvider.authenticate(authToken);

			// 認證成功後取得結果
			// 以自定義的 UserDetail 接收已認證的使用者
			UserDetail userDetail = (UserDetail) authToken.getPrincipal();

			// 產生 token
			String accessToken = createAccessToken(userDetail.getUsername());

			String refreshToken = createRefreshToken(userDetail.getUsername());

			// 添加更多資料給登入方
			LoginResponse res = new LoginResponse();
			res.setAccessToken(accessToken);
			res.setRefreshToken(refreshToken);

			res.setUserId(userDetail.getId());
			res.setUserAccount(userDetail.getAccount());
			res.setUserRole(userDetail.getRole());

			return res;
		} catch (BadCredentialsException e) {
			throw new CustomAuthenticationException("Invalid username or password");
		}

	}

	// 創立token
	private String createAccessToken(String username) {
		// 有效時間（毫秒）
		long expirationMillis = System.currentTimeMillis() + accessExpiration * 1000;
		// 設置標準內容與自定義內容
		Claims claims = Jwts.claims();
		claims.setSubject("Access Token");
		claims.setIssuedAt(new Date());
		claims.setExpiration(new Date(expirationMillis));
		claims.put("username", username);

		// 簽名後產生 token
		return Jwts.builder().setClaims(claims).signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	// 創立 refresh token
	private String createRefreshToken(String username) {
		long expirationMillis = System.currentTimeMillis() + refreshExpiration * 1000;

		Claims claims = Jwts.claims();
		claims.setSubject("Refresh Token");
		claims.setIssuedAt(new Date());
		claims.setExpiration(new Date(expirationMillis));
		claims.put("username", username);

		return Jwts.builder().setClaims(claims).signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	public String refreshAccessToken(String refreshToken) {
		Map<String, Object> payload = parseToken(refreshToken);
		String username = (String) payload.get("username");

		return createAccessToken(username);
	}

	// 解析token
	public Map<String, Object> parseToken(String token) {

		Claims claims = Jwts.parserBuilder().setSigningKey(getSignKey()).build()
											.parseClaimsJws(token).getBody();
		return new HashMap<>(claims);
	}
	
	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	// Checks if the JWT token is expired.
	// return-> True if the token is expired, false otherwise.
	public Boolean isTokenExpired(Date expirationDate) {
		// Check if the token's expiration time is before the current time
		return expirationDate.before(new Date());
	}
	
	// 驗證username & expiration Date
	public Boolean validateToken(UserDetails userDetails, String userName, Date expirationDate) {
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(expirationDate));
	}

}
